package com.example.gps_g21.DoacoesAdocoes;

import com.example.gps_g21.Animais.AnimaisInfoController;
import com.example.gps_g21.Modelos.Animal;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.ByteArrayInputStream;
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
    private TableView tbDoacoesAdocoes;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameAnimalColumn;
    @FXML
    private TableColumn nameAdopterColumn;
    @FXML
    private TableColumn adoptionDateColumn;

    private Stage stage;
    private Scene scene;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    String query = "select * from adoption_regist";
    private ObservableList<DoacoesAdocoes> dataDoacoesAdocoes;
    List<String> SearchType = Arrays.asList("All", "Doacoes", "Adocoes");

    public void initialize(){
        typeCategory.setItems(FXCollections.observableArrayList(SearchType));
        typeCategory.getSelectionModel().selectFirst();

        dataDoacoesAdocoes = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, Integer>("id"));
        nameAnimalColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("nameAnimal"));
        nameAdopterColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, String>("nameAdopter"));
        adoptionDateColumn.setCellValueFactory(new PropertyValueFactory<DoacoesAdocoes, Date>("adoptionDate"));
        TableColumn<DoacoesAdocoes, Boolean> colBtn = new TableColumn<>("Editar");
        colBtn.setMinWidth(63);
        colBtn.setCellFactory(new Callback<TableColumn<DoacoesAdocoes, Boolean>, TableCell<DoacoesAdocoes, Boolean>>() {
            @Override
            public TableCell<DoacoesAdocoes, Boolean> call(TableColumn<DoacoesAdocoes, Boolean> p) {
                return new EditButton();
            }
        });
        tbDoacoesAdocoes.getColumns().add(colBtn);
        loadInfoDoacoesAdocoes();
    }
    public class EditButton extends TableCell<DoacoesAdocoes, Boolean> {
        final Button colBtn = new Button("Editar");
        EditButton() {
            colBtn.setOnAction(event -> {
                DoacoesAdocoes selectedDoacoesAdocoes = (DoacoesAdocoes) getTableView().getItems().get(getIndex());
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
        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String selectedType = typeCategory.getSelectionModel().getSelectedItem().toString();

        String joinQuery = "select adoption_regist.id, animals.name as animal_name, " +
                "adopters.name as adopter_name, adoption_regist.adoption_date " +
                "from adoption_regist " +
                "join animals on adoption_regist.animal_id = animals.id " +
                "join adopters on adoption_regist.adopter_id = adopters.id";

        if (selectedType.equals("All")) {
            query = joinQuery;
        } else if (selectedType.equals("Doacoes")) {
            query = joinQuery; // + " where is_donation = 1"; Completar com o nome da coluna
        } else {
            query = joinQuery; // + " where is_donation = 0"; Completar com o nome da coluna
        }

        try {
            dataDoacoesAdocoes.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                DoacoesAdocoes doacoesadocoes = new DoacoesAdocoes();
                doacoesadocoes.setId(resultSet.getInt("id"));
                doacoesadocoes.setNameAnimal(resultSet.getString("animal_name"));
                doacoesadocoes.setNameAdopter(resultSet.getString("adopter_name"));
                doacoesadocoes.setAdoptionDate(resultSet.getString("adoption_date"));
                dataDoacoesAdocoes.add(doacoesadocoes);
            }

            tbDoacoesAdocoes.setItems(dataDoacoesAdocoes);
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
}
