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
    private Button btn_ExportarF;
    @FXML
    private Button btn_ExportarM;
    @FXML
    private Button btn_ExportarO;

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
    void exportarF(ActionEvent event) {
        String ruta = "";
        ControlGaleria cGaleria = new ControlGaleria();
        Exportacion cExportacion = new Exportacion();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Report File");
        ruta = String.valueOf(fileChooser.showSaveDialog(null));
        try {
            cExportacion.exportarXML(ruta, "f", cGaleria.getListaClientes().values());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void exportarM(ActionEvent event) {
        String ruta = "";
        ControlGaleria cGaleria = new ControlGaleria();
        Exportacion cExportacion = new Exportacion();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Report File");
        ruta = String.valueOf(fileChooser.showSaveDialog(null));
        try {
            cExportacion.exportarXML(ruta, "m", cGaleria.getListaCompras());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void exportarO(ActionEvent event) {
        String ruta = "";
        ControlGaleria cGaleria = new ControlGaleria();
        Exportacion cExportacion = new Exportacion();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Report File");
        ruta = String.valueOf(fileChooser.showSaveDialog(null));
        try {
            cExportacion.exportarXML(ruta, "o", cGaleria.getListaArtistas().values());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarM != null : "fx:id=\"btn_ExportarM\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarO != null : "fx:id=\"btn_ExportarO\" was not injected: check your FXML file 'Hero.fxml'.";

    }


}