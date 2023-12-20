package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.StarterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

import static com.example.gps_g21.Casotas.CasotaAddController.isNumeric;

public class VeterinariosAddController {
    
    @FXML
    public TextField nome;
    @FXML
    public TextField address;
    @FXML
    public TextField phone;
    @FXML
    public TextField email;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    
    public void initialize(){}

    public void addVets(ActionEvent event) throws IOException {
        if(Objects.equals(nome.getText(), "") || Objects.equals(email.getText(), "") || Objects.equals(phone.getText(), "")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dados por preencher");
            alert.setContentText("Existem dados por preencher! Por favor, preencha todos os dados");
            alert.showAndWait();
        }
        else {
            if(!isNumeric(phone.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Telefone inválido!");
                alert.setContentText("Insira um número de telefone válido!");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Adicionar Veterinário");
                alert.setHeaderText(null);
                alert.setContentText("Tem a certeza que deseja adicionar veterinário?");

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

                        String sql = "INSERT INTO vets (name, email, phone, address) VALUES (?, ?, ?, ?)";

                        PreparedStatement pstmt = connection.prepareStatement(sql);
                        pstmt.setString(1, nome.getText());
                        pstmt.setString(2, email.getText());
                        pstmt.setInt(3, Integer.parseInt(phone.getText()));
                        pstmt.setString(4, address.getText());
                        pstmt.executeUpdate();
                        //System.out.println("Dados inseridos com sucesso.");

                    } catch (SQLException e) {
                        System.err.println("Erro ao inserir dados: " + e.getMessage());
                    }

                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    preScene = stage.getScene();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }
    }

    public void backVol(ActionEvent event) throws IOException {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
        }

    }
}
