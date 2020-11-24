package view;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;

import control.ControlGaleria;
import exceptions.ArtistNotFoundException;
import exceptions.ArtworkDoesntExistException;
import exceptions.ArtworkExistsException;
import exceptions.ArtworkNotPurchasedException;
import exceptions.ClientDoesntExistException;
import exceptions.ClientExistsException;
import exceptions.ClientNotFoundException;
import exceptions.CodeSizeException;
import exceptions.EmptyArtistListException;
import exceptions.EmptyPurchasesListException;
import exceptions.PurchaseNotFoundException;
import exceptions.TypoException;
import model.*;

//Boundary
public class PantallaGaleria {

	// Main Boundary del Sistema
	public static void main(String[] args) {
		// Creaciones de Objetos
		Scanner entrada = new Scanner(System.in);
		PantallaGaleria pantallaGaleria = new PantallaGaleria();
		String opc = "0";
		int eleccion = 0, salir = 19;
		do {
			pantallaGaleria.clearScreen();
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

	private ControlGaleria controlGaleria;

	// Constructor
	public PantallaGaleria() {
		this.controlGaleria = new ControlGaleria();
	}

	// Limpia la Pantalla
	public void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
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
		String material, peso, codigo, newCodigo;
		Clasificacion clasificacion = null;
		boolean modObra;
		// Registra la decicion del Usuario
		System.out.println("Digita el Numero de la Opcion: ");
		String opcionSeleect = entrada.nextLine();
		System.out.println("---------------------------------");
		System.out.println(" ");
		// Procesa la decición del Usuario
		try {
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
							try {
								System.out.println(controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)));
							} catch (ArtworkDoesntExistException e) {
								System.out.println("No existe o no está disponible");
							}
							break;
						}
						// Busqueda Por año
						case 4: {
							this.clearScreen();// Limpia la Pantalla
							System.out.println("Busca Obras por Fecha");
							System.out.println("Escribe el Año:");
							String anos = entrada.next();
							try {
								fecha.set(Integer.parseInt(anos), 1, 1);
								System.out.println("--Obras de la Fecha " + fecha.get(Calendar.YEAR));
								for (Obra o : controlGaleria.buscarObra(fecha)) {
									System.out.println(o);
								}
							} catch (NumberFormatException e) {
								throw e;
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
					try {
						Long.parseLong(codigoObra);
						controlGaleria.buscarObra(Long.parseLong(codigoObra));
						System.out.println("Ya existe una obra con este codigo");
						break;
					} catch (ArtworkDoesntExistException e) {
						System.out.println("Titulo: ");
						Titulo = entrada.next();
						System.out.println("_________Fecha__________ ");
						System.out.println("Año:");
						ano = entrada.next();
						Integer.valueOf(ano);
						System.out.println("Mes:");
						mes = entrada.next();
						Integer.valueOf(mes);
						System.out.println("Dia:");
						dia = entrada.next();
						Integer.valueOf(dia);
						System.out.println("Precio de referencia: ");
						precioRef = entrada.next();
						Long.parseLong(precioRef);
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
								try {
									controlGaleria.buscarArtista(Long.parseLong(valor));
									controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano,
											mes, dia, controlGaleria.buscarArtista(Long.parseLong(valor)), tema,
											tecnica, clasificacion);
								} catch (TypoException oe) {
									System.out.println("La obra no existe");
									break;
								} catch (EmptyArtistListException ae) {
									System.out.println("La lista de artistas está vacia");
									break;
								} catch (ArtistNotFoundException ne) {
									System.out.println("Nombre: ");
									nombre = entrada.next();
									System.out.println("Apellidos: ");
									apellido = entrada.next();
									System.out.println("Telefono: ");
									telefono = entrada.next();
									try {
										controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones,
												ano, mes, dia, nombre, apellido, telefono, tema, tecnica,
												clasificacion);
										System.out.println("Se ha agregado con exito la obra al nuevo artista");
									} catch (TypoException e1) {
										System.out.println("Error en la obra");
									}
								}
								break;
							case 2:
								// Instalacion
								System.out.println("Descripcion:");
								descripcion = entrada.next();
								System.out.println("Artista");
								System.out.println("Cedula:");
								valor = entrada.next();
								try {
									controlGaleria.buscarArtista(Long.parseLong(valor));
									controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano,
											mes, dia, controlGaleria.buscarArtista(Long.parseLong(valor)), descripcion);
								} catch (TypoException awe) {
									System.out.println("La obra no existe");
									break;
								} catch (EmptyArtistListException ele) {
									System.out.println("Lista de artistas vacía");
									break;
								} catch (ArtistNotFoundException ae) {
									System.out.println("Nombre: ");
									nombre = entrada.next();
									System.out.println("Apellidos: ");
									apellido = entrada.next();
									System.out.println("Telefono: ");
									telefono = entrada.next();
									try {
										controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones,
												ano, mes, dia, nombre, apellido, telefono, descripcion);
										System.out.println("Se ha agregado con exito la obra al nuevo artista");
									} catch (NumberFormatException | TypoException e1) {
										System.out.println("Error en la obra");
									}
								}
								break;
							case 3:
								// Escultura
								System.out.println("Material:");
								material = entrada.next();
								System.out.println("Peso:");
								peso = entrada.next();
								Long.parseLong(peso);
								System.out.println("Artista");
								System.out.println("Cedula:");
								valor = entrada.next();
								try {
									controlGaleria.buscarArtista(Long.parseLong(valor));
									controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones, ano,
											mes, dia, controlGaleria.buscarArtista(Long.parseLong(valor)), material,
											Double.valueOf(peso));
								} catch (TypoException oe) {
									System.out.println("La obra no existe");
									break;
								} catch (EmptyArtistListException ae) {
									System.out.println("La lista de artistas está vacia");
									break;
								} catch (ArtistNotFoundException ne) {
									System.out.println("Nombre: ");
									nombre = entrada.next();
									System.out.println("Apellidos: ");
									apellido = entrada.next();
									System.out.println("Telefono: ");
									telefono = entrada.next();
									Long.parseLong(telefono);
									try {
										controlGaleria.insertarObra(Titulo, precioRef, valor, codigoObra, dimensiones,
												ano, mes, dia, nombre, apellido, telefono, material,
												Double.valueOf(peso));
										System.out.println("Se ha agregado con exito la obra al nuevo artista");
									} catch (TypoException e1) {
										System.out.println("Error en la obra");
									}
								}
								break;
							default:
								break;
						}
						System.out.println("---------------------------------");
						break;
					}
				}
				// Modifica Obra por Codigo
				case 4: {
					retornar = 4;
					this.clearScreen();// Limpia la Pantalla
					String criterio;
					System.out.println("Modificar Obra");
					System.out.println("Escribe el Codigo:");
					buscarObraporCodigo = entrada.next();
					try {
						Obra modificar = controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo));
						System.out.println();
						System.out.println("\tModificando Obra *" + modificar.getTitulo() + "*");
						System.out.println();
						System.out.println("1. Codigo: " + modificar.getCodigoObra());
						System.out.println("2. Titulo: " + modificar.getTitulo());
						System.out.println("3. Fecha: " + modificar.getFecha().get(0) + " / "
								+ modificar.getFecha().get(2) + " / " + modificar.getFecha().get(1));
						System.out.println("4. Precio referencia: " + modificar.getPrecioRef());
						System.out.println("5. Dimensiones del cuadro: " + modificar.getDimensiones());
						System.out.println("6. No modificar ");
						System.out.println();
						System.out.println("Que opcion desea ingresar: ");
						criterio = entrada.next();
						modObra = false;
						switch (Integer.parseInt(criterio)) {
							case 1: {
								System.out.println("Ingrese el codigo nuevo: ");
								do {
									System.out.print("El codigo debe tener 7 caracteres: ");
									newCodigo = entrada.next();
								} while (newCodigo.length() != 7);
								try {
									modObra = this.controlGaleria.modificarObra(modificar, Integer.parseInt(criterio),
											newCodigo);
								} catch (CodeSizeException e) {
									System.out.println("Error en el tamaño del codigo");
								} catch (TypoException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkNotPurchasedException e) {

								} catch (ArtworkExistsException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							case 2: {
								System.out.println("Ingrese Titulo nuevo: ");
								entrada.nextLine();
								String newTittle = entrada.nextLine();
								try {
									modObra = this.controlGaleria.modificarObra(
											controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
											Integer.parseInt(criterio), newTittle);
								} catch (CodeSizeException e) {
									System.out.println("Error en el tamaño del codigo");
								} catch (TypoException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkNotPurchasedException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkExistsException e) {
									System.out.println(e.getMessage());
								}
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
								try {
									modObra = this.controlGaleria.modificarObra(
											controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
											Integer.parseInt(criterio), fecha_);
								} catch (CodeSizeException e) {
									System.out.println("Error en el tamaño del codigo");
								} catch (TypoException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkNotPurchasedException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkExistsException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							case 4: {
								System.out.println("Ingrese el nuevo precio de referencia: ");
								String newPrecio = entrada.next();
								try {
									modObra = this.controlGaleria.modificarObra(
											controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
											Integer.parseInt(criterio), newPrecio);
								} catch (CodeSizeException e) {
									System.out.println("Error en el tamaño del codigo");
								} catch (TypoException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkNotPurchasedException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkExistsException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							case 5: {
								System.out.println("Ingrese la dimension: ");
								dimensiones = entrada.next();
								try {
									modObra = this.controlGaleria.modificarObra(
											controlGaleria.buscarObra(Long.parseLong(buscarObraporCodigo)),
											Integer.parseInt(criterio), dimensiones);
								} catch (ArtworkExistsException e) {
									System.out.println(e.getMessage());
								} catch (CodeSizeException e) {
									System.out.println("Error en el tamaño del codigo");
								} catch (TypoException e) {
									System.out.println(e.getMessage());
								} catch (ArtworkNotPurchasedException e) {
									System.out.println(e.getMessage());
								}
								break;
							}
							case 6: {
								break;
							}
							default:
								System.out.println("Opcion incorrecta");
						}
						if (modObra)
							System.out.println("La obra se modificó correctamente");
						else
							System.out.println("La obra no se modifico correctamente");
					} catch (ArtworkDoesntExistException ae) {
						System.out.println("No se encontro la obra");
						break;
					}
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
					boolean mensaje = false;
					try {
						mensaje = this.controlGaleria.eliminarObra(Long.parseLong(codigo));
					} catch (ArtworkDoesntExistException e) {
						System.out.println(e.getMessage());
						break;
					}
					if (mensaje)
						System.out.println("Eliminado con exito");
					else
						System.out.println("No se pudo eliminar");
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
							try {
								System.out.println(controlGaleria.buscarCliente(Long.parseLong(buscarClienteC)));
							} catch (ClientNotFoundException e) {
								System.out.println(e.getMessage());
							}
							break;
						case 2:
							System.out.println("Ingrese la cedula");
							buscarClienteC = entrada.next();
							try {
								System.out.println(controlGaleria.buscarCliente(Long.parseLong(buscarClienteC), ""));
							} catch (ClientNotFoundException e) {
								System.out.println(e.getMessage());
							}
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
					try {
						controlGaleria.buscarCliente(Integer.parseInt(codigoCliente));
						System.out.println("Este cliente ya existe");
						break;
					} catch (ClientNotFoundException ce) {
						do {
							System.out.println("Ingrese la cedula del cliente");
							valor = entrada.next();
							if (valor.length() < 7)
								System.out.println("Cedula invalida");
						} while (valor.length() < 7);
						try {
							controlGaleria.buscarCliente(Long.parseLong(valor), "cedula");
							System.out.println("Este cliente ya existe");
							break;
						} catch (ClientNotFoundException cne) {
						}
					}
					String nombres;
					boolean agregado = false;
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
					try {
						agregado = controlGaleria.crearCliente(Integer.parseInt(codigoCliente), Long.parseLong(valor),
								nombres, apellidos, direccionEntrega, Long.parseLong(telefono));
					} catch (ClientExistsException e) {
						System.out.println(e.getMessage());
						break;
					} catch (CodeSizeException e) {
						System.out.println(e.getMessage());
						break;
					}
					System.out.println((agregado) ? "Cliente agregado con exito" : "Ocurrio un error");
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
					try {
						controlGaleria.buscarCliente(Long.parseLong(codigoCliente));
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					}
					try {
						controlGaleria.buscarCliente(Long.parseLong(codigoCliente)).printC();
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					}
					do {
						System.out.print("Ingrese numero de atributo a modificar: ");
						respuesta = entrada.next();
					} while (Integer.parseInt(respuesta) > 6 || Integer.parseInt(respuesta) < 0);
					switch (Integer.parseInt(respuesta)) {
						case 1:
							System.out.print("Ingrese el codigo nuevo: ");
							valor = entrada.next();

							if (Long.parseLong(valor) < 0) {
								System.err.println("Codigo invalido");
								return retornar;
							}
							try {
								this.controlGaleria.buscarCliente(Long.parseLong(valor));
								System.out.println("Ya existe un cliente con ese codigo");
							} catch (ClientNotFoundException e) {
							}
							break;
						case 2:
							System.out.print("Ingrese la cedula nueva: ");
							valor = entrada.next();
							try {
								this.controlGaleria.buscarCliente(Long.parseLong(valor), "cedula");
								System.out.println("Ya existe un cliente con esa cedula");
							} catch (ClientNotFoundException e) {
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
							Long.parseLong(valor);
							break;
						default:
							System.err.println("Opcion invalida");
							return retornar;
					}
					try {
						controlGaleria.modificarCliente(controlGaleria.buscarCliente(Long.parseLong(codigoCliente)),
								Integer.parseInt(respuesta), valor);
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					} catch (TypoException e) {
						System.out.println(e.getMessage());
						break;
					}
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
					try {
						this.controlGaleria.eliminarCliente(Long.parseLong(codigoCliente));
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					}
					break;
				}
				case 13: {
					retornar = 13;
					// Realizar compra de una Obra
					System.out.println("Realizar compra de una Obra");
					System.out.println("Ingrese codigo del cliente");
					valor = entrada.next();
					try {
						controlGaleria.buscarCliente(Long.parseLong(valor));
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Ingrese codigo de la obra");
					respuesta = entrada.next();
					try {
						controlGaleria.buscarObra(Long.parseLong(respuesta));
					} catch (ArtworkDoesntExistException e) {
						System.out.println(e.getMessage());
						break;
					}
					try {
						controlGaleria.buscarClienteYObraEnCompra(controlGaleria.buscarCliente(Long.parseLong(valor)),
								controlGaleria.buscarObra(Long.parseLong(respuesta)));
						System.out.println("Ya existe");
						break;
					} catch (EmptyPurchasesListException e) {
					} catch (PurchaseNotFoundException e) {
						try {
							controlGaleria.buscarObraEnCompras(controlGaleria.buscarObra(Long.parseLong(respuesta)));
							System.out.println("Esta obra ya fue comprada");
							break;
						} catch (ArtworkNotPurchasedException e2) {
							System.out.println(e2.getMessage());
							try {
								controlGaleria.realizarCompra(controlGaleria.buscarCliente(Long.parseLong(valor)),
										controlGaleria.buscarObra(Long.parseLong(respuesta)));
								System.out.println("---------------------------------");
							} catch (ArtworkDoesntExistException e1) {
							} catch (ClientDoesntExistException e1) {
								System.out.println(e1.getMessage());
								break;
							} catch (EmptyPurchasesListException e1) {
							} catch (ClientNotFoundException e1) {
								System.out.println(e1.getMessage());
								break;
							}
							break;
						} catch (ArtworkDoesntExistException e2) {
							System.out.println(e2.getMessage());
							break;
						}
					} catch (ClientNotFoundException e) {
						System.out.println(e.getMessage());
					} catch (ArtworkDoesntExistException e) {
						System.out.println(e.getMessage());
					}
					break;
				}
				case 14: {
					retornar = 14;
					// Eliminar compra de obra
					System.out.println("Eliminar compra de una Obra");
					System.out.println("Ingrese codigo de la compra");
					valor = entrada.next();
					try {
						controlGaleria.buscarCompra(valor);
					} catch (EmptyPurchasesListException e) {
						System.out.println(e.getMessage());
						break;
					} catch (PurchaseNotFoundException e) {
						System.out.println(e.getMessage());
						break;
					}
					System.out.println("Seguro? (0/1)");
					respuesta = entrada.next();
					if (Integer.parseInt(respuesta) != 1) {
						break;
					}
					try {
						controlGaleria.eliminCompra(valor);
					} catch (EmptyPurchasesListException e) {
						System.out.println(e.getMessage());
					} catch (PurchaseNotFoundException e) {
						System.out.println(e.getMessage());
					}
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
					Integer.parseInt(valor);
					System.out.println("Año");
					respuesta = entrada.next();
					Integer.parseInt(respuesta);
					for (String s : controlGaleria.listadoDeCompra(valor, respuesta))
						System.out.println(s);
					break;
				}
				case 17: {
					retornar = 17;
					// Ver listado de Artistas más vendidos
					try {
						for (Artista a : controlGaleria.verListadoArtistas().values())
							System.out.println(a);
					} catch (EmptyPurchasesListException e) {
						System.out.println(e.getMessage());
						break;
					}
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
		} catch (NumberFormatException e) {
			System.out.println("Error al escribir: " + e.getCause());
		}
		return retornar;
	}

	// Accesors control galeria
	public ControlGaleria getControlGaleria() {
		return this.controlGaleria;
	}

	// Imprimir asociadas a un cuadro
	public void imprimirAsociadasCuadro() {
		HashSet<Compra> compras = this.controlGaleria.comprasAsociadasACuadro();
		for (Compra c : compras)
			System.out.println(c);
	}

	// Imprime el metodo de esculturas
	public void imprimirEsculturas() {
		HashSet<Obra> esculturas = this.controlGaleria.buscarEsculturas();
		for (Obra o : esculturas)
			System.out.println(o);
	}

	// Imprime el total
	public void imprimirTotal() {
		System.out.println(
				"El precio de todas las obras en el sistema es: $" + this.controlGaleria.calcularPrecioTotal());
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

	// Imprime los artistas
	public void printArtistas() {
		System.out.println("Lista de artistas:");
		for (Artista artista : this.controlGaleria.getListaArtistas().values()) {
			System.out.println(artista);
		}
	}

	// Imprimir listaClientes
	public void printClientes() {
		System.out.println("Imprimiendo la lista de Clientes:");
		for (Cliente cliente : this.controlGaleria.getListaClientes().values()) {
			System.out.println(cliente);
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

	public void setControlGaleria(ControlGaleria controlGaleria) {
		this.controlGaleria = controlGaleria;
	}
}
