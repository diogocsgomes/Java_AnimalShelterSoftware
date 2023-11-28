package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class VeterinariansInfoEditController {

    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private ChoiceBox<String> active;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    private SqliteController sqliteController = new SqliteController();
    private Connection connection = null;

    private int veterinarianId;

    public VeterinariansInfoEditController(int veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public void initialize() throws SQLException {
        ObservableList<String> options = FXCollections.observableArrayList("Sim", "Não");
        active.setItems(options);

        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            } else {
                System.out.println("Db aberta no VeterinariansInfoEditController");
            }

            String query = "select * from vets where id = " + veterinarianId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            name.setText(resultSet.getString("name"));
            address.setText(resultSet.getString("address"));
            phone.setText(String.valueOf(resultSet.getInt("phone")));
            email.setText(resultSet.getString("email"));

            if (resultSet.getBoolean("active"))
                active.setValue("Sim");
            else
                active.setValue("Não");
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada no VeterinariansInfoEditController (initialize)");
        }
    }
/*
    public void save(ActionEvent event) throws SQLException, IOException {
        String sql = "UPDATE vets SET name = ?, address = ?, phone = ?, active = ?, email = ? WHERE id = ?";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name.getText());
            pstmt.setString(2, address.getText());
            pstmt.setInt(3, Integer.parseInt(phone.getText()));
            pstmt.setBoolean(4, "Sim".equals(active.getValue()));
            pstmt.setString(5, email.getText());
            pstmt.setInt(6, veterinarianId);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + affectedRows);
            System.out.println("Dados atualizados com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarians-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
*/
    public void back(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarians-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
