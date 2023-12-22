package com.example.gps_g21.Animais;

import com.example.gps_g21.Modelos.RegistosBanho;
import com.example.gps_g21.Modelos.SqliteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimaisBathListController {
    @FXML
    private TableView<RegistosBanho> tbBath;
    @FXML
    private TableColumn<RegistosBanho, Integer> idColumn;
    @FXML
    private TableColumn<RegistosBanho, String> dateColumn;
    @FXML
    private TableColumn<RegistosBanho, String> timeColumn;

    private ObservableList<RegistosBanho> RegistosBanhosList = FXCollections.observableArrayList();

    private Stage stage;
    private Scene scene;
    private Connection connection;
    private SqliteController sqliteController = new SqliteController();
    private int animal_id;

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public void initialize() throws SQLException {
        System.out.println("Animal ID set to: " + animal_id);
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }

        RegistosBanhosList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<RegistosBanho, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<RegistosBanho, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<RegistosBanho, String>("time"));

        loadBathingHistory();
    }

    public void switchCriarNovoBanho(ActionEvent event) {
        
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-info-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        AnimaisInfoController infoController = loader.getController();
        infoController.editAnimal(animal_id);
        stage.setScene(scene);
        stage.show();
    }

    private void loadBathingHistory() throws SQLException {
        String query = "SELECT * FROM animals_bathing_history WHERE animal_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, animal_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                RegistosBanho registo_banho = new RegistosBanho(id, animal_id, date, time);
                RegistosBanhosList.add(registo_banho);
            }

            tbBath.setItems(RegistosBanhosList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*
    public void switchCriarNovoBanho(ActionEvent actionEvent) {
    }

    public void switchVoltar(ActionEvent actionEvent) {
    }

     */
}
