package com.example.shelterwise;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
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
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchVerDoacoes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchListaStock(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchVerCalendario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
