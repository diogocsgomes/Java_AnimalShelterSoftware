package com.example.gps_g21.Benemeritos;

import com.example.gps_g21.Modelos.Adopter;
import com.example.gps_g21.Modelos.Animal;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Voluntarios.VoluntariosInfoEditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public TextField searchAdotanteDoador;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    private ObservableList<Adopter> dataBenemeritos;
    String queryBenemeritos = "select * from adopters";
    Adopter benemerito;
    private Stage stage;
    private Scene scene;

    private Scene preScene;
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

    public void onNovo(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/benemerito-create.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onEditar(ActionEvent actionEvent) throws IOException {
        if (benemerito != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/benemeritos-info-edit.fxml"));

            // Set the controller factory to create an instance of BenemeritosInfoEditController
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == BenemeritosInfoEditController.class) {
                    return new BenemeritosInfoEditController(benemerito.getId());
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Parent root = loader.load();
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            preScene = stage.getScene();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }




    public void searchBenemerito(ActionEvent actionEvent) {

        String nameToSearch = searchAdotanteDoador.getText();
        boolean sqlFlag = true;
        String sqlBenemeritos = "SELECT * FROM adopters WHERE name == ?";
        if(nameToSearch.isEmpty() || isAllWhiteSpaces(nameToSearch)) {
            System.out.println("é nulo caralho");
            sqlBenemeritos = "SELECT  * FROM adopters";
            sqlFlag = false;
        }


        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            dataBenemeritos.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlBenemeritos);
            if(sqlFlag)
                preparedStatement.setString(1,nameToSearch);
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




    }


    private boolean isAllWhiteSpaces(String str){
        if(str.isEmpty())
            return true;
        int spaceCounter = 0;
        for(int i = 0; i< str.length(); i++)
        {
            char c = str.charAt(i);
            if(c == ' ')
                spaceCounter++;
        }
        return spaceCounter == str.length();
    }
}
