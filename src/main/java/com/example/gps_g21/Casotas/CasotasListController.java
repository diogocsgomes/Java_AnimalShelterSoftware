package com.example.gps_g21.Casotas;

import com.example.gps_g21.Modelos.Casotas;
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

public class CasotasListController {
    @FXML
    private TableView tbCasota;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn maxColumn;
    @FXML
    private TableColumn numColumn;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    private ObservableList<Casotas> dataCasotas;

    @FXML
    private ComboBox typeCasota;
    @FXML
    private TextField searchBoxes;

    public boolean initialize() {

        ObservableList<String> options = FXCollections.observableArrayList("ID", "Máximo", "Todos");
        typeCasota.setItems(options);
        typeCasota.getSelectionModel().selectLast();

        List<Integer> idList = new ArrayList<>();


        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
            return false;
        }else{
            System.out.println("Db aberta no CasotasListController");
        }

        dataCasotas = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<Casotas, Integer>("id"));
        maxColumn.setCellValueFactory(new PropertyValueFactory<Casotas, Integer>("max"));
        numColumn.setCellValueFactory(new PropertyValueFactory<Casotas, Integer>("num"));
        TableColumn<Casotas, Void> colBtn = new TableColumn("Visualizar Dados");
        String query = "select * from kennel";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Casotas casotas = new Casotas();
                casotas.setId(resultSet.getInt("id"));
                casotas.setMax(resultSet.getInt("max"));

                String query2 = "select COUNT(*) AS total from animals where kennel_id = " + resultSet.getInt("id");
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                casotas.setNum(resultSet2.getInt("total"));
                dataCasotas.add(casotas);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbCasota.setItems(dataCasotas);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada no CasotasListController");
        }
        return true;
    }

    public void switcharAdd(ActionEvent event) throws IOException {
        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/casota-create.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
        }
    }

    public boolean switchVoltar(ActionEvent event) throws IOException {
        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/admin-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return true;
        }else{
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
            return false;
        }
    }

    private void addButtonToTable(List<Integer> idColumnName) {

        TableColumn<Casotas, Void> colBtn = new TableColumn("Ver Dados Casotas");

        Callback<TableColumn<Casotas, Void>, TableCell<Casotas, Void>> cellFactory = new Callback<TableColumn<Casotas, Void>, TableCell<Casotas, Void>>() {
            @Override
            public TableCell<Casotas, Void> call(final TableColumn<Casotas, Void> param) {
                final TableCell<Casotas, Void> cell = new TableCell<Casotas, Void>() {

                    Button btn = new Button("Visualizar");
                    Integer id = null;

                    {
                        btn.setOnAction((ActionEvent event) -> {

                            Parent root = null;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/casotas-info-view.fxml"));

                                loader.setControllerFactory(controllerClass -> {
                                    if (controllerClass == CasotasViewDataController.class) {
                                        return new CasotasViewDataController(id);
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
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
                            Casotas data = getTableView().getItems().get(getIndex());
                            id = idColumnName.get(getIndex()); // Obtenha o ID correspondente
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        if (isTableColumnExists(colBtn.getText(), tbCasota)) {
            System.out.println("A TableColumn existe na tabela.");
            //tbVoluntiers.getColumns();
        } else {
            System.out.println("A TableColumn não existe na tabela.");
            tbCasota.getColumns().add(colBtn);
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

    public void procurar(javafx.scene.input.MouseEvent mouseEvent) {
        String selectedType = typeCasota.getSelectionModel().getSelectedItem().toString();
        String searchName = searchBoxes.getText().trim();
        String query = null;
        List<Integer> idList = new ArrayList<>();

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
            return;
        } else {
            System.out.println("Db aberta no VoluntariosListController");
        }

       if (selectedType.equals("Todos") || (searchName.isEmpty() && selectedType.equals("ID")) || (searchName.isEmpty() && selectedType.equals("Máximo")) ) {
            query = "select * from kennel;";
        } else if (!searchName.isEmpty() && selectedType.equals("ID")) {
            query = "select * from kennel where id like '%" + searchName + "%'";
        } else if (!searchName.isEmpty() && selectedType.equals("Máximo")) {
            query = "select * from kennel where max like '%" + searchName + "%'";
        }


        try {
            dataCasotas.clear();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Casotas casotas = new Casotas();
                casotas.setId(resultSet.getInt("id"));
                casotas.setMax(resultSet.getInt("max"));

                String query2 = "select COUNT(*) AS total from animals where kennel_id = " + resultSet.getInt("id");
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                casotas.setNum(resultSet2.getInt("total"));
                dataCasotas.add(casotas);
                idList.add(resultSet.getInt("id"));
            }
            addButtonToTable(idList);
            tbCasota.setItems(dataCasotas);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }
}
