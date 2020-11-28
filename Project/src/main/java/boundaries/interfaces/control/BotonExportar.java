package boundaries.interfaces.control;

import java.net.URL;
import java.util.ResourceBundle;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class BotonExportar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_Exportar;

    @FXML
    void exportarA(ActionEvent event) {
        String ruta = "";
        ControlGaleria cGaleria = new ControlGaleria();
        Exportacion cExportacion = new Exportacion();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Report File");
        ruta = String.valueOf(fileChooser.showSaveDialog(null));
        try {
            cExportacion.exportarXML(ruta, "a", cGaleria.listaObrasDisponibles());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Hero.fxml'.";

    }
}