package com.example.gps_g21.Benemeritos;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class BenemeritosAddController {

    @FXML
    private TextField nome;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birth_date;
    @FXML
    private TextField nif;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnVoltar;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    private SqliteController sqliteController = new SqliteController();
    private Connection connection = null;

    @FXML
    public void addBen(ActionEvent event) throws IOException {
        if(Objects.equals(nome.getText(), "") || Objects.equals(email.getText(), "") || Objects.equals(phone.getText(), "") || Objects.equals(nif.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dados por preencher");
            alert.setContentText("Existem dados por preencher! Por favor, preencha todos os dados");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adicionar Benemerito");
            alert.setHeaderText(null);
            alert.setContentText("Tem a certeza que deseja adicionar Benemerito?");

            ButtonType buttonTypeSim = new ButtonType("Adicionar");
            ButtonType buttonTypeNao = new ButtonType("Cancelar");
            alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.isPresent() && resultado.get() == buttonTypeSim) {
                try {
                    connection = sqliteController.createDBConnection();
                    if (connection == null) {
                        System.out.println("Erro de conexão à base de dados");
                        System.exit(1);
                    }

                    String sql = "INSERT INTO adopters (name, email, phone, address, nif) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, nome.getText());
                    pstmt.setString(2, email.getText());
                    pstmt.setInt(3, Integer.parseInt(phone.getText()));
                    pstmt.setString(4, address.getText());
                    pstmt.setString(5, nif.getText());
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    System.err.println("Erro ao inserir dados: " + e.getMessage());
                }

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/benemeritos-list-view.fxml")));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                preScene = stage.getScene();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/benemeritos-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}