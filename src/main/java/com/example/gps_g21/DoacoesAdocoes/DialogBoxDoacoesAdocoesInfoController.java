package com.example.gps_g21.DoacoesAdocoes;

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

public class DialogBoxDoacoesAdocoesInfoController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelemovel;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private Label lblError;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public void btnGuardar(ActionEvent event) throws IOException, SQLException {
        if (sqliteController.verificaCampos(txtNome.getText(), txtTelemovel.getText(), txtDescricao.getText())) {
            System.out.println("Por favor, preencha todos os campos.");
            lblError.setVisible(true);
            return;
        }
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        try{
            String query = "INSERT INTO donations (name, phone_number, description) values (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, txtNome.getText());
            statement.setString(2, txtTelemovel.getText());
            statement.setString(3, txtDescricao.getText());

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

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
