package poo.project.Control;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import poo.project.Model.*;

public class ControlGaleria {
	// Colecciones
	private HashSet<Obra> listaObras;
	private HashSet<Cliente> listaClientes;
	private HashSet<Compra> listaCompras;
	private HashSet<Artista> listaArtistas;

	private GestionObras gestionObras;
	private GestionClientes gestionClientes;

	// Métodos
	// Accessors
	// gestionObras
	public GestionObras getGestionObras() {
		return this.gestionObras;
	}

	public void setGestionObras(GestionObras gestionObras) {
		this.gestionObras = gestionObras;
	}

	// Lista Artistas
	public HashSet<Artista> getListaArtistas() {
		return listaArtistas;
	}

	public void setListaArtistas(HashSet<Artista> listaArtistas) {
		this.listaArtistas = listaArtistas;
	}

	public Artista agregarArtista(Artista artista) {
		for (Artista art : this.listaArtistas) {
			if (art.equals(artista))
				return null;
		}
		this.listaArtistas.add(artista);
		return artista;
	}

	// gestionClientes
	public GestionClientes getGestionClientes() {
		return this.gestionClientes;
	}

	public void setGestionClientes(GestionClientes gestionClientes) {
		this.gestionClientes = gestionClientes;
	}

	// listaCompras
	public HashSet<Compra> getListaCompras() {
		return this.listaCompras;
	}

	public void setListaCompras(HashSet<Compra> listaCompras) {
		this.listaCompras = listaCompras;
	}

	// listaObras
	public HashSet<Obra> getListaObras() {
		return this.listaObras;
	}

	public void setListaObras(HashSet<Obra> listaObras) {
		this.listaObras = listaObras;
	}

	// listaClientes
	public HashSet<Cliente> getListaClientes() {
		return this.listaClientes;
	}

