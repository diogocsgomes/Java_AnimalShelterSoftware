package com.example.gps_g21.Calendario;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.example.gps_g21.Modelos.Calendario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

import static com.calendarfx.model.CalendarEvent.*;
public class CalendarioViewController {
    @FXML
    public CalendarView calendarView;
    @FXML
    private VBox buttonContainer;
    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private static CalendarioController calendarioController;
    List<Calendario> calendario = new ArrayList<>();
    private Timer updateTimer;
    public void initialize() throws Exception {
        Calendar lavar = new Calendar("Lavar");
        lavar.setStyle(Calendar.Style.STYLE1);
        Calendar alimentar = new Calendar("Alimentar");
        alimentar.setStyle(Calendar.Style.STYLE2);
        Calendar limpar = new Calendar("Limpar");
        limpar.setStyle(Calendar.Style.STYLE3);
        Calendar passear = new Calendar("Passear");
        passear.setStyle(Calendar.Style.STYLE4);

        calendarioController = CalendarioController.getInstance();
        CalendarSource calendarSource = new CalendarSource("Tarefas");
        calendarSource.getCalendars().addAll(lavar, alimentar, limpar, passear);
        calendarView.getCalendarSources().remove(0);
        calendarView.getCalendarSources().addAll(calendarSource);

        EventHandler<CalendarEvent> handler = event -> handleEventCalendario(event);
        calendarView.getCalendars().forEach(calendar -> calendar.addEventHandler(handler));
        Thread updateTime = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });
                    try {
                        sleep(10000); //update every 10 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        updateTime.setPriority(Thread.MIN_PRIORITY);
        updateTime.setDaemon(true);
        updateTime.start();

        if(calendario.isEmpty()){
            calendario = calendarioController.loadCalendar();
            for (Calendario c : calendario) {
                Entry<Calendario> entry = new Entry<>(c.getTitle());
                LocalDate startDate = LocalDate.parse(c.getStartDate());
                LocalDate endDate = LocalDate.parse(c.getEndDate());
                entry.setInterval(startDate.atTime(LocalTime.parse(c.getStartTime())),endDate.atTime(LocalTime.parse(c.getEndTime())));
                ZoneId zoneId = ZoneId.of(c.getZoneId());
                entry.setZoneId(zoneId);
                entry.setFullDay(c.isFullDay());
                entry.setRecurrenceRule(c.getRecurrenceRule());
                entry.setId(c.getId());
                entry.setUserObject(c);
                switch (c.getCalendarName()) {
                    case "Lavar" -> entry.setCalendar(lavar);
                    case "Alimentar" -> entry.setCalendar(alimentar);
                    case "Limpar" -> entry.setCalendar(limpar);
                    case "Passear" -> entry.setCalendar(passear);
                }
                if(c.getIdsVoluntiers() == null || c.getIdsVoluntiers().equals(" ")){
                    entry.getStyleClass().add("custom-verde");
                    System.out.println("Cor verde");
                }else{
                    String[] ids = c.getIdsVoluntiers().split(";");
                    if(ids.length < c.getMaxVoluntiers() && ids.length > 0) {
                        entry.getStyleClass().add("custom-amarelo");
                        System.out.println("Cor amarelo");
                    }
                    else if(ids.length == c.getMaxVoluntiers()) {
                        entry.getStyleClass().add("custom-vermelho");
                        System.out.println("Cor vermelho");
                    }
                }
                calendarView.getCalendars().forEach(calendar -> {
                    if(calendar.getName().equals(c.getCalendarName())){
                        calendar.addEntry(entry);
                    }
                });
            }
        }
    }
    private void handleEventCalendario(CalendarEvent event) {
        EventType type = event.getEventType();
        System.out.println("Tipo Evento: " + type);
        if (event.isEntryRemoved()) {
            calendarioController.deleteCalendarEvent(event.getEntry().getId());
        } else if (event.isEntryAdded()) {
            calendarioController.insertCalendarEvent(event.getEntry());
        } else if (type == ENTRY_INTERVAL_CHANGED || type == ENTRY_TITLE_CHANGED || type == ENTRY_FULL_DAY_CHANGED || type == ENTRY_LOCATION_CHANGED || type == ENTRY_RECURRENCE_RULE_CHANGED  || type == ENTRY_USER_OBJECT_CHANGED || type == ENTRY_CALENDAR_CHANGED) {
            scheduleUpdate(event.getEntry()); //Atrasa a atualização para evitar que muitas atualizações sejam executadas em um curto período de tempo
        }
    }

    private void scheduleUpdate(Entry calendarEvent) {
        if (updateTimer != null) {
            updateTimer.cancel();
        }

        updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    calendarioController.updateCalendarEvent(calendarEvent);
                    calendarView.refreshData();
                });
            }
        }, 5000); // Aguarda 5000 milissegundos (5 segundo)
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
