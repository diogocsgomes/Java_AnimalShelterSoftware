package com.example.gps_g21.TestMethods;

import com.example.gps_g21.Modelos.Adopter;
import com.example.gps_g21.Modelos.Animal;
import com.example.gps_g21.Modelos.DoacoesAdocoes;
import com.example.gps_g21.Modelos.SqliteController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingDoacoesAdocoesClass {
    public boolean loadInfoDoacoesAdocoes() {
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.exit(1);
        }

        // Query para carregar as informações de adoption_regist
        String adoptionQuery = "SELECT adoption_regist.id, " +
                "animals.name AS animal_name, " +
                "adopters.name AS adopter_name, " +
                "adoption_regist.adoption_date " +
                "FROM adoption_regist " +
                "JOIN animals ON adoption_regist.animal_id = animals.id " +
                "JOIN adopters ON adoption_regist.adopter_id = adopters.id";

        // Query para carregar as informações de donations
        String donationsQuery = "SELECT * FROM donations";
        try {
            PreparedStatement adoptionStatement = connection.prepareStatement(adoptionQuery);
            ResultSet adoptionResultSet = adoptionStatement.executeQuery();

            while (adoptionResultSet.next()) {
                DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                doacoesadocoes.setId(adoptionResultSet.getInt("id"));
                doacoesadocoes.setNameAnimal(adoptionResultSet.getString("animal_name"));
                doacoesadocoes.setNameAdopter(adoptionResultSet.getString("adopter_name"));
                doacoesadocoes.setAdoptionDate(adoptionResultSet.getString("adoption_date"));
            }

            PreparedStatement donationsStatement = connection.prepareStatement(donationsQuery);
            ResultSet donationsResultSet = donationsStatement.executeQuery();

            while (donationsResultSet.next()) {
                DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                doacoesadocoes.setId(donationsResultSet.getInt("id"));
                doacoesadocoes.setDonorName(donationsResultSet.getString("name"));
                doacoesadocoes.setDonorPhone(donationsResultSet.getString("phone_number"));
                doacoesadocoes.setDonationDescription(donationsResultSet.getString("description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        return true;
    }
    public boolean loadInfoAdocoes(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.exit(1);
        }

        // Query para carregar as informações de adoption_regist
        String adoptionQuery = "SELECT adoption_regist.id, " +
                "animals.name AS animal_name, " +
                "adopters.name AS adopter_name, " +
                "adoption_regist.adoption_date " +
                "FROM adoption_regist " +
                "JOIN animals ON adoption_regist.animal_id = animals.id " +
                "JOIN adopters ON adoption_regist.adopter_id = adopters.id";
        try {
            PreparedStatement adoptionStatement = connection.prepareStatement(adoptionQuery);
            ResultSet adoptionResultSet = adoptionStatement.executeQuery();

            while (adoptionResultSet.next()) {
                DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                doacoesadocoes.setId(adoptionResultSet.getInt("id"));
                doacoesadocoes.setNameAnimal(adoptionResultSet.getString("animal_name"));
                doacoesadocoes.setNameAdopter(adoptionResultSet.getString("adopter_name"));
                doacoesadocoes.setAdoptionDate(adoptionResultSet.getString("adoption_date"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        return true;
    }
    public boolean loadInfoDoacoes(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.exit(1);
        }

        // Query para carregar as informações de donations
        String donationsQuery = "SELECT * FROM donations";
        try {
            PreparedStatement donationsStatement = connection.prepareStatement(donationsQuery);
            ResultSet donationsResultSet = donationsStatement.executeQuery();

            while (donationsResultSet.next()) {
                DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                doacoesadocoes.setId(donationsResultSet.getInt("id"));
                doacoesadocoes.setDonorName(donationsResultSet.getString("name"));
                doacoesadocoes.setDonorPhone(donationsResultSet.getString("phone_number"));
                doacoesadocoes.setDonationDescription(donationsResultSet.getString("description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        return true;
    }
}
