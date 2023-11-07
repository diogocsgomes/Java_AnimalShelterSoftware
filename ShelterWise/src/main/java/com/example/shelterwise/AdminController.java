package com.example.shelterwise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Button btnAnimais;
    @FXML
    private Button btnVoluntiers;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchListaAnimais(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animais-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchListaVoluntarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("voluntarios-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchListaVeterinarios(ActionEvent event) throws IOException {

    }
    public void switchVerDoacoes(ActionEvent event) throws IOException {

    }
    public void switchListaStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stock-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchVerCalendario(ActionEvent event) throws IOException {

    }
}
