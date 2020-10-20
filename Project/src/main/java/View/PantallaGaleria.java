package View;

import Control.ControlGaleria;
import Model.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;

//Boundary
public class PantallaGaleria {

	private ControlGaleria controlGaleria;

	// Limpia la Pantalla
	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// Imprime los artistas
	public void printArtistas() {
		System.out.println("Lista de artistas:");
		for (Artista artista : this.controlGaleria.getListaArtistas().values()) {
			System.out.println(artista);
		}
	}

	// Imprime el metodo de esculturas
	public void imprimirEsculturas() {
		HashSet<Obra> esculturas = this.controlGaleria.buscarEsculturas();
		for (Obra o : esculturas)
			System.out.println(o);
	}

	// Imprimir asociadas a un cuadro
	public void imprimirAsociadasCuadro() {
		HashSet<Compra> compras = this.controlGaleria.comprasAsociadasACuadro();
		for (Compra c : compras)
			System.out.println(c);
	}

	// Imprime el total
	public void imprimirTotal() {
		System.out.println(
				"El precio de todas las obras en el sistema es: $" + this.controlGaleria.calcularPrecioTotal());
	}

	// Imprimir listaClientes
	public void printClientes() {
		System.out.println("Imprimiendo la lista de Clientes:");
		for (Cliente cliente : this.controlGaleria.getListaClientes().values()) {
			System.out.println(cliente);
		}
	}

	// Imprimir listaObras
	public void listaObras() {
		System.out.println("Imprimiendo la lista de Obras:");
		if (this.controlGaleria.getListaCompras().isEmpty()) {
			for (Obra obra : this.controlGaleria.getListaObras()) {
				System.out.println(obra);
			}
			return;
		} else {
			for (Obra obra : this.controlGaleria.getListaObras()) {
				if (!this.controlGaleria.obraEnCompra(obra))
					System.out.println(obra);
			}
		}
	}

	// Imprimir listaCompras
	public void printCompras() {
		System.out.println("Imprimiendo la lista de Compras:");
		for (Compra compra : this.controlGaleria.getListaCompras()) {
			System.out.println(compra);
		}
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
		System.out.println("6. Imprimir obras de tipo escultura");
		System.out.println("7. Imprimir valor total de todas las obras");
		System.out.println(" ");
		System.out.println("-- Clientes ----------------");
		System.out.println("8. Listar Clientes");
		System.out.println("9. Buscar Cliente");
		System.out.println("10. Insertar Cliente");
		System.out.println("11. Modificar Cliente");
		System.out.println("12. Eliminar Cliente");
		System.out.println(" ");
		System.out.println("-- Compras ------------------");
		System.out.println("13. Compra de Obra");
		System.out.println("14. Eliminar Compra");
		System.out.println("15. Listar Compras");
		System.out.println("16. Listar Compras Filtado");
		System.out.println("17. Listar Artistas mas Vendidos");
		System.out.println("18. Compras asociadas a un Cuadro");
		System.out.println("19. Salir");
		System.out.println("---------------------------------");
	}

	// Controla las decisiones del usuario
	public int controlMenu() {
		// Variables internas
		int retornar = 0;
		Scanner entrada = new Scanner(System.in);
		entrada.useDelimiter("\n");
		ControlGaleria controlGaleria = this.getControlGaleria();
		String opcionObras = "0", buscarObraporCodigo, buscarCliente, buscarClienteC, buscarObraporArtista = " ";
		Calendar fecha = Calendar.getInstance();
		String buscarObraporTitulo = " ", codigoCliente, Titulo, ano, mes, dia, precioRef, dimensiones;
		String codigoObra, respuesta, nombre, apellido, telefono, valor, tema, tecnica, descripcion, seleObra;
		String material, peso, codigo;
		Clasificacion clasificacion = null;
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
				retornar = 1;
				this.clearScreen();// Limpia la Pantalla
				// Imprime por pantalla todas las obras Disponibles
				System.out.println("Lista De Obras Disponibles: ");
				this.listaObras();
				System.out.println("---------------------------------");
				break;
			}

