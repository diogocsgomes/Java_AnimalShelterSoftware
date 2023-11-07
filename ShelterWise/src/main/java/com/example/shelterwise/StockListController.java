package com.example.shelterwise;

import com.example.shelterwise.Modelos.Stock;
import com.example.shelterwise.usertypes.UserTypes;
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

public class StockListController {
    @FXML
    private TableView tbStock;
    @FXML
    private TableColumn nameColumn;

    @FXML
    private TableColumn descriptionColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn quantityColumn;

    @FXML
    private TableColumn categoryColumn;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Stock> dataStock;

    public void initialize(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        dataStock = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("expired_date"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("category"));
        String query = "select * from product";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Stock prod = new Stock();
                prod.setName(resultSet.getString("nome"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("date_expired"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getString("category"));
                dataStock.add(prod);
            }
            tbStock.setItems(dataStock);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        /*Parent root = preScene.getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(root.getScene());
        stage.show();

         */

        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(StarterController.userType == UserTypes.VULUNTIER){
            Parent root = FXMLLoader.load(getClass().getResource("voluntarios-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(StarterController.userType == UserTypes.DONATOR){
            Parent root = FXMLLoader.load(getClass().getResource("doador-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }

    public void eliminarProd(ActionEvent actionEvent) {
    }

    public void switchEditarProd(ActionEvent actionEvent) {
    }

    public void switcharProd(ActionEvent actionEvent) {
    }
}
