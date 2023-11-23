package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.Users;
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

public class VeterinariansListController {

    @FXML
    private TableView<Users> tbVeterinarians;
    @FXML
    private TextField searchVeterinarian;
    @FXML
    private TableColumn<Users, String> nameColumn;
    @FXML
    private TableColumn<Users, Integer> phoneColumn;
    @FXML
    private TableColumn<Users, String> emailColumn;
    @FXML
    private TableColumn<Users, String> addressColumn;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Users> dataVeterinarians;

    @FXML
    private ComboBox<String> typeVeterinarian;

    public boolean initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Nome", "Telefone", "Todos");
        typeVeterinarian.setItems(options);
        typeVeterinarian.getSelectionModel().selectLast();

        List<Integer> idList = new ArrayList<>();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return false;
        } else {
            System.out.println("Db aberta no VeterinariansListController");
        }

        dataVeterinarians = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        String query = "select * from vets";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users vet = new Users();
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                vet.setAddress(resultSet.getString("address"));
                dataVeterinarians.add(vet);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVeterinarians.setItems(dataVeterinarians);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada no VeterinariansListController");
        }
        return true;
    }

    private void addButtonToTable(List<Integer> idColumnName) {
        TableColumn<Users, Void> colBtn = new TableColumn<>("Visualizar Dados");

        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Users, Void> call(final TableColumn<Users, Void> param) {
                final TableCell<Users, Void> cell = new TableCell<>() {

                    Button btn = new Button("Visualizar");
                    Integer id = null;

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Parent root = null;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/veterinarians-info-view.fxml"));
                                loader.setControllerFactory(controllerClass -> {
                                    if (controllerClass == VeterinariansViewDataController.class) {
                                        return new VeterinariansViewDataController(id);
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
        if (isTableColumnExists(colBtn.getText(), tbVeterinarians)) {
            System.out.println("A TableColumn existe na tabela.");
        } else {
            System.out.println("A TableColumn não existe na tabela.");
            tbVeterinarians.getColumns().add(colBtn);
        }
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
            System.out.println("A OPERAÇÃO SAIR DA LISTA DE VETERINARIOS APENAS DEVE SER EFETUADA POR UM ADMIN");
            return false;
        }
    }
/*
    public void switcharAdd(ActionEvent event) throws IOException {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarians-create.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("A OPERAÇÃO ADICIONAR VETERINÁRIO APENAS DEVE SER EFETUADA POR UM ADMIN");
        }
    }
*/
    public void procurar(MouseEvent mouseEvent) {
        String selectedType = typeVeterinarian.getSelectionModel().getSelectedItem().toString();
        String searchName = searchVeterinarian.getText().trim();
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
            dataVeterinarians.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users vet = new Users();
                vet.setId(resultSet.getInt("id"));
                vet.setName(resultSet.getString("name"));
                vet.setPhone(resultSet.getInt("phone"));
                vet.setEmail(resultSet.getString("email"));
                vet.setAddress(resultSet.getString("address"));
                dataVeterinarians.add(vet);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVeterinarians.setItems(dataVeterinarians);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
}

