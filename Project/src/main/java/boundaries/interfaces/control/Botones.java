package boundaries.interfaces.control;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.ArtworkDoesntExistException;
import exceptions.TypoException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
			this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a", this.obras);
		} catch (TypoException e) {
			e.printStackTrace();
		}
	}

	// ....................BONTON LISTAR ........................./
	@FXML
	private Button btn_ListarObras;
	@FXML
	private ListView<String> ListaObras;
	@FXML
	private CheckBox opt_typeArtWork;
	private ArrayList<String> obras;

	@FXML
	void listarObras(ActionEvent event) throws TypoException {
		this.ListaObras.setItems(null);
		this.obras = new ArrayList<>();
		for (Obra o : this.cGaleria.listaObrasDisponibles()) {
			this.obras.add(o.toString());
		}
		this.ListaObras.setItems(FXCollections.observableArrayList(this.obras));
	}

	@FXML
	void listarEscultura(ActionEvent event) throws TypoException {
		if (opt_typeArtWork.isSelected()) {
			this.ListaObras.setItems(null);
			this.obras = new ArrayList<>();
			for (Obra o : this.cGaleria.buscarEsculturas()) {
				this.obras.add(o.toString());
			}
			this.ListaObras.setItems(FXCollections.observableArrayList(this.obras));
		} else
			this.listarObras(event);
	}

	// ---------------------------------BOTON BUSCAR
	// OBRA--------------------------------
	@FXML
	private Button btn_BuscarObra;
	@FXML
	private TextField txtBuscarObra;

	@FXML
	void buscarObra(ActionEvent event) throws TypoException {
		String search = txtBuscarObra.getText();
		this.obras = new ArrayList<>();
		for (Obra o : this.cGaleria.buscarObra(search)) {
			obras.add(o.toString());
		}
		this.ListaObras.setItems(FXCollections.observableArrayList(this.obras));
	}

	// -----------------------------ELIMINAR OBRA-----------------

	@FXML
	private TextField txt_buscarObraEliminar;

	@FXML
	private Button btn_buscarObraEliminar;

	@FXML
	private ListView<String> list_obraEliminar;

	@FXML
	void buscarObraCodigo(ActionEvent event) {
		long search = Long.parseLong(txt_buscarObraEliminar.getText());
		try {
			Obra o = cGaleria.buscarObra(search);
			this.obras = new ArrayList<>();
			this.obras.add(o.toString());
		} catch (ArtworkDoesntExistException e) {
			e.printStackTrace();
		}
		this.list_obraEliminar.setItems(FXCollections.observableArrayList(this.obras));
	}

	@FXML
	void eliminarObra(ActionEvent event) {
		long search = Long.parseLong(txt_buscarObraEliminar.getText());
		try {
			cGaleria.eliminarObra(search);
			System.out.println("Eliminado");
		} catch (ArtworkDoesntExistException e) {

		}
	}

//--------------------------------------CREAR OBRA---------------------------------
	@FXML
	private Button bton_cuadro;

	@FXML
	private Button bton_Escultura;

	@FXML
	private Button bton_Instalacion;

	@FXML
	void bton_callCreateCuadroScreen(ActionEvent event) {
	}

	@FXML
	void bton_callCreateEsculturaScreen(ActionEvent event) {

	}

	@FXML
	void bton_callCreateInstalacionScreen(ActionEvent event) {

	}

	@FXML
	void changeToA(ActionEvent event) {
		String nomFXML = "ListarObra.fxml";
		Stage primaryStage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle(nomFXML);
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.showAndWait();
	}

	@FXML
	void initialize() {
		this.cGaleria = new ControlGaleria();
		this.cExportacion = new Exportacion();
		this.fileChooser = new FileChooser();
		this.fileChooser.setTitle("Select Report File");
		this.nomFXMLError = "ErrorWindow.fxml";
		// Crear obra________________________________________-
		assert bton_cuadro != null : "fx:id=\"bton_cuadro\" was not injected: check your FXML file 'MenuCrear.fxml'.";
		assert bton_Escultura != null
				: "fx:id=\"bton_Escultura\" was not injected: check your FXML file 'MenuCrear.fxml'.";
		assert bton_Instalacion != null
				: "fx:id=\"bton_Instalacion\" was not injected: check your FXML file 'MenuCrear.fxml'.";
		/// _______________________________________________ELIMINAR
		/// OBRA_____________________________
		assert txt_buscarObraEliminar != null
				: "fx:id=\"txt_buscarObraEliminar\" was not injected: check your FXML file 'EliminarObra.fxml'.";
		assert btn_buscarObraEliminar != null
				: "fx:id=\"btn_buscarObraEliminar\" was not injected: check your FXML file 'EliminarObra.fxml'.";
		assert list_obraEliminar != null
				: "fx:id=\"list_obraEliminar\" was not injected: check your FXML file 'EliminarObra.fxml'.";
		// -------------------------------------------------------------------------------
		assert opt_typeArtWork != null
				: "fx:id=\"opt_typeArtWork\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert btn_ExportarObra != null
				: "fx:id=\"btn_ExportarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		// ........................................................................................................./
		assert btn_ListarObras != null
				: "fx:id=\"btn_ListarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert btn_BuscarObra != null
				: "fx:id=\"btn_BuscarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert txtBuscarObra != null
				: "fx:id=\"txtBuscarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";

	}
}