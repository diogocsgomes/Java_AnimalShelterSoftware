package com.example.shelterwise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class AnimaisController {
    @FXML
    private static Label lblTitulo;
    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;

    public void switchVoltar(ActionEvent event) throws IOException {
        Parent root = preScene.getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(root.getScene());
        stage.show();
    }
    public void switchCriarAnimal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animais-info.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();
        scene = new Scene(root);
        //stage.setTitle("Criar Animal");  Corrigir problema de não mudar o título
        stage.setScene(scene);
        stage.show();
    }
    public void switchEditarAnimal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animais-info.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();
        scene = new Scene(root);
        stage.setTitle("Editar Animal");
        //lblTitulo.setText("Editar Animal");  Corrigir problema de não mudar o título
        stage.setScene(scene);
        stage.show();
    }
    public void eliminarAnimal(ActionEvent event) throws IOException {

    }
}
