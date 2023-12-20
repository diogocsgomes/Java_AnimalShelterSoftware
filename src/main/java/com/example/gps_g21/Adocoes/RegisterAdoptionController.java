package com.example.gps_g21.Adocoes;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.gps_g21.Voluntarios.VoluntariosAddController.isValidEmailAddressRegex;


public class RegisterAdoptionController {


    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;

    @FXML
    private TextField birthDateField;
    @FXML
    private TextField phoneNumberField;

    @FXML
    private Text errorMessage;

    private Stage stage;
    private Scene scene;
    private Connection connection = null;
    private SqliteController sqliteController = new SqliteController();

    public void initialize(){
    //System.out.println("No name está " + nameField.getText().length());
    }

    public void insertAdopter(ActionEvent actionEvent) throws IOException {
        String name, address,email, birthDate;
        int phoneNumber;
        String sql = "INSERT INTO adopters(name,address,phone_number,email,birth_date) VALUES(?,?,?,?,?)";

        name = nameField.getText();
        address = addressField.getText();
        email = emailField.getText();
        birthDate = birthDateField.getText();


        if(name.length() == 0 || address.length() == 0 || email.length() == 0 || birthDate.length() == 0
                || phoneNumberField.getText().length() == 0 )
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Existem campos vazios!");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha os campos todos os campos.");
            alert.showAndWait();

            return;
        }
        else if(!isValidEmailAddressRegex(emailField.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Existem campos inválidos!");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha os campos de forma a serem válidos.");
            alert.showAndWait();
            return;
        }
        phoneNumber =Integer.parseInt( phoneNumberField.getText());
        connection = sqliteController.createDBConnection();
        try(PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1,name);
            pstm.setString(2,address);
            pstm.setInt(3,phoneNumber);
            pstm.setString(4,email);
            pstm.setString(5,birthDate);
            pstm.executeUpdate();
        } catch (SQLException e) {
            errorMessage.setText("Non sucedded operation");
            sqliteController.closeDBConnection(connection);
            throw new RuntimeException(e);

        }

        sqliteController.closeDBConnection(connection);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/new-adoption-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    public void switchReturn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/new-adoption-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

