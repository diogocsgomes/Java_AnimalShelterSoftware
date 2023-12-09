package com.example.gps_g21.Adocoes;

import com.example.gps_g21.Modelos.Adopter;
import com.example.gps_g21.Modelos.Animal;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

public class NewAdoptionController {

    //FXML fields for animals
    @FXML
    private TableView tbAnimais;
    private ObservableList<Animal> dataAnimals;

    @FXML
    private TableColumn idColumnAnimais;
    @FXML
    private TableColumn nameColumnAnimais;

    @FXML
    private TableColumn typeColumnAnimais;
    @FXML
    private TableColumn genderColumnAnimais;

    //FXML fields for adopters

    @FXML
    private TableView tbAdopters;

    private ObservableList<Adopter> dataAdobters;

    @FXML
    private TableColumn idColumnAdopters;
    @FXML
    private TableColumn nameColumnAdopters;

    @FXML
    private TableColumn phoneNumberColumnAdopters;

    @FXML
    private TableColumn emailColumnAdopters;



    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    String queryAnimais = "select * from animals WHERE adopted == false";
    String queryAdopters = "select * from adopters";

    private Adopter adopter;
    private Animal animalToAdopt;


    private Stage stage;
    private Scene scene;
    public void initialize(){
        dataAnimals = FXCollections.observableArrayList();
        idColumnAnimais.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        nameColumnAnimais.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));

        typeColumnAnimais.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));
        genderColumnAnimais.setCellValueFactory(new PropertyValueFactory<Animal, String>("gender"));

        dataAdobters = FXCollections.observableArrayList();

        idColumnAdopters.setCellValueFactory(new PropertyValueFactory<Adopter, Integer>("id"));
        nameColumnAdopters.setCellValueFactory(new PropertyValueFactory<Adopter, String>("name"));
        phoneNumberColumnAdopters.setCellValueFactory(new PropertyValueFactory<Adopter, Integer>("phoneNumber"));
        emailColumnAdopters.setCellValueFactory(new PropertyValueFactory<Adopter, String>("email"));



        loadInfoAnimals();
        loadInfoAdopters();
    }


    public void loadInfoAnimals(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            dataAnimals.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(queryAnimais);
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
                animal.setKennelId(String.valueOf(resultSet.getInt("kennel_id")));
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

        tbAnimais.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null)
            {
                animalToAdopt = (Animal) tbAnimais.getSelectionModel().getSelectedItem();
                System.out.println(animalToAdopt.toString());
            }
        });


    }

    public void loadInfoAdopters(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try {
            dataAdobters.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(queryAdopters);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Adopter adopter = new Adopter(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("phone_number"),
                        resultSet.getString("email"),
                        resultSet.getString("birth_date"));
                /*animal.setId(resultSet.getInt("id"));
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

                 */
                dataAdobters.add(adopter);
            }
            tbAdopters.setItems(dataAdobters);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }

        tbAdopters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue != null)
            {
                adopter = (Adopter) tbAdopters.getSelectionModel().getSelectedItem();
                System.out.println(adopter.toString());
            }
        });
    }

    public void switchAdopt(ActionEvent actionEvent) {
        String addAdoptionSQL = "INSERT INTO adoption_regist(animal_id,adopter_id,adoption_date) VALUES(?,?,?)";
        String updateAnimalSQL = "UPDATE ANIMALS SET adopted = true, kennel_id = 0 where id = ?";
        int animalId = animalToAdopt.getId();
        int adopterId = adopter.getId();

        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");

        try(PreparedStatement pstm = connection.prepareStatement(addAdoptionSQL)) {
            pstm.setInt(1,animalId);
            pstm.setInt(2,adopterId);
            Calendar c = Calendar.getInstance();
            pstm.setString(3,c.getTime().toString());
           pstm.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(PreparedStatement pstm = connection.prepareStatement(updateAnimalSQL)) {
            pstm.setInt(1,animalId);

            pstm.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    loadInfoAnimals();

    }

    public void switchReturn(ActionEvent actionEvent) throws IOException {

        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (StarterController.userType == UserTypes.VULUNTIER) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-view.fxml")));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void switchNewAdopter(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/register-adopter-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
