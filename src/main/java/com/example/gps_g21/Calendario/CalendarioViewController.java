package com.example.gps_g21.Calendario;

import java.time.LocalDate;
import java.time.LocalTime;

import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import com.example.gps_g21.Animais.AnimaisInfoController;
import com.example.gps_g21.Modelos.Calendario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.calendarfx.model.CalendarEvent.*;
public class CalendarioViewController {
    @FXML
    public CalendarView calendarView;
    private static CalendarioController calendarioController;
    private Timer updateTimer;
    public void initialize() throws Exception {
        calendarioController = CalendarioController.getInstance();
        CalendarSource calendarSource = new CalendarSource();
        calendarView.getCalendarSources().addAll(calendarSource);
        calendarView.setRequestedTime(LocalTime.now());
        //calendarView.setShowAddCalendarButton(false);

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
                if(c.getIdsVoluntiers() == null){
                    entry.getStyleClass().add("custom");
                    System.out.println("Cor vermelha");
                }else{
                    System.out.println("Cor verde");
                }
                calendarView.getCalendars().get(0).addEntry(entry);
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

    }
}
