package boundaries.interfaces.control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.TypoException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;

public class BotonesCliente {

    private static ControlGaleria cGaleria = new ControlGaleria();
    private Exportacion cExportacion;
    private FileChooser fileChooser;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainWindow;

    @FXML
    private Button btn_ExportarF;

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
    private ListView<String> listarClientes;

    @FXML
    private Button btn_ListarClientes;

    @FXML
    private TextField txtCedulaClienteBuscarC;

    @FXML
    private Button btnBuscarC;

    @FXML
    private TextField txtCodigoClienteAC;

    @FXML
    private TextField txtCodigoClienteBuscarC;

    @FXML
    private TextField campoModificar;

    @FXML
    private TextField valorModificar;

    @FXML
    private Text txtCodigoClienteMC;

    @FXML
    private Text txtNombreMC;

    @FXML
    private Text txtApellidoMC;

    @FXML
    private Text txtCedulaMC;

    @FXML
    private Text txtTelefonoMC;

    @FXML
    private Text txtDireccionMC;


    // ....................BONTON EXPORTAR ........................./

    @FXML
    void exportarF(ActionEvent event) {

        try {
            this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "f",
            BotonesCliente.cGaleria.getListaClientes().values());
        } catch (TypoException e) {
            this.createNewStageError(e);
        }
    }

    // ....................FIN BONTON EXPORTAR ........................./

    // ....................BONTON LISTAR ........................./

    private ArrayList<String> clientes = new ArrayList<>();

    @FXML
    void listarLosClientes(ActionEvent event) throws TypoException {
        listarClientes.refresh();
        this.clientes = new ArrayList<>();
        System.out.println("Lista de clientes en el listar\n" + BotonesCliente.cGaleria.getListaClientes());
        for (Cliente c : BotonesCliente.cGaleria.getListaClientes().values()) {
            this.clientes.add(c.toString());
        }
        this.listarClientes.setItems(FXCollections.observableArrayList(this.clientes));
        this.clientes = null;
    }

    // Buscar cliente

    private Label buscarClientes;

    @FXML
    void buscarCliente(ActionEvent event) {
        String code = txtCodigoClienteBuscarC.getText();
        String cc = txtCedulaClienteBuscarC.getText();
        this.buscarClientes.setText("");
        String c = null;
        try {
            if (!code.equals("")) {
                c = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(code)).toString());
            } else if (!cc.equals("")) {
                c = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(cc), "").toString());
            } else {
                throw new TypoException();
            }
            buscarClientes.setText(c);
        } catch (Exception e) {
            this.createNewStageError(e);
        } finally {
            this.txtCedulaClienteBuscarC.setText("");
            this.txtCodigoClienteBuscarC.setText("");
        }
    }

    // ....................FIN BONTON LISTAR ........................./
    // Boton Agregar Cliente

    @FXML
    void agregarCliente(ActionEvent event) {
        try {
            if (txtNombreAC.getText().equals("") || txtCedulaAC.getText().equals("")
                    || txtCodigoClienteAC.getText().equals("") || txtTelefonoAC.getText().equals("")
                    || txtDireccionAC.equals("") || txtApellidoAC.getText().equals(""))
                throw new TypoException();
            Cliente n = new Cliente(Long.parseLong(txtCodigoClienteAC.getText()), Long.parseLong(txtCedulaAC.getText()),
                    txtNombreAC.getText(), txtApellidoAC.getText(), txtDireccionAC.getText(),
                    Long.parseLong(txtTelefonoAC.getText()));
                    BotonesCliente.cGaleria.addCliente(n);
            txtCodigoClienteAC.clear();
            txtNombreAC.clear();
            txtApellidoAC.clear();
            txtCedulaAC.clear();
            txtTelefonoAC.clear();
            txtDireccionAC.clear();
        } catch (Exception e) {
            this.createNewStageError(e);
        }
    }

    // Boton error
    @FXML
    private Label txtLabelError;

    private void createNewStageError(Exception msg) {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        AlertType alert = AlertType.ERROR;
        Alert alerta = new Alert(alert, "");
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.initOwner(stage);
        alerta.getDialogPane().setContentText(msg.getMessage());
        alerta.getDialogPane().setHeaderText(String.valueOf(msg.getClass()));
        alerta.showAndWait();
    }

    // Boton modificar
    @FXML
    private Button btnModificarCliente;

    @FXML
    private TextField txtCedulaClienteBuscarC2;

    @FXML
    private Button btnBuscarC2;

    @FXML
    private TextField txtCodigoClienteBuscarC2;

    @FXML
    private Button btnModificar;

    @FXML
    private Pane modificarWindow;

    private Cliente c;

    @FXML
    void buscarCliente2(ActionEvent event) {
        try {
            this.c = null;
            String code = txtCodigoClienteBuscarC2.getText();
            String cc = txtCedulaClienteBuscarC2.getText();
            if (!code.equals("")) {
                this.c = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(code)));
            } else if (!cc.equals("")) {
                this.c = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(cc), ""));
            } else {
                throw new TypoException();
            }
            txtCodigoClienteMC.setText(String.valueOf(this.c.getCodigoCliente()));
            txtApellidoMC.setText(this.c.getApellidos());
            txtCedulaMC.setText(String.valueOf(this.c.getCedula()));
            txtDireccionMC.setText(this.c.getDireccionEntrega());
            txtNombreMC.setText(this.c.getNombre());
            txtTelefonoMC.setText(String.valueOf(this.c.getTelefono()));
        } catch (Exception e) {
            this.c = null;
            this.createNewStageError2(e);
        }
    }

    @FXML
    void modificarCliente(ActionEvent event) {
        try {
            BotonesCliente.cGaleria.modificarCliente(BotonesCliente.cGaleria.buscarCliente(this.c.getCodigoCliente()),
                    Integer.parseInt(this.campoModificar.getText()), this.valorModificar.getText());
            System.out.println("Lista de clientes en el modificar\n" + BotonesCliente.cGaleria.getListaClientes());
            txtCodigoClienteMC.setText(String.valueOf(this.c.getCodigoCliente()));
            txtApellidoMC.setText(this.c.getApellidos());
            txtCedulaMC.setText(String.valueOf(this.c.getCedula()));
            txtDireccionMC.setText(this.c.getDireccionEntrega());
            txtNombreMC.setText(this.c.getNombre());
            txtTelefonoMC.setText(String.valueOf(this.c.getTelefono()));
            
            
        } catch (Exception e) {
            this.createNewStageError2(e);
        }
    }

    @FXML
    void modificarClientePantalla(ActionEvent event) {
        String nomFXML = "ModificarCliente.fxml";
        Parent root = null;
        this.listarClientes.refresh();
        this.listarClientes.setItems(null);
        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {
            this.createNewStageError2(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modificar Cliente");
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void createNewStageError2(Exception msg) {
        Stage stage = (Stage) modificarWindow.getScene().getWindow();
        AlertType alert = AlertType.ERROR;
        Alert alerta = new Alert(alert, "");
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.initOwner(stage);
        alerta.getDialogPane().setContentText(msg.getMessage());
        alerta.getDialogPane().setHeaderText(String.valueOf(msg.getClass()));
        alerta.showAndWait();
    }

    // Boton eliminar Cliente

    @FXML
    private Pane eliminarWindow;

    @FXML
    private TextField txtCedulaClienteEliminar;

    @FXML
    private Button btnBuscarEliminar;

    @FXML
    private TextField txtCodigoClienteBuscarEliminar;

    @FXML
    private Text txtCodigoClienteEC;

    @FXML
    private Text txtNombreEC;

    @FXML
    private Text txtApellidoEC;

    @FXML
    private Text txtCedulaEC;

    @FXML
    private Text txtTelefonoEC;

    @FXML
    private Text txtDireccionEC;

    @FXML
    private Button btnEliminar;

    private Cliente clien;

    @FXML
    void buscarCliente3(ActionEvent event) {
        try {
            this.clien = null;
            String code = txtCodigoClienteBuscarEliminar.getText();
            String cc = txtCedulaClienteEliminar.getText();
            if (!code.equals("")) {
                this.clien = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(code)));
            } else if (!cc.equals("")) {
                this.clien = (BotonesCliente.cGaleria.buscarCliente(Long.parseLong(cc), ""));
            } else {
                throw new TypoException();
            }
            txtCodigoClienteEC.setText(String.valueOf(this.clien.getCodigoCliente()));
            txtApellidoEC.setText(this.clien.getApellidos());
            txtCedulaEC.setText(String.valueOf(this.clien.getCedula()));
            txtDireccionEC.setText(this.clien.getDireccionEntrega());
            txtNombreEC.setText(this.clien.getNombre());
            txtTelefonoEC.setText(String.valueOf(this.clien.getTelefono()));
        } catch (Exception e) {
            this.clien = null;
            this.createNewStageError3(e);
        }
    }

    @FXML
    void eliminarCliente(ActionEvent event) {
        try {
            boolean opc = createMensajeConfirmacion();
            System.out.println("Lista de clientes en el eliminar\n" + BotonesCliente.cGaleria.getListaClientes());
            if (opc)
            BotonesCliente.cGaleria.eliminarCliente(this.clien.getCodigoCliente());
        } catch (Exception e) {
            this.createNewStageError3(e);
        }
    }

    private boolean createMensajeConfirmacion() {
        Stage stage = (Stage) this.eliminarWindow.getScene().getWindow();
        AlertType alert = AlertType.CONFIRMATION;
        Alert alerta = new Alert(alert, "", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.initOwner(stage);
        alerta.getDialogPane().setContentText("Está seguro?");
        alerta.getDialogPane().setHeaderText("Confirmando...");
        alerta.showAndWait();
        if (alerta.getResult() == ButtonType.YES)
            return true;
        else
            return false;
    }

    @FXML
    void eliminarClientePantalla(ActionEvent event) {
        String nomFXML = "EliminarCliente.fxml";
        Parent root = null;
        this.listarClientes.refresh();
        this.listarClientes.setItems(null);
        try {
            root = FXMLLoader.load(getClass().getResource(nomFXML));
        } catch (IOException e) {
            this.createNewStageError3(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Eliminar Cliente");
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void createNewStageError3(Exception msg) {
        Stage stage = (Stage) this.eliminarWindow.getScene().getWindow();
        AlertType alert = AlertType.ERROR;
        Alert alerta = new Alert(alert, "");
        alerta.initModality(Modality.APPLICATION_MODAL);
        alerta.initOwner(stage);
        alerta.getDialogPane().setContentText(msg.getMessage());
        alerta.getDialogPane().setHeaderText(String.valueOf(msg.getClass()));
        alerta.showAndWait();
    }

    @FXML
    void initialize() {
        this.cExportacion = new Exportacion();
        this.fileChooser = new FileChooser();
        this.fileChooser.setTitle("Select Report File");
        // ........................................................................................................./

        assert mainWindow != null : "fx:id=\"mainWindow\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtNombreAC != null : "fx:id=\"txtNombreAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtApellidoAC != null : "fx:id=\"txtApellidoAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCedulaAC != null : "fx:id=\"txtCedulaAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtTelefonoAC != null : "fx:id=\"txtTelefonoAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtDireccionAC != null : "fx:id=\"txtDireccionAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert btnAgregarCliente != null : "fx:id=\"btnAgregarCliente\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert listarClientes != null : "fx:id=\"listarClientes\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert btn_ListarClientes != null : "fx:id=\"btn_ListarClientes\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCedulaClienteBuscarC != null : "fx:id=\"txtCedulaClienteBuscarC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert btnBuscarC != null : "fx:id=\"btnBuscarC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCodigoClienteAC != null : "fx:id=\"txtCodigoClienteAC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCodigoClienteBuscarC != null : "fx:id=\"txtCodigoClienteBuscarC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert campoModificar != null : "fx:id=\"campoModificar\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert valorModificar != null : "fx:id=\"valorModificar\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCodigoClienteMC != null : "fx:id=\"txtCodigoClienteMC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtNombreMC != null : "fx:id=\"txtNombreMC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtApellidoMC != null : "fx:id=\"txtApellidoMC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtCedulaMC != null : "fx:id=\"txtCedulaMC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtTelefonoMC != null : "fx:id=\"txtTelefonoMC\" was not injected: check your FXML file 'Clientes.fxml'.";
        assert txtDireccionMC != null : "fx:id=\"txtDireccionMC\" was not injected: check your FXML file 'Clientes.fxml'.";

        // ................................................................................................................
        assert mainWindow != null : "fx:id=\"errorWindow\" was not injected: check your FXML file 'Untitled'.";
        assert txtLabelError != null : "fx:id=\"txtLabelError\" was not injected: check your FXML file 'Untitled'.";
    }
}