package com.example.gps_g21.Stock;

import com.example.gps_g21.Modelos.Stock;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.StarterController;
import com.example.gps_g21.Modelos.UserTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StockListController {
    @FXML
    private ComboBox typeStock;
    @FXML
    private TextField searchStock;
    @FXML
    private TableView tbStock;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableColumn expiredDateColumn;
    @FXML
    private TableColumn quantityColumn;
    @FXML
    private TableColumn categoryColumn;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;

    String query = "select * from product";
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Stock> dataStock;

    List<String> ProductCategory = Arrays.asList("Todas", "Saude", "Higiene", "Lazer");

    public StockListController() {
    }

    public void initialize(){
        typeStock.setItems(FXCollections.observableArrayList(ProductCategory));
        typeStock.getSelectionModel().selectFirst();
        dataStock = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("description"));
        expiredDateColumn.setCellValueFactory(new PropertyValueFactory<Stock, String>("expiredDate"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantity"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("category"));
    }

    public void loadInfoStock(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        String selectedType = typeStock.getSelectionModel().getSelectedItem().toString();

        String searchName = searchStock.getText().trim();
        System.out.println("Selected Type: " + selectedType + " / Search Name: " + searchName);


        if (!searchName.isEmpty() && selectedType.equals("Todas")) {
            query = "select * from product where name like '%" + searchName + "%'";
        } else if (!searchName.isEmpty() && selectedType.equals("Saude")) {
            query = "select * from product where name like '%" + searchName + "%' and category = '1'";
        } else if (!searchName.isEmpty() && selectedType.equals("Higiene")) {
            query = "select * from product where name like '%\" + searchName + \"%' and category = '2'";
        } else if (!searchName.isEmpty() && selectedType.equals("Lazer")){
            query = "select * from product where name like '%\" + searchName + \"%' and category = '3'";
        } else if(searchName.isEmpty() && selectedType.equals("Todas")) {
            query = "select * from product";
        } else if (searchName.isEmpty() && selectedType.equals("Saude")) {
            query = "select * from product where category = '1'";
        } else if (searchName.isEmpty() && selectedType.equals("Higiene")) {
            query = "select * from product where category = '2'";
        } else if (searchName.isEmpty() && selectedType.equals("Lazer")){
            query = "select * from product where category = '3'";
        }

        //String query = "select * from product";
        try {
            dataStock.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Stock prod = new Stock();
                prod.setName(resultSet.getString("name"));
                prod.setDescription(resultSet.getString("description"));
                prod.setExpiredDate(resultSet.getString("expired_date"));
                prod.setQuantity(resultSet.getInt("quantity"));
                prod.setCategory(resultSet.getInt("category"));
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(StarterController.userType == UserTypes.VULUNTIER){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if(StarterController.userType == UserTypes.DONATOR){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/doador-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }


    }

    public void switcharAddProd(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/stock-create.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void eliminarProd(ActionEvent actionEvent) {
    }

    public void switchEditarProd(ActionEvent actionEvent) {
    }

    public void switcharProd(ActionEvent actionEvent) {
    }
}
