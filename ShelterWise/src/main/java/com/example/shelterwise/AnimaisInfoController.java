package com.example.shelterwise;

import com.example.shelterwise.usertypes.UserTypes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimaisInfoController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPeso;
    @FXML
    private TextField txtTipoPelagem;
    @FXML
    private TextField txtCor;
    @FXML
    private TextField txtTipoAnimal;
    @FXML
    private TextField txtRaca;
    @FXML
    private TextArea txtComentarios;
    @FXML
    private DatePicker dtNascimento;
    @FXML
    private ChoiceBox cbCasota;
    @FXML
    private ChoiceBox cbGenero;
    @FXML
    private Circle circleImg;
    @FXML
    private Circle circleAlimentado;
    @FXML
    private Circle circleSaude;
    @FXML
    public int AnimalId;
    private Stage stage;
    private Scene scene;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public void switchVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animais-list-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setAnimalId(int selectedAnimalId) {
        AnimalId = selectedAnimalId;
        loadAnimalInfo();
        System.out.println("Animal ID: " + AnimalId);
    }

    public void loadAnimalInfo() {
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
        String query = "select * from animals where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, AnimalId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve data from the ResultSet
                String name = resultSet.getString("name");
                double weight = resultSet.getDouble("weight");
                String furType = resultSet.getString("fur_type");
                String furColor = resultSet.getString("fur_color");
                String type = resultSet.getString("type");
                String breed = resultSet.getString("breed");
                String comments = resultSet.getString("comments");
                String birthDate = resultSet.getString("birth_date");
                int kennelId = resultSet.getInt("kennel_id");
                String gender = resultSet.getString("gender");


                List<String> genders = Arrays.asList("Male", "Female", "Other"); // Replace with actual gender values
                cbGenero.getItems().setAll(genders);

                List<Integer> kennelIds = new ArrayList<>();
                String kennelQuery = "Select id from kennel";
                PreparedStatement kennelStatement = connection.prepareStatement(kennelQuery);
                ResultSet kennelResultSet = kennelStatement.executeQuery();
                while (kennelResultSet.next()) {
                    kennelIds.add(kennelResultSet.getInt("id"));
                }
                cbCasota.getItems().setAll(kennelIds);

                txtNome.setText(name);
                txtPeso.setText(String.valueOf(weight));
                txtTipoPelagem.setText(furType);
                txtCor.setText(furColor);
                txtTipoAnimal.setText(type);
                txtRaca.setText(breed);
                txtComentarios.setText(comments);
                LocalDate dataNascimento = LocalDate.parse(birthDate);
                dtNascimento.setValue(dataNascimento);

                cbGenero.setValue(gender);
                cbCasota.setValue(kennelId);

                connection.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            sqliteController.closeDBConnection(connection);
        }
    }
}
