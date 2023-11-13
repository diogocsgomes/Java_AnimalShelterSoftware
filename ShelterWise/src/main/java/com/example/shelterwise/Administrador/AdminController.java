package com.example.shelterwise.Administrador;

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

public class AdminController {
    @FXML
    private Button btnAnimais;
    @FXML
    private Button btnVoluntiers;
    @FXML
    private Button btnCasotas;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchListaAnimais(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/animais-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Animais");
        stage.setScene(scene);
        stage.show();
    }
    public void switchListaVoluntarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/voluntarios-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Voluntarios");
        stage.setScene(scene);
        stage.show();
    }
    public void switchListaVeterinarios(ActionEvent event) throws IOException {

    }
    public void switchVerDoacoes(ActionEvent event) throws IOException {

    }
    public void switchListaStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/stock-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Stock");
        stage.setScene(scene);
        stage.show();
    }
    public void switchVerCalendario(ActionEvent event) throws IOException {

    }
    public void switchListaCasotas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/casotas-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Casotas");
        stage.setScene(scene);
        stage.show();
    }

    public void switchNovaAdocao(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/new-adoption-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Adocao");
        stage.setScene(scene);
        stage.show();
    }
}