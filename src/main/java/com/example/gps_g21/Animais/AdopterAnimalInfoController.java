package com.example.gps_g21.Animais;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AdopterAnimalInfoController {

    @FXML
    private Text morada;
    @FXML
    private Text telefone;
    @FXML
    private Text email;
    @FXML
    private Text data_nas;
    @FXML
    private Text nome;
    @FXML
    private TextArea animais_adotados;
    private int id; //id do animal

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public AdopterAnimalInfoController(int id) {
        this.id=id;
    }

    public void initialize() throws SQLException {

        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            } else{
                System.out.println("Db aberta");
            }


            String query = "select * from adoption_regist where animal_id = " + id;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            String query1 = "select * from adopters where id = " + resultSet.getInt("adopter_id");

            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();



            nome.setText("Nome: "+resultSet1.getString("name"));
            morada.setText("Morada: "+resultSet1.getString("address"));
            telefone.setText("Telefone: "+String.valueOf(resultSet1.getInt("phone_number")));
            data_nas.setText("Data de nascimento: "+resultSet1.getString("birth_date"));
            email.setText("Email: "+resultSet1.getString("email"));


            StringBuilder animaisAdotadosText = new StringBuilder();

            String query2 = "select animals.name from animals, adoption_regist " +
                    "where animals.id = adoption_regist.animal_id " +
                    "AND adoption_regist.adopter_id = " + resultSet.getInt("adopter_id") + ";";
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet2.next()) {
                animaisAdotadosText.append(resultSet2.getString("name")).append("\n");
                //animais_adotados.setText(resultSet2.getString("name"));
                // Aqui você pode adicionar lógica adicional para processar outros dados do resultSet2, se necessário.
            }

            animais_adotados.setText(String.valueOf(animaisAdotadosText));
            animais_adotados.setEditable(false);

        } finally {
            sqliteController.closeDBConnection(connection);
            System.out.println("Db fechada (initialize)");
        }

    }

    public void Editar(ActionEvent actionEvent) {

    }

    public void Voltar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/animais-list-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
