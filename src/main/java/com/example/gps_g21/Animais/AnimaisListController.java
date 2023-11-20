package com.example.gps_g21.Animais;

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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AnimaisListController {
    @FXML
    private ComboBox typeCategory;
    @FXML
    private TextField searchAnimal;
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
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    String query = "select * from animals";
    private ObservableList<Animal> dataAnimals;
    List<String> SearchType = Arrays.asList("All", "Dog", "Cat", "Other", "Adopted", "Adoptable");

    public void initialize(){
        typeCategory.setItems(FXCollections.observableArrayList(SearchType));
        typeCategory.getSelectionModel().selectFirst();

        dataAnimals = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("name"));
        //weightColumn.setCellValueFactory(new PropertyValueFactory<Animal, Double>("weight"));
        //breedColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("breed"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("type"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("gender"));
        //furColorColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("furColor"));
        //furTypeColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("furType"));
        //birthDateColumn.setCellValueFactory(new PropertyValueFactory<Animal, Date>("birthDate"));
        kennelIdColumn.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("kennelId"));
        commentsColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("comments"));
        TableColumn<Animal, Boolean> colBtn = new TableColumn<>("Editar");
        colBtn.setMinWidth(63);
        colBtn.setCellFactory(new Callback<TableColumn<Animal, Boolean>, TableCell<Animal, Boolean>>() {
            @Override
            public TableCell<Animal, Boolean> call(TableColumn<Animal, Boolean> p) {
                return new EditButton();
            }
        });
        tbAnimais.getColumns().add(colBtn);
        loadInfoAnimals();
    }
    public class EditButton extends TableCell<Animal, Boolean> {
        final Button colBtn = new Button("Editar");
        EditButton() {
            colBtn.setOnAction(event -> {
                Animal selectedAnimalId = (Animal) getTableView().getItems().get(getIndex());
                if (selectedAnimalId != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-info-view.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    try {
                        scene = new Scene(loader.load());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    AnimaisInfoController infoController = loader.getController();
                    infoController.editAnimal(selectedAnimalId.getId());
                    stage.setTitle("Editar Animal");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("No animal selected");
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(colBtn);
            } else {
                setGraphic(null);
            }
        }
    }

    public void loadInfoAnimals(){
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String selectedType = typeCategory.getSelectionModel().getSelectedItem().toString();
        String searchName = searchAnimal.getText().trim();
        System.out.println("Selected Type: " + selectedType + " Search Name: " + searchName);

        if(!searchName.isEmpty() && (selectedType.equals("Dog") || selectedType.equals("Cat") || selectedType.equals("Other"))){
            query = "select * from animals where name like '%" + searchName + "%' and type = '" + selectedType + "'";
        } else if(!searchName.isEmpty() && selectedType.equals("Adopted")){
            query = "select * from animals where name like '%" + searchName + "%' and adopted = 1";
        } else if(!searchName.isEmpty() && selectedType.equals("Adoptable")){
            query = "select * from animals where name like '%" + searchName + "%' and adopted = 0";
        } else if(!searchName.isEmpty()){
            query = "select * from animals where name like '%" + searchName + "%'";
        } else if(selectedType.equals("All")){
            query = "select * from animals";
        } else if(selectedType.equals("Dog") || selectedType.equals("Cat")){
            query = "select * from animals where type = '" + selectedType + "'";
        }
        else if(selectedType.equals("Other")){
            query = "select * from animals where type != 'Dog' and type != 'Cat'";
        }
        else if(selectedType.equals("Adopted")){
            query = "select * from animals where adopted = 1";
        }
        else if(selectedType.equals("Adoptable")){
            query = "select * from animals where adopted = 0";
        }
        try {
            dataAnimals.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Animal animal = new Animal();
                animal.setId(resultSet.getInt("id"));
//                animal.setBirthDate(resultSet.getString("birth_date"));
//                animal.setBreed(resultSet.getString("breed"));
                animal.setComments(resultSet.getString("comments"));
//                animal.setFurColor(resultSet.getString("fur_color"));
//                animal.setFurType(resultSet.getString("fur_type"));
                animal.setGender(resultSet.getString("gender"));
                animal.setKennelId(resultSet.getInt("kennel_id"));
                animal.setName(resultSet.getString("name"));
                animal.setType(resultSet.getString("type"));
//                animal.setWeight(resultSet.getDouble("weight"));
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (StarterController.userType == UserTypes.VULUNTIER) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (StarterController.userType == UserTypes.VET) {
            //ainda nao existe uma vista exclusiva para veterinarios
           System.out.println("FUNCIONALIDADE POR IMPLEMENTAR");
        }
    }
    public void switchCriarAnimal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-info-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        AnimaisInfoController infoController = loader.getController();
        infoController.createAnimal();
        stage.setTitle("Criar Animal");
        stage.setScene(scene);
        stage.show();
    }
}
