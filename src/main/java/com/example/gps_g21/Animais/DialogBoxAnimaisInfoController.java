package com.example.gps_g21.Animais;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DialogBoxAnimaisInfoController {
    @FXML
    private TextArea inputHealthStatus;
    @FXML
    private ListView listStatusHealth;
    @FXML
    private Button btnGuardar;
    List<String> saude = Arrays.asList("Péssimo (0 - 24)", "Fratura (25 - 49)", "Doença leve (50 - 74)", "Bom saúde (75 - 100)");
    int id;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public void loadStatusHealth(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        listStatusHealth.getItems().addAll(saude);
        String query = "select health from animals where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int healthStatus = resultSet.getInt("health");
                System.out.println("Estado de saude: " + healthStatus);
                inputHealthStatus.setText(String.valueOf(healthStatus));
            }
            sqliteController.closeDBConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnGuardar(ActionEvent event) throws IOException, SQLException {
        if (sqliteController.verificaCampos(inputHealthStatus.getText())) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        try{
            String query = "UPDATE animals SET health=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(inputHealthStatus.getText()));
            statement.setInt(2, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
                closeStage(event);
            } else {
                System.out.println("Failed to update.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqliteController.closeDBConnection(connection);
    }

    public void setId(int _id) {
        id = _id;
        loadStatusHealth();
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
