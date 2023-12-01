package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.Modelos.Users;
import com.example.gps_g21.Modelos.Vets;
import com.example.gps_g21.StarterController;
import com.example.gps_g21.Voluntarios.VoluntariosListController;
import com.example.gps_g21.Voluntarios.VoluntariosViewDataController;
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
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VeterinariosListControllerAdmin {

    @FXML
    private TableView<Vets> tbVets;
    @FXML
    private TextField searchVet;
    @FXML
    private TableColumn<Vets, String> nameColumn;
    @FXML
    private TableColumn<Vets, Integer> phoneColumn;
    @FXML
    private TableColumn<Vets, String> emailColumn;
    @FXML
    private TableColumn<Vets, String> addressColumn;
    @FXML
    private TableColumn idColumn;
    @FXML
    private Button btnCriar;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Vets> dataVets;

    @FXML
    private ComboBox<String> typeVets;
    String query = "select * from vets";

    public void initialize() {

        ObservableList<String> options = FXCollections.observableArrayList("Nome", "Telefone", "Todos");
        typeVets.setItems(options);
        typeVets.getSelectionModel().selectLast();

        dataVets = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Vets, Boolean> colBtn = new TableColumn("Visualizar Dados");

        colBtn.setCellFactory(new Callback<TableColumn<Vets, Boolean>, TableCell<Vets, Boolean>>() {
            @Override
            public TableCell<Vets, Boolean> call(TableColumn<Vets, Boolean> p) {
                return new EditButton();
            }
        });

        tbVets.getColumns().add(colBtn);
        loadInfo();
        if (StarterController.userType != UserTypes.ADMIN) {
            btnCriar.setVisible(false);
        }

    }

    public class EditButton extends TableCell<Vets, Boolean> {

        final Button colBtn = new Button("Ver Dados");
        EditButton() {
            colBtn.setOnAction(event -> {
                Vets selectedVetId = (Vets) getTableView().getItems().get(getIndex());
                if (selectedVetId != null) {

                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/veterinarios-info-view.fxml"));

                        loader.setControllerFactory(controllerClass -> {
                            if (controllerClass == VetsViewDataController.class) {
                                return new VetsViewDataController(selectedVetId.getId());
                            } else {
                                try {
                                    return controllerClass.newInstance();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    preScene = stage.getScene();

                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Não existe nenhum veterinário selecionado");
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


    public void loadInfo(){

        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String selectedType = typeVets.getSelectionModel().getSelectedItem().toString();
        String searchName = searchVet.getText().trim();
        //System.out.println("Selected Type: " + selectedType + " Search Name: " + searchName);

        if (selectedType.equals("Todos")) {
            query = "select * from vets;";
        } else if (!searchName.isEmpty() && selectedType.equals("Nome")) {
            query = "select * from vets where name like '%" + searchName + "%';";
        } else if (!searchName.isEmpty() && selectedType.equals("Telefone")) {
            query = "select * from vets where phone like '%" + searchName + "%';";
        }
        try {
            dataVets.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Vets vet = new Vets();
                vet.setId(resultSet.getInt("id"));
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                dataVets.add(vet);

            }
            tbVets.setItems(dataVets);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }

    public boolean switchVoltar(ActionEvent event) throws IOException {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return true;
        } else {
            if(StarterController.userType == UserTypes.VULUNTIER){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-view.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                preScene = stage.getScene();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                return true;
            }else{
                System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN/Voluntario");
                return false;
            }
        }
    }

    public void Add(ActionEvent event) throws IOException {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-create.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
        }
    }

}

