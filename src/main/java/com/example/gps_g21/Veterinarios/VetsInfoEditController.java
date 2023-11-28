package com.example.gps_g21.Veterinarios;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class VetsInfoEditController {

    @FXML
    public TextField name;
    @FXML
    public TextField address;
    @FXML
    public TextField phone;
    @FXML
    public TextField email;
    @FXML
    public TextField horario;

    int id;

    private Stage stage;
    private Scene scene;
    private static Scene preScene;
    private Parent root;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    public VetsInfoEditController(Integer id) {this.id=id;}

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

            name.setText(resultSet.getString("name"));
            address.setText(resultSet.getString("address"));
            phone.setText(String.valueOf(resultSet.getInt("phone")));
            email.setText(resultSet.getString("email"));


            /*
            horario.setText("NIF: "+resultSet.getInt("nif"));
            vets_schedules, vet_id
             */

        } catch (SQLException e) {
            System.err.println("Erro ao inserir dados: " + e.getMessage());
        }finally {
            sqliteController.closeDBConnection(connection);
        }

    }


    public void Editar(ActionEvent actionEvent) throws IOException {

        String sql = "update vets set name ='"+name.getText()+"', address ='"+address.getText()+"', phone ="+Integer.parseInt(phone.getText())+", email ='"+email.getText()+"' where id ="+id+";";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            System.out.println("Dados atualizados com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            // Move the stage initialization here
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        /*
        try {
            connection = sqliteController.createDBConnection();
            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            }

            String sql = "update vets set name ='"+name.getText()+"', address ='"+address.getText()+"', phone ="+Integer.parseInt(phone.getText())+", email ='"+email.getText()+"' where id ="+id+";";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeQuery();


            /*
            horario.setText("NIF: "+resultSet.getInt("nif"));
            vets_schedules, vet_id


        } catch (SQLException e) {
            System.err.println("Erro ao editar dados: " + e.getMessage());
        }
*/

    }

    public void Voltar(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/veterinarios-list-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
