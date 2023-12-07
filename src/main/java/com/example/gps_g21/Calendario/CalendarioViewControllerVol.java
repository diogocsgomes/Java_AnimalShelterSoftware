package com.example.gps_g21.Calendario;

import com.example.gps_g21.Modelos.Calendario;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.WeekPage;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.calendarfx.model.CalendarEvent.*;

public class CalendarioViewControllerVol {
    @FXML
    public WeekPage weekPage;
    @FXML
    private VBox buttonContainer;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;


    private static CalendarioController calendarioController;
    private Timer updateTimer;
    public void initialize() throws Exception {
        Calendar lavar = new Calendar("Lavar");
        lavar.setStyle(Calendar.Style.STYLE1);
        Calendar alimentar = new Calendar("Alimentar");
        alimentar.setStyle(Calendar.Style.STYLE2);
        Calendar limpar = new Calendar("Limpar");
        limpar.setStyle(Calendar.Style.STYLE3);

        calendarioController = CalendarioController.getInstance();
        CalendarSource calendarSource = new CalendarSource();
        calendarSource.getCalendars().addAll(lavar, alimentar, limpar);
        weekPage.getCalendarSources().addAll(calendarSource);
        weekPage.setRequestedTime(LocalTime.now());
        //calendarView.setShowAddCalendarButton(false);

        EventHandler<CalendarEvent> handler = event -> handleEventCalendario(event);
        weekPage.getCalendars().forEach(calendar -> calendar.addEventHandler(handler));
        Thread updateTime = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        weekPage.setToday(LocalDate.now());
                        weekPage.setTime(LocalTime.now());
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

        List<Calendario> calendario = calendarioController.loadCalendar();
        if(calendario != null){
            for (Calendario c : calendario) {
                Entry<String> entry = new Entry<>(c.getTitle());
                LocalDate startDate = LocalDate.parse(c.getStartDate());
                LocalDate endDate = LocalDate.parse(c.getEndDate());
                entry.setInterval(startDate.atTime(LocalTime.parse(c.getStartTime())),endDate.atTime(LocalTime.parse(c.getEndTime())));
                ZoneId zoneId = ZoneId.of(c.getZoneId());
                entry.setZoneId(zoneId);
                entry.setFullDay(c.isFullDay());
                entry.setRecurrenceRule(c.getRecurrenceRule());
                entry.setId(c.getId());
                switch (c.getCalendarName()) {
                    case "Lavar" -> entry.setCalendar(lavar);
                    case "Alimentar" -> entry.setCalendar(alimentar);
                    case "Limpar" -> entry.setCalendar(limpar);
                }
                if(c.getIdsVoluntiers() == null){
                    entry.getStyleClass().add("custom");
                    System.out.println("Cor vermelha");
                }else{
                    System.out.println("Cor verde");
                }
                weekPage.getCalendars().get(0).addEntry(entry);
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
            addInscreverButton(event.getEntry());
        }
    }

    private void addInscreverButton(Entry calendarEvent) {
        Button inscreverButton = new Button("Inscrever");
        //inscreverButton.setOnAction(event -> handleInscreverButton(calendarEvent));

        Label eventLabel = new Label(calendarEvent.getTitle());
        HBox entryBox = new HBox(eventLabel, inscreverButton);

        // Add the button directly to the entry box
        //calendarEvent.getControl().setGraphic(entryBox);
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
                    weekPage.refreshData();
                });
            }
        }, 5000); // Aguarda 5000 milissegundos (5 segundo)
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
