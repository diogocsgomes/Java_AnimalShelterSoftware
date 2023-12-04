package com.example.gps_g21.Benemeritos;

import com.example.gps_g21.Modelos.Adopter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class BenemeritosController {
    @FXML
    public TableView tbBenemeritos;
    @FXML
    public TableColumn nameColumnBenemeritos;
    @FXML
    public TableColumn phoneNumberColumnBenemeritos;
    @FXML
    public TableColumn emailColumnBenemeritos;
    public Button btnSair;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    private ObservableList<Adopter> dataBenemeritos;
    String queryBenemeritos = "select * from adopters";
    Adopter benemerito;
    private Stage stage;
    private Scene scene;
    public void initialize() {


        dataBenemeritos = FXCollections.observableArrayList();

        nameColumnBenemeritos.setCellValueFactory(new PropertyValueFactory<Adopter, String>("name"));
        phoneNumberColumnBenemeritos.setCellValueFactory(new PropertyValueFactory<Adopter, Integer>("phoneNumber"));
        emailColumnBenemeritos.setCellValueFactory(new PropertyValueFactory<Adopter, String>("email"));

        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            dataBenemeritos.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(queryBenemeritos);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Adopter adopter = new Adopter(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("birth_date"));

                dataBenemeritos.add(adopter);
            }
            tbBenemeritos.setItems(dataBenemeritos);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }

        tbBenemeritos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null)
            {
                benemerito = (Adopter) tbBenemeritos.getSelectionModel().getSelectedItem();
                System.out.println(benemerito.toString());
            }
        });



    }


    public void onSair(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