	public void setListaClientes(HashSet<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	// Este método añade Clientes, Obras y Compras a la galería
	public void startDay() {
		for (Cliente c : this.gestionClientes.startClientes()) {
			if (c != null)
				this.listaClientes.add(c);
		}
		for (Obra c : this.gestionObras.startObras()) {
			if (c != null)
				this.listaObras.add(c);
		}
		for (Artista c : this.gestionObras.startArtistas()) {
			if (c != null) {
				this.listaArtistas.add(c);
			}
		}
	}

	// Buscar un cliente por cedula
	public Cliente buscarCliente(long cedula, String s) {
		Cliente cliente2 = null;
		for (Cliente cliente : this.listaClientes) {
			if (cliente.getCedula() == cedula) {
				cliente2 = cliente;
			}
		}
		this.organizarListaClientes();
		return cliente2;
	}

	// Eliminar Cliente
	public void eliminarCliente() {
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\n");
		try {
			Cliente cliente;
			long codigoCliente;
			System.out.println("Ingrese el codigo del cliente a eliminar: ");
			codigoCliente = scan.nextLong();
			cliente = this.buscarCliente(codigoCliente);
			if (cliente == null) {
				System.err.println("No existe");
				return;
			}
			this.listaClientes.remove(cliente);
		} catch (Exception e) {
		}
		this.organizarListaClientes();
	}

	// Agregar Cliente
	public Cliente addCliente(Cliente cliente) {
		this.listaClientes.add(cliente);
		this.organizarListaClientes();
		return cliente;
	}

	public void crearCliente() {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		Cliente c;
		String codigoCliente;
		System.out.println("Ingrese el codigo del cliente");
		codigoCliente = sc.next();
		c = this.buscarCliente(Long.parseLong(codigoCliente));
		if (c != null) {
			System.out.println("Ya existe un cliente con este codigo");
			return;
		}
		String cedula;
		System.out.println("Ingrese la cedula del cliente");
		cedula = sc.next();
		c = this.buscarCliente(Long.parseLong(cedula), "cc");
		if (c != null) {
			System.out.println("Ya existe un cliente con esta cedula");
			return;
		}
		String nombres;
		System.out.println("Ingrese los nombres del cliente");
		nombres = sc.next();
		String apellidos;
		System.out.println("Ingrese los apellidos del cliente");
		apellidos = sc.next();
		String direccionEntrega;
		System.out.println("Ingrese la direccion de entrega del cliente");
		direccionEntrega = sc.next();
		String telefono;
		System.out.println("Ingrese el telefono del cliente");
		telefono = sc.next();
		c = new Cliente(Long.parseLong(codigoCliente), Long.parseLong(cedula), nombres, apellidos, direccionEntrega,
				Long.parseLong(telefono));
		c = this.addCliente(c);
		this.printClientes();
		this.organizarListaClientes();
		this.printClientes();
	}

	// Modificar Cliente
	public void modificarCliente() {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		Cliente cliente;
		int respuesta;
		System.out.println();
		System.out.print("Ingrese el codigo del cliente a modificar: ");
		respuesta = sc.nextInt();
		cliente = this.buscarCliente(respuesta);
		if (cliente == null) {
			System.out.println("El cliente no existe");
		} else {
			cliente.printC();
			do {
				System.out.print("Ingrese numero de atributo a modificar: ");
				respuesta = sc.nextInt();
			} while (respuesta > 6 || respuesta < 0);
			switch (respuesta) {
			case 1:
				System.out.print("Ingrese el codigo nuevo: ");
				String codigoCliente2 = sc.next();

				if (Long.parseLong(codigoCliente2) < 1) {
					System.err.println("Codigo invalido");
					return;
				}
				if (this.buscarCliente(Long.parseLong(codigoCliente2)) != null) {
					System.out.println("Ya existe un cliente con ese codigo");
					return;
				}
				cliente.setCodigoCliente(Long.parseLong(codigoCliente2));
				break;
			case 2:
				System.out.print("Ingrese la cedula nueva: ");
				String cedula1 = sc.next();
				long cedula2 = Long.parseLong(cedula1);
				if (this.buscarCliente(cedula2, "cedula") != null) {
					System.out.println("Ya existe un cliente con esa cedula");
					return;
				}
				cliente.setCedula(cedula2);
				break;
			case 3:
				System.out.print("Ingrese el nombre nuevo: ");
				sc.next();
				String nombre2 = sc.next();
				cliente.setNombre(nombre2);
				break;
			case 4:
				System.out.print("Ingrese los apellidos nuevos: ");
				sc.next();
				String apellidos2 = sc.next();
				cliente.setApellidos(apellidos2);
				break;
			case 5:
				System.out.print("Ingrese la direccion nueva: ");
				sc.next();
				String direccion2 = sc.next();
				cliente.setDireccionEntrega(direccion2);
				break;
			case 6:
				System.out.print("Ingrese el telefono nuevo: ");
				sc.next();
				long telefono2 = sc.nextLong();
				cliente.setTelefono(telefono2);
				break;
			default:
				System.err.println("Opcion invalida");
				return;
			}
			this.organizarListaClientes();
		}
		return;
	}

	// Imprimir listaClientes
	public void printClientes() {
		System.out.println("Imprimiendo la lista de Clientes:");
		for (Cliente cliente : this.listaClientes) {
			System.out.println(cliente);
		}
	}

	// Buscar un cliente
	public Cliente buscarCliente(long codigoCliente) {
		Cliente cliente2 = null;
		for (Cliente cliente : this.listaClientes) {
			if (cliente.getCodigoCliente() == codigoCliente) {
				cliente2 = cliente;
			}
		}
		this.organizarListaClientes();
		return cliente2;
	}

	// Organizar la lista (Para mantener un orden)
	public void organizarListaClientes() {
		TreeSet<Cliente> nuevo = new TreeSet<Cliente>();
		nuevo.addAll(this.listaClientes);
		this.listaClientes.clear();
		this.listaClientes.addAll(nuevo);
		nuevo = null;
	}

	// Obras
	// Buscar obra por titulo
	public void buscarObra(String titulo) {
		for (Obra obra : this.listaObras) {
			if (obra.getTitulo().equals(titulo)) {
				System.out.println(obra);
			}
		}
	}

	// Buscar obra por código
	public Obra buscarObra(long codigo) {
		for (Obra obra : this.listaObras) {
			if (obra.getCodigoObra() == codigo) {
				return obra;
			}
		}
		return null;
	}

	// BUSCAR SOLO POR AÑO
	public void buscarObra(Calendar fecha) {
		for (Obra obra : this.listaObras) {
			if (obra.getFecha().get(Calendar.YEAR) == fecha.get(Calendar.YEAR)) {
				System.out.println(obra);
			}
		}
	}

	public void buscarObraporArtista(String nombre_artista) {
		for (Obra obra : this.listaObras) {
			for (Artista artista : obra.getArtista()) {
				if (artista.getNombre().equals(nombre_artista)) {
					System.out.println(obra);
				}
			}
		}
	}

	public void insertarObra() {
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		String Titulo;
		String precioRef, cedula;
		String codigoObra;
		String dimensiones;
		Artista artista;
		String ano, mes, dia;
		System.out.println();
		System.out.println("______INSERTANDO OBRA_____");
		do {
			System.out.println("Codigo de la obra(7 digitos):");
			codigoObra = sc.next();
		} while (codigoObra.length() != 7);
		if (this.buscarObra(Long.parseLong(codigoObra)) != null) {
			System.out.println("Ya existe una obra con este codigo");
			sc.close();
			return;
		}
		System.out.println("Titulo: ");
		Titulo = sc.next();
		System.out.println("_________Fecha__________ ");
		System.out.println("Año:");
		ano = sc.next();
		System.out.println("Mes:");
		mes = sc.next();
		System.out.println("Dia:");
		dia = sc.next();
		Calendar fecha = Calendar.getInstance();
		fecha.set(Integer.parseInt(ano), Integer.parseInt(mes), Integer.parseInt(dia));
		System.out.println("Precio de referencia: ");
		precioRef = sc.next();
		System.out.println("Dimensiones: ");
		dimensiones = sc.next();
		Obra obra=new Obra(Long.parseLong(codigoObra), Titulo, fecha, Float.parseFloat(precioRef), dimensiones);
		System.out.println("Datos del artista");
		System.out.println("Cedula:");
		cedula=sc.next();
		artista=this.buscarArtista(Long.parseLong(cedula));
		if (artista != null) {
			artista.getObras().add(obra);
			obra.getArtista().add(artista);
			this.listaArtistas.add(artista);
			this.listaObras.add(obra);
			System.out.println("Exito");
			return;
		}
		artista=this.leerArtista(Long.parseLong(cedula));
		artista.getObras().add(obra);
		obra.getArtista().add(artista);
		this.listaObras.add(obra);
		System.out.println("Exito");
		return;
	}
	public Artista buscarArtista(long cedula){
		Artista artista=null;
		for (Artista a:this.listaArtistas){
			if(artista==null&&a.getCedula()==cedula)
				artista=a;
		}
		return artista;
	}
	public Artista leerArtista(long cedula) {
		Artista art;
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\n");
		System.out.println("Artista");
		String nombre, apellido, telefono;
		Calendar date = Calendar.getInstance();
		System.out.println("Nombre: ");
		nombre = sc.next();
		System.out.println("Apellidos: ");
		apellido = sc.next();
		System.out.println("Telefono: ");
		telefono = sc.next();
		art = new Artista(cedula, nombre, apellido, date, Long.parseLong(telefono));
		this.agregarArtista(art);
		return art;
	}
	public void printArtistas(){

		System.out.println("Lista de artistas:");
		for (Artista artista : this.listaArtistas) {
			System.out.println(artista);
		}
	}
	public Obra addObra(Obra obra) {
		if (this.buscarObra(obra.getCodigoObra()) == null) {
			this.listaObras.add(obra);
			return obra;
		} else {
			return null;
		}
	}

	public void modificarObra(long codigo) {
		Scanner input = new Scanner(System.in);
		Obra obra;
		obra = this.buscarObra(codigo);
		int criterio = 1;
		if (obra != null) {

			System.out.println("Modificando Obra *" + obra.getTitulo() + "*");
			System.out.println("1. Codigo: " + obra.getCodigoObra());
			System.out.println("2. Titulo: " + obra.getTitulo());
			System.out.println("3. Fecha: " + obra.getFecha().get(0) + " / " + obra.getFecha().get(2) + " / "
					+ obra.getFecha().get(1));
			System.out.println("4. Precio referencia: " + obra.getPrecioRef());
			System.out.println("5. Dimensiones del cuadro: " + obra.getDimensiones());
			System.out.println("Que opcion desea ingresar: ");
			try {
				criterio = input.nextInt();
			} catch (Exception e) {
				return;
			}
			switch (criterio) {

			case 1: {
				System.out.println("Ingrese el codigo nuevo: ");
				long newCodigo = input.nextLong();
				if (this.buscarObra(newCodigo) == null) {
					if (!((Long.toString(newCodigo)).length() < 7))
						obra.setCodigoObra(newCodigo);
					System.out.println(obra + "   " + obra.getCodigoObra());
					return;
				} else {
					System.out.println("No se modifico");
				}
				break;
			}
			case 2: {
				System.out.println("Ingrese Titulo nuevo: ");
				String newTittle = input.next();
				obra.setTitulo(newTittle);
				break;
			}
			// FALTA EL CASO 3
			case 3: {
				System.out.print("Ingrese la fecha nueva (YY/MM/DD): ");
				String fecha = input.next();
				fecha.split("/");
				break;
			}
			case 4: {
				System.out.println("Ingrese el nuevo precio de referencia: ");
				float newPrecio = input.nextFloat();
				obra.setPrecioRef(newPrecio);
				break;
			}
			case 5: {
				System.out.println("Ingrese la dimension: ");
				String dimensiones = input.next();
				obra.setDimensiones(dimensiones);
				break;
			}
			default:
				System.out.println("Shit2");

			}
		} else {
			System.out.println("\nLa obra no existe");
		}
	}

	public void eliminarObra(long codigo) {
		Scanner input = new Scanner(System.in);
		Obra obra;
		obra = this.buscarObra(codigo);
		if (obra != null) {
			// RECTIFICAR QUE NO ESTE ASOCIADO A UNA COMPRA PARA ELIMINARLO(FALTA LA CLASE
			// COMPRA)
			/* INCOMPLETO-FALTA EL CONDICIONAL Y LUEGO SI ELIMINAR- */
			if (this.listaObras.remove(obra)) {
				System.out.println("Se ha eliminado con exito la obra");
			}
		} else {
			System.err.println("No se encuentra la obra");
		}
	}

	// Imprimir listaObras
	public void listaObras() {
		System.out.println("Imprimiendo la lista de Obras:");
		if (this.getListaCompras().isEmpty()) {
			for (Obra obra : this.listaObras) {
				System.out.println(obra);
			}
			return;
		} else {
			HashSet<Obra> list = new HashSet<>();
			for (Obra obra : this.listaObras) {
				for (Compra compra : this.listaCompras) {
					if (compra.getObra().equals(obra)) {
						list.add(obra);
					}
				}
			}
			for (Obra obra : this.listaObras) {
				if (!list.contains(obra))
					System.out.println(obra);
			}

		}
	}

	// Compras
	public boolean existeCodCompra(long cod) {
		for (Compra compra : this.listaCompras) {
			if (compra.getCodigoCompra() == cod) {
				return true;
			}
		}
		return false;
	}

	public boolean buscarClienteYObraEnCompra(Cliente cliente, Obra obra) {
		for (Compra compra : this.listaCompras) {
			if (compra.getCliente() == cliente && compra.getObra() == obra) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 12.[5]Eliminar compra de obra a.Solicitar el número de compra a eliminar, si
	 * este no existe, se debe mostrar un mensaje y volver al menú principal. b.Se
	 * debe mostrar un mensaje de confirmación para eliminar la compra
	 */
	public Compra eliminCompra() {
		String codigo;
		Compra compra = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese codigo de la compra");
		codigo = sc.next();
		for (Compra c : this.listaCompras) {
			if (c.getCodigoCompra() == Long.parseLong(codigo) && compra == null)
				compra = c;
		}
		if (compra == null) {
			System.out.println("La compra no existe");
			return compra;
		}
		System.out.println("Seguro? (0/1)");
		codigo = sc.next();
		if (Long.parseLong(codigo) == 0) {
			return null;
		}
		this.listaCompras.remove(compra);
		return compra;
	}

	// Realizar una compra
	public void realizarCompra() {
		Compra comp;
		Cliente clien;
		long codigo;
		Obra obr;
		Calendar fecha = Calendar.getInstance();
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese codigo del cliente");
		codigo = sc.nextLong();
		clien = this.buscarCliente(codigo);
		if (clien == null) {
			System.out.println("No existe");
			return;
		}
		System.out.println("Ingrese codigo de la obra");
		codigo = sc.nextLong();
		obr = this.buscarObra(codigo);
		if (obr == null){
			System.out.println("No existe");
			return;
		}
		if (this.buscarClienteYObraEnCompra(clien, obr)) {
			System.out.println("Esta compra ya existe en el sistema");
			return;
		}
		long cod;
		cod = this.listaCompras.size();
		do {
			cod += 1;
		} while (this.existeCodCompra(cod));
		comp = new Compra(cod, fecha, true);
		comp.setCliente(clien);
		comp.setObra(obr);
		this.listaCompras.add(comp);
	}

	/*
	 * 14. [5] Ver listado de Compras para un mes y año específico insertado por el
	 * usuario a. Se debe solicitar mes y año al usuario y mostrar listado de Obras
	 * que hayan sido compradas, cliente que la compró, fecha y precio.
	 */
	public void listadoDeCompra(int mes, int ano) {
		for (Compra compra : this.listaCompras) {
			if ((compra.getFecha().get(Calendar.YEAR) == ano) && (compra.getFecha().get(Calendar.MONTH) == mes)) {
				System.out.println("Obra: " + compra.getObra().getTitulo());
				System.out.println("Comprador: " + compra.getCliente().getNombre());
				System.out.println("Fecha: " + compra.getFecha());
				System.out.println("Precio: " + compra.getObra().getPrecioRef());
			}
		}
	}

	// Imprimir listaCompras
	public void printCompras() {
		System.out.println("Imprimiendo la lista de Compras:");
		for (Compra compra : this.listaCompras) {
			System.out.println(compra);
		}
	}

	// Artistas
	/*
	 * 15. [5] Ver listado de Artistas más vendidos a. Mostrar los artistas más
	 * vendidos ordenados de mayor a menor ventas
	 */
	public void verListadoArtistas() {

		HashMap<Artista, Integer> mapsold = new HashMap<Artista, Integer>();
		HashSet<Artista> artistas;
		TreeMap<Integer, Artista> sort = new TreeMap<Integer, Artista>();
		for (Compra compra : this.listaCompras) {
			// Así busco un elemento un en el hashmap
			// Si no lo encuentraS
			artistas = compra.getObra().getArtista();
			for (Artista art : artistas) {
				if (mapsold.containsKey(art)) {
					mapsold.replace(art, mapsold.get(art) + 1);
				} else {
					mapsold.put(art, 1);
				}
			}
		}
		/*
		 * System.out.println("\nSorted Map......By Key"); Map<String, String> treeMap =
		 * new TreeMap<String, String>(unsortMap); printMap(treeMap);
		 */
	}

//Main (Solo para probar que todo funcione bien)
	/*
	 * public static void main(String[] args) { ControlGaleria controlGaleria = new
	 * ControlGaleria(); // Añado todo lo inicial controlGaleria.startDay();
	 * controlGaleria.listaObras(); controlGaleria.modificarObra(123456);
	 * controlGaleria.eliminarObra(765432); controlGaleria.listaObras();
	 * System.err.println(); controlGaleria.printClientes();
	 * controlGaleria.modificarCliente(); controlGaleria.eliminarCliente();
	 * controlGaleria.printClientes(); }
	 */
	// Constructor
	public ControlGaleria() {
		this.gestionClientes = new GestionClientes();
		this.gestionObras = new GestionObras();
		this.listaArtistas = new HashSet<Artista>();
		this.listaClientes = new HashSet<Cliente>();
		this.listaCompras = new HashSet<Compra>();
		this.listaObras = new HashSet<Obra>();
	}
}
