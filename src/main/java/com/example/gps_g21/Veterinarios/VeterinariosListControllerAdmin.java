package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.Modelos.Users;
import com.example.gps_g21.Modelos.Vets;
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

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Vets> dataVets;

    @FXML
    private ComboBox<String> typeVets;

    public void initialize() {

        ObservableList<String> options = FXCollections.observableArrayList("Nome", "Telefone", "Todos");
        typeVets.setItems(options);
        typeVets.getSelectionModel().selectLast();

        List<Integer> idList = new ArrayList<>();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return;
        }

        dataVets = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<Users, Void> colBtn = new TableColumn("Visualizar Dados");

        String query = "select * from vets";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vets vet = new Vets();
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                dataVets.add(vet);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVets.setItems(dataVets);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        } finally {
            sqliteController.closeDBConnection(connection);
            //System.out.println("Db fechada");
        }

    }

    private void addButtonToTable(List<Integer> idColumnName) {
        TableColumn<Vets, Void> colBtn = new TableColumn<>("Visualizar Dados");

        Callback<TableColumn<Vets, Void>, TableCell<Vets, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Vets, Void> call(final TableColumn<Vets, Void> param) {
                final TableCell<Vets, Void> cell = new TableCell<>() {

                    Button btn = new Button("Visualizar");
                    Integer id = null;

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Parent root = null;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/veterinari-info-view.fxml"));
                                loader.setControllerFactory(controllerClass -> {
                                    if (controllerClass == VetsViewDataController.class) {
                                        return new VetsViewDataController(id);
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
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            id = idColumnName.get(getIndex());
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        if (!isTableColumnExists(colBtn.getText(), tbVets))
            tbVets.getColumns().add(colBtn);

    }

    public boolean isTableColumnExists(String columnName, TableView<?> tableView) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            if (column.getText().equals(columnName)) {
                return true;
            }
        }
        return false;
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
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
            return false;
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


    public void procurar(MouseEvent mouseEvent) { // todo after

        /*String selectedType = typeVets.getSelectionModel().getSelectedItem().toString();
        String searchName = searchVet.getText().trim();
        String query = null;
        List<Integer> idList = new ArrayList<>();
        idList.clear();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return;
        } else {
            System.out.println("Db aberta no VeterinariansListController");
        }

        if (selectedType.equals("Todos")) {
            query = "select * from vets";
        } else if (!searchName.isEmpty() && selectedType.equals("Nome")) {
            query = "select * from vets where name like '%" + searchName + "%'";
        } else if (!searchName.isEmpty() && selectedType.equals("Telefone")) {
            query = "select * from vets where phone like '%" + searchName + "%'";
        }

        try {
            dataVets.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Vets vet = new Vets();
                vet.setId(resultSet.getInt("id"));
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                vet.setAddress(resultSet.getString("address"));
                dataVets.add(vet);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVets.setItems(dataVets);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }*/
    }


}

