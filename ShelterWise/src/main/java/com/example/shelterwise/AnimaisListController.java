package com.example.shelterwise;

import com.example.shelterwise.Modelos.Animal;
import com.example.shelterwise.usertypes.UserTypes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class AnimaisListController {
    @FXML
    private static Label lblTitulo;
    @FXML
    private TableView tbAnimais;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn weightColumn;
    @FXML
    private TableColumn breedColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn genderColumn;
    @FXML
    private TableColumn furColorColumn;
    @FXML
    private TableColumn furTypeColumn;
    @FXML
    private TableColumn birthDateColumn;
    @FXML
    private TableColumn kennelIdColumn;
    @FXML
    private TableColumn commentsColumn;
    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Animal> dataAnimals;

    public void initialize(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        dataAnimals = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<Animal, Double>("weight"));
        breedColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("breed"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("gender"));
        furColorColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("furColor"));
        furTypeColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("furType"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Animal, Date>("birthDate"));
        kennelIdColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("kennelId"));
        commentsColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("comments"));
        String query = "select * from animals";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
                animal.setBirthDate(resultSet.getString("birth_date"));
                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
                animal.setFurColor(resultSet.getString("fur_color"));
                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
                animal.setWeight(resultSet.getDouble("weight"));
                dataAnimals.add(animal);
            }
            tbAnimais.setItems(dataAnimals);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }

    public void switchVoltar(ActionEvent event) throws IOException {
        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (StarterController.userType == UserTypes.VULUNTIER) {
            Parent root = FXMLLoader.load(getClass().getResource("voluntarios-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (StarterController.userType == UserTypes.VET) {
            //ainda nao existe uma vista exclusiva para veterinarios
           System.out.println("FUNCIONALIDADE POR IMPLEMENTAR");
        }


    }
    public void switchCriarAnimal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animais-info-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        preScene = stage.getScene();
        scene = new Scene(root);
        //stage.setTitle("Criar Animal");  Corrigir problema de não mudar o título
        stage.setScene(scene);
        stage.show();
    }
    public void switchEditarAnimal(ActionEvent event) throws IOException {
        Animal selectedAnimalId = (Animal) tbAnimais.getSelectionModel().getSelectedItem();
        if (selectedAnimalId != null) {
            System.out.println("Animal Selecionado: " + selectedAnimalId.getId());
            //Parent root = FXMLLoader.load(getClass().getResource("animais-info-view.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("animais-info-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(loader.load());
            AnimaisInfoController infoController = loader.getController();
            infoController.setAnimalId(selectedAnimalId.getId());
            stage.setTitle("Editar Animal");
            //lblTitulo.setText("Editar Animal");  Corrigir problema de não mudar o título
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("No animal selected");
        }
    }
}
