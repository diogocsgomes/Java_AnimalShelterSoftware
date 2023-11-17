package com.example.gps_g21.Casotas;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class CasotasViewDataController {
    @FXML
    private Text id;
    @FXML
    private Text total;
    @FXML
    private Text max;
    @FXML
    private Text descricao;
    @FXML
    private TextArea animais;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    private int id_;
    public void initialize() throws SQLException {

        try{
            connection = sqliteController.createDBConnection();

            if(connection == null){
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String query = "select * from kennel where id = " + id_;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            id.setText("ID: " + id_);
            max.setText("Número máximo de animais: "+resultSet.getInt("max"));
            descricao.setText("Descrição: "+resultSet.getString("description"));

            String query2 = "select COUNT(*) AS total from animals where kennel_id = " + id_;
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            total.setText("Total de animais na casota: "+resultSet2.getInt("total"));

            query2 = "select * from animals where kennel_id = " + id_;
            preparedStatement2 = connection.prepareStatement(query2);
            resultSet2 = preparedStatement2.executeQuery();

            animais.setWrapText(true);
            animais.appendText("Animais e tipo na casota\n");
            while (resultSet2.next()){
                animais.appendText(resultSet2.getString("name") + " - " + resultSet2.getString("type") + "\n");
            }

        } finally {
            sqliteController.closeDBConnection(connection);
        }

    }

    public CasotasViewDataController(int id) {
        this.id_ = id;
    }

    public void editData(ActionEvent event) {

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/casotas-info-edit.fxml"));

            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == CasotasInfoEditController.class) {
                    return new CasotasInfoEditController(id_);
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/casotas-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void eliminar(ActionEvent event) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cuidado!");
        alert.setHeaderText(null);
        alert.setContentText("Tem a certeza que deseja eliminar a casota?");

        ButtonType buttonTypeSim = new ButtonType("Eliminar");
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

                String query2 = "select COUNT(*) AS total from animals where kennel_id = " + id_;
                PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.getInt("total") > 0) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Cuidado!");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Não é possível eliminar uma casota que tem animais!");
                    alert2.showAndWait();
                } else {
                    String deleteQuery = "DELETE FROM sua_tabela WHERE id = " + id_;
                    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Registro excluído com sucesso.");
                    } else {
                        System.out.println("Nenhum registro foi excluído. Verifique o ID do registro.");
                    }
                }
            }finally {
                sqliteController.closeDBConnection(connection);
            }

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/casotas-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }

    }
}
