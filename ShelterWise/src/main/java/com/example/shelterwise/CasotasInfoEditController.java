package com.example.shelterwise;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.shelterwise.CasotaAddController.isNumeric;

public class CasotasInfoEditController {
    @FXML
    private Text id_;
    @FXML
    private TextField max;
    @FXML
    private TextField descricao;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private int id;

    public CasotasInfoEditController(int id) {
        this.id=id;
    }


    public void initialize() throws SQLException {

        try{
            connection = sqliteController.createDBConnection();

            if(connection == null){
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String query = "select * from kennel where id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            id_.setText("ID: " + id);
            max.setText(String.valueOf(resultSet.getInt("max")));
            descricao.setText(resultSet.getString("description"));



        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }

    public void editData(ActionEvent event) throws IOException {

        if(!isNumeric(max.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText(null);
            alert.setContentText("O máximo tem de ser um número!");
            alert.showAndWait();
        }
        else {
            try {
                connection = sqliteController.createDBConnection();

                if (connection == null) {
                    System.out.println("Erro de conexão à base de dados");
                    System.exit(1);
                }

                String query = "UPDATE kennel SET max = ?, description = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, Integer.parseInt(max.getText()));
                preparedStatement.setString(2, descricao.getText());
                preparedStatement.setInt(3, id);

                if (preparedStatement.executeUpdate() > 0) {
                    System.out.println("Registro atualizado com sucesso.");
                } else {
                    System.out.println("Nenhum registro foi atualizado. Verifique o ID do registro.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                sqliteController.closeDBConnection(connection);
            }
            Parent root = FXMLLoader.load(getClass().getResource("casotas-list-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("casotas-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
