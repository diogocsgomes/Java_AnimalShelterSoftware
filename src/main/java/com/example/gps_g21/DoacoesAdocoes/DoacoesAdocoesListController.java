package com.example.gps_g21.DoacoesAdocoes;

import com.example.gps_g21.Adocoes.NewAdoptionController;
import com.example.gps_g21.Animais.AnimaisInfoController;
import com.example.gps_g21.Modelos.DoacoesAdocoes;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.StarterController;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DoacoesAdocoesListController {
    @FXML
    private ComboBox typeCategory;
    @FXML
    private TextField searchAdotanteDoador;
    @FXML
    private TableView tbDoacoesAdocoes;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameAnimalColumn;
    @FXML
    private TableColumn nameAdopterColumn;
    @FXML
    private TableColumn adoptionDateColumn;
    @FXML
    private TableColumn donorNameColumn;
    @FXML
    private TableColumn donorPhoneColumn;
    @FXML
    private TableColumn donationDescriptionColumn;

    private Stage stage;
    private Scene scene;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    String adoptionQuery, donationsQuery;
    private ObservableList<DoacoesAdocoes> dataDoacoesAdocoes;
    List<String> SearchType = Arrays.asList("Todos", "Doacoes", "Adocoes");

    private DoacoesAdocoes selectedDoacoesAdocoes;

    public void initialize(){
        typeCategory.setItems(FXCollections.observableArrayList(SearchType));
        typeCategory.getSelectionModel().selectFirst();

        dataDoacoesAdocoes = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, Integer>("id"));
        nameAnimalColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("nameAnimal"));
        nameAdopterColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("nameAdopter"));
        adoptionDateColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, Date>("adoptionDate"));
        donorNameColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("donorName"));
        donorPhoneColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("donorPhone"));
        donationDescriptionColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("donationDescription"));
        /*TableColumn<DoacoesAdocoes, Boolean> colBtn = new TableColumn<>("Editar");
        colBtn.setMinWidth(63);
        colBtn.setCellFactory(new Callback<TableColumn<DoacoesAdocoes, Boolean>, TableCell<DoacoesAdocoes, Boolean>>() {
            @Override
            public TableCell<DoacoesAdocoes, Boolean> call(TableColumn<DoacoesAdocoes, Boolean> p) {
                return new EditButton();
            }
        });
        tbDoacoesAdocoes.getColumns().add(colBtn);*/
        loadInfoDoacoesAdocoes();
    }

    public void editar(ActionEvent actionEvent) {
        if(selectedDoacoesAdocoes == null)
            return;
        if(selectedDoacoesAdocoes.nameAnimal != null){
            //ir para campo de adocao animal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/edit-adoption-view.fxml"));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //AnimaisInfoController infoController = loader.getController();
            NewAdoptionController infoController = loader.getController();
            infoController.idAdocao = selectedDoacoesAdocoes.getId();
            //infoController.editAnimal(selectedDoacoesAdocoes.getId());
            System.out.println("ID selecionado"+selectedDoacoesAdocoes.getId());
            stage.setTitle("ShelterWise");
            stage.setScene(scene);
            stage.show();


        }
        if(selectedDoacoesAdocoes.nameAdopter != null){
            //ir para campo de editar adotante
        }
    }

    public class EditButton extends TableCell<DoacoesAdocoes, Boolean> {
        final Button colBtn = new Button("Editar");
        EditButton() {
            colBtn.setOnAction(event -> {
                DoacoesAdocoes selectedDoacoesAdocoes = (DoacoesAdocoes) getTableView().getItems().get(getIndex());
                System.out.println("yaaa" + selectedDoacoesAdocoes.donorName + "  " + selectedDoacoesAdocoes.nameAnimal);
                if (selectedDoacoesAdocoes != null) {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-info-view.fxml"));
//                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    try {
//                        scene = new Scene(loader.load());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    AnimaisInfoController infoController = loader.getController();
//                    infoController.editAnimal(selectedDoacoesAdocoes.getId());
//                    stage.setTitle("Editar Animal");
//                    stage.setScene(scene);
//                    stage.show();
                } else {
                    System.out.println("No Doacoes or Adocoes selected");
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

    public void loadInfoDoacoesAdocoes() {
        int don = 0, ado = 0;
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String selectedType = typeCategory.getSelectionModel().getSelectedItem().toString();
        String searchName = searchAdotanteDoador.getText().trim();
        System.out.println("Selected Type: " + selectedType + " Search Name: " + searchName);

        if(searchName.isEmpty() && selectedType.equals("Todos")){
            adoptionQuery = "SELECT adoption_regist.id, " +
                    "animals.name AS animal_name, " +
                    "adopters.name AS adopter_name, " +
                    "adoption_regist.adoption_date " +
                    "FROM adoption_regist " +
                    "JOIN animals ON adoption_regist.animal_id = animals.id " +
                    "JOIN adopters ON adoption_regist.adopter_id = adopters.id";
            donationsQuery = "SELECT * FROM donations";
        }
        else if(!searchName.isEmpty() && selectedType.equals("Todos")){
            adoptionQuery = "SELECT adoption_regist.id, " +
                    "animals.name AS animal_name, " +
                    "adopters.name AS adopter_name, " +
                    "adoption_regist.adoption_date " +
                    "FROM adoption_regist " +
                    "JOIN animals ON adoption_regist.animal_id = animals.id " +
                    "JOIN adopters ON adoption_regist.adopter_id = adopters.id " +
                    "WHERE adopters.name LIKE '%" + searchName + "%'";
            donationsQuery = "SELECT * FROM donations WHERE name LIKE '%" + searchName + "%'";
        }
        else if(!searchName.isEmpty() && selectedType.equals("Doacoes")){
            adoptionQuery = "SELECT adoption_regist.id, " +
                    "animals.name AS animal_name, " +
                    "adopters.name AS adopter_name, " +
                    "adoption_regist.adoption_date " +
                    "FROM adoption_regist " +
                    "JOIN animals ON adoption_regist.animal_id = animals.id " +
                    "JOIN adopters ON adoption_regist.adopter_id = adopters.id " +
                    "WHERE adopters.name LIKE '%" + searchName + "%'";
        }
        else if(!searchName.isEmpty() && selectedType.equals("Adocoes")){
            donationsQuery = "SELECT * FROM donations WHERE name LIKE '%" + searchName + "%'";
        }
        else if(searchName.isEmpty() && selectedType.equals("Doacoes")){
            adoptionQuery = "SELECT adoption_regist.id, " +
                    "animals.name AS animal_name, " +
                    "adopters.name AS adopter_name, " +
                    "adoption_regist.adoption_date " +
                    "FROM adoption_regist " +
                    "JOIN animals ON adoption_regist.animal_id = animals.id " +
                    "JOIN adopters ON adoption_regist.adopter_id = adopters.id";
        }
        else if(searchName.isEmpty() && selectedType.equals("Adocoes")){
            donationsQuery = "SELECT * FROM donations";
        }

        if (selectedType.equals("Todos")) {
            don = 1;
            ado = 1;
        } else if (selectedType.equals("Doacoes")) {
            don = 1;
            ado = 0;
        } else {
            don = 0;
            ado = 1;
        }

        try {
            dataDoacoesAdocoes.clear();

            if(ado == 1){
                nameAnimalColumn.setVisible(true);
                nameAdopterColumn.setVisible(true);
                adoptionDateColumn.setVisible(true);
                if(don == 0){
                    donorNameColumn.setVisible(false);
                    donorPhoneColumn.setVisible(false);
                    donationDescriptionColumn.setVisible(false);
                }
                PreparedStatement adoptionStatement = connection.prepareStatement(adoptionQuery);
                ResultSet adoptionResultSet = adoptionStatement.executeQuery();

                while (adoptionResultSet.next()) {
                    DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                    doacoesadocoes.setId(adoptionResultSet.getInt("id"));
                    doacoesadocoes.setNameAnimal(adoptionResultSet.getString("animal_name"));
                    doacoesadocoes.setNameAdopter(adoptionResultSet.getString("adopter_name"));
                    doacoesadocoes.setAdoptionDate(adoptionResultSet.getString("adoption_date"));

                    dataDoacoesAdocoes.add(doacoesadocoes);
                }
            }

            if(don == 1){
                donorNameColumn.setVisible(true);
                donorPhoneColumn.setVisible(true);
                donationDescriptionColumn.setVisible(true);
                if(ado == 0){
                    nameAnimalColumn.setVisible(false);
                    nameAdopterColumn.setVisible(false);
                    adoptionDateColumn.setVisible(false);
                }
                PreparedStatement donationsStatement = connection.prepareStatement(donationsQuery);
                ResultSet donationsResultSet = donationsStatement.executeQuery();

                while (donationsResultSet.next()) {
                    DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                    doacoesadocoes.setId(donationsResultSet.getInt("id"));
                    doacoesadocoes.setDonorName(donationsResultSet.getString("name"));
                    doacoesadocoes.setDonorPhone(donationsResultSet.getString("phone_number"));
                    doacoesadocoes.setDonationDescription(donationsResultSet.getString("description"));

                    dataDoacoesAdocoes.add(doacoesadocoes);
                }
            }
            // Atualizar a TableView
            tbDoacoesAdocoes.setItems(dataDoacoesAdocoes);

            tbDoacoesAdocoes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
                selectedDoacoesAdocoes = (DoacoesAdocoes) newValue;
                System.out.println("Selected Doacoes or Adocoes: " + selectedDoacoesAdocoes.donorName + "  " + selectedDoacoesAdocoes.nameAnimal);
            });

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
        }
        else{
            System.out.println("User type not recognized");
        }
    }

    @FXML
    void novaDoacao(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/dialogbox-doacoesAdocoes-info-view.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
        loadInfoDoacoesAdocoes();
    }
}
