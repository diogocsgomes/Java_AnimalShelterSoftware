package com.example.gps_g21.Voluntarios;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


public class VoluntariosViewDataController {
    @FXML
    private Text morada;
    @FXML
    private Text telefone;
    @FXML
    private Text email_;
    @FXML
    private Text data_nas;
    @FXML
    private Text name;
    @FXML
    private Text id_;
    @FXML
    private Circle activo;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private List<TextField> textFields;

    private int id;

    public VoluntariosViewDataController(int id) {
        this.id = id;
    }

    public void initialize() throws SQLException {

        try{
            connection = sqliteController.createDBConnection();

            if(connection == null){
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String query = "select * from users where id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            name.setText("Nome: "+resultSet.getString("nome"));
            morada.setText("Morada: "+resultSet.getString("address"));
            telefone.setText("Telefone: "+resultSet.getInt("phone"));
            data_nas.setText("Data de nascimento: "+resultSet.getString("date_birth"));
            //name.setText("Nome: "+resultSet.getString("nome"));
            email_.setText("Email: "+resultSet.getString("email"));
            id_.setText("NIF: "+resultSet.getInt("nif"));

            if(resultSet.getBoolean("active"))
                activo.setFill(Color.GREEN);
            else
                activo.setFill(Color.RED);
        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }

    public void editData(ActionEvent event) {

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/voluntarios-info-edit.fxml"));

            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == VoluntariosInfoEditController.class) {
                    return new VoluntariosInfoEditController(id);
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

    }

    public void Back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/voluntarios-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void viewUser(int id) {
        this.id=id;
    }
}
