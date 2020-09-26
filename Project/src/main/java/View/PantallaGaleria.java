package View;
import Control.ControlGaleria;
import java.util.Calendar;
import java.util.Scanner;
//Boundary
public class PantallaGaleria {

	private ControlGaleria controlGaleria;

	// Limpia la Pantalla
	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// Muestra el Menu al Usuario
	public void printMenu() {
		this.clearScreen();// Limpia la Pantalla
		System.out.println("-----------------------------");
		System.out.println("- Galeria de Arte Javeriana -");
		System.out.println("-----------------------------");
		System.out.println(" ");
		System.out.println("----------- Menu ------------");
		System.out.println("-- Obras ------------------");
		System.out.println("1. Listar Obras Disponibles");
		System.out.println("2. Buscar Obra");
		System.out.println("3. Insertar Obra");
		System.out.println("4. Modificar Obra");
		System.out.println("5. Eliminar Obra");
		System.out.println(" ");

		System.out.println("-- Clientes ----------------");
		System.out.println("6. Listar Clientes");
		System.out.println("7. Buscar Cliente");
		System.out.println("8. Insertar Cliente");
		System.out.println("9. Modificar Cliente");
		System.out.println("10. Eliminar Cliente");
		System.out.println(" ");

		System.out.println("-- Compras ------------------");
		System.out.println("11. Compra de Obra");
		System.out.println("12. Eliminar Compra");
		System.out.println("13. Listar Compras");
		System.out.println("14. Listar Compras Filtado");
		System.out.println("15. Listar Artistas mas Vendidos");
		System.out.println("16. Salir");
		System.out.println("---------------------------------");
		/*
		 * System.out.println(" "); System.out.println("16. Salir");
		 * System.out.println(" ");
		 */
		System.out.println("---------------------------------");

	}

