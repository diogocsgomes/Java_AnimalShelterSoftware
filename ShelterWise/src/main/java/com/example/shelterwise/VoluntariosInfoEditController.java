package com.example.shelterwise;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class VoluntariosInfoEditController {
    @FXML
    private TextField morada;
    @FXML
    private TextField telefone;
    @FXML
    private TextField email;
    @FXML
    private DatePicker birth_date;
    @FXML
    private TextField nome;
    @FXML
    private TextField nif;
    @FXML
    private ChoiceBox ative;


    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private int id;

    public VoluntariosInfoEditController(int id) {
        this.id = id;
    }

    /*
    public void initialize() throws SQLException {
        ObservableList<String> options = FXCollections.observableArrayList("Sim", "Não");
        ative.setItems(options);

        connection = sqliteController.createDBConnection();

        if(connection == null){
            System.out.println("Erro de conexão à base de dados");
            System.exit(1);
        }

        String query = "select * from users where id = " + id;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        nome.setText(resultSet.getString("nome"));
        morada.setText(resultSet.getString("address"));
        telefone.setText(String.valueOf(resultSet.getInt("phone")));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        birth_date.setValue(LocalDate.parse(resultSet.getString("date_birth"), formatter));
        nif.setText(resultSet.getString("nif"));
        email.setText(resultSet.getString("email"));

        if(resultSet.getBoolean("active"))
            ative.setValue("Sim");
        else
            ative.setValue("Não");

    }

     */
    public void initialize() throws SQLException {
        ObservableList<String> options = FXCollections.observableArrayList("Sim", "Não");
        ative.setItems(options);

        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            } else{
                System.out.println("Db aberta no VoluntariosInfoEditController");
            }

            String query = "select * from users where id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            nome.setText(resultSet.getString("nome"));
            morada.setText(resultSet.getString("address"));
            telefone.setText(String.valueOf(resultSet.getInt("phone")));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
            birth_date.setValue(LocalDate.parse(resultSet.getString("date_birth"), formatter));
            nif.setText(resultSet.getString("nif"));
            email.setText(resultSet.getString("email"));

            if (resultSet.getBoolean("active"))
                ative.setValue("Sim");
            else
                ative.setValue("Não");
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada no VoluntariosInfoEditController (initialize)");
        }
    }


    public void Save(ActionEvent event) throws SQLException, IOException {
        String sql = "UPDATE users SET nome = ?, date_birth = ?, address = ?, phone = ?, active = ?, nif = ?, email = ? WHERE id = ?";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nome.getText());
            pstmt.setString(2, String.valueOf(birth_date.getValue()));
            pstmt.setString(3, morada.getText());
            pstmt.setInt(4, Integer.parseInt(telefone.getText()));
            pstmt.setBoolean(5, "Sim".equals(ative.getValue()));
            pstmt.setInt(6, Integer.parseInt(nif.getText()));
            pstmt.setString(7, email.getText());
            pstmt.setInt(8, id);

            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            System.out.println("Dados atualizados com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            // Move the stage initialization here
            Parent root = FXMLLoader.load(getClass().getResource("voluntarios-list-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        // Close the current connection
        /*
        sqliteController.closeDBConnection(connection);
        stage.show();
         */
    }

    public void Back(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);

        Parent root = FXMLLoader.load(getClass().getResource("voluntarios-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
