package com.example.shelterwise.Casotas;

import com.example.shelterwise.Modelos.SqliteController;
import com.example.shelterwise.StarterController;
import com.example.shelterwise.Modelos.UserTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;


public class CasotaAddController {
    @FXML
    private TextField max;
    @FXML
    private TextField description;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public void initialize() {}

    public void addCasota(ActionEvent event) throws IOException {

        if(!isNumeric(max.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO!");
            alert.setHeaderText(null);
            alert.setContentText("O máximo tem de ser um número!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adicionar Casota");
            alert.setHeaderText(null);
            alert.setContentText("Tem a certeza que deseja adicionar casota?");

            ButtonType buttonTypeSim = new ButtonType("Adicionar");
            ButtonType buttonTypeNao = new ButtonType("Cancelar");
            alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.isPresent() && resultado.get() == buttonTypeSim) {

                try {

                    connection = sqliteController.createDBConnection();

                    if (connection == null) {
                        System.out.println("Erro de conexão à base de dados");
                        System.exit(1);
                    }
                    String sql = "INSERT INTO kennel (max, description) VALUES (?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setInt(1, Integer.parseInt(max.getText()));
                    pstmt.setString(2, description.getText());
                    pstmt.executeUpdate();
                    System.out.println("Dados inseridos com sucesso.");

                } catch (SQLException e) {
                    System.err.println("Erro ao inserir dados: " + e.getMessage());
                }finally {
                    sqliteController.closeDBConnection(connection);
                }

                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/casotas-list-view.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                preScene = stage.getScene();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void backCasotas(ActionEvent event) throws IOException {
        if(StarterController.userType == UserTypes.ADMIN)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/shelterwise/casotas-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            preScene = stage.getScene();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("Não é admin");
        }
    }
}
