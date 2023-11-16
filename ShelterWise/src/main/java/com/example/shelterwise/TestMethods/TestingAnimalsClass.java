package com.example.shelterwise.TestMethods;

import com.example.shelterwise.Modelos.Animal;
import com.example.shelterwise.Modelos.SqliteController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingAnimalsClass {
    public boolean loadInfoAnimals(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        String queryAnimais = "select * from animals WHERE adopted == false";
        if(connection == null){
            //System.out.println("Connection not successful");
            System.exit(1);
        }
        //System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnimais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setBirthDate(resultSet.getString("birth_date"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
                animal.setFurColor(resultSet.getString("fur_color"));
                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
                animal.setWeight(resultSet.getDouble("weight"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Animals not loaded successfully");
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Animals loaded successfully");
        return true;
    }
    public boolean loadInfoAnimalsForName(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        String queryAnimais = "select * from animals where name like '%" + "Max" + "%'";
        if(connection == null){
            //System.out.println("Connection not successful");
            System.exit(1);
        }
        //System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnimais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setBirthDate(resultSet.getString("birth_date"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
                animal.setFurColor(resultSet.getString("fur_color"));
                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
                animal.setWeight(resultSet.getDouble("weight"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Animals for name not loaded successfully");
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Animals for name loaded successfully");
        return true;
    }
    public boolean loadInfoAnimalsForType(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        String queryAnimais = "select * from animals where type = '" + "Cat" + "'";
        if(connection == null){
            //System.out.println("Connection not successful");
            System.exit(1);
        }
        //System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnimais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setBirthDate(resultSet.getString("birth_date"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
                animal.setFurColor(resultSet.getString("fur_color"));
                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
                animal.setWeight(resultSet.getDouble("weight"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Animals for type not loaded successfully");
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Animals for type loaded successfully");
        return true;
    }
    public boolean loadInfoAnimalsForNameAndType(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        String queryAnimais = "select * from animals where name like '%" + "Max" + "%' and type = '" + "Dog" + "'";
        if(connection == null){
            //System.out.println("Connection not successful");
            System.exit(1);
        }
        //System.out.println("Connection successful");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnimais);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setBirthDate(resultSet.getString("birth_date"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
                animal.setFurColor(resultSet.getString("fur_color"));
                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
                animal.setWeight(resultSet.getDouble("weight"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Animals for name and type not loaded successfully");
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        System.out.println("Animals for name and type loaded successfully");
        return true;
    }
}
