package com.example.gps_g21.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteController {
    public Connection createDBConnection() {
        try {
            String dbPath = "jdbc:sqlite:ShelterWiseDB.sqlite";
            return DriverManager.getConnection(dbPath);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean verificaCampos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void closeDBConnection(Connection connection) {
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
