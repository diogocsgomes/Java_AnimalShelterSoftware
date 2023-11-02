package com.example.shelterwise;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StarterController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Text status;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = isValidUser(username, password);
            if(role != null){
                openApp(role);
            } //login fail
        });
    }

    public String isValidUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("ShelterWise\\ficheirosTxt\\users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    String storedRole = parts[2];
                    System.out.println(storedUsername);
                    System.out.println(storedPassword);
                    System.out.println(storedRole);

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return storedRole;
                    } else{
                        System.out.println("Dados invalidos");
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return null;
    }

    public void openApp(String role){
        try{
            if("admin".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            } else if("voluntario".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("voluntarios-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            }
            else if("veterinario".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("veterinarios-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
