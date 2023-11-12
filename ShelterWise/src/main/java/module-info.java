module com.example.shelterwise {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.shelterwise to javafx.fxml;
    exports com.example.shelterwise;
    exports com.example.shelterwise.Modelos;
    opens com.example.shelterwise.Modelos to javafx.fxml;
    exports com.example.shelterwise.Animais;
    opens com.example.shelterwise.Animais to javafx.fxml;
    exports com.example.shelterwise.Casotas;
    opens com.example.shelterwise.Casotas to javafx.fxml;
    exports com.example.shelterwise.Voluntarios;
    opens com.example.shelterwise.Voluntarios to javafx.fxml;
    exports com.example.shelterwise.Adocoes;
    opens com.example.shelterwise.Adocoes to javafx.fxml;
    exports com.example.shelterwise.Stock;
    opens com.example.shelterwise.Stock to javafx.fxml;
    exports com.example.shelterwise.Administrador;
    opens com.example.shelterwise.Administrador to javafx.fxml;
}