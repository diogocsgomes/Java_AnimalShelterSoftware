package com.example.gps_g21;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StarterController {
    public static String loggedName;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;
    @FXML
    private Text txtStatus;
    int i = 0;
    private Stage stage;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public static UserTypes userType = null;


    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){

        i = 0;
        btnLogin.setOnAction(event -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String role = isValidUser(email, password);
            if(role != null){
                openApp(role);
            }else{
                txtStatus.setVisible(true);
                txtStatus.setText("Dados inválidos (" + i + "x)");
                i++;
            }
        });
    }

    public String isValidUser(String email, String password) {
        String role = null;
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "select * from users where email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
                loggedName = resultSet.getString("nome");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
        return role;
    }

    public void openApp(String role){
        try{
            if("1".equalsIgnoreCase(role)){
                userType = UserTypes.ADMIN;
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("admin-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                stage.setScene(scene);
                stage.centerOnScreen();
            }
            else if("2".equalsIgnoreCase(role)){
                userType = UserTypes.VULUNTIER;
                FXMLLoader fxmlLoader = new FXMLLoader(StarterApplication.class.getResource("voluntarios-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                stage.setScene(scene);
                stage.centerOnScreen();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
