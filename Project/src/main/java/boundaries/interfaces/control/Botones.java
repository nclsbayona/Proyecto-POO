//LLLASLASDLSADSA
package boundaries.interfaces.control;

import model.Clasificacion;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import boundaries.interfaces.Exportacion;
import control.ControlGaleria;
import exceptions.ArtworkAlreadyPurchasedException;
import exceptions.ArtworkDoesntExistException;
import exceptions.ArtworkExistsException;
import exceptions.CodeSizeException;
import exceptions.EmptyPurchasesListException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Compra;
import model.Cuadro;
import model.Obra;
import model.Escultura;
import model.Instalacion;

public class Botones {

	// General
	private static ControlGaleria cGaleria = new ControlGaleria();
	private Exportacion cExportacion;
	private FileChooser fileChooser;

	// Main
	@FXML
	private Button btnObras1;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnClientes;

	@FXML
	private Button btnCompras;

	@FXML
	void cambiarAObras(ActionEvent event) {
		this.changeToA("MainObras.fxml");

	}

	@FXML
	void cambiarACliente(ActionEvent event) {
		String nomFXML = "cliente/Clientes.fxml";
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error modificando");
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Cliente");
		stage.setScene(scene);
		stage.showAndWait();
	}

	@FXML
	void cambiarAArtistas(ActionEvent event) {
	}

	@FXML
	void cambiarACompras(ActionEvent event) {
		String nomFXML = "compras/Compras.fxml";
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error modificando");
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Cliente");
		stage.setScene(scene);
		stage.showAndWait();
	}

	// End del main
	// Clientes

	// ....................BONTON EXPORTAR ........................./
	@FXML
	private Button btn_ExportarF;

