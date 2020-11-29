package boundaries.interfaces.control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import model.Obra;

public class Botones {
    ControlGaleria cGaleria;
    Exportacion cExportacion;
    FileChooser fileChooser;
    String nomFXMLError;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

//....................BONTON EXPORTAR ........................./
    @FXML
    private Button btn_ExportarObra;
    @FXML
    void exportarObra(ActionEvent event) {
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a", this.cGaleria.listaObrasDisponibles());
        } catch (TypoException e) {
            e.printStackTrace();
        }
    }

    //....................BONTON LISTAR ........................./
    @FXML
    private Button btn_ListarObras;
    @FXML
    private ListView<String> ListaObras;

    private ArrayList<String> obras;
    @FXML
    void listarObras(ActionEvent event) throws TypoException {
        this.ListaObras.setItems(null);
        this.obras=new ArrayList<>();
        for (Obra o: this.cGaleria.listaObrasDisponibles()) {
            this.obras.add(o.toString());
        }
        this.ListaObras.setItems(FXCollections.observableArrayList(this.obras));

    }
    @FXML
    void initialize() {
        this.cGaleria = new ControlGaleria();
        this.cExportacion = new Exportacion();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Select Report File");
        this.nomFXMLError = "ErrorWindow.fxml";
        //........................................................................................................./
      //  assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Hero.fxml'.";
       // assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert btn_ExportarM != null : "fx:id=\"btn_ExportarM\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarObra != null : "fx:id=\"btn_ExportarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
        //........................................................................................................./

        assert btn_ListarObras != null : "fx:id=\"btn_ListarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
        //assert btn_ListarClientes != null : "fx:id=\"btn_ListarClientes\" was not injected: check your FXML file 'Hero.fxml'.";
        //......................................................................................................................
       // assert txtCodigoClienteAC != null : "fx:id=\"txtCodigoClienteAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert txtNombreAC != null : "fx:id=\"txtNombreAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert txtApellidoAC != null : "fx:id=\"txtApellidoAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert txtCedulaAC != null : "fx:id=\"txtCedulaAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert txtTelefonoAC != null : "fx:id=\"txtTelefonoAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert txtDireccionAC != null : "fx:id=\"txtDireccionAC\" was not injected: check your FXML file 'Hero.fxml'.";
        //assert btnAgregarCliente != null : "fx:id=\"btnAgregarCliente\" was not injected: check your FXML file 'Hero.fxml'.";
        //................................................................................................................
        //assert errorWindow != null : "fx:id=\"errorWindow\" was not injected: check your FXML file 'Untitled'.";
     //   assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Untitled'.";
      //  assert txtLabelError != null : "fx:id=\"txtLabelError\" was not injected: check your FXML file 'Untitled'.";
       
    }
}