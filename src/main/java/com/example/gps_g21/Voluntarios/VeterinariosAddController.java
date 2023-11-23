package com.example.gps_g21.Voluntarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.StarterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

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

    public void addVets(ActionEvent event) {
    }

    public void backVol(ActionEvent event) throws IOException {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return;
        } else {
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
            return;
        }

    }
}