	@FXML
	void exportarF(ActionEvent event) {

		try {
			this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "f",
					Botones.cGaleria.getListaClientes().values());
		} catch (TypoException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error exportando");
		}
	}

	// ....................FIN BONTON EXPORTAR ........................./
	// ....................BONTON LISTAR ........................./
	@FXML
	private Button btn_ListarClientes;
	@FXML
	private ListView<String> listarClientes;

	private ArrayList<String> clientes;

	@FXML
	void listarLosClientes(ActionEvent event) throws TypoException {
		listarClientes.refresh();
		this.clientes = new ArrayList<>();
		// System.out.println("Lista de clientes en el listar\n" +
		// Botones.cGaleria.getListaClientes());
		for (Cliente c : Botones.cGaleria.getListaClientes().values()) {
			this.clientes.add(c.toString());
		}
		this.listarClientes.setItems(FXCollections.observableArrayList(this.clientes));
		this.clientes = null;
	}

	// Buscar cliente
	@FXML
	private TextField txtCedulaClienteBuscarC;

	@FXML
	private Button btnBuscarC;

	@FXML
	private TextField txtCodigoClienteBuscarC;

	@FXML
	private Label buscarClientes;

	@FXML
	void buscarCliente(ActionEvent event) {
		String code = txtCodigoClienteBuscarC.getText();
		String cc = txtCedulaClienteBuscarC.getText();
		this.buscarClientes.setText("");
		String c = null;
		try {
			if (!code.equals("")) {
				c = (Botones.cGaleria.buscarCliente(Long.parseLong(code)).toString());
			} else if (!cc.equals("")) {
				c = (Botones.cGaleria.buscarCliente(Long.parseLong(cc), "").toString());
			} else {
				throw new TypoException();
			}
			buscarClientes.setText(c);
		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error buscando");
		} finally {
			this.txtCedulaClienteBuscarC.setText("");
			this.txtCodigoClienteBuscarC.setText("");
		}
	}

	// ....................FIN BONTON LISTAR ........................./
	// Boton Agregar Cliente
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
			if (txtNombreAC.getText().equals("") || txtCedulaAC.getText().equals("")
					|| txtCodigoClienteAC.getText().equals("") || txtTelefonoAC.getText().equals("")
					|| txtDireccionAC.equals("") || txtApellidoAC.getText().equals(""))
				throw new TypoException();
			Cliente n = new Cliente(Long.parseLong(txtCodigoClienteAC.getText()), Long.parseLong(txtCedulaAC.getText()),
					txtNombreAC.getText(), txtApellidoAC.getText(), txtDireccionAC.getText(),
					Long.parseLong(txtTelefonoAC.getText()));
			Botones.cGaleria.addCliente(n);
			txtCodigoClienteAC.clear();
			txtNombreAC.clear();
			txtApellidoAC.clear();
			txtCedulaAC.clear();
			txtTelefonoAC.clear();
			txtDireccionAC.clear();
		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error agregando");
		}
	}

	// Boton error
	@FXML
	private Pane mainWindow;

	@FXML
	private Label txtLabelError;

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

	@FXML
	private TextField campoModificar;

	@FXML
	private TextField valorModificar;

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
				this.c = (Botones.cGaleria.buscarCliente(Long.parseLong(code)));
			} else if (!cc.equals("")) {
				this.c = (Botones.cGaleria.buscarCliente(Long.parseLong(cc), ""));
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
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	void modificarCliente(ActionEvent event) {
		try {
			Botones.cGaleria.modificarCliente(Botones.cGaleria.buscarCliente(this.c.getCodigoCliente()),
					Integer.parseInt(this.campoModificar.getText()), this.valorModificar.getText());
			System.out.println("Lista de clientes en el modificar\n" + Botones.cGaleria.getListaClientes());
			txtCodigoClienteMC.setText(String.valueOf(this.c.getCodigoCliente()));
			txtApellidoMC.setText(this.c.getApellidos());
			txtCedulaMC.setText(String.valueOf(this.c.getCedula()));
			txtDireccionMC.setText(this.c.getDireccionEntrega());
			txtNombreMC.setText(this.c.getNombre());
			txtTelefonoMC.setText(String.valueOf(this.c.getTelefono()));

		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	void modificarClientePantalla(ActionEvent event) {
		String nomFXML = "cliente/ModificarCliente.fxml";
		Parent root = null;
		this.listarClientes.refresh();
		this.listarClientes.setItems(null);
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Modificar Cliente");
		stage.setScene(scene);
		stage.showAndWait();
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
				this.clien = (Botones.cGaleria.buscarCliente(Long.parseLong(code)));
			} else if (!cc.equals("")) {
				this.clien = (Botones.cGaleria.buscarCliente(Long.parseLong(cc), ""));
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
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	void eliminarCliente(ActionEvent event) {
		try {
			boolean opc = this.createNewStage("Seguro?", AlertType.CONFIRMATION, "Error eliminando");
			// System.out.println("Lista de clientes en el eliminar\n" +
			// Botones.cGaleria.getListaClientes());
			if (opc) {
				Botones.cGaleria.eliminarCliente(this.clien.getCodigoCliente());
				txtCodigoClienteEC.setText("");
				txtNombreEC.setText("");
				txtApellidoEC.setText("");
				txtCedulaEC.setText("");
				txtTelefonoEC.setText("");
				txtDireccionEC.setText("");
			}
		} catch (Exception e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	void eliminarClientePantalla(ActionEvent event) {
		String nomFXML = "cliente/EliminarCliente.fxml";
		Parent root = null;
		this.listarClientes.refresh();
		this.listarClientes.setItems(null);
		try {
			root = FXMLLoader.load(getClass().getResource(nomFXML));
		} catch (IOException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error abriendo pantalla");
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Eliminar Cliente");
		stage.setScene(scene);
		stage.showAndWait();
	}

	private boolean createNewStage(String msg, AlertType at, String title) {
		Alert alert = new Alert(at);
		alert.setTitle(title);
		alert.setHeaderText(msg);
		alert.setContentText(msg);
		alert.showAndWait();
		if (at == AlertType.CONFIRMATION) {
			if (alert.getResult() == ButtonType.YES || alert.getResult() == ButtonType.OK)
				return true;
			else
				return false;
		} else
			return true;
	}

	// End of Clientes
	// Compras
	@FXML
	private Pane CompraMainWindow;

	private ArrayList<String> obrArrayList;
	private ArrayList<String> comprArrayList;

	@FXML
	private ListView<String> listadoObrasDisponibles;

	@FXML
	private Button btnListarObrasDisponiblesCompras;

	@FXML
	private Button btnEliminarObra;

	@FXML
	private TextField txtCodigoClienteComprar;

	@FXML
	private Button btnComprarObra;

	@FXML
	private TextField txtCodigoObraComprar;

	@FXML
	private RadioButton checkFiltrado;

	@FXML
	private ToggleGroup GrupoA;

	@FXML
	private RadioButton checkSoloCuadro;

	@FXML
	private Text gananciaTotalCompras;

	@FXML
	private Button btnRefrescarGananciaTotalCompras;

	@FXML
	private TextField txtCodigoCompraEliminar;

	@FXML
	private ListView<String> listadoCompras;

	@FXML
	private Button btnListarCompras;

	@FXML
	private Button btnExportarCompras;

	@FXML
	private TextField txtAñoCompra;

	@FXML
	private TextField txtMesCompra;

	@FXML
	void refrescarGananciaTotalObras(ActionEvent event) {
		this.gananciaTotalCompras.setText(String.valueOf(Botones.cGaleria.calcularPrecioTotal()));
	}

	@FXML
	void comprarObra(ActionEvent event) {
		try {
			Obra obra = Botones.cGaleria.buscarObra(Long.valueOf(this.txtCodigoObraComprar.getText()));
			Botones.cGaleria.buscarObraEnCompras(obra);
			if (Botones.cGaleria.buscarClienteYObraEnCompra(
					Botones.cGaleria.buscarCliente(Long.parseLong(txtCodigoClienteComprar.getText())),
					Botones.cGaleria.buscarObra(Long.parseLong(this.txtCodigoObraComprar.getText()))))
				this.createNewStage("La compra ya existe", AlertType.ERROR, "_Ya existe");
			this.createNewStage("La obra ya fue comprada", AlertType.ERROR, "La obra ya fue comprada");
		} catch (Exception e) {
			try {
				Botones.cGaleria.realizarCompra(
						Botones.cGaleria.buscarCliente(Long.parseLong(txtCodigoClienteComprar.getText())),
						Botones.cGaleria.buscarObra(Long.parseLong(this.txtCodigoObraComprar.getText())));
			} catch (Exception e2) {
				this.createNewStage(e2.getMessage(), AlertType.ERROR, "La compra no fue exitosa");
			}
		}
	}

	@FXML
	void eliminarCompra(ActionEvent event) {
		try {
			Botones.cGaleria.buscarCompra(txtCodigoCompraEliminar.getText());
			boolean opc = this.createNewStage("Seguro", AlertType.CONFIRMATION, "Eliminar");
			if (opc) {
				Botones.cGaleria.eliminCompra(txtCodigoCompraEliminar.getText());
				txtCodigoCompraEliminar.clear();
			}
		} catch (Exception e) {
			this.createNewStage("Error eliminando", AlertType.ERROR, "Error eliminando");
		}
	}

	@FXML
	void exportarLasCompras(ActionEvent event) {
		try {
			if (this.checkFiltrado.isSelected()) {
				try {
					this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "n",
							Botones.cGaleria.listadoDeCompra(txtMesCompra.getText(), txtAñoCompra.getText()));
				} catch (Exception e) {
					this.createNewStage("Lista de compras vacía", AlertType.INFORMATION, "Vacía");
				}

			} else if (this.checkSoloCuadro.isSelected()) {
				try {
					this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "q",
							Botones.cGaleria.comprasAsociadasACuadro());
				} catch (EmptyPurchasesListException e) {
					this.createNewStage("Lista de compras vacía", AlertType.INFORMATION, "Vacía");
				}
			} else
				this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "m",
						Botones.cGaleria.getListaCompras());
		} catch (TypoException e) {
			this.createNewStage(e.getMessage(), AlertType.ERROR, "Error exportando");
		}

	}

	@FXML
	void listarLasCompras(ActionEvent event) {
		if (this.checkFiltrado.isSelected()) {
			this.listadoCompras.setItems(null);
			this.comprArrayList = new ArrayList<>();
			for (String c : Botones.cGaleria.listadoDeCompra(txtMesCompra.getText(), txtAñoCompra.getText())) {
				this.comprArrayList.add(c);
			}
			this.listadoCompras.setItems(FXCollections.observableArrayList(this.comprArrayList));
		} else if (this.checkSoloCuadro.isSelected()) {
			this.listadoCompras.setItems(null);
			this.comprArrayList = new ArrayList<>();
			try {
				for (Compra c : Botones.cGaleria.comprasAsociadasACuadro()) {
					this.comprArrayList.add(c.toString());
				}
			} catch (EmptyPurchasesListException e) {
				this.createNewStage("Lista de compras vacía", AlertType.INFORMATION, "Vacía");
			}
			this.listadoCompras.setItems(FXCollections.observableArrayList(this.comprArrayList));
		} else {
			this.listadoCompras.setItems(null);
			this.comprArrayList = new ArrayList<>();
			for (Compra c : Botones.cGaleria.getListaCompras()) {
				this.comprArrayList.add(c.toString());
			}
			this.listadoCompras.setItems(FXCollections.observableArrayList(this.comprArrayList));
		}
	}

	@FXML
	void listarLasObrasDisponiblesCompra(ActionEvent event) {
		this.listadoObrasDisponibles.setItems(null);
		this.obrArrayList = new ArrayList<>();
		for (Obra c : Botones.cGaleria.listaObrasDisponibles()) {
			this.obrArrayList.add(c.toString());
		}
		this.listadoObrasDisponibles.setItems(FXCollections.observableArrayList(this.obrArrayList));
	}

	// End of compras

	// obras********************
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

	// ....................BONTON LISTAR ........................./
	@FXML
	private Button btn_ListarObras;
	@FXML
	private ListView<String> ListaObras;
	private ArrayList<String> obras;
	@FXML
	private ToggleGroup GrupoB;
	@FXML
	private RadioButton radioBtnAnioListarObra;
	@FXML
	private RadioButton opt_typeArtWork;
	@FXML
	private RadioButton radioBtnTituloListarObra;
	@FXML
	private RadioButton radioBtnNombreArtistaListarObra;
	@FXML
	private ToggleGroup GrupoB2;
	@FXML
	private Button btn_BuscarObra;
	@FXML
	private TextField txtBuscarObra;

	// ....................BONTON EXPORTAR ........................./
	@FXML
	private Button btn_ExportarObra;

	@FXML
	void exportarObra(ActionEvent event) {
		try {
			if (opt_typeArtWork.isSelected()) {
				this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a",
						cGaleria.buscarEsculturas());
			} else {
				this.cExportacion.exportarXML(String.valueOf(fileChooser.showSaveDialog(null)), "a",
						cGaleria.listaObrasDisponibles());
			}
		} catch (TypoException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void listarObras(ActionEvent event) throws TypoException {
		this.ListaObras.setItems(null);
		this.obras = null;
		this.obras = new ArrayList<>();
		for (Obra o : Botones.cGaleria.listaObrasDisponibles()) {
			obras.add(o.toString());
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
	void buscarObra(ActionEvent event) throws TypoException {
		this.obras = new ArrayList<>();
		if (radioBtnTituloListarObra.isSelected()) {
			for (Obra o : Botones.cGaleria.buscarObra(txtBuscarObra.getText())) {
				this.obras.add(o.toString());
			}
		} else if (radioBtnAnioListarObra.isSelected()) {
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(txtBuscarObra.getText()), 10, 10);
			for (Obra o : Botones.cGaleria.buscarObra(c)) {
				this.obras.add(o.toString());
			}
		} else if (radioBtnNombreArtistaListarObra.isSelected()) {
			for (Obra o : Botones.cGaleria.buscarObraporArtista(txtBuscarObra.getText())) {
				this.obras.add(o.toString());
			}
		} else
			this.obras.add("No hay obra");

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

	// --------------------------------------CREAR
	// OBRA---------------------------------
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
			root = FXMLLoader.load(getClass().getResource("obras/" + nomFXML));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		primaryStage.setTitle(nomFXML);
		primaryStage.setScene(scene);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.showAndWait();
	}
	// CREAR OBRA TIPO CUADRO

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

	// CREAR OBRA INSTALACION
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

//****************************MODIFICAR*********************************************
	@FXML
	private Button btnBuscarObraM2;

	@FXML
	private TextField txtCodigoBuscarMObra2;

	@FXML
	private Text txtCodigoObraMO;

	@FXML
	private Text txtFechaMObra;

	@FXML
	private Text txtPrecioMObra;

	@FXML
	private Text txtTitutloMObra;

	@FXML
	private Text txtDimensionesMObra;

	@FXML
	private TextField campoModificarObra;

	@FXML
	private TextField valorModificarObra;

	@FXML
	private Button btnModificarObra;
	private Obra oModificar = null;

	@FXML
	void buscarObraModificar(ActionEvent event) {
		long search = Long.parseLong(txtCodigoBuscarMObra2.getText());
		try {
			oModificar = cGaleria.buscarObra(search);
			txtCodigoObraMO.setText(String.valueOf(oModificar.getCodigoObra()));
			txtTitutloMObra.setText(oModificar.getTitulo());
			txtDimensionesMObra.setText(oModificar.getDimensiones());
			txtPrecioMObra.setText(String.valueOf(oModificar.getPrecioRef()));
			Calendar c = oModificar.getFecha();
			txtFechaMObra.setText(String.valueOf(c.get(Calendar.DATE)) + "/" + String.valueOf(c.get(Calendar.MONTH))
					+ "/" + String.valueOf(c.get(Calendar.YEAR)));

		} catch (ArtworkDoesntExistException e) {
			Botones.infoAlert("Error", "No se encontro la obra", "");
		}

	}

	@FXML
	void modificarObraAction(ActionEvent event) throws ArtworkDoesntExistException, CodeSizeException,
			ArtworkExistsException, ArtworkAlreadyPurchasedException {
		int opcion = Integer.parseInt(campoModificarObra.getText());
		try {
			Botones.cGaleria.modificarObra(oModificar, opcion, valorModificarObra.getText());
			Botones.infoAlert("Correcto", "Se ha modificado con éxito", "");

		} catch (TypoException e) {
			Botones.errorAlert("Error", e.getMessage().toString(), "");
		} catch (ArtworkExistsException e) {
			Botones.errorAlert("Error", e.getMessage().toString(), "");
		}

	}

	// ******************************************************************************
	@FXML
	void initialize() {
		this.cExportacion = new Exportacion();
		this.fileChooser = new FileChooser();
		this.fileChooser.setTitle("Select Report File");
		// ****************************************************
		assert btnBuscarObraM2 != null
				: "fx:id=\"btnBuscarObraM2\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtCodigoBuscarMObra2 != null
				: "fx:id=\"txtCodigoBuscarMObra2\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtCodigoObraMO != null
				: "fx:id=\"txtCodigoObraMO\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtFechaMObra != null
				: "fx:id=\"txtFechaMObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtPrecioMObra != null
				: "fx:id=\"txtPrecioMObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtTitutloMObra != null
				: "fx:id=\"txtTitutloMObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert txtDimensionesMObra != null
				: "fx:id=\"txtDimensionesMObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert campoModificarObra != null
				: "fx:id=\"campoModificarObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert valorModificarObra != null
				: "fx:id=\"valorModificarObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";
		assert btnModificarObra != null
				: "fx:id=\"btnModificarObra\" was not injected: check your FXML file 'ModificarObra.fxml'.";

		// ........................................................................................................./
		assert btn_ExportarF != null : "fx:id=\"btn_ExportarF\" was not injected: check your FXML file 'Hero.fxml'.";
		// .
		assert btn_ListarClientes != null
				: "fx:id=\"btn_ListarClientes\" was not injected: check your FXML file 'Hero.fxml'.";
		// ......................................................................................................................
		assert txtCodigoClienteAC != null
				: "fx:id=\"txtCodigoClienteAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert txtNombreAC != null : "fx:id=\"txtNombreAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert txtApellidoAC != null : "fx:id=\"txtApellidoAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert txtCedulaAC != null : "fx:id=\"txtCedulaAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert txtTelefonoAC != null : "fx:id=\"txtTelefonoAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert txtDireccionAC != null : "fx:id=\"txtDireccionAC\" was not injected: check your FXML file 'Hero.fxml'.";
		assert btnAgregarCliente != null
				: "fx:id=\"btnAgregarCliente\" was not injected: check your FXML file 'Hero.fxml'.";
		// ................................................................................................................
		assert mainWindow != null : "fx:id=\"errorWindow\" was not injected: check your FXML file 'Untitled'.";
		assert txtLabelError != null : "fx:id=\"txtLabelError\" was not injected: check your FXML file 'Untitled'.";
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
		assert radioBtnNombreArtistaListarObra != null
				: "fx:id=\"radioBtnNombreArtistaListarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert GrupoB != null : "fx:id=\"GrupoB\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert radioBtnAnioListarObra != null
				: "fx:id=\"radioBtnAnioListarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert radioBtnTituloListarObra != null
				: "fx:id=\"radioBtnTituloListarObra\" was not injected: check your FXML file 'ListarObra.fxml'.";
		assert GrupoB2 != null : "fx:id=\"GrupoB2\" was not injected: check your FXML file 'ListarObra.fxml'.";
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