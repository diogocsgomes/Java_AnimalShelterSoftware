package com.example.shelterwise.Animais;

import com.example.shelterwise.Modelos.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

import java.sql.Connection;
import java.util.Objects;

public class AnimaisFeedListController {
    @FXML
    private TableView<RegistosAlimentacao> tbFeed;
    @FXML
    private TableColumn<RegistosAlimentacao, Integer> idColumn;
    @FXML
    private TableColumn<RegistosAlimentacao, String> dateColumn;
    @FXML
    private TableColumn<RegistosAlimentacao, String> timeColumn;

    private ObservableList<RegistosAlimentacao> RegistosAlimentacaoList = FXCollections.observableArrayList();

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

        RegistosAlimentacaoList = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<RegistosAlimentacao, Integer>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<RegistosAlimentacao, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<RegistosAlimentacao, String>("time"));


        loadFeedingHistory();
    }

    public void switchCriarNovaAlimentacao(ActionEvent actionEvent) {
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/shelterwise/animais-info-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        AnimaisInfoController infoController = loader.getController();
        infoController.editAnimal(animal_id);
        stage.setScene(scene);
        stage.show();
    }

    private void loadFeedingHistory() throws SQLException {
        String query = "SELECT * FROM animals_feeding_history WHERE animal_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, animal_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String date = resultSet.getString("date");
                String time = resultSet.getString("time");

                RegistosAlimentacao registo_alimentacao = new RegistosAlimentacao(id, animal_id, date, time);
                RegistosAlimentacaoList.add(registo_alimentacao);
            }

            tbFeed.setItems(RegistosAlimentacaoList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
