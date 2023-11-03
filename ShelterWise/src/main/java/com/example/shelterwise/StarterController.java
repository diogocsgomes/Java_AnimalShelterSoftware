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
import java.sql.*;

public class StarterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;
    @FXML
    private Text txtStatus;
    int i = 0;
    private Stage stage;
    Connection connection = null;
    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        i = 0;
        btnLogin.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = isValidUser(username, password);
            if(role != null){
                openApp(role);
            }else{
                txtStatus.setVisible(true);
                txtStatus.setText("Dados inválidos (" + i + "x)");
                i++;
            }
        });
    }

    public String isValidUser(String username, String password) {
        String role = null;
        connection = createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "select role from users where nome = ? and password = ?";
        try {
            System.out.println("user: " + username + " pass: " + password);
            System.out.println("sqlQuery: " + query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
        } finally {
            closeDBConnection();
        }
        return role;
    }

    public Connection createDBConnection() {
        try {
            String dbPath = "jdbc:sqlite:ShelterWise\\ShelterWiseDB.sqlite";
            return DriverManager.getConnection(dbPath);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public void closeDBConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void openApp(String role){
        try{
            if("1".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            } else if("2".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("voluntarios-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            }
            else if("3".equalsIgnoreCase(role)){
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("veterinarios-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                stage.setScene(scene);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
