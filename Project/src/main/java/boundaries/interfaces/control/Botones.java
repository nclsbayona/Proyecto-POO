package boundaries.interfaces.control;

import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.ArtworkDoesntExistException;
import exceptions.ArtworkExistsException;
import exceptions.TypoException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Botones {
	private static ControlGaleria cGaleria = new ControlGaleria();
	Exportacion cExportacion;
	FileChooser fileChooser;
	String nomFXMLError;
	// MAIN SCREEN
	@FXML
	private Button screen_listarObra;

	@FXML
	private Button screen_addObra;

	@FXML
	private Button screen_modificarObra;

	@FXML
	private Button screen_eliminarObra;

	@FXML
	void call_addObraScreen(ActionEvent event) {
		this.changeToA("MenuCrear.fxml");
	}

	@FXML
	void call_eliminarObraScreen(ActionEvent event) {
		this.changeToA("EliminarObra.fxml");
	}

	@FXML
	void call_listarObraScreen(ActionEvent event) {
		this.changeToA("ListarObra.fxml");
	}

	@FXML
	void call_modificarObraScreen(ActionEvent event) {
		this.changeToA("ModificarObra.fxml");

	}

//....................BOTON EXPORTAR ........................./
	@FXML
	private Button btn_ExportarObra;

	@FXML
	void exportarObra(ActionEvent event) {
		try {
			this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a",
					cGaleria.listaObrasDisponibles());
		} catch (TypoException e) {
			e.printStackTrace();
		}
	}

	// ....................BOTON LISTAR ........................./
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
		this.obras = null;
		this.obras = new ArrayList<>();
		for (Obra o : Botones.cGaleria.listaObrasDisponibles()) {
			this.obras.add(o.toString());
		}
		this.ListaObras.setItems(FXCollections.observableArrayList(this.obras));
	}

	@FXML
	void listarEscultura(ActionEvent event) throws TypoException {
		this.obras = null;
		if (opt_typeArtWork.isSelected()) {
			this.ListaObras.setItems(null);
			this.obras = new ArrayList<>();
			for (Obra o : Botones.cGaleria.buscarEsculturas()) {
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
		for (Obra o : Botones.cGaleria.buscarObra(search)) {
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
		this.obras = null;
		this.list_obraEliminar.setItems(null);
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
			if (Botones.confirmacionAlert("Confirmación", "Desea eliminar la obra?", ""))
				cGaleria.eliminarObra(search);
			else
				Botones.infoAlert("Información", "No se elimino la obra", "");
		} catch (ArtworkDoesntExistException e) {
			Botones.errorAlert("Error", e.toString(), "");
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
		this.changeToA("CrearCuadro.fxml");
	}

	@FXML
	void bton_callCreateEsculturaScreen(ActionEvent event) {
		this.changeToA("CrearEscultura.fxml");
	}

	@FXML
	void bton_callCreateInstalacionScreen(ActionEvent event) {
		this.changeToA("InstalarObra.fxml");
	}

	void changeToA(String n) {
		String nomFXML = n;
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

//CREAR OBRA TIPO CUADRO

	@FXML
	private TextField txt_codigodelaObraC;

	@FXML
	private TextField txt_TitulodelaObraC;

	@FXML
	private DatePicker txt_FechadelaObraC;

	@FXML
	private TextField txt_preciodelaObraC;

	@FXML
	private TextField txt_DimensionesdelaObraC;

	@FXML
	private TextField txt_TemadelaObraC;

	@FXML
	private TextField txt_TecnicadelaObraC;

	@FXML
	private TextField txt_clasidelaObraC;
	@FXML
	private Button bton_crearObraC;
	@FXML
	private RadioButton check_obraMaestraC;

	@FXML
	private RadioButton check_obraRepresentativaC;
	@FXML
	private TextField txt_cedulaObraC;

	@FXML
	void crearObraC(ActionEvent event) throws ArtworkExistsException {
		Obra o = null;
		if (check_obraMaestraC.isSelected()) {
			try {
				if (txt_codigodelaObraC.getText().equals("") || txt_TitulodelaObraC.getText().equals("")
						|| txt_FechadelaObraC.getValue().equals("") || txt_preciodelaObraC.getText().equals("")
						|| txt_DimensionesdelaObraC.getText().equals("") || txt_TemadelaObraC.getText().equals("")
						|| txt_TecnicadelaObraC.getText().equals(""))
					throw new TypoException();
				long codigo = Long.parseLong(txt_codigodelaObraC.getText());
				String titulo = txt_TitulodelaObraC.getText();
				LocalDate ld = txt_FechadelaObraC.getValue();
				Date date = java.sql.Date.valueOf(ld);
				float precio = Float.parseFloat(txt_preciodelaObraC.getText());
				String dimensiones = txt_DimensionesdelaObraC.getText();
				String tema = txt_TemadelaObraC.getText();
				String tecnica = txt_TecnicadelaObraC.getText();
				o = new Cuadro(codigo, titulo, date, precio, dimensiones, tema, tecnica, Clasificacion.OBRA_MAESTRA);
				try {
					cGaleria.buscarObra(codigo);
					Botones.errorAlert("Error", "Ya existe una obra con el mismo código", "");

				} catch (ArtworkDoesntExistException e) {
					if (Botones.confirmacionAlert("Confirmación", "Desea agregar la obra?", ""))
						cGaleria.addObra(o);
					else
						Botones.infoAlert("Información", "No se agrego la obra", "");
				}
			} catch (Exception e) {
				Botones.errorAlert("Error", e.toString(), "Excepciones");
			}

		} else if (check_obraRepresentativaC.isSelected()) {

			try {
				if (txt_codigodelaObraC.getText().equals("") || txt_TitulodelaObraC.getText().equals("")
						|| txt_FechadelaObraC.getValue().equals("") || txt_preciodelaObraC.getText().equals("")
						|| txt_DimensionesdelaObraC.getText().equals("") || txt_TemadelaObraC.getText().equals("")
						|| txt_TecnicadelaObraC.getText().equals(""))
					throw new TypoException();
				long codigo = Long.parseLong(txt_codigodelaObraC.getText());
				String titulo = txt_TitulodelaObraC.getText();
				LocalDate ld = txt_FechadelaObraC.getValue();
				Date date = java.sql.Date.valueOf(ld);
				float precio = Float.parseFloat(txt_preciodelaObraC.getText());
				String dimensiones = txt_DimensionesdelaObraC.getText();
				String tema = txt_TemadelaObraC.getText();
				String tecnica = txt_TecnicadelaObraC.getText();
				o = new Cuadro(codigo, titulo, date, precio, dimensiones, tema, tecnica,
						Clasificacion.OBRA_REPRESENTATIVA);
				try {
					cGaleria.buscarObra(codigo);
					Botones.errorAlert("Error", "Ya existe una obra con el mismo código", "");

				} catch (ArtworkDoesntExistException e) {
					if (Botones.confirmacionAlert("Confirmación", "Desea agregar la obra?", ""))
						cGaleria.addObra(o);
					else
						Botones.infoAlert("Información", "No se agrego la obra", "");
				}
			} catch (Exception e) {
				Botones.errorAlert("Error", e.toString(), "Excepciones");
			}

		} else if (!check_obraRepresentativaC.isSelected() && !check_obraMaestraC.isSelected()) {
			Botones.errorAlert("Error", "No ha seleccionado el tipo de obra", "");
		}

	}

	// CREAR OBRA ESCULTURA
	@FXML
	private TextField txt_codigodelaObraE;

	@FXML
	private TextField txt_TitulodelaObraE;

	@FXML
	private TextField txt_preciodelaObraE;

	@FXML
	private TextField txt_DimensionesdelaObraE;

	@FXML
	private TextField txt_MaterialE;

	@FXML
	private TextField txt_PesoE;

	@FXML
	private Button btn_crearEscultura;

	@FXML
	private DatePicker txt_FechadelaObraE;

	@FXML
	void crearEscultura(ActionEvent event) throws ArtworkExistsException {
		Obra o = null;
		try {
			if (txt_codigodelaObraE.getText().equals("") || txt_TitulodelaObraE.getText().equals("")
					|| txt_FechadelaObraE.getValue().equals("") || txt_preciodelaObraE.getText().equals("")
					|| txt_DimensionesdelaObraE.getText().equals("") || txt_MaterialE.getText().equals("")
					|| txt_PesoE.getText().equals(""))
				throw new TypoException();
			long codigo = Long.parseLong(txt_codigodelaObraE.getText());
			String titulo = txt_TitulodelaObraE.getText();
			LocalDate ld = txt_FechadelaObraE.getValue();
			Date date = java.sql.Date.valueOf(ld);
			float precio = Float.parseFloat(txt_preciodelaObraE.getText());
			String dimensiones = txt_DimensionesdelaObraE.getText();
			String material = txt_MaterialE.getText();
			double peso = Double.parseDouble(txt_PesoE.getText());
			o = new Escultura(codigo, titulo, date, precio, dimensiones, material, peso);
			try {
				cGaleria.buscarObra(codigo);
				Botones.errorAlert("Error", "Ya existe una obra con el mismo código", "");

			} catch (ArtworkDoesntExistException e) {
				if (Botones.confirmacionAlert("Confirmación", "Desea agregar la obra?", ""))
					cGaleria.addObra(o);
				else
					Botones.infoAlert("Información", "No se agrego la obra", "");
			}
		} catch (TypoException e) {
			Botones.errorAlert("Error", e.toString(), "Excepciones");
		}

	}

//MODIFICAR OBRA

	//Ingresar nuevos datos para modificar

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txt_BuscarObraM;

	@FXML
	private Button boton_BuscarM;

	@FXML
	private Button boton_ModificarObra;

	@FXML
	private Button boton_SeleccionarObraM;

	@FXML
	private Text txt_NuevoCodigo;

	@FXML
	private Text txt_NuevoTitulo;

	@FXML
	private Text txt_NuevoPrecio;

	@FXML
	private Text txt_NuevaDimension;

	@FXML
	private DatePicker date_ModificarFecha;

	@FXML
	private TextField txt_NumeroCampo;

	@FXML
	private TextField txt_Valor;

	@FXML
	private ListView<?> list_MostrarObras;

	@FXML
	private Label label_BuscarObras;

    private Obra obra;

    private ArrayList<String> Obras;

    @FXML
	void listarLasObras(ActionEvent event) throws TypoException {

		list_MostrarObras.refresh();
		this.Obras = new ArrayList<>();
		//System.out.println("Lista de clientes en el listar\n" + Botones.cGaleria.getListaObras());
		for (Obra obra : Botones.cGaleria.getListaObras().values()) {
			this.Obras.add(obra.toString());
		}
		this.list_MostrarObras.setItems(FXCollections.observableArrayList(this.Obras));
		this.Obras = null;
	}
	@FXML
	void modificarObra(ActionEvent event) {
		try {
			Botones.cGaleria.modificarObra(Botones.cGaleria.buscarObra(this.obra.getCodigoObra()),
					Integer.parseInt(this.txt_NumeroCampo.getText()), this.txt_Valor.getText());
			System.out.println("Lista de Obras en el modificar\n" + Botones.cGaleria.getListaObras());
			txt_NuevoCodigo.setTextContent(String.valueOf(this.obra.getCodigoObra()));
			txt_NuevoTitulo.setTextContent(this.obra.getTitulo());
			//Fecha
			txt_NuevoPrecio.setTextContent(String.valueOf(this.obra.getPrecioRef()));
			txt_NuevaDimension.setTextContent(this.obra.getDimensiones());

		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	private void createNewStage(String message, AlertType error, String error_eliminando) {
	}

	@FXML
	void modificarObraPantalla(ActionEvent event) {
		String nomFXML = "cliente/ModificarCliente.fxml";
		Parent root = null;
		this.list_MostrarObras.refresh();
		this.list_MostrarObras.setItems(null);
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Modificar Obra");
		stage.setScene(scene);
		stage.showAndWait();
	}

	@FXML
	void buscarObra2(ActionEvent event) {
		String code = txt_BuscarObraM.getText();
		this.label_BuscarObras.setText("");
		String c = null;
		try {
			if (!code.equals("")) {
				c = (Botones.cGaleria.buscarObra(Long.parseLong(code)).toString());
			} else {
				throw new TypoException();
			}
			label_BuscarObras.setText(c);
		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error buscando");
		} finally {
			this.txt_BuscarObraM.setText("");
		}
	}



	//CREAR OBRA INSTALACION
	@FXML
	private TextField txt_codigoInsta;

	@FXML
	private TextField txt_tituloInsta;

	@FXML
	private TextField txt_precioInsta;

	@FXML
	private TextField txt_dimensionesInsta;

	@FXML
	private TextField txt_descripcionInsta;

	@FXML
	private Button crearInstalacion;

	@FXML
	private DatePicker date_fecha;



	@FXML
	void crearObraInstalacion(ActionEvent event) throws ArtworkExistsException {
		Obra o = null;
		try {
			if (txt_codigoInsta.getText().equals("") || txt_tituloInsta.getText().equals("")
					|| date_fecha.getValue().equals("") || txt_precioInsta.getText().equals("")
					|| txt_dimensionesInsta.getText().equals("") || txt_descripcionInsta.getText().equals(""))
				throw new TypoException();
			long codigo = Long.parseLong(txt_codigoInsta.getText());
			String titulo = txt_tituloInsta.getText();
			LocalDate ld = date_fecha.getValue();
			Date date = java.sql.Date.valueOf(ld);
			float precio = Float.parseFloat(txt_precioInsta.getText());
			String dimensiones = txt_dimensionesInsta.getText();
			String descripcion = txt_descripcionInsta.getText();
			o = new Instalacion(codigo, titulo, date, precio, dimensiones, descripcion);
			try {
				cGaleria.buscarObra(codigo);
				Botones.errorAlert("Error", "Ya existe una obra con el mismo código", "");

			} catch (ArtworkDoesntExistException e) {
				if (Botones.confirmacionAlert("Confirmación", "Desea agregar la obra?", ""))
					cGaleria.addObra(o);
				else
					Botones.infoAlert("Información", "No se agrego la obra", "");
			}
		} catch (TypoException e) {
			Botones.errorAlert("Error", e.toString(), "Excepciones");
		}

	}

	public static boolean confirmacionAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		Optional<ButtonType> action = alert.showAndWait();
		if (action.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}

	public static void infoAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public static void errorAlert(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	@FXML
	void initialize() {
		this.cExportacion = new Exportacion();
		this.fileChooser = new FileChooser();
		this.fileChooser.setTitle("Select Report File");
		this.nomFXMLError = "ErrorWindow.fxml";
		// Crear Obra instalacion
		assert txt_codigoInsta != null
				: "fx:id=\"txt_codigoInsta\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert txt_tituloInsta != null
				: "fx:id=\"txt_tituloInsta\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert txt_precioInsta != null
				: "fx:id=\"txt_precioInsta\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert txt_dimensionesInsta != null
				: "fx:id=\"txt_dimensionesInsta\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert txt_descripcionInsta != null
				: "fx:id=\"txt_descripcionInsta\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert crearInstalacion != null
				: "fx:id=\"crearInstalacion\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		assert date_fecha != null : "fx:id=\"date_fecha\" was not injected: check your FXML file 'InstalarObra.fxml'.";
		// Crear Obra Escultura
		assert txt_codigodelaObraE != null
				: "fx:id=\"txt_codigodelaObraE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_TitulodelaObraE != null
				: "fx:id=\"txt_TitulodelaObraE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_preciodelaObraE != null
				: "fx:id=\"txt_preciodelaObraE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_DimensionesdelaObraE != null
				: "fx:id=\"txt_DimensionesdelaObraE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_MaterialE != null
				: "fx:id=\"txt_MaterialE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_PesoE != null : "fx:id=\"txt_PesoE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert btn_crearEscultura != null
				: "fx:id=\"btn_crearEscultura\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		assert txt_FechadelaObraE != null
				: "fx:id=\"txt_FechadelaObraE\" was not injected: check your FXML file 'CrearEscultura.fxml'.";
		// Crear obra cuadro
		assert txt_cedulaObraC != null
				: "fx:id=\"txt_cedulaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_codigodelaObraC != null
				: "fx:id=\"txt_codigodelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_TitulodelaObraC != null
				: "fx:id=\"txt_TitulodelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_FechadelaObraC != null
				: "fx:id=\"txt_FechadelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_preciodelaObraC != null
				: "fx:id=\"txt_preciodelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_DimensionesdelaObraC != null
				: "fx:id=\"txt_DimensionesdelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_TemadelaObraC != null
				: "fx:id=\"txt_TemadelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_TecnicadelaObraC != null
				: "fx:id=\"txt_TecnicadelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert txt_clasidelaObraC != null
				: "fx:id=\"txt_clasidelaObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert check_obraMaestraC != null
				: "fx:id=\"check_obraMaestraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert check_obraRepresentativaC != null
				: "fx:id=\"check_obraRepresentativaC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";
		assert bton_crearObraC != null
				: "fx:id=\"bton_crearObraC\" was not injected: check your FXML file 'CrearCuadro.fxml'.";

		// MAIN SCREEN
		assert screen_listarObra != null
				: "fx:id=\"screen_listarObra\" was not injected: check your FXML file 'Main.fxml'.";
		assert screen_addObra != null : "fx:id=\"screen_addObra\" was not injected: check your FXML file 'Main.fxml'.";
		assert screen_modificarObra != null
				: "fx:id=\"screen_modificarObra\" was not injected: check your FXML file 'Main.fxml'.";
		assert screen_eliminarObra != null
				: "fx:id=\"screen_eliminarObra\" was not injected: check your FXML file 'Main.fxml'.";
		// _______________-_______
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

		//-------------------------------------------------------MODIFICAR
		assert list_MostrarObras != null : "fx:id=\"list_MostrarObras\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_BuscarObraM != null : "fx:id=\"txt_BuscarObraM\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert boton_BuscarM != null : "fx:id=\"boton_BuscarM\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert boton_ModificarObra != null : "fx:id=\"boton_ModificarObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert boton_SeleccionarObraM != null : "fx:id=\"boton_SeleccionarObraM\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_NuevoCodigo != null : "fx:id=\"txt_NuevoCodigo\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_NuevoTitulo != null : "fx:id=\"txt_NuevoTitulo\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_NuevoPrecio != null : "fx:id=\"txt_NuevoPrecio\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_NuevaDimension != null : "fx:id=\"txt_NuevaDimension\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_NumeroCampo != null : "fx:id=\"txt_NumeroCampo\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txt_Valor != null : "fx:id=\"txt_Valor\" was not injected: check your FXML file 'ModificarObra.fxml'.";
	}

}