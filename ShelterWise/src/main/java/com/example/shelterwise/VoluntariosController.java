package com.example.shelterwise;

import com.example.shelterwise.Modelos.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.layout.VBox;

public class VoluntariosController {
    @FXML
    private TableView tbVoluntiers;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn birthColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn nifColumn;
    @FXML
    private TableColumn activeColumn;

  /*  @FXML
    private VBox dynamicPaneContainer; // Referência ao VBox no FXML
    @FXML
    private Pane pnlInfoVol;*/

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Users> dataVoluntiers;

    public void initialize(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        dataVoluntiers = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("date_birth"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("address"));
        nifColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("nif"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<Users, Boolean>("active"));
        String query = "select * from users where role = 2";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Users vol = new Users();
                vol.setName(resultSet.getString("nome"));
                vol.setBirth(resultSet.getString("date_birth"));
                vol.setPhone(resultSet.getInt("phone"));
                vol.setEmail(resultSet.getString("email"));
                vol.setAddress(resultSet.getString("address"));
                vol.setNif(resultSet.getInt("nif"));
                vol.setActive(resultSet.getBoolean("active"));
                dataVoluntiers.add(vol);
            }
            tbVoluntiers.setItems(dataVoluntiers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
    public void switchVoltar(ActionEvent event) throws IOException {
        Parent root = preScene.getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(root.getScene());
        stage.show();
    }

    public void eliminarVol(ActionEvent actionEvent) {
    }

    public void switchEditarVol(ActionEvent actionEvent) {
    }

    public void switcharVol(ActionEvent actionEvent) {
    }


    // Caso tentamos depois fazer com os panes em vez de tabelas
    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "select * from users where role = 2";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                criarPaneVol(resultSet);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
    private void criarPaneVol(ResultSet resultSet) throws SQLException {
        Pane pane = new Pane();
        pane.setStyle(pnlInfoVol.getStyle());

        ((Text) pane.lookup("#nomeText")).setText(resultSet.getString("nome"));
        ((Text) pane.lookup("#phoneText")).setText(resultSet.getString("phone"));

        dynamicPaneContainer.getChildren().add(pane);
    }*/


}
