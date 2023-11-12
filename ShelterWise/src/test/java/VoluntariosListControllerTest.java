import com.example.shelterwise.Voluntarios.VoluntariosListController;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class VoluntariosListControllerTest {

    @Test
    void initialize() {
        // Verificações
        VoluntariosListController test = new VoluntariosListController();
        boolean result = test.initialize();
        assertTrue(result);


    }

    @Test
    void switchVoltar() throws IOException {
        VoluntariosListController test = new VoluntariosListController();
        boolean result = test.switchVoltar(new ActionEvent());
        assertTrue(result);
    }

    @Test
    void switcharAdd() {
    }
}