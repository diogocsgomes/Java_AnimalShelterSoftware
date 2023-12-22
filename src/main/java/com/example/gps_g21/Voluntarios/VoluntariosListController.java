package com.example.gps_g21.Voluntarios;

import com.example.gps_g21.Animais.AnimaisInfoController;
import com.example.gps_g21.Animais.AnimaisListController;
import com.example.gps_g21.Modelos.Animal;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.ls.LSOutput;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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
    @FXML
    private TableColumn idColumn;

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
    String query = "select * from users where role = 2";

    public void initialize() {

        ObservableList<String> options = FXCollections.observableArrayList("Nome", "Telefone", "Ativos", "Todos");
        typeVoluntier.setItems(options);
        typeVoluntier.getSelectionModel().selectLast();

        dataVoluntiers = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Users, String>("nome"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Users, Integer>("phone"));
        activeColumn.setCellValueFactory(new PropertyValueFactory<Users, Boolean>("active"));
        TableColumn<Users, Boolean> colBtn = new TableColumn("Visualizar Dados");

        colBtn.setCellFactory(new Callback<TableColumn<Users, Boolean>, TableCell<Users, Boolean>>() {
            @Override
            public TableCell<Users, Boolean> call(TableColumn<Users, Boolean> p) {
                return new EditButton();
            }
        });
        tbVoluntiers.getColumns().add(colBtn);
        loadInfo();

    }

    public void loadInfo(){

        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        System.out.println("Connection successful");
        String selectedType = typeVoluntier.getSelectionModel().getSelectedItem().toString();
        String searchName = searchVoluntier.getText().trim();
        //System.out.println("Selected Type: " + selectedType + " Search Name: " + searchName);

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

            while (resultSet.next()){
                Users vol = new Users();
                vol.setId(resultSet.getInt("id"));
                vol.setName(resultSet.getString("nome"));
                vol.setPhone(resultSet.getInt("phone"));
                if(resultSet.getBoolean("active"))
                    vol.setActive("Sim");
                else
                    vol.setActive("Não");
                //vol.setActive(resultSet.getBoolean("active"));
                dataVoluntiers.add(vol);

            }
            tbVoluntiers.setItems(dataVoluntiers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }

    public class EditButton extends TableCell<Users, Boolean> {

        final Button colBtn = new Button("Ver Dados");
        EditButton() {
            colBtn.setOnAction(event -> {
                Users selectedUserId = (Users) getTableView().getItems().get(getIndex());
                if (selectedUserId != null) {

                    Parent root = null;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/voluntarios-info-view.fxml"));

                        loader.setControllerFactory(controllerClass -> {
                            if (controllerClass == VoluntariosViewDataController.class) {
                                return new VoluntariosViewDataController(selectedUserId.getId());
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
                    System.out.println("Não existe nenhum voluntário selecionado");
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
            System.out.println("A OPERAÇÃO SAIR DA LISTA DE VOLUNTARIOS APENAS DEVE SER EFETUADA POR UM ADMIN");
            return false;
        }
    }


    public void switcharAdd(ActionEvent event) throws IOException {

        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-create.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("A OPERAÇÃO ADICIONAR VOLUNTÁRIO APENAS DEVE SER EFETUADA POR UM ADMIN");
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

