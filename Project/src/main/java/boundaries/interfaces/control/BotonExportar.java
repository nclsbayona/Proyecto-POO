package boundaries.interfaces.control;

import java.net.URL;
import java.util.ResourceBundle;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import model.Cliente;
import model.Obra;
public class BotonExportar {
    ControlGaleria cGaleria;
    Exportacion cExportacion;
    FileChooser fileChooser;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//....................BONTON EXPORTAR ........................./
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
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a", this.cGaleria.listaObrasDisponibles());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void exportarF(ActionEvent event) {
    
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "f", this.cGaleria.getListaClientes().values());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void exportarM(ActionEvent event) {
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "m", this.cGaleria.getListaCompras());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exportarO(ActionEvent event) {
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "o", this.cGaleria.getListaArtistas().values());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }



//....................FIN BONTON EXPORTAR ........................./



    //....................BONTON LISTAR ........................./
    @FXML
    private Button btn_ListarObras;
    @FXML
    private ListView<String> ListaObras;
    @FXML
    private Button btn_ListarClientes;
    @FXML
    private ListView<String> ListarClientes;

    @FXML
    void listarObras(ActionEvent event) throws TypoException {
        for(Obra o: this.cGaleria.listaObrasDisponibles()){
            ListaObras.getItems().add(o.getTitulo());
        }

    }
    @FXML
    void listarClientes(ActionEvent event) throws TypoException {
        for(Cliente c: this.cGaleria.getListaClientes().values()){
            ListarClientes.getItems().add(c.getNombre());
        }

    }

    //....................FIN BONTON LISTAR ........................./

    @FXML
    void initialize() {
        this.cGaleria = new ControlGaleria();
        this.cExportacion = new Exportacion();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Select Report File");
        //........................................................................................................./
        assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarM != null : "fx:id=\"btn_ExportarM\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarO != null : "fx:id=\"btn_ExportarO\" was not injected: check your FXML file 'Hero.fxml'.";
        //........................................................................................................./

        assert btn_ListarObras != null : "fx:id=\"btn_ListarObras\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ListarClientes != null : "fx:id=\"btn_ListarClientes\" was not injected: check your FXML file 'Hero.fxml'.";

    }
}