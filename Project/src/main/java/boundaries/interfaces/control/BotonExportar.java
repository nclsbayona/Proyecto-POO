package boundaries.interfaces.control;

import java.net.URL;
import java.util.Calendar;
import java.util.HashSet;
import java.util.ResourceBundle;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import model.Clasificacion;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
import model.Obra;

public class BotonExportar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button butA;

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
        assert butA != null : "fx:id=\"butE\" was not injected: check your FXML file 'UIVacia.fxml'.";

    }
}