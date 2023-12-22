package com.example.gps_g21.TestMethods;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingVoluntariosClass {
    public boolean loadInfoVoluntarios() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }

    public boolean loadInfoVoluntariosByName() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users where nome like '%" + "Vol" + "%'";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }

    public boolean loadInfoVoluntariosByRole() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users where role = 1";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }

    public boolean loadInfoVoluntariosById() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users where id = 1";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }

    public boolean loadInfoVoluntariosByPhone() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users where phone = 123456789";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }

    public boolean loadInfoVoluntariosByNif() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from users where nif = 123456789";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setName(resultSet.getString("nome"));
                user.setId(resultSet.getInt("id"));
                user.setBirth(resultSet.getString("date_birth"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setNif(resultSet.getInt("nif"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("User carregado corretamente");
        return true;
    }
}
