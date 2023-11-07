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
}