package com.example.gps_g21.Voluntarios;

import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.StarterController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VoluntariosController {

    @FXML
    private Button btnAnimais;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private int loggedID = 0;

    public void switchListaAnimais(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/animais-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchNovaAdocao(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/new-adoption-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchListaCasotas(ActionEvent event) throws IOException {
        if(StarterController.userType == UserTypes.ADMIN){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/casotas-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Casotas");
            stage.setScene(scene);
            stage.show();
        } else if(StarterController.userType == UserTypes.VULUNTIER){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/casotas-list-view-vol.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Casotas");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchVerPerfil(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-profile-view.fxml")));

        //VoluntariosViewProfileController profileController = load.
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchListaVeterinarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Veterinários");
        stage.setScene(scene);
        stage.show();
    }

    public void switchCalendario(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/calendario-view-vol.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Calendário");
        stage.setScene(scene);
        stage.show();
    }

    public void sair(ActionEvent actionEvent) {
        Platform.exit();
    }
}
