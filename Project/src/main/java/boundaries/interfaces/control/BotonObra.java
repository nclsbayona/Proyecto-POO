package boundaries.interfaces.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BotonObra {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Exportar;

    @FXML
    private Button btn_ListarObras;

    @FXML
    void exportarA(ActionEvent event) {

    }

    @FXML
    void listarObras(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Obra.fxml'.";
        assert btn_ListarObras != null : "fx:id=\"btn_ListarObras\" was not injected: check your FXML file 'Obra.fxml'.";

    }
}
