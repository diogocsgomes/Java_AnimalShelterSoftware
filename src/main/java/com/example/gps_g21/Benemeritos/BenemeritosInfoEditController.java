package com.example.gps_g21.Benemeritos;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class BenemeritosInfoEditController {

        @FXML
        private TextField nome;
        @FXML
        private TextField address;
        @FXML
        private TextField phone;
        @FXML
        private TextField email;
        @FXML
        private DatePicker birth_date;
        @FXML
        private TextField nif;
        @FXML
        private Button btnGuardar;
        @FXML
        private Button btnVoltar;

        private Stage stage;
        private Scene scene;

        private SqliteController sqliteController = new SqliteController();
        private Connection connection = null;
        private int id; // You should set this value when initializing the controller

        public BenemeritosInfoEditController(int id) {
            this.id = id;
        }

        public void initialize() throws SQLException {
            try {
                connection = sqliteController.createDBConnection();

                if (connection == null) {
                    System.out.println("Erro de conexão à base de dados");
                    System.exit(1);
                }else {
                    System.out.println("Db aberta no BenemeritosInfoEditController");
                }

                String query = "SELECT * FROM adopters WHERE id = " + id;

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                nome.setText(resultSet.getString("name"));
                address.setText(resultSet.getString("address"));
                phone.setText(String.valueOf(resultSet.getInt("phone")));
                email.setText(resultSet.getString("email"));
                // No birth_date in the adopters table according to your information
                nif.setText(resultSet.getString("nif"));
            } finally {
                sqliteController.closeDBConnection(connection);
                System.out.println("Db fechada no BenemeritosInfoEditController (initialize)");
            }
        }

        @FXML
        public void save() {
            // Implement the logic to save changes to the database
        }

        @FXML
        public void goBack(ActionEvent event) throws IOException {
            sqliteController.closeDBConnection(connection);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/benemeritos-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


}
