package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class VeterinariansViewDataController {

    @FXML
    private Text address;
    @FXML
    private Text phone;
    @FXML
    private Text email;
    @FXML
    private Text name;
    @FXML
    private Text id;
    @FXML
    private Circle active;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private int veterinarianId;

    public VeterinariansViewDataController(int veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public void initialize() throws SQLException {
        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String query = "select * from vets where id = " + veterinarianId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            name.setText("Nome: " + resultSet.getString("name"));
            address.setText("Morada: " + resultSet.getString("address"));
            phone.setText("Telefone: " + resultSet.getInt("phone"));
            email.setText("Email: " + resultSet.getString("email"));
            id.setText("ID: " + resultSet.getInt("id"));

            if (resultSet.getBoolean("active"))
                active.setFill(Color.GREEN);
            else
                active.setFill(Color.RED);
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
/*
    public void editData(ActionEvent event) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/veterinarians-info-edit.fxml"));

            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == VeterinariansInfoEditController.class) {
                    return new VeterinariansInfoEditController(veterinarianId);
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        preScene = stage.getScene();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

*/

    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarians-list-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
