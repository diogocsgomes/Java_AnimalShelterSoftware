package com.example.gps_g21.Calendario;

import com.calendarfx.model.Entry;
import com.example.gps_g21.Modelos.Calendario;
import com.example.gps_g21.Modelos.SqliteController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalendarioController {
    private static CalendarioController instance;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    public List<Calendario> calendarioData = new ArrayList<>();

    public List<Calendario> loadCalendar() {
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        calendarioData.clear();
        String query = "SELECT * FROM calendar";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Calendario calendario1 = new Calendario();
                calendario1.setId(resultSet.getString("id"));
                calendario1.setTitle(resultSet.getString("title"));
                calendario1.setStartDate(resultSet.getString("startDate"));
                calendario1.setEndDate(resultSet.getString("endDate"));
                calendario1.setStartTime(resultSet.getString("startTime"));
                calendario1.setEndTime(resultSet.getString("endTime"));
                calendario1.setZoneId(resultSet.getString("zoneId"));
                calendario1.setRecurrenceRule(resultSet.getString("recurrenceRule"));
                calendario1.setFullDay(resultSet.getBoolean("fullDay"));
                calendario1.setRecurring(resultSet.getBoolean("recurring"));
                calendario1.setRecurrence(resultSet.getBoolean("recurrence"));
                calendario1.setIdsVoluntiers(resultSet.getString("idsVoluntiers"));
                calendario1.setMaxVoluntiers(resultSet.getInt("maxVoluntiers"));
                calendario1.setCalendarName(resultSet.getString("calendarName"));
                calendario1.setNAttendances(resultSet.getInt("nAttendances"));
                calendarioData.add(calendario1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        return calendarioData;
    }

    public boolean insertCalendarEvent(Entry calendarEvent){
        Calendario calendario = new Calendario(calendarEvent);
        System.out.println("InsertCalendarEvent: " + calendario.getTitle() + " " + calendario.getStartDate() + " " + calendario.getEndDate() + " " + calendario.getStartTime() + " " + calendario.getEndTime() + " ");
        for(Calendario c : calendarioData){
            if(c.getStartDate().equals(calendario.getStartDate()) && c.getStartTime().equals(calendario.getStartTime())){
                System.out.println("Existe um evento com a mesma data e hora");
                return false; //Existe um evento com a mesma data e hora
            }
        }
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "INSERT INTO calendar (title, startDate, endDate, startTime, endTime, zoneId, recurrenceRule, fullDay, recurring, recurrence, idsVoluntiers, maxVoluntiers, calendarName, id, nAttendances) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, calendario.getTitle());
            statement.setString(2, calendario.getStartDate());
            statement.setString(3, calendario.getEndDate());
            statement.setString(4, calendario.getStartTime());
            statement.setString(5, calendario.getEndTime());
            statement.setString(6, calendario.getZoneId());
            statement.setString(7, calendario.getRecurrenceRule());
            statement.setBoolean(8, calendario.isFullDay());
            statement.setBoolean(9, calendario.isRecurring());
            statement.setBoolean(10, calendario.isRecurrence());
            statement.setString(11, calendario.getIdsVoluntiers());
            statement.setInt(12, calendario.getMaxVoluntiers());
            statement.setString(13, calendario.getCalendarName());
            statement.setString(14, calendario.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
                calendarioData.add(calendario);
            } else {
                System.out.println("Failed to update.");
            }
            //connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            sqliteController.closeDBConnection(connection);
        }
        return true;
    }

    public boolean updateCalendarEvent(Entry calendarEvent) {
        Calendario calendario = new Calendario(calendarEvent);
        Calendario updateCalendario = getIdCalendario(calendario.getId());
        System.out.println("UpdateCalendarEvent: " + updateCalendario.getTitle() + " " + updateCalendario.getStartDate() + " " + updateCalendario.getEndDate() + " " + updateCalendario.getStartTime() + " " + updateCalendario.getEndTime() + " ");
        for (Calendario c : calendarioData){
            if(c.getStartDate().equals(calendario.getStartDate()) && c.getStartTime().equals(calendario.getStartTime()) && !c.getId().equals(calendario.getId())){
                System.out.println("Calendario: " + c.getTitle() + " " + c.getStartDate() + " " + c.getEndDate() + " " + c.getStartTime() + " " + c.getEndTime() + " ");
                return false; //Existe um evento com a mesma data e hora
            }
        }
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "UPDATE calendar SET title = ?, startDate = ?, endDate = ?, startTime = ?, endTime = ?, zoneId = ?, recurrenceRule = ?, fullDay = ?, recurring = ?, recurrence = ?, idsVoluntiers = ?, maxVoluntiers = ?, calendarName = ? WHERE id = ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, calendario.getTitle());
            statement.setString(2, calendario.getStartDate());
            statement.setString(3, calendario.getEndDate());
            statement.setString(4, calendario.getStartTime());
            statement.setString(5, calendario.getEndTime());
            statement.setString(6, calendario.getZoneId());
            statement.setString(7, calendario.getRecurrenceRule());
            statement.setBoolean(8, calendario.isFullDay());
            statement.setBoolean(9, calendario.isRecurring());
            statement.setBoolean(10, calendario.isRecurrence());
            statement.setString(11, calendario.getIdsVoluntiers());
            if(calendario.getCalendarName().equals("Lavar")){
                calendario.setMaxVoluntiers(4);
            }else if(calendario.getCalendarName().equals("Alimentar")){
                calendario.setMaxVoluntiers(2);
            }
            else if(calendario.getCalendarName().equals("Limpar")){
                calendario.setMaxVoluntiers(5);
            }
            else if(calendario.getCalendarName().equals("Passear")){
                calendario.setMaxVoluntiers(1);
            }
            statement.setInt(12, calendario.getMaxVoluntiers());
            statement.setString(13, calendario.getCalendarName());
            statement.setString(14, calendario.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
                calendarioData.remove(updateCalendario);
                calendarioData.add(calendario);
            } else {
                System.out.println("Failed to update.");
            }
            //connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        finally {
            sqliteController.closeDBConnection(connection);
        }
        return true;
    }

    public boolean deleteCalendarEvent(String idCalendarEvent) {
        Calendario calendario = getIdCalendario(idCalendarEvent);
        if (calendario != null) {
            connection = sqliteController.createDBConnection();
            if(connection == null){
                System.out.println("Connection not successful");
                System.exit(1);
            }
            System.out.println("Connection successful");
            String query = "DELETE FROM calendar WHERE id = ?";
            try{
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, calendario.getId());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Deleted successfully.");
                    calendarioData.remove(calendario);
                } else {
                    System.out.println("Failed to delete.");
                }
                //connection.close();
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
            finally {
                sqliteController.closeDBConnection(connection);
            }
            return true;
        }
        return false;
    }

    public Calendario getIdCalendario(String id){
        for(Calendario c : calendarioData){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public static CalendarioController getInstance() {
        if (instance == null) {
            instance = new CalendarioController();
        }
        return instance;
    }

}
