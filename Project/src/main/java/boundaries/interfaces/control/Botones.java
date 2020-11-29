package boundaries.interfaces.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
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
            this.createNewStageError(e.getMessage());
        }
    }
    
    @FXML
    void exportarM(ActionEvent event) {
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "m", this.cGaleria.getListaCompras());
        } catch (TypoException e) {
            this.createNewStageError(e.getMessage());
        }
    }

    @FXML
    void exportarO(ActionEvent event) {
        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "o", this.cGaleria.getListaArtistas().values());
        } catch (TypoException e) {
            this.createNewStageError(e.getMessage());
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
    //Boton Agregar Cliente
    @FXML
    private TextField txtCodigoClienteAC;
    
    @FXML
    private TextField txtNombreAC;

    @FXML
    private TextField txtApellidoAC;

    @FXML
    private TextField txtCedulaAC;

    @FXML
    private TextField txtTelefonoAC;

    @FXML
    private TextField txtDireccionAC;

    @FXML
    private Button btnAgregarCliente;
    @FXML
    void agregarCliente(ActionEvent event) {
        try {
            Cliente n=new Cliente(Long.parseLong(txtCodigoClienteAC.getText()), Long.parseLong(txtCedulaAC.getText()), txtNombreAC.getText(), txtApellidoAC.getText(), txtDireccionAC.getText(), Long.parseLong(txtTelefonoAC.getText()));
            this.cGaleria.addCliente(n);
        } catch (Exception e) {
            this.createNewStageError(e.getMessage());
        }
    }

    //Boton error
    @FXML
    private AnchorPane errorWindow;

    @FXML
    private Button btnOk;

    @FXML
    private Label txtLabelError;

    private Stage createNewStageError(String msg) {
        Parent root=null;
        Stage stage=new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource(this.nomFXMLError));
        } catch (IOException e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        txtLabelError.setText(msg);
        return stage;
    }

    @FXML
    void closeError(ActionEvent event) {
        
    }

    @FXML
    void initialize() {
        this.cGaleria = new ControlGaleria();
        this.cExportacion = new Exportacion();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Select Report File");
        this.nomFXMLError = "ErrorWindow.fxml";
        //........................................................................................................./
        assert btn_Exportar != null : "fx:id=\"btn_Exportar\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarM != null : "fx:id=\"btn_ExportarM\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ExportarO != null : "fx:id=\"btn_ExportarO\" was not injected: check your FXML file 'Hero.fxml'.";
        //........................................................................................................./

        assert btn_ListarObras != null : "fx:id=\"btn_ListarObras\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btn_ListarClientes != null : "fx:id=\"btn_ListarClientes\" was not injected: check your FXML file 'Hero.fxml'.";
        //......................................................................................................................
        assert txtCodigoClienteAC != null : "fx:id=\"txtCodigoClienteAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert txtNombreAC != null : "fx:id=\"txtNombreAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert txtApellidoAC != null : "fx:id=\"txtApellidoAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert txtCedulaAC != null : "fx:id=\"txtCedulaAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert txtTelefonoAC != null : "fx:id=\"txtTelefonoAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert txtDireccionAC != null : "fx:id=\"txtDireccionAC\" was not injected: check your FXML file 'Hero.fxml'.";
        assert btnAgregarCliente != null : "fx:id=\"btnAgregarCliente\" was not injected: check your FXML file 'Hero.fxml'.";
        //................................................................................................................
        assert errorWindow != null : "fx:id=\"errorWindow\" was not injected: check your FXML file 'Untitled'.";
        assert btnOk != null : "fx:id=\"btnOk\" was not injected: check your FXML file 'Untitled'.";
        assert txtLabelError != null : "fx:id=\"txtLabelError\" was not injected: check your FXML file 'Untitled'.";
       
    }
}