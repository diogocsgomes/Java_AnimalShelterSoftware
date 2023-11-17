package com.example.gps_g21.Animais;

import com.example.gps_g21.Modelos.SqliteController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AnimaisInfoController {
    public Label lblTitulo;
    @FXML
    private Button btnSelecImg;
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
    byte[] imageBytes;
    String imageString;
    SqliteController sqliteController = new SqliteController();
    Connection connection = null;
    List<String> genders = Arrays.asList("Male", "Female", "Other");

    public void initialize() throws SQLException {
        connection = sqliteController.createDBConnection();
        if(connection == null){
            System.out.println("Connection not successful");
            System.exit(1);
        }
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
        connection.close();
    }
    public void switchVoltar(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/animais-list-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void createAnimal() {
        lblTitulo.setText("Criar Animal");
        btnSelecImg.setDisable(false);
        btnSelecImg.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open a file");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")+ "/Desktop"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
            Stage stage = (Stage) btnSelecImg.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if(selectedFile != null){
                Image image = new Image(selectedFile.getPath());
                try {
                    FileInputStream imageStream = new FileInputStream(selectedFile.getPath());
                    imageBytes = imageStream.readAllBytes();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                imgAnimal.setImage(image);
            }else{
                System.out.println("No file has been selected");
            }
        });

    }

    public void editAnimal(int selectedAnimalId) {
        lblTitulo.setText("Editar Animal");
        AnimalId = selectedAnimalId;
        btnSelecImg.setDisable(true);
        loadAnimalInfo();
        System.out.println("Animal ID: " + AnimalId);
    }

    public void loadAnimalInfo() {
        String query = "select * from animals where id = ?";
        connection = sqliteController.createDBConnection();
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
                int healthStatus = resultSet.getInt("health");
                boolean feedStatus = resultSet.getBoolean("feed");

                //Codigo para converter a imagem de base64 para image
                if (base64Image != null) {
                    imageBytes = Base64.getDecoder().decode(base64Image);
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

                Tooltip tooltipAlimentado = null, tooltipSaude = null;
                if(feedStatus){
                    tooltipAlimentado = new Tooltip("Alimentado");
                    circleAlimentado.setFill(Color.GREEN);
                }else{
                    tooltipAlimentado = new Tooltip("Não Alimentado");
                    circleAlimentado.setFill(Color.RED);
                }
                Tooltip.install(circleAlimentado, tooltipAlimentado);

                if(healthStatus >= 0 && healthStatus < 25){
                    tooltipSaude = new Tooltip("Muito mau de saúde");
                    circleSaude.setFill(Color.GREEN);
                }
                else if(healthStatus >= 25 && healthStatus < 50){
                    tooltipSaude = new Tooltip("Mau de saúde");
                    circleSaude.setFill(Color.ORANGE);
                }
                else if(healthStatus >= 50 && healthStatus < 75){
                    tooltipSaude = new Tooltip("Em recuperação");
                    circleSaude.setFill(Color.YELLOW);
                }
                else if(healthStatus >= 75){
                    tooltipSaude = new Tooltip("Saudável");
                    circleSaude.setFill(Color.GREEN);
                }
                Tooltip.install(circleSaude, tooltipSaude);
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchGuardar(ActionEvent event) {
        if (sqliteController.verificaCampos(
                txtNome.getText(),
                txtPeso.getText(),
                txtTipoPelagem.getText(),
                txtCor.getText(),
                txtTipoAnimal.getText(),
                txtRaca.getText(),
                dtNascimento.getValue() == null ? null : dtNascimento.getValue().toString(),
                cbGenero.getValue() == null ? null : cbGenero.getValue().toString(),
                cbCasota.getValue() == null ? null : cbCasota.getValue().toString(),
                (imageBytes == null || imageBytes.length == 0) ? null : Base64.getEncoder().encodeToString(imageBytes))) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        connection = sqliteController.createDBConnection();

        String nome = txtNome.getText();
        String imageConverter = Base64.getEncoder().encodeToString(imageBytes);
        double peso = Double.parseDouble(txtPeso.getText());
        String tipoPelagem = txtTipoPelagem.getText();
        String cor = txtCor.getText();
        String tipoAnimal = txtTipoAnimal.getText();
        String raca = txtRaca.getText();
        String comentarios = txtComentarios.getText();
        LocalDate dataNascimento = dtNascimento.getValue();
        String genero = cbGenero.getValue().toString();
        int casotaId = Integer.parseInt(cbCasota.getValue().toString());

        try {
            String query;
            int val;
            if(!btnSelecImg.isDisable()){
                query = "INSERT INTO animals (name, weight, fur_type, fur_color, type, breed, comments, birth_date, gender, kennel_id, image, adopted, health, feed) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                val = 1;

            }
            else{
                query = "UPDATE animals SET name=?, weight=?, fur_type=?, fur_color=?, type=?, " +
                        "breed=?, comments=?, birth_date=?, gender=?, kennel_id=?, image=?, adopted=?, health=?, feed=? WHERE id=?";
                val = 0;
            }
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nome);
            statement.setDouble(2, peso);
            statement.setString(3, tipoPelagem);
            statement.setString(4, cor);
            statement.setString(5, tipoAnimal);
            statement.setString(6, raca);
            statement.setString(7, comentarios);
            statement.setString(8, dataNascimento.toString());
            statement.setString(9, genero);
            statement.setInt(10, casotaId);
            if(val == 0)
                statement.setInt(12, AnimalId);
            statement.setString(11, imageConverter);
            statement.setBoolean(13, false);
            statement.setInt(14, 100);
            statement.setBoolean(15, true);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Updated successfully.");
                switchVoltar(event);
            } else {
                System.out.println("Failed to update.");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchHistoricoAlimentacao(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-feed-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        //AnimaisInfoController infoController = loader.getController();
        //infoController.createAnimal();
        AnimaisFeedListController feedListController = loader.getController();
        feedListController.setAnimal_id(AnimalId); // Set the animal_id
        feedListController.initialize();
        stage.setTitle("Histórico de Alimentação");
        stage.setScene(scene);
        stage.show();
        System.out.println("aberta feed list");
    }

    public void switchHistoricoBanhos(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/animais-bath-list-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        //AnimaisInfoController infoController = loader.getController();
        //infoController.createAnimal();
        AnimaisBathListController bathListController = loader.getController();
        bathListController.setAnimal_id(AnimalId); // Set the animal_id
        bathListController.initialize();
        stage.setTitle("Histórico de Banhos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openDialog(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/gps_g21/dialogbox-animais-info-view.fxml"));
        Parent parent = fxmlLoader.load();
        DialogBoxAnimaisInfoController dialogController = fxmlLoader.<DialogBoxAnimaisInfoController>getController();
        dialogController.setId(AnimalId);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
        loadAnimalInfo();
    }
}
