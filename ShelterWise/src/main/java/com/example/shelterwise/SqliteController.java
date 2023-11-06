package com.example.shelterwise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteController {
    public Connection createDBConnection() {
        try {
            String dbPath = "jdbc:sqlite:ShelterWiseDB\\ShelterWiseDB.sqlite";
            return DriverManager.getConnection(dbPath);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public void closeDBConnection(Connection connection) {
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException ex) {
            //ex.printStackTrace();
        }
    }
}
