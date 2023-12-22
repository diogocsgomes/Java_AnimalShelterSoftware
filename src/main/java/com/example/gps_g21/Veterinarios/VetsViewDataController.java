package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.UserTypes;
import com.example.gps_g21.StarterController;
import com.example.gps_g21.Voluntarios.VoluntariosInfoEditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class VetsViewDataController {

    @FXML
    public Text nome;
    @FXML
    public Text morada;
    @FXML
    public Text telefone;
    @FXML
    public Text email;
    @FXML
    public TextArea horarios;
    @FXML
    public Button btnGuardar;

    int id;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public VetsViewDataController(Integer id) {this.id=id;}

    public void initialize(){

        try {
            connection = sqliteController.createDBConnection();
            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String sql = "Select * from vets where id ="+id+";";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            nome.setText("Nome: "+resultSet.getString("name"));
            morada.setText("Morada: "+resultSet.getString("address"));
            telefone.setText("Telefone: "+resultSet.getInt("phone"));
            email.setText("Email: "+resultSet.getString("email"));


            /*
            horarios.setText("NIF: "+resultSet.getInt("nif"));

             */

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }finally {
            sqliteController.closeDBConnection(connection);
        }
        if (StarterController.userType != UserTypes.ADMIN) {
            btnGuardar.setVisible(false);
        }
    }

    public void Editar(ActionEvent actionEvent) {
        if (StarterController.userType == UserTypes.ADMIN) {
            Parent root = null;
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/veterinarios-info-edit.fxml"));

                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == VetsInfoEditController.class) {
                        return new VetsInfoEditController(id);
                    } else {
                        try {
                            return controllerClass.newInstance();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                root = loader.load();

            } catch(IOException e){
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            System.out.println("A OPERAÇÃO DEVE SER EFETUADA POR UM ADMIN");
        }

    }

    public void Voltar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
