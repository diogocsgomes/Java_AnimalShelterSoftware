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
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
    private ImageView imgAnimal;
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
    List<String> genders = Arrays.asList("Male", "Female", "Other");
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
                String name = resultSet.getString("name");
                String base64Image = resultSet.getString("image");
                double weight = resultSet.getDouble("weight");
                String furType = resultSet.getString("fur_type");
                String furColor = resultSet.getString("fur_color");
                String type = resultSet.getString("type");
                String breed = resultSet.getString("breed");
                String comments = resultSet.getString("comments");
                String birthDate = resultSet.getString("birth_date");
                int kennelId = resultSet.getInt("kennel_id");
                String gender = resultSet.getString("gender");
                cbGenero.getItems().setAll(genders);

                //Codigo para dar load aos ids das casotas
                List<Integer> kennelIds = new ArrayList<>();
                String kennelQuery = "Select id from kennel";
                PreparedStatement kennelStatement = connection.prepareStatement(kennelQuery);
                ResultSet kennelResultSet = kennelStatement.executeQuery();
                while (kennelResultSet.next()) {
                    kennelIds.add(kennelResultSet.getInt("id"));
                }
                cbCasota.getItems().setAll(kennelIds);


                //Codigo para convert a imagem de base64 para image
                if (base64Image != null) {
                    byte[] imageBytes = Base64.getDecoder().decode(base64Image);
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                    Image image = new Image(byteArrayInputStream);
                    imgAnimal.setImage(image);
                }
                else{
                    imgAnimal.setImage(null);
                }

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

    public void switchGuardar(ActionEvent event) {
        String nome = txtNome.getText();
        double peso = Double.parseDouble(txtPeso.getText());
        String tipoPelagem = txtTipoPelagem.getText();
        String cor = txtCor.getText();
        String tipoAnimal = txtTipoAnimal.getText();
        String raca = txtRaca.getText();
        String comentarios = txtComentarios.getText();
        LocalDate dataNascimento = dtNascimento.getValue();
        String genero = cbGenero.getValue().toString();
        int casotaId = Integer.parseInt(cbCasota.getValue().toString());

        // Converta a imagem em base64
        Image image = imgAnimal.getImage();
        String base64Image = null;
        if (image != null) {
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            PixelReader pixelReader = image.getPixelReader();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4 * width * height);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color color = pixelReader.getColor(x, y);
                    byteBuffer.put((byte) (color.getRed() * 255));
                    byteBuffer.put((byte) (color.getGreen() * 255));
                    byteBuffer.put((byte) (color.getBlue() * 255));
                    byteBuffer.put((byte) (color.getOpacity() * 255));
                }
            }

            byte[] imageBytes = byteBuffer.array();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        }

        connection = sqliteController.createDBConnection();
        if (connection == null) {
            System.out.println("Connection not successful");
            System.exit(1);
        }

        try {
            String updateQuery = "UPDATE animals SET name=?, weight=?, fur_type=?, fur_color=?, type=?, " +
                    "breed=?, comments=?, birth_date=?, gender=?, kennel_id=?, image=? WHERE id=?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, nome);
            updateStatement.setDouble(2, peso);
            updateStatement.setString(3, tipoPelagem);
            updateStatement.setString(4, cor);
            updateStatement.setString(5, tipoAnimal);
            updateStatement.setString(6, raca);
            updateStatement.setString(7, comentarios);
            updateStatement.setString(8, dataNascimento.toString());
            updateStatement.setString(9, genero);
            updateStatement.setInt(10, casotaId);
            updateStatement.setString(11, base64Image);
            updateStatement.setInt(12, AnimalId);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
            } else {
                System.out.println("Failed to update.");
            }
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);
        }
    }

}
