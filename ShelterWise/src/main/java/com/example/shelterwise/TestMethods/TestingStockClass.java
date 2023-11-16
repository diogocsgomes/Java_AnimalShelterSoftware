package com.example.shelterwise.TestMethods;

import com.example.shelterwise.Modelos.SqliteController;
import com.example.shelterwise.Modelos.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingStockClass {
    public boolean loadInfoStock() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from product";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Stock prod = new Stock();
                prod.setName(resultSet.getString("name"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("expired_date"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getInt("category"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Stock carregado corretamente");
        return true;
    }

    public boolean loadInfoStockByName() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from product where name like '%" + "Ração" + "%'";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Stock prod = new Stock();
                prod.setName(resultSet.getString("name"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("expired_date"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getInt("category"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Produto 'Ração' carregada corretamente");
        return true;
    }

    public boolean loadInfoStockByCategory() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from product where category = 2";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Stock prod = new Stock();
                prod.setName(resultSet.getString("name"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("expired_date"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getInt("category"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Produtos da categoria Higiene carregados corretamente");
        return true;
    }

    public boolean loadInfoStockByNameAndCategory() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        //connection = sqliteController.createDBConnection();
        String query = "select * from product where name like '%" + "Ração" + "%' and category = 1";
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Stock prod = new Stock();
                prod.setName(resultSet.getString("name"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("expired_date"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getInt("category"));
            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Produto 'Ração' da Categoria 1 (Saúde)");
        return true;
    }
}
