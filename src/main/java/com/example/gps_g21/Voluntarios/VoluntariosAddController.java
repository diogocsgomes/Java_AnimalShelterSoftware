package com.example.gps_g21.Voluntarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.StarterController;
import com.example.gps_g21.Modelos.UserTypes;
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
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class VoluntariosAddController {
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField nif;
    @FXML
    private TextField nome;
    @FXML
    private DatePicker birth_date;
    @FXML
    private PasswordField password;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private List<TextField> textFields;

    public void initialize() {}

    public void addVoluntier(ActionEvent event) throws IOException {

        if(nome.getText() == "" || password.getText() == "" || birth_date.getValue() == null || address.getText() == "" || phone.getText() == "" || nif.getText() == "" ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Existem campos vazios!");
            alert.setHeaderText(null);
            alert.setContentText("Existem campos vazios! Por favor, preencha todos os campos para inserir novo utilizador.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adicionar Voluntário");
            alert.setHeaderText(null);
            alert.setContentText("Tem a certeza que deseja adicionar voluntário?");

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

                    String sql = "INSERT INTO users (nome, password, date_birth, address, phone, active, nif, role, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, nome.getText());
                    pstmt.setString(2, password.getText());
                    pstmt.setString(3, String.valueOf(birth_date.getValue()));
                    pstmt.setString(4, address.getText());
                    pstmt.setInt(5, Integer.parseInt(phone.getText()));
                    pstmt.setBoolean(6, true);
                    pstmt.setInt(7, Integer.parseInt(nif.getText()));
                    pstmt.setInt(8, 2);
                    pstmt.setString(9, email.getText());

                    pstmt.executeUpdate();
                    System.out.println("Dados inseridos com sucesso.");

                } catch (SQLException e) {
                    System.err.println("Erro ao inserir dados: " + e.getMessage());
                }

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-list-view.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                preScene = stage.getScene();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }

    }

    public void backVol(ActionEvent event) throws IOException {

        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("Não é admin");
        }

    }
}
