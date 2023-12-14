package com.example.gps_g21.Benemeritos;

import com.example.gps_g21.Modelos.SqliteController;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        public static int id; // You should set this value when initializing the controller
        public static boolean cameToEditFromAdcoesDoacoes = false;

        public BenemeritosInfoEditController(int id) {
            this.id = id;
        }
        public BenemeritosInfoEditController(){}


    public void initialize() {
        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Error connecting to the database");
                System.exit(1);
            } else {
                System.out.println("Database connected in BenemeritosInfoEditController");
            }
            System.out.println("ID: " + this.id);

            String query = "SELECT * FROM adopters WHERE id = " + this.id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nome.setText(resultSet.getString("name"));
                address.setText(resultSet.getString("address"));
                phone.setText(String.valueOf(resultSet.getInt("phone_number")));
                email.setText(resultSet.getString("email"));

                String birthDateStr = resultSet.getString("birth_date");
                if (birthDateStr != null && !birthDateStr.isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
                        birth_date.setValue(birthDate);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error parsing birth date: " + e.getMessage());
                    }
                }

                // Set nif value
                nif.setText(String.valueOf(resultSet.getInt("nif")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Database closed in BenemeritosInfoEditController (initialize)");
        }
    }

    @FXML
    public void save(ActionEvent event) throws IOException {
        String sql = "UPDATE adopters SET name = ?, birth_date = ?, address = ?, phone_number = ?, email = ?, nif = ? WHERE id = ?";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome.getText());
            pstmt.setString(2, birth_date.getValue().toString());
            pstmt.setString(3, address.getText());
            pstmt.setInt(4, Integer.parseInt(phone.getText()));
            pstmt.setString(5, email.getText());
            pstmt.setInt(6, Integer.parseInt(nif.getText()));
            pstmt.setInt(7,id);


            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            System.out.println("Dados atualizados com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            // Move the stage initialization here
            if(cameToEditFromAdcoesDoacoes)
            {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/doacoesAdocoes-list-view.fxml")));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                cameToEditFromAdcoesDoacoes = false;
            }
            else {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/benemeritos-list-view.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
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
