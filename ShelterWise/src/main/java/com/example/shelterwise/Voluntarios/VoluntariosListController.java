package com.example.shelterwise.Voluntarios;

import com.example.shelterwise.Modelos.Animal;
import com.example.shelterwise.Modelos.Users;
import com.example.shelterwise.Modelos.SqliteController;
import com.example.shelterwise.StarterController;
import com.example.shelterwise.Modelos.UserTypes;
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

public class VoluntariosListController {
    @FXML
    private TableView tbVoluntiers;
    @FXML
    private TextField searchVoluntier;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn birthColumn;
    @FXML
    private TableColumn phoneColumn;
    @FXML
    private TableColumn emailColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn nifColumn;
    @FXML
    private TableColumn activeColumn;

  /*  @FXML
    private VBox dynamicPaneContainer; // Referência ao VBox no FXML
    @FXML
    private Pane pnlInfoVol;*/

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private ObservableList<Users> dataVoluntiers;

    @FXML
    private ComboBox<String> typeVoluntier; //para depois

    public boolean initialize() {

        ObservableList<String> options = FXCollections.observableArrayList("Nome", "Telefone", "Ativos", "Todos");
        typeVoluntier.setItems(options);
        typeVoluntier.getSelectionModel().selectLast();

        List<Integer> idList = new ArrayList<>();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return false;
        } else {
            System.out.println("Db aberta no VoluntariosListController");
        }

        dataVoluntiers = FXCollections.observableArrayList();
        nameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("phone"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<Users, Boolean>("active"));
        TableColumn<Users, Void> colBtn = new TableColumn("Visualizar Dados");
        String query = "select * from users where role = 2";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users vol = new Users();
                vol.setName(resultSet.getString("nome"));
                vol.setPhone(resultSet.getInt("phone"));
                vol.setActive(resultSet.getBoolean("active"));
                dataVoluntiers.add(vol);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVoluntiers.setItems(dataVoluntiers);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada no VoluntariosListController");
        }
        return true;
    }

    private void addButtonToTable(List<Integer> idColumnName) {

        TableColumn<Users, Void> colBtn = new TableColumn("Visualizar Dados");

        Callback<TableColumn<Users, Void>, TableCell<Users, Void>> cellFactory = new Callback<TableColumn<Users, Void>, TableCell<Users, Void>>() {
            @Override
            public TableCell<Users, Void> call(final TableColumn<Users, Void> param) {
                final TableCell<Users, Void> cell = new TableCell<Users, Void>() {

                    Button btn = new Button("Visualizar");
                    Integer id = null;

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Parent root = null;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/shelterwise/voluntarios-info-view.fxml"));

                                loader.setControllerFactory(controllerClass -> {
                                    if (controllerClass == VoluntariosViewDataController.class) {
                                        return new VoluntariosViewDataController(id);
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
                            Users data = getTableView().getItems().get(getIndex());
                            id = idColumnName.get(getIndex()); // Obtenha o ID correspondente
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        if (isTableColumnExists(colBtn.getText(), tbVoluntiers)) {
            System.out.println("A TableColumn existe na tabela.");
            //tbVoluntiers.getColumns();
        } else {
            System.out.println("A TableColumn não existe na tabela.");
            tbVoluntiers.getColumns().add(colBtn);
        }

    }

    public boolean isTableColumnExists(String columnName, javafx.scene.control.TableView<?> tableView) {
        for (TableColumn<?, ?> column : tableView.getColumns()) {
            if (column.getText().equals(columnName)) {
                return true;
            }
        }
        return false;
    }

    public boolean switchVoltar(ActionEvent event) throws IOException {

        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/admin-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return true;
        } else {
            System.out.println("A OPERAÇÃO SAIR DA LISTA DE VOLUNTARIOS APENAS DEVE SER EFETUADA POR UM ADMIN");
            return false;
        }


    }


    public void switcharAdd(ActionEvent event) throws IOException {

        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/voluntarios-create.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("A OPERAÇÃO ADICIONAR VOLUNTÁRIO APENAS DEVE SER EFETUADA POR UM ADMIN");
        }

    }

    public void procurar(MouseEvent mouseEvent) {
        String selectedType = typeVoluntier.getSelectionModel().getSelectedItem().toString();
        String searchName = searchVoluntier.getText().trim();
        String query = null;
        List<Integer> idList = new ArrayList<>();
        idList.clear();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return;
        } else {
            System.out.println("Db aberta no VoluntariosListController");
        }

        if (selectedType.equals("Ativos")) {
            query = "select * from users where role = 2 and active = 1";
        } else if (selectedType.equals("Todos")) {
            query = "select * from users where role = 2";
        } else if (!searchName.isEmpty() && selectedType.equals("Nome")) {
            query = "select * from users where nome like '%" + searchName + "%' and role = 2";
        } else if (!searchName.isEmpty() && selectedType.equals("Telefone")) {
            query = "select * from users where phone like '%" + searchName + "%' and role = 2";
        }


            try {
            dataVoluntiers.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setActive(resultSet.getBoolean("active"));
                user.setName(resultSet.getString("nome"));
                user.setPhone(resultSet.getInt("phone"));
                user.setEmail(resultSet.getString("email"));
                dataVoluntiers.add(user);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbVoluntiers.setItems(dataVoluntiers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
}


    // Caso tentamos depois fazer com os panes em vez de tabelas
    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String query = "select * from users where role = 2";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                criarPaneVol(resultSet);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }
    private void criarPaneVol(ResultSet resultSet) throws SQLException {
        Pane pane = new Pane();
        pane.setStyle(pnlInfoVol.getStyle());

        ((Text) pane.lookup("#nomeText")).setText(resultSet.getString("nome"));
        ((Text) pane.lookup("#phoneText")).setText(resultSet.getString("phone"));

        dynamicPaneContainer.getChildren().add(pane);
    }*/

