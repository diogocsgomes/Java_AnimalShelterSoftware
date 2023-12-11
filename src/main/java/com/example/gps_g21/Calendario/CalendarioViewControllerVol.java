package com.example.gps_g21.Calendario;

import com.example.gps_g21.Modelos.Calendario;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarEvent;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.page.WeekPage;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.StarterController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private String idEntry;

    private int id;

    private String nameStarter = StarterController.loggedName;

    private static CalendarioController calendarioController;
    private Timer updateTimer;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
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
        weekPage.getCalendarSources().addAll(calendarSource);

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
        //AtomicReference<String> idEntry = null;
        if (calendario != null) {
            for (Calendario c : calendario) {
                Entry<Calendario> entry = new Entry<>(c.getTitle());
                LocalDate startDate = LocalDate.parse(c.getStartDate());
                LocalDate endDate = LocalDate.parse(c.getEndDate());
                entry.setInterval(startDate.atTime(LocalTime.parse(c.getStartTime())), endDate.atTime(LocalTime.parse(c.getEndTime())));
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
                weekPage.getCalendars().forEach(calendar -> {
                    if(calendar.getName().equals(c.getCalendarName())){
                        calendar.addEntry(entry);
                    }
                });
            }
        }


        weekPage.setEntryDetailsPopOverContentCallback(param -> {
            //Entry<String> selectedEntry = (Entry<String>) param.getEntry();
            Entry<Calendario> selectedEntry = (Entry<Calendario>) param.getEntry();

            Calendario calendarioPop = selectedEntry.getUserObject();
            String entryId = selectedEntry.getId();
            System.out.println("Clicked Entry ID: " + entryId);

            idEntry = entryId;

            int nAttendances = calendarioPop.getNAttendances();
            int maxAttendances = calendarioPop.getMaxVoluntiers();
            //return "N Attendances: " + nAttendances;
            System.out.println(nAttendances);
            Label label = new Label("Voluntários inscritos: " + nAttendances + "/" + maxAttendances);
            label.setStyle("-fx-padding: 10px;");

            return label;

            //return null;
        });



        idEntry = weekPage.getEntryDetailsPopOverContentCallback().toString();
        System.out.println(idEntry);

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
            //addInscreverButton(event.getEntry());
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

    public void handleInscreverButton(MouseEvent mouseEvent) throws SQLException {
        System.out.println("cliquei entry");
        registerAttendance(idEntry, nameStarter);
    }

    /*
    public boolean registerAttendance(String eventId, int userId) throws SQLException {
        String selectUserSql = "SELECT id FROM users WHERE email = " + nameStarter;
        String sql = "INSERT INTO attendances (event_id, user_id, n_attendances) VALUES (?, ?, 1)";

        PreparedStatement pstmtUser = connection.prepareStatement(selectUserSql);
        PreparedStatement pstmt = connection.prepareStatement(sql);


        try {
            connection = sqliteController.createDBConnection();

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, eventId);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
            return false;
        } finally {
            // Close the resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
        }
    }

     */
    public boolean registerAttendance(String eventId, String userEmail) throws SQLException {
        String selectUserSql = "SELECT id FROM users WHERE nome = ?";
        String selectNAttendancesSql = "SELECT nAttendances FROM calendar WHERE id = ?";
        String selectMaxAttendancesSql = "SELECT maxVoluntiers FROM calendar WHERE id = ?";
        String insertAttendanceSql = "INSERT INTO attendances (event_id, user_id) VALUES (?, ?)";
        String insertNAttendanceSql = "INSERT INTO calendar (nAttendances) VALUES (?)";
        String updateAttendanceSql = "UPDATE calendar SET nAttendances = ? WHERE id = ?";

        PreparedStatement selectUserStmt = null;
        PreparedStatement selectNAttendancesStmt = null;
        PreparedStatement selectMaxAttendancesStmt = null;
        PreparedStatement insertAttendanceStmt = null;
        PreparedStatement insertNAttendanceStmt = null;
        PreparedStatement updateAttendanceStmt = null;
        ResultSet resultSet = null;
        ResultSet resNAttendances = null;
        ResultSet resMaxAttendances = null;
        ResultSet resInsNAttendances = null;

        System.out.println("estou no inicio do registerattendance");

        try {
            connection = sqliteController.createDBConnection();

            // pega o id do user
            selectUserStmt = connection.prepareStatement(selectUserSql);
            selectUserStmt.setString(1, userEmail);
            resultSet = selectUserStmt.executeQuery();

            int userId;

            System.out.println("procurou id pelo email: " + nameStarter);

            // verifica se há alguém c o email
            if (resultSet.next()) {
                System.out.println("verificou e existe email");
                userId = resultSet.getInt("id");

                //guarda o n de attendances no evento
                selectNAttendancesStmt = connection.prepareStatement(selectNAttendancesSql);
                selectNAttendancesStmt.setString(1, eventId);
                resNAttendances = selectNAttendancesStmt.executeQuery();

                int nAttendances = 0;

                if (resNAttendances.next()) {
                    nAttendances = resNAttendances.getInt("nAttendances");
                }

                //guarda o máx de voluntários para um evento
                selectMaxAttendancesStmt = connection.prepareStatement(selectMaxAttendancesSql);
                selectMaxAttendancesStmt.setString(1, eventId);
                resMaxAttendances = selectMaxAttendancesStmt.executeQuery();

                int maxAttendances = 0;
                if (resMaxAttendances.next()) {
                    maxAttendances = resMaxAttendances.getInt("maxVoluntiers");
                }

                if (nAttendances < maxAttendances) {
                    //check p saber se n existe
                    insertAttendanceStmt = connection.prepareStatement(insertAttendanceSql);
                    insertAttendanceStmt.setString(1, eventId);
                    insertAttendanceStmt.setInt(2, userId);

                    int rowsAffected = insertAttendanceStmt.executeUpdate();

                    System.out.println("meti na tabela attendances");

                    System.out.println(nAttendances);
                    nAttendances = nAttendances + 1;
                    System.out.println(nAttendances);
                    //insertAttendanceStmt.setInt(3, nAttendances);
                    //insertNAttendanceStmt = connection.prepareStatement(insertNAttendanceSql);
                    //insertNAttendanceStmt.setInt(1, nAttendances);
                    updateAttendanceStmt =  connection.prepareStatement(updateAttendanceSql);
                    updateAttendanceStmt.setInt(1,nAttendances);
                    updateAttendanceStmt.setString(2, eventId);
                    updateAttendanceStmt.executeUpdate();

                    Platform.runLater(() -> {
                        weekPage.refreshData();
                    });


                    System.out.println("meti na tabela calendar");


                    return rowsAffected > 0;
                } else {
                    System.out.println("Lotação máxima do evento atingida");
                    return false;
                }
            } else {
                System.out.println("User com este email não encontrado");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Close the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (resNAttendances != null) {
                    resNAttendances.close();
                }
                if (resMaxAttendances != null) {
                    resMaxAttendances.close();
                }
                if (selectUserStmt != null) {
                    selectUserStmt.close();
                }
                if (selectNAttendancesStmt != null) {
                    selectNAttendancesStmt.close();
                }
                if (selectMaxAttendancesStmt != null) {
                    selectMaxAttendancesStmt.close();
                }
                if (insertAttendanceStmt != null) {
                    insertAttendanceStmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
