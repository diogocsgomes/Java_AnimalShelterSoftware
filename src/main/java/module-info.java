module com.example.shelterwise {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.gps_g21 to javafx.fxml;
    exports com.example.gps_g21;
    exports com.example.gps_g21.Modelos;
    opens com.example.gps_g21.Modelos to javafx.fxml;
    exports com.example.gps_g21.Animais;
    opens com.example.gps_g21.Animais to javafx.fxml;
    exports com.example.gps_g21.Casotas;
    opens com.example.gps_g21.Casotas to javafx.fxml;
    exports com.example.gps_g21.Voluntarios;
    opens com.example.gps_g21.Voluntarios to javafx.fxml;
    exports com.example.gps_g21.Adocoes;
    opens com.example.gps_g21.Adocoes to javafx.fxml;
    exports com.example.gps_g21.Stock;
    opens com.example.gps_g21.Stock to javafx.fxml;
    exports com.example.gps_g21.Administrador;
    opens com.example.gps_g21.Administrador to javafx.fxml;
    exports com.example.gps_g21.DoacoesAdocoes;
    opens com.example.gps_g21.DoacoesAdocoes to javafx.fxml;
}