    package com.example.shelterwise;

    import com.example.shelterwise.Modelos.CategoryProduct;
    import com.example.shelterwise.usertypes.UserTypes;
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
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    public class StockAddController {
        @FXML
        private TextField name;

        @FXML
        private TextField description;

        @FXML
        private DatePicker expired_date;

        @FXML
        private TextField quantity;

        @FXML
        private ComboBox<CategoryProduct> category;

        private Stage stage;
        private Scene scene;
        private static Scene preScene;

        SqliteController sqliteController = new SqliteController();
        Connection connection = null;

        private List<TextField> textFields;

        public void initialize() {
            List<CategoryProduct> categories = fetchCategories();
            category.getItems().addAll(categories)  ;
        }

        private List<CategoryProduct> fetchCategories() {
            List<CategoryProduct> categories = new ArrayList<>();

            connection = sqliteController.createDBConnection();
            if (connection != null) {
                try {
                    String query = "SELECT * FROM category_product";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        CategoryProduct categoryProduct = new CategoryProduct(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        categories.add(categoryProduct);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    sqliteController.closeDBConnection(connection);
                }
            }
            return categories;
        }


        public void addProd(ActionEvent event) throws IOException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Adicionar Produto");
            alert.setHeaderText(null);
            alert.setContentText("Tem a certeza que deseja adicionar este produto?");

            ButtonType buttonTypeSim = new ButtonType("Adicionar");
            ButtonType buttonTypeNao = new ButtonType("Cancelar");
            alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.isPresent() && resultado.get() == buttonTypeSim) {
                CategoryProduct selectedCategory = category.getValue(); // Get the selected category
                int categoryId = selectedCategory.getId(); // Assuming there's an 'id' field in CategoryProduct

                try{

                    connection = sqliteController.createDBConnection();

                    if(connection == null){
                        System.out.println("Erro de conexão à base de dados");
                        System.exit(1);
                    }

                    String sql = "INSERT INTO product (name, description, expired_date, quantity, category) VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement pstmt = connection.prepareStatement(sql);
                    pstmt.setString(1, name.getText());
                    pstmt.setString(2, description.getText());
                    pstmt.setString(3, String.valueOf(expired_date.getValue()));
                    pstmt.setInt(4, Integer.parseInt(quantity.getText()));
                    pstmt.setInt(5, categoryId); //category

                    pstmt.executeUpdate();
                    System.out.println("Dados inseridos com sucesso.");

                }catch (SQLException e) {
                    System.err.println("Erro ao inserir dados: " + e.getMessage());
                }

                Parent root = FXMLLoader.load(getClass().getResource("stock-list-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                preScene = stage.getScene();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }

        public void backProd(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("stock-list-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                preScene = stage.getScene();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
