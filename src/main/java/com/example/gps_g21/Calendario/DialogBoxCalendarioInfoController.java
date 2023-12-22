package com.example.gps_g21.Calendario;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DialogBoxCalendarioInfoController {
    @FXML
    private Button btnGuardar;
    String id;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public void loadCalendarInfo(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        String query = "select * from calendar where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int maxVoluntiers = resultSet.getInt("maxVoluntiers");
                String[] ids = resultSet.getString("idsVoluntiers").split(";");
            }
            sqliteController.closeDBConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnGuardar(ActionEvent event) throws IOException, SQLException {
//        if (sqliteController.verificaCampos(inputHealthStatus.getText())) {
//            System.out.println("Por favor, preencha todos os campos.");
//            return;
//        }
//        connection = sqliteController.createDBConnection();
//        if(connection == null){
//            System.out.println("Connection not successful");
//            System.exit(1);
//        }
//        try{
//            String query = "UPDATE animals SET health=? WHERE id=?";
//            PreparedStatement statement = connection.prepareStatement(query);
//            statement.setInt(1, Integer.parseInt(inputHealthStatus.getText()));
//            statement.setInt(2, id);
//
//            int rowsAffected = statement.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("Updated successfully.");
//                closeStage(event);
//            } else {
//                System.out.println("Failed to update.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        sqliteController.closeDBConnection(connection);
    }

    public void setId(String _id) {
        id = _id;
        loadCalendarInfo();
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