	// Controla las deciciones del usuario
	public void controlMenu() {

		// Variables internas
		Scanner entrada = new Scanner(System.in);
		ControlGaleria controlGaleria = this.getControlGaleria();
		String opcionObras = "0";
		String buscarObraporCodigo, buscarCliente, buscarClienteC;
		Calendar fecha = Calendar.getInstance();
		String buscarObraporArtista = " ";
		String buscarObraporTitulo = " ";
		String codigoCliente;
		String respuesta;
		String valor;

		// Registra la decicion del Usuario
		System.out.println("Digita el Numero de la Opcion: ");
		String opcionSeleect = entrada.nextLine();
		System.out.println("---------------------------------");
		System.out.println(" ");

		// Procesa la decición del Usuario
		switch (Integer.parseInt(opcionSeleect)) {

		// Obras
		// Lista Obras
		case 1: {
			this.clearScreen();// Limpia la Pantalla
			// Imprime por pantalla todas las obras Disponibles
			System.out.println("Lista De Obras Disponibles: ");
			System.out.println("---------------------------------");
			controlGaleria.listaObras();
			System.out.println("---------------------------------");
			break;
		}

		// Busca obras por titulo, autor, fecha o codigo
		case 2: {
			this.clearScreen();// Limpia la Pantalla

			// Menu de Busqueda Interno para Obras
			System.out.println("Menu Busca Obras por: ");
			System.out.println("1. Titulo");
			System.out.println("2. Autor");
			System.out.println("3. Codigo");
			System.out.println("4. Fecha");
			// Registra la decicion del Usuario
			System.out.println("Selecciona la Opcion: ");
			opcionObras = entrada.next();
			System.out.println(" ");
			// Procesa la Decición del Usuario
			switch (Integer.parseInt(opcionObras)) {
				// Busqueda por Titulo
				case 1: {
					this.clearScreen();// Limpia la Pantalla
					System.out.println("Busca Obras por Titulo");
					System.out.println("Escribe el Titulo:");
					entrada.nextLine();
					buscarObraporTitulo = entrada.nextLine();
					System.out.println("--Obras del Titulo " + buscarObraporTitulo);
					controlGaleria.buscarObra(buscarObraporTitulo);
					System.out.println("---------------------------------");
					break;
				}
				// Busqueda Por Autor
				case 2: {
					this.clearScreen();// Limpia la Pantalla
					System.out.println("Busca Obras por Autor");
					System.out.println("Escribe el Nombre:");
					entrada.nextLine();
					buscarObraporArtista = entrada.nextLine();
					System.out.println("--Obras del Autor " + buscarObraporArtista);
					controlGaleria.buscarObraporArtista(buscarObraporArtista);
					System.out.println("---------------------------------");
					break;
				}
				// Busqueda Por Codigo
				case 3: {
					this.clearScreen();// Limpia la Pantalla
					System.out.println("Busca Obras por Codigo");
					System.out.println("Escribe el Codigo:");
					buscarObraporCodigo = entrada.next();
					System.out.println("--Obra del Codigo " + buscarObraporCodigo);
					if(controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo))!=null)
					{
						System.out.println(controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)));
					}
					System.out.println("---------------------------------");
					break;
				}
				// Busqueda Por año
				case 4: {
					this.clearScreen();// Limpia la Pantalla
					System.out.println("Busca Obras por Fecha");
					System.out.println("Escribe el Año:");
					int ano = entrada.nextInt();
					fecha.set(ano, 1, 1);
					System.out.println("--Obras de la Fecha " + fecha.get(Calendar.YEAR));
					controlGaleria.buscarObra(fecha);
					System.out.println("---------------------------------");
					break;
				}
			}
			break;// Case 2
		}
		// Inserta Obra
		case 3: {
			this.clearScreen();// Limpia la Pantalla
			System.out.println("Insertar Obra");
			controlGaleria.insertarObra();
			System.out.println("---------------------------------");
			break;
		}
		// Modifica Obra por Codigo
		case 4: {
			this.clearScreen();// Limpia la Pantalla

			System.out.println("Modificar Obra");

			System.out.println("Escribe el Codigo:");
			buscarObraporCodigo = entrada.next();
			if (controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo))!=null){

			}
			System.out.println("---------------------------------");
			break;
		}

		// Elimina Obra Por Codigo
		case 5: {
			this.clearScreen();// Limpia la Pantalla
			controlGaleria.eliminarObra();
			System.out.println("---------------------------------");
			break;
		}

		// Clientes
		// Lista Clientes Activos
		case 6: {
			this.clearScreen();// Limpia la Pantalla
			System.out.println("Listar Clientes");
			controlGaleria.printClientes();
			System.out.println("---------------------------------");
			break;
		}
		// Lista Clientes Activos
		case 7: {
			this.clearScreen();// Limpia la Pantalla
			System.out.println("Buscar Cliente");
			System.out.println("Menu Busca Clientes por: ");
			System.out.println("1. CodigoCliente");
			System.out.println("2. Cedula");
			System.out.println();
			buscarCliente = entrada.next();
			switch (Integer.parseInt(buscarCliente)) {
			case 1:
				System.out.println("Ingrese el codigo");
				buscarClienteC = entrada.next();
				System.out.println(controlGaleria.buscarCliente(Long.parseLong(buscarClienteC)) != null
						? controlGaleria.buscarCliente(Long.parseLong(buscarClienteC))
						: "");
				break;
			case 2:
				System.out.println("Ingrese la cedula");
				buscarClienteC = entrada.next();
				System.out.println(controlGaleria.buscarCliente(Long.parseLong(buscarClienteC), "cedula") != null
						? controlGaleria.buscarCliente(Long.parseLong(buscarClienteC), "cedula")
						: "");
				break;
			default:
				break;
			}
			System.out.println("---------------------------------");
			break;
		}
		// Insertar Cliente
		case 8: {
			this.clearScreen();// Limpia la Pantalla
			System.out.println("Insertar clientes");
			controlGaleria.crearCliente();
			System.out.println("---------------------------------");
			break;
		}
		// Modificar cliente
		case 9: {
			this.clearScreen();// Limpia la Bundle
			System.out.println("Modificar Cliente");
			System.out.print("Ingrese el codigo del cliente a modificar: ");
			codigoCliente = entrada.next();
			if (controlGaleria.buscarCliente(Long.parseLong(codigoCliente))==null){
				System.out.println("No encontrado");
				break;
			}
			controlGaleria.buscarCliente(Long.parseLong(codigoCliente)).printC();
			do {
                System.out.print("Ingrese numero de atributo a modificar: ");
                respuesta = entrada.next();
			} while (Integer.parseInt(respuesta) > 6 || Integer.parseInt(respuesta) < 0);
			switch (Integer.parseInt(respuesta)) {
                case 1:
                    System.out.print("Ingrese el codigo nuevo: ");
                    valor = entrada.next();

                    if (Long.parseLong(valor) < 1) {
                        System.err.println("Codigo invalido");
                        return;
                    }
                    if (this.controlGaleria.buscarCliente(Long.parseLong(valor)) != null) {
                        System.out.println("Ya existe un cliente con ese codigo");
                        return;
                    }
                    break;
                case 2:
                    System.out.print("Ingrese la cedula nueva: ");
                    valor = entrada.next();
                    if (this.controlGaleria.buscarCliente(Long.parseLong(valor), "cedula") != null) {
                        System.out.println("Ya existe un cliente con esa cedula");
                        return;
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre nuevo: ");
                    valor = entrada.next();
                    break;
                case 4:
                    System.out.print("Ingrese los apellidos nuevos: ");
                    valor = entrada.next();
                    break;
                case 5:
                    System.out.print("Ingrese la direccion nueva: ");
                    valor = entrada.next();
                    break;
                case 6:
                    System.out.print("Ingrese el telefono nuevo: ");
                    valor = entrada.next();
                    break;
                default:
                    System.err.println("Opcion invalida");
                    return;
			}
			controlGaleria.modificarCliente(controlGaleria.buscarCliente(Long.parseLong(codigoCliente)), Integer.parseInt(respuesta), valor);
            this.controlGaleria.organizarListaClientes();
			System.out.println("---------------------------------");
			break;
		}
		// Eliminar cliente
		case 10: {
			this.clearScreen();// Limpia la Pantalla
            System.out.println("Ingrese el codigo del cliente a eliminar: ");
            codigoCliente = entrada.next();
            this.controlGaleria.eliminarCliente(Long.parseLong(codigoCliente));
			System.out.println("Eliminar Cliente");
			System.out.println("---------------------------------");
			break;
		}
		case 11: {
			// 11.Realizar compra de una Obra
			System.out.println("Realizar compra de una Obra");
			System.out.println("---------------------------------");
			System.out.println("Ingrese codigo del cliente");
        	valor = entrada.next();
			if (controlGaleria.buscarCliente(Long.parseLong(valor)) == null) {
				System.out.println("No existe");
				return;
			}
			System.out.println("Ingrese codigo de la obra");
			respuesta = entrada.next();
			if (controlGaleria.buscarObra(Long.parseLong(respuesta)) == null) {
				System.out.println("No existe la obra");
				return;
			}
			if (controlGaleria.buscarClienteYObraEnCompra(controlGaleria.buscarCliente(Long.parseLong(valor)), controlGaleria.buscarObra(Long.parseLong(respuesta)))) {
				System.out.println("Esta compra ya existe en el sistema");
				return;
			}
			if (controlGaleria.buscarObraEnCompras(controlGaleria.buscarObra(Long.parseLong(respuesta)))) {
				System.out.println("Esta obra ya fue comprada");
				return;
			}
			controlGaleria.realizarCompra(controlGaleria.buscarCliente(Long.parseLong(valor)), controlGaleria.buscarObra(Long.parseLong(respuesta)));
			break;
			}
		case 12: {
			// 12.Eliminar compra de obra
			System.out.println("Eliminar compra de una Obra");
			System.out.println("---------------------------------");
			System.out.println("Ingrese codigo de la compra");
			valor = entrada.next();
			if (controlGaleria.buscarCompra(valor) == null) {
				System.out.println("La compra no existe");
				break;
			}
			System.out.println("Seguro? (0/1)");
			respuesta = entrada.next();
			controlGaleria.eliminCompra(valor);
			break;
		}
		case 13: {
			// 13.Ver listado de Compras existentes
			System.out.println("Ver listado de Compras existentes");
			System.out.println("---------------------------------");
			controlGaleria.printCompras();
			break;
		}
		case 14: {
			// 14.Ver listado de Compras para un mes y año específico insertado por el
			// usuario
			controlGaleria.listadoDeCompra();
			break;
		}
		case 15: {
			// 15.Ver listado de Artistas más vendidos
			controlGaleria.verListadoArtistas();
			break;
		}
		case 16:{
			break;
		}
		default:
			System.out.println("Opcion incorrecta!");
			break;
		}
	}

	// Main Boundary del Sistema
	public static void main(String[] args) {

		// Creaciones de Objetos
		Scanner entrada = new Scanner(System.in);
		PantallaGaleria pantallaGaleria = new PantallaGaleria();
		String opc;
		do {
			// Muestra el Menu al Usuario
			pantallaGaleria.printMenu();
			// Controla las deciciones del usuario
			pantallaGaleria.controlMenu();
			System.out.println("Desea ingresar otra opción (1/0)");
			opc = entrada.next();
		} while (!opc.equals("0"));
		entrada.close();
	}
	//Accesors control galeria
	public ControlGaleria getControlGaleria() {
		return this.controlGaleria;
	}

	public void setControlGaleria(ControlGaleria controlGaleria) {
		this.controlGaleria = controlGaleria;
	}
	//Constructor
	public PantallaGaleria() {
		this.controlGaleria = new ControlGaleria();
	}
}
