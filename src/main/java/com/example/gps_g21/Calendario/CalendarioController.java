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
                System.out.println("Valores de Entrada: " + calendario1.getTitle() + " " + calendario1.getStartDate() + " " + calendario1.getEndDate() + " " + calendario1.getStartTime() + " " + calendario1.getEndTime() + " ");
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
        //faz um system.out.println para mostrar a informação recebida
        System.out.println("Valores de Entrada: " + calendario.getTitle() + " " + calendario.getStartDate() + " " + calendario.getEndDate() + " " + calendario.getStartTime() + " " + calendario.getEndTime() + " ");

        if(calendarioData != null){
            System.out.println("Tamanho do calendarioData: " + calendarioData.size());
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
            String query = "INSERT INTO calendar (title, startDate, endDate, startTime, endTime, zoneId, recurrenceRule, fullDay, recurring, recurrence, idsVoluntiers, maxVoluntiers, calendarName, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        else{
            System.out.println("Tamanho do calendarioData: null");
            return false;
        }
    }

    public boolean updateCalendarEvent(Entry calendarEvent) {
        Calendario calendario = new Calendario(calendarEvent);
        Calendario updateCalendario = getIdCalendario(calendario.getId());
        System.out.println("Valores de EntradaV2: " + calendario.getTitle() + " " + calendario.getStartDate() + " " + calendario.getEndDate() + " " + calendario.getStartTime() + " " + calendario.getEndTime() + " ");
        System.out.println("Valores de updateCalendarioV2: " + updateCalendario.getTitle() + " " + updateCalendario.getStartDate() + " " + updateCalendario.getEndDate() + " " + updateCalendario.getStartTime() + " " + updateCalendario.getEndTime() + " ");
        if (updateCalendario != null) {
            for (Calendario c : calendarioData){
                if(c.getStartDate().equals(calendario.getStartDate()) && c.getStartTime().equals(calendario.getStartTime()) && !c.getId().equals(calendario.getId())){
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
        }else{
            System.out.println("Ouve um erro ao dar update ao evento");
            return false;
        }
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