			// Busca obras por titulo, autor, fecha o codigo
			case 2: {
				retornar = 2;
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
						for (Obra o : controlGaleria.buscarObra(buscarObraporTitulo)) {
							System.out.println(o);
						}
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
						for (Obra o : controlGaleria.buscarObraporArtista(buscarObraporArtista)) {
							System.out.println(o);
						}
						break;
					}
					// Busqueda Por Codigo
					case 3: {
						this.clearScreen();// Limpia la Pantalla
						System.out.println("Busca Obras por Codigo");
						System.out.println("Escribe el Codigo:");
						buscarObraporCodigo = entrada.next();
						System.out.println("--Obra del Codigo " + buscarObraporCodigo);
						if (controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)) != null) {
							System.out.println(controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)));
						}
						break;
					}
					// Busqueda Por año
					case 4: {
						this.clearScreen();// Limpia la Pantalla
						System.out.println("Busca Obras por Fecha");
						System.out.println("Escribe el Año:");
						int anos = entrada.nextInt();
						fecha.set(anos, 1, 1);
						System.out.println("--Obras de la Fecha " + fecha.get(Calendar.YEAR));
						for (Obra o : controlGaleria.buscarObra(fecha)) {
							System.out.println(o);
						}
						break;
					}
				}
				System.out.println("---------------------------------");
				break;// Case 2
			}
			// Inserta Obra
			case 3: {
				retornar = 3;
				// Falta arreglar esta parte
				this.clearScreen();// Limpia la Pantalla
				System.out.println("Insertar Obra");
				this.printArtistas();

				System.out.print("1. Cuadro\n2. Instalación\n3. Escultura\nIngrese eleccion: ");
				do {
					seleObra = entrada.next();
				} while (!(seleObra.equals("1") || seleObra.equals("2") || seleObra.equals("3")));
				do {
					System.out.println("Codigo de la obra(7 digitos):");
					codigoObra = entrada.next();
				} while (codigoObra.length() != 7);
				if (controlGaleria.buscarObra(Long.parseLong(codigoObra)) != null) {
					System.out.println("Ya existe una obra con este codigo");
					break;
				}
				System.out.println("Titulo: ");
				Titulo = entrada.next();
				System.out.println("_________Fecha__________ ");
				System.out.println("Año:");
				ano = entrada.next();
				System.out.println("Mes:");
				mes = entrada.next();
				System.out.println("Dia:");
				dia = entrada.next();
				System.out.println("Precio de referencia: ");
				precioRef = entrada.next();
				System.out.println("Dimensiones: ");
				dimensiones = entrada.next();
				switch (Integer.valueOf(seleObra)) {
					case 1:
						// Cuadro
						System.out.println("Tema:");
						tema = entrada.next();
						System.out.println("Tecnica:");
						tecnica = entrada.next();
						System.out.println("Clasificación:");
						do {
							System.out.println("1. Obra maestra\n2. Obra representativa");
							buscarClienteC = entrada.next();
						} while (!(buscarClienteC.equals("1") || buscarClienteC.equals("2")));
						if (Integer.valueOf(buscarClienteC) == 1)
							clasificacion = Clasificacion.OBRA_MAESTRA;
						else
							clasificacion = Clasificacion.OBRA_REPRESENTATIVA;
						System.out.println("Artista");
						System.out.println("Cedula:");
						valor = entrada.next();
						if (controlGaleria.buscarArtista(Long.parseLong(valor)) != null) {
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, controlGaleria.buscarArtista(Long.parseLong(valor)), tema, tecnica,
									clasificacion);
						} else {
							System.out.println("Nombre: ");
							nombre = entrada.next();
							System.out.println("Apellidos: ");
							apellido = entrada.next();
							System.out.println("Telefono: ");
							telefono = entrada.next();
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, nombre, apellido, telefono, tema, tecnica, clasificacion);
							System.out.println("Se ha agregado con exito la obra al nuevo artista");
						}
						break;
					case 2:
						// Instalacion
						System.out.println("Descripcion:");
						descripcion = entrada.next();
						System.out.println("Artista");
						System.out.println("Cedula:");
						valor = entrada.next();
						if (controlGaleria.buscarArtista(Long.parseLong(valor)) != null) {
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, controlGaleria.buscarArtista(Long.parseLong(valor)), descripcion);
						} else {
							System.out.println("Nombre: ");
							nombre = entrada.next();
							System.out.println("Apellidos: ");
							apellido = entrada.next();
							System.out.println("Telefono: ");
							telefono = entrada.next();
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, nombre, apellido, telefono, descripcion);
							System.out.println("Se ha agregado con exito la obra al nuevo artista");
						}
						break;
					case 3:
						// Escultura
						System.out.println("Material:");
						material = entrada.next();
						System.out.println("Peso:");
						peso = entrada.next();
						System.out.println("Artista");
						System.out.println("Cedula:");
						valor = entrada.next();
						if (controlGaleria.buscarArtista(Long.parseLong(valor)) != null) {
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, controlGaleria.buscarArtista(Long.parseLong(valor)), material,
									Double.valueOf(peso));
						} else {
							System.out.println("Nombre: ");
							nombre = entrada.next();
							System.out.println("Apellidos: ");
							apellido = entrada.next();
							System.out.println("Telefono: ");
							telefono = entrada.next();
							controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano, mes,
									dia, nombre, apellido, telefono, material, Double.valueOf(peso));
							System.out.println("Se ha agregado con exito la obra al nuevo artista");
						}
						break;
					default:
						break;
				}
				System.out.println("---------------------------------");
				break;
			}
			// Modifica Obra por Codigo
			case 4: {
				retornar = 4;
				this.clearScreen();// Limpia la Pantalla
				String criterio;
				System.out.println("Modificar Obra");
				System.out.println("Escribe el Codigo:");
				buscarObraporCodigo = entrada.next();
				if (controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)) != null) {
					System.out.println();
					System.out.println("\tModificando Obra *"
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getTitulo() + "*");
					System.out.println();
					System.out.println("1. Codigo: "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getCodigoObra());
					System.out.println(
							"2. Titulo: " + controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getTitulo());
					System.out.println("3. Fecha: "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getFecha().get(0) + " / "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getFecha().get(2) + " / "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getFecha().get(1));
					System.out.println("4. Precio referencia: "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getPrecioRef());
					System.out.println("5. Dimensiones del cuadro: "
							+ controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)).getDimensiones());
					System.out.println("6. No modificar ");
					System.out.println();
					System.out.println("Que opcion desea ingresar: ");
					criterio = entrada.next();
					switch (Integer.parseInt(criterio)) {
						case 1: {
							System.out.println("Ingrese el codigo nuevo: ");
							String newCodigo = entrada.next();
							this.controlGaleria.modificarObra(
									controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
									Integer.parseInt(criterio), newCodigo);
							break;
						}
						case 2: {
							System.out.println("Ingrese Titulo nuevo: ");
							entrada.nextLine();
							String newTittle = entrada.nextLine();
							this.controlGaleria.modificarObra(
									controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
									Integer.parseInt(criterio), newTittle);
							break;
						}
						case 3: {
							String fecha_;
							System.out.println("\tFecha nueva (YY/MM/DD)");
							System.out.println("Año:");
							ano = entrada.next();
							System.out.println("Mes:");
							mes = entrada.next();
							System.out.println("Dia:");
							dia = entrada.next();
							fecha_ = ano + "/" + mes + "/" + dia;
							this.controlGaleria.modificarObra(
									controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
									Integer.parseInt(criterio), fecha_);
							break;
						}
						case 4: {
							System.out.println("Ingrese el nuevo precio de referencia: ");
							String newPrecio = entrada.next();
							this.controlGaleria.modificarObra(
									controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
									Integer.parseInt(criterio), newPrecio);
							break;
						}
						case 5: {
							System.out.println("Ingrese la dimension: ");
							dimensiones = entrada.next();
							this.controlGaleria.modificarObra(
									controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
									Integer.parseInt(criterio), dimensiones);
							break;
						}
						case 6: {
							break;
						}
						default:
							System.out.println("Opcion incorrecta");
					}
				} else
					System.out.println("No se encontro el la obra");
				System.out.println("---------------------------------");
				break;
			}
			// Elimina Obra Por Codigo
			case 5: {
				retornar = 5;
				this.clearScreen();
				System.out.println("Eliminar Obra");
				System.out.println("Escribe el Codigo:");
				codigo = entrada.next();
				this.controlGaleria.eliminarObra(Long.parseLong(codigo));
				System.out.println("---------------------------------");
				break;
			}
			case 6: {
				retornar = 6;
				// Imprimir obras de tipo escultura
				System.out.println("Obras de tipo escultura: ");
				this.imprimirEsculturas();
				System.out.println("---------------------------------");
				break;
			}
			case 7: {
				retornar = 7;
				// Imprimir valor total de todas las obras
				System.out.println("Valor total de todas las obras: ");
				this.imprimirTotal();
				System.out.println("---------------------------------");
				break;
			}
			// Clientes
			// Lista Clientes Activos
			case 8: {
				retornar = 8;
				this.clearScreen();// Limpia la Pantalla
				System.out.println("Listar Clientes");
				this.printClientes();
				System.out.println("---------------------------------");
				break;
			}
			// Buscar Clientes Activos
			case 9: {
				retornar = 9;
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
						System.out
								.println(controlGaleria.buscarCliente(Long.parseLong(buscarClienteC), "cedula") != null
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
			case 10: {
				retornar = 10;
				this.clearScreen();// Limpia la Pantalla
				System.out.println("Insertar clientes");
				System.out.println("Ingrese el codigo del cliente");
				codigoCliente = entrada.next();
				if (Integer.parseInt(codigoCliente) < 0) {
					System.out.println("Codigo invalido");
					return retornar;
				}
				if (controlGaleria.buscarCliente(Integer.parseInt(codigoCliente)) != null) {
					System.out.println("Este cliente ya existe");
					break;
				}
				do {
					System.out.println("Ingrese la cedula del cliente");
					valor = entrada.next();
					if (valor.length() < 7) {
						System.out.println("Cedula invalida");
					}
				} while (valor.length() < 7);
				if (controlGaleria.buscarCliente(Long.parseLong(valor), "cedula") != null) {
					System.out.println("Este cliente ya existe");
					break;
				}
				String nombres;
				System.out.println("Ingrese los nombres del cliente");
				nombres = entrada.next();
				String apellidos;
				System.out.println("Ingrese los apellidos del cliente");
				apellidos = entrada.next();
				String direccionEntrega;
				System.out.println("Ingrese la direccion de entrega del cliente");
				direccionEntrega = entrada.next();
				System.out.println("Ingrese el telefono del cliente");
				telefono = entrada.next();
				controlGaleria.crearCliente(Integer.parseInt(codigoCliente), Long.parseLong(valor), nombres, apellidos,
						direccionEntrega, Long.parseLong(telefono));
				controlGaleria.organizarListaClientes();
				System.out.println("---------------------------------");
				break;
			}
			// Modificar cliente
			case 11: {
				retornar = 11;
				this.clearScreen();// Limpia la Bundle
				System.out.println("Modificar Cliente");
				System.out.print("Ingrese el codigo del cliente a modificar: ");
				codigoCliente = entrada.next();
				if (controlGaleria.buscarCliente(Long.parseLong(codigoCliente)) == null) {
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
							return retornar;
						}
						if (this.controlGaleria.buscarCliente(Long.parseLong(valor)) != null) {
							System.out.println("Ya existe un cliente con ese codigo");
							return retornar;
						}
						break;
					case 2:
						System.out.print("Ingrese la cedula nueva: ");
						valor = entrada.next();
						if (this.controlGaleria.buscarCliente(Long.parseLong(valor), "cedula") != null) {
							System.out.println("Ya existe un cliente con esa cedula");
							return retornar;
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
						return retornar;
				}
				controlGaleria.modificarCliente(controlGaleria.buscarCliente(Long.parseLong(codigoCliente)),
						Integer.parseInt(respuesta), valor);
				this.controlGaleria.organizarListaClientes();
				System.out.println("---------------------------------");
				break;
			}

			case 12: {
				retornar = 12;
				// Eliminar cliente
				this.clearScreen();// Limpia la Pantalla
				System.out.println("Eliminar Cliente");
				System.out.println("Ingrese el codigo del cliente a eliminar: ");
				codigoCliente = entrada.next();
				this.controlGaleria.eliminarCliente(Long.parseLong(codigoCliente));
				System.out.println("---------------------------------");
				break;
			}
			case 13: {
				retornar = 13;
				// Realizar compra de una Obra
				System.out.println("Realizar compra de una Obra");
				System.out.println("Ingrese codigo del cliente");
				valor = entrada.next();
				if (controlGaleria.buscarCliente(Long.parseLong(valor)) == null) {
					System.out.println("No existe");
					return retornar;
				}
				System.out.println("Ingrese codigo de la obra");
				respuesta = entrada.next();
				if (controlGaleria.buscarObra(Long.parseLong(respuesta)) == null) {
					System.out.println("No existe la obra");
					return retornar;
				}
				if (controlGaleria.buscarClienteYObraEnCompra(controlGaleria.buscarCliente(Long.parseLong(valor)),
						controlGaleria.buscarObra(Long.parseLong(respuesta)))) {
					System.out.println("Esta compra ya existe en el sistema");
					return retornar;
				}
				if (controlGaleria.buscarObraEnCompras(controlGaleria.buscarObra(Long.parseLong(respuesta)))) {
					System.out.println("Esta obra ya fue comprada");
					return retornar;
				}
				controlGaleria.realizarCompra(controlGaleria.buscarCliente(Long.parseLong(valor)),
						controlGaleria.buscarObra(Long.parseLong(respuesta)));
				System.out.println("---------------------------------");
				break;
			}
			case 14: {
				retornar = 14;
				// Eliminar compra de obra
				System.out.println("Eliminar compra de una Obra");
				System.out.println("Ingrese codigo de la compra");
				valor = entrada.next();
				if (controlGaleria.buscarCompra(valor) == null) {
					System.out.println("La compra no existe");
					break;
				}
				System.out.println("Seguro? (0/1)");
				respuesta = entrada.next();
				if (Integer.parseInt(respuesta) != 1) {
					break;
				}
				controlGaleria.eliminCompra(valor);
				System.out.println("---------------------------------");
				break;
			}
			case 15: {
				retornar = 15;
				// Ver listado de Compras existentes
				System.out.println("Ver listado de Compras existentes");
				this.printCompras();
				System.out.println("---------------------------------");
				break;
			}
			case 16: {
				retornar = 16;
				// Ver listado de Compras para un mes y año específico insertado por el
				// usuario
				System.out.println("Mes");
				valor = entrada.next();
				System.out.println("Año");
				respuesta = entrada.next();
				controlGaleria.listadoDeCompra(valor, respuesta);
				break;
			}
			case 17: {
				retornar = 17;
				// Ver listado de Artistas más vendidos
				for (Artista a : controlGaleria.verListadoArtistas().values())
					System.out.println(a);
				break;
			}
			case 18: {
				retornar = 18;
				// Compras asociadas a un Cuadro
				System.out.println("Compras asociadas a un cuadro: ");
				this.imprimirAsociadasCuadro();
				System.out.println("---------------------------------");
				break;
			}
			case 19: {
				retornar = 19;
				// Salir
				break;
			}
			default:
				System.out.println("Opcion incorrecta!");
				break;
		}
		return retornar;
	}

	// Main Boundary del Sistema
	public static void main(String[] args) {

		// Creaciones de Objetos
		Scanner entrada = new Scanner(System.in);
		PantallaGaleria pantallaGaleria = new PantallaGaleria();
		String opc = "0";
		int eleccion = 0, salir = 19;
		do {
			// Muestra el Menu al Usuario
			pantallaGaleria.printMenu();
			// Controla las deciciones del usuario
			eleccion = pantallaGaleria.controlMenu();
			if (eleccion != salir) {
				System.out.println("Desea ingresar otra opción (1/0)");
				opc = entrada.next();
			}
		} while (!opc.equals("0") && eleccion != salir);
		entrada.close();
	}

	// Accesors control galeria
	public ControlGaleria getControlGaleria() {
		return this.controlGaleria;
	}

	public void setControlGaleria(ControlGaleria controlGaleria) {
		this.controlGaleria = controlGaleria;
	}

	// Constructor
	public PantallaGaleria() {
		this.controlGaleria = new ControlGaleria();
	}
}
