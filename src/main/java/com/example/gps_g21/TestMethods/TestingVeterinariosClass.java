package com.example.gps_g21.TestMethods;

import com.example.gps_g21.Modelos.Animal;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.Vets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingVeterinariosClass {
    public boolean loadInfoVets(){
        SqliteController sqliteController = new SqliteController();
        Connection connection = sqliteController.createDBConnection();
        String query = "select * from vets";
        if(connection == null){
            return false;
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Vets vet = new Vets();
                vet.setId(resultSet.getInt("id"));
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                vet.setAddress(resultSet.getString("address"));
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
