package com.example.gps_g21.Stock;

import com.example.gps_g21.Modelos.CategoryProduct;
import com.example.gps_g21.Modelos.SqliteController;
import com.example.gps_g21.Modelos.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockInfoEditController {
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

    private CategoryProduct selectedCategory;

    public void setSelectedCategory(CategoryProduct selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    private Stage stage;
    private Scene scene;
    private static Scene preScene;

    SqliteController sqliteController = new SqliteController();
    Connection connection = null;

    private int id;

    public void setId(int id) {
        this.id = id;
    }

    private int idProd;

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    /*
    public StockInfoEditController(int id) {
        this.id = id;
    }
     */
    public StockInfoEditController(){}

    public void initialize(int stockId) throws SQLException {
        //int categoryId = this.selectedCategory.getId();
        int categoryId = (selectedCategory != null) ? selectedCategory.getId() : 0;
        String productName = null;
        String productDescription = null;
        LocalDate productExpiredDate = null;
        int productQuantity = 0;
        CategoryProduct selectedCategory = null;

        List<CategoryProduct> categories = fetchCategories();
        category.getItems().addAll(categories);



        try {
            connection = sqliteController.createDBConnection();

            if (connection == null) {
                System.out.println("Erro de conexão à base de dados");
                System.exit(1);
            } else {
                System.out.println("Db aberta no StockInfoEditController");
            }

            String query = "select * from product where id = " + stockId;

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Retrieve data and store in local variables
            if (resultSet.next()) {
                productName = resultSet.getString("name");
                productDescription = resultSet.getString("description");
                productExpiredDate = LocalDate.parse(resultSet.getString("expired_date"), DateTimeFormatter.ofPattern("yyyy-M-d"));
                productQuantity = resultSet.getInt("quantity");
                categoryId = resultSet.getInt("category");
            }

        } finally {
            // Close the ResultSet and the connection
            if (connection != null) {
                connection.close();
            }
            System.out.println("Db fechada no StockInfoEditController (initialize)");
        }

        // Set the values after closing the ResultSet
        name.setText(productName);
        description.setText(productDescription);

        // Loop through the items in the ComboBox to find the matching CategoryProduct
        for (CategoryProduct categoryProduct : category.getItems()) {
            if (categoryProduct.getId() == categoryId) {
                // Set the found CategoryProduct as the selected value
                //category.setValue(categoryProduct);
                //selectedCategory = categoryProduct;
                //break;
                category.setValue(categoryProduct);
                selectedCategory = categoryProduct;
                break;
            }
        }

        category.setValue(selectedCategory);

        expired_date.setValue(productExpiredDate);
        quantity.setText(String.valueOf(productQuantity));
    }

    public void Save(ActionEvent event) throws SQLException, IOException {
        String sql = "UPDATE product SET name = ?, description = ?, expired_date = ?, quantity = ?, category = ? WHERE id = ?";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name.getText());
            //pstmt.setString(2, String.valueOf(description.getValue()));
            pstmt.setString(2, description.getText());
            pstmt.setString(3, expired_date.getValue().toString());
            pstmt.setInt(4, Integer.parseInt(quantity.getText()));
            //pstmt.setInt(5, Integer.parseInt(category.getText()));
            CategoryProduct selectedCategory = category.getValue();
            pstmt.setInt(5, selectedCategory.getId());
            pstmt.setInt(6, id);

            System.out.println("Name: " + name.getText());
            System.out.println("Description: " + description.getText());
            System.out.println("Expired Date: " + expired_date.getValue());
            System.out.println("Quantity: " + Integer.parseInt(quantity.getText()));
            System.out.println("Selected Category ID: " + selectedCategory.getId());
            System.out.println("ID: " + id);




            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            System.out.println("Dados atualizados com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/stock-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        // Close the current connection
        /*
        sqliteController.closeDBConnection(connection);
        stage.show();
         */
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

    public void Delete(ActionEvent event) throws SQLException, IOException {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection connection = sqliteController.createDBConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Linhas afetadas: " + linhasAfetadas);
            System.out.println("Produto excluído com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sqliteController.closeDBConnection(connection);

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/stock-list-view.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void Back(ActionEvent event) throws IOException {
        sqliteController.closeDBConnection(connection);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/gps_g21/stock-list-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Stock stock;
}
