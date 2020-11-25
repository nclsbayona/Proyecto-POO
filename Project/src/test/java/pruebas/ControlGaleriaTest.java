package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Calendar;
import java.util.HashSet;

import control.ControlGaleria;
import exceptions.*;
import model.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ControlGaleriaTest {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	ControlGaleria controlGaleria = new ControlGaleria();

	@Test
	// Busca un cliente en las compras (FALLA POR EL TAMAÑO DE LA LISTA)
	void buscarClienteYObraEnCompra() {
		Calendar fecha3 = Calendar.getInstance();
		try {
			final Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA);
			Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
			assertThrows(EmptyPurchasesListException.class, () -> {
				this.controlGaleria.buscarClienteYObraEnCompra(c, o);
			});
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	// Busca cliente y obra en compra (FALLA PORQUE NO LO ENCUENTRA)
	void buscarClienteYObraEnCompra3() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o;
		try {
			o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA);
			Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
			Cliente cl = new Cliente(6, 1234672, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
			this.controlGaleria.realizarCompra(c, o);
			assertThrows(PurchaseNotFoundException.class, () -> {
				this.controlGaleria.buscarClienteYObraEnCompra(cl, o);
			});
		} catch (ArtworkDoesntExistException | ClientDoesntExistException | TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	// Busca un cliente en las compras (BIEN)
	void buscarClienteYObraEnCompra2() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = null;
		try {
			o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA);
			Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
			this.controlGaleria.realizarCompra(c, o);
			assertNotNull(this.controlGaleria.buscarClienteYObraEnCompra(c, o));
		} catch (TypoException | ArtworkDoesntExistException | ClientDoesntExistException | EmptyPurchasesListException
				| PurchaseNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test
	// Rectificar si existe compra, en este caso deberia ser verdadero (BIEN)
	void existeCodCompra() {
		Obra obra = null;
		try {
			obra = this.controlGaleria.buscarObra(1234567);
			Cliente c = new Cliente(100, Long.valueOf(143325536), "Jorge", "Muñoz", "tr 100 # 20-36", 98826628);
			this.controlGaleria.realizarCompra(c, obra);
			assertTrue(this.controlGaleria.existeCodCompra(1));
		} catch (ArtworkDoesntExistException | ClientDoesntExistException | PurchaseDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test
	// Rectificar si existe compra, en este caso deberia ser falso (NO EXISTE)
	void existeCodCompra2() {
		assertThrows(PurchaseDoesntExistException.class, () -> {
			this.controlGaleria.existeCodCompra(1);
		});
	}

	@Test
	// addCircObrayArtista Caso verdadero
	void addCircObrayArtista() {
		Calendar fecha = Calendar.getInstance();
		try {
			assertTrue(this.controlGaleria.addCircObryArt(
					new Instalacion(1342573, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"),
					new Artista(1000512331, "Natalia", "Castro Sepulveda", fecha, 314231233)));
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test // insertarObra caso falso (OBRA EXISTE)
	void addCircObraYArtista2() {
		Calendar fecha = Calendar.getInstance();
		assertThrows(TypoException.class, () -> {
			this.controlGaleria.addCircObryArt(
					new Instalacion(1234567, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"),
					new Artista(1000512331, "Natalia", "Castro Sepulveda", fecha, 314231233));
		});
	}

	@Test // Add cliente mal (CLIENTE EXISTE))
	void testAddCliente() {
		Cliente client = new Cliente(1, Long.valueOf(114223731), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertThrows(ClientExistsException.class, () -> {
			this.controlGaleria.addCliente(client);
		});
	}

	@Test // Add cliente mal , por cedula
	void testAddCliente1() {
		Cliente client = new Cliente(231, Long.valueOf(1183937), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertThrows(ClientExistsException.class, () -> {
			this.controlGaleria.addCliente(client);
		});
	}

	@Test // Add cliente bien
	void testAddCliente2() {
		Cliente client = new Cliente(231, Long.valueOf(1001191582), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		try {
			assertEquals(client, this.controlGaleria.addCliente(client));
		} catch (ClientExistsException e) {
			fail(e.getMessage());
		}
	}

	// Funciona
	@Test
	void testAddObra() {
		Calendar fecha = Calendar.getInstance();
		fecha.set(2020, 11, 01);
		Obra n = null;
		try {
			n = this.controlGaleria.addObra(
					new Instalacion(1111111, "Probando", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"));
			Obra o = new Instalacion(1111111, "Probando", fecha.getTime(), 20000, "20x5", "Prueba de instalacion");
			assertEquals(o, n);
		} catch (TypoException | ArtworkExistsException e) {
			fail(e.getMessage());
		}

	}

	// No funciona
	@Test
	void testAddObra2() {
		Calendar fecha2 = Calendar.getInstance();

		assertThrows(ArtworkExistsException.class, () -> {
			this.controlGaleria
					.addObra(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
		});

	}

	@Test // Agregar obra a la lista
	void testAnadirObra() {
		Calendar fecha3 = Calendar.getInstance();
		try {
			Obra expected = new Escultura(1111111, "Prueba", fecha3.getTime(), 20000, "20x5", "Hola", 2019);

			assertEquals(expected, this.controlGaleria.addObra(expected));
		} catch (TypoException | ArtworkExistsException e) {
			fail(e.getMessage());
		}
	}

	@Test // Agregar repetida a la lista
	void testAnadirObra2() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected;
		try {
			expected = new Instalacion(1234567, "Prueba", fecha3.getTime(), 20000, "20x5", "Hola");
			assertThrows(ArtworkExistsException.class, () -> {
				this.controlGaleria.addObra(expected);
			});
		} catch (TypoException e) {
			fail(e.getMessage());
		}

	}

	// Funciona
	@Test
	void testBuscarArtista() {
		Calendar fecha = Calendar.getInstance();
		Artista expected = new Artista(Long.parseLong("1000471976"), "Sebastian", "Herrera Guaitero", fecha, 350612646);
		try {
			assertEquals(expected, this.controlGaleria.buscarArtista(Long.valueOf("1000471976")));
		} catch (NumberFormatException | EmptyArtistListException | ArtistNotFoundException e) {
			fail(e.getMessage());
		}
	}

	// No funciona
	@Test
	void testBuscarArtista2() {
		assertThrows(ArtistNotFoundException.class, () -> {
			this.controlGaleria.buscarArtista(1000000000);
		});
	}

	@Test // Buscar cliente mal , por cedula
	void testBuscarCliente() {
		assertThrows(ClientNotFoundException.class, () -> {
			this.controlGaleria.buscarCliente(1010101, "");
		});
	}

	@Test // Buscar cliente bien , por cedula
	void testBuscarCliente2() {
		Cliente client = new Cliente(1, Long.valueOf(1422373), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		try {
			assertEquals(client, this.controlGaleria.buscarCliente(1422373, ""));
		} catch (ClientNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Buscar compra, debe encontrarlo
	void testBuscarCompra() {
		try {
			Calendar fecha3 = Calendar.getInstance();
			Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA);
			Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
			this.controlGaleria.realizarCompra(c, o);
		} catch (TypoException | ArtworkDoesntExistException | ClientDoesntExistException e) {
			fail(e.getMessage());
		}

		try {
			assertEquals(this.controlGaleria.buscarCompra("1"), this.controlGaleria.buscarCompra("1"));
		} catch (EmptyPurchasesListException | PurchaseNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Buscar compra que noexiste, no deberia encontrar
	void testBuscarCompra2() {
		assertThrows(PurchaseNotFoundException.class, () -> {
			controlGaleria.buscarCompra("0");
		});
	}

	// Deberia funcionar

	@Test
	void testBuscarEscultura() {
		HashSet<Escultura> expected = new HashSet<Escultura>();
		Calendar fecha2 = Calendar.getInstance();
		Calendar fecha5 = Calendar.getInstance();
		fecha2.set(2001, 8, 6);
		fecha5.set(1984, 2, 3);
		try {
			expected.add(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
			expected.add(new Escultura(7654321, "Machupichu", fecha5.getTime(), 15000, "10x2", "Marmol", 1550));
		} catch (TypoException e) {
			fail(e.getMessage());
		}
		assertEquals(expected, this.controlGaleria.buscarEsculturas());
	}

	// No deberia funcionar

	@Test
	void testBuscarEscultura2() {
		HashSet<Obra> expected = new HashSet<>();
		assertNotEquals(expected, this.controlGaleria.buscarEsculturas());
	}

	@Test // Busca una obra que ya deberia estar
	void testBuscarObraCodigo() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = null;
		try {
			expected = new Instalacion(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Esta es otra prueba");
		} catch (TypoException e1) {
			fail(e1.getMessage());
		}
		try {
			assertEquals(expected, this.controlGaleria.buscarObra(1234567));
		} catch (ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test
	// Busca una obra que se encuentra en la lista
	void testBuscarObraCodigo2() {
		assertThrows(ArtworkDoesntExistException.class, () -> {
			this.controlGaleria.buscarObra(7918273);
		});
	}

	// Buscar fecha que se encuentra
	@Test
	void testBuscarObraFecha() {
		HashSet<Obra> obras = new HashSet<>();
		Calendar fecha4 = Calendar.getInstance();
		Calendar fecha = Calendar.getInstance();
		fecha.set(1999, 1, 1);
		fecha4.set(1999, 9, 22);
		try {
			obras.add(new Cuadro(5432198, "Okalokas", fecha4.getTime(), 20000, "10x8", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA));
		} catch (TypoException e) {
			fail(e.getMessage());
		}
		assertEquals(obras, this.controlGaleria.buscarObra(fecha));
	}

	@Test // Busca una obra que no se encuentra en la lista
	void testBuscarObraFecha2() {
		Calendar fecha = Calendar.getInstance();
		fecha.set(2222, 11, 2);
		HashSet<Obra> obras = new HashSet<>();
		assertEquals(obras, this.controlGaleria.buscarObra(fecha));
	}

	@Test
	void testBuscarObraPorArtista() {
		HashSet<Obra> obras = new HashSet<>();
		Calendar fecha3 = Calendar.getInstance();
		try {
			this.controlGaleria.insertarObra("Hola", "1000", "1000471976", "1920901", "20x20", "11", "2", "1",
					this.controlGaleria.buscarArtista(Long.valueOf("1000471976")), "tema", "tecnica",
					Clasificacion.OBRA_MAESTRA);
		} catch (NumberFormatException | TypoException | EmptyArtistListException | ArtistNotFoundException e) {
			fail(e.getMessage());
		}
		Obra o = null;
		try {
			o = new Cuadro(Long.parseLong("1920901"), "Hola", fecha3.getTime(), Float.parseFloat("1000"), "20x20",
					"tema", "tecnica", Clasificacion.OBRA_MAESTRA);
		} catch (NumberFormatException | TypoException e) {
			fail(e.getMessage());
		}
		obras.add(o);
		assertEquals(obras, this.controlGaleria.buscarObraporArtista("Sebastian"));
	}

	@Test
	void testBuscarObraPorArtista2() {
		HashSet<Obra> expected = new HashSet<Obra>();
		assertEquals(expected, this.controlGaleria.buscarObraporArtista("Natalia"));
	}

	// Buscar que se
	@Test
	void testBuscarObraTitulo() {
		HashSet<Obra> obras = new HashSet<>();
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = null;
		try {
			expected = new Instalacion(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Esta es otra prueba");
		} catch (TypoException e) {
			fail(e.getMessage());
		}
		obras.add(expected);
		assertEquals(obras, this.controlGaleria.buscarObra("Michuelo"));
	}

	@Test // Busca una obra que no se encuentra en la lista
	void testBuscarObraTitulo2() {
		HashSet<Obra> obras = new HashSet<>();
		assertEquals(obras, this.controlGaleria.buscarObra("La Maria"));
	}

	@Test // Crear cliente mal , por cedula
	void testCrearCliente() {
		assertThrows(ClientExistsException.class, () -> {
			this.controlGaleria.crearCliente(2, 9253620, "Nicolas", "Bayona", "Calle 4-43-60", 34251653);
		});
	}

	@Test // Crear cliente bien , por cedula
	void testCrearCliente2() {
		try {
			assertTrue(this.controlGaleria.crearCliente(101, Long.valueOf(1001191743), "Lucas", "Ramirez",
					"Diagonal 68 #78-03", 3208426));
		} catch (ClientExistsException | CodeSizeException e) {
			fail(e.getMessage());
		}
	}

	@Test // Eliminar cliente, debe eliminarlo
	void testEliminarCliente() {
		try {
			assertTrue(this.controlGaleria.eliminarCliente(1));
		} catch (ClientNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Eliminar cliente que no existe, no deberia eliminar
	void testEliminarCliente2() {
		assertThrows(ClientNotFoundException.class, () -> {
			this.controlGaleria.eliminarCliente(21);
		});
	}

	@Test // Eliminar compra, debe encontrarlo
	void testEliminarCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = null;
		try {
			o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
					Clasificacion.OBRA_REPRESENTATIVA);
		} catch (TypoException e1) {
			fail(e1.getMessage());
		}
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		try {
			this.controlGaleria.realizarCompra(c, o);
		} catch (ArtworkDoesntExistException | ClientDoesntExistException e) {
			fail(e.getMessage());
		}
		try {
			assertNotNull(this.controlGaleria.eliminCompra("1"));
		} catch (EmptyPurchasesListException | PurchaseNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Eliminar compra que no existe, no deberia encontrar
	void testEliminarCompra2() {
		assertThrows(PurchaseNotFoundException.class, () -> {
			this.controlGaleria.eliminCompra("0");
		});
	}

	@Test // Eliminar obra, debe eliminarlo
	void testEliminarObra() {
		try {
			assertTrue(this.controlGaleria.eliminarObra(1234567));
		} catch (ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test // Eliminar obra que no existe, no deberia eliminar
	void testEliminarObra2() {
		assertThrows(ArtworkDoesntExistException.class, () -> {
			this.controlGaleria.eliminarObra(Long.valueOf("9991872"));
		});
	}

	@Test // insertarObra caso verdadero
	void testInsertarObra() {
		Artista a = null;
		try {
			a = this.controlGaleria.buscarArtista(1000512331);
		} catch (EmptyArtistListException | ArtistNotFoundException e) {
			fail(e.getMessage());
		}
		try {
			assertTrue(this.controlGaleria.insertarObra("Hola", "30000", "1000512331", "2001009", "20x20", "1999", "11",
					"24", a, "Hola2"));
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test // Insertar Obra caso falso
	void testInsertarObra2() {
		try {
			final Artista a = this.controlGaleria.buscarArtista(1000512331);
			assertThrows(TypoException.class, () -> {
				this.controlGaleria.insertarObra("Hola", "30000", "1000512331", "200009", "20x20", "1999", "11", "24",
						a, "Hola");
			});
		} catch (EmptyArtistListException | ArtistNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Modificar cliente caso positivo
	void testModificarCliente() {
		Cliente c = null;
		try {
			c = this.controlGaleria.buscarCliente(1);
			assertTrue(this.controlGaleria.modificarCliente(c, 1, "1347291"));
		} catch (NumberFormatException | ClientNotFoundException | TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test // Modificar cliente caso falso
	void testModificarCliente2() {
		assertThrows(TypoException.class, () -> {
			this.controlGaleria.modificarCliente(this.controlGaleria.buscarCliente(1), 0, "2");
		});
	}

	@Test // Modificar cliente caso cedula repetida
	void testModificarCliente3() {
		Cliente c;
		try {
			c = this.controlGaleria.buscarCliente(1);
			assertThrows(TypoException.class, () -> {
				this.controlGaleria.modificarCliente(c, 2, "1422373");
			});
		} catch (ClientNotFoundException e) {
			fail(e.getMessage());
		}
	}

	@Test // Modificar Obra caso positivo
	void testModificarObra() {
		Obra o;
		try {
			o = this.controlGaleria.buscarObra(1234567);
			assertTrue(this.controlGaleria.modificarObra(o, 1, "1031456"));
		} catch (CodeSizeException | TypoException | ArtworkNotPurchasedException | ArtworkExistsException
				| ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test // Modificar obra caso codigo repetido
	void testModificarObra2() {
		try {
			Obra o = this.controlGaleria.buscarObra(1234567);
			assertThrows(ArtworkExistsException.class, () -> {
				this.controlGaleria.modificarObra(o, 1, "1234567");
			});
		} catch (ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test // Modificar obra valor mal insertado
	void testModificarObra3() {
		Obra o;
		try {
			o = this.controlGaleria.buscarObra(Long.valueOf(1234567));
			assertThrows(TypoException.class, () -> {
				this.controlGaleria.modificarObra(o, 1, "123");
			});
		} catch (ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test // Mirar si una obra ya fue comprada,no deberia estar
	void testObraEnCompra() {
		Obra obra;
		try {
			obra = this.controlGaleria.buscarObra(Long.valueOf(1234567));
			assertThrows(PurchaseDoesntExistException.class, () -> {
				this.controlGaleria.obraEnCompra(obra);
			});
		} catch (ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}

	@Test // Mirar si una obra ya fue comprada, si deberia estar
	void testObraEnCompra2() {
		Obra obra;
		try {
			obra = this.controlGaleria.buscarObra(1234567);
			Cliente c = new Cliente(100, Long.valueOf(143325536), "Jorge", "Muñoz", "tr 100 # 20-36", 98826628);
			this.controlGaleria.realizarCompra(c, obra);
			assertTrue(this.controlGaleria.obraEnCompra(obra));
		} catch (PurchaseDoesntExistException | ClientDoesntExistException | ArtworkDoesntExistException e) {
			fail(e.getMessage());
		}
	}
	// Funciona

	@Test
	void testPrecioTotal() {
		double expected = 0;
		assertEquals(expected, this.controlGaleria.calcularPrecioTotal());
	}
	// No funciona

	@Test
	void testPrecioTotal2() {
		double expected = -1;
		assertNotEquals(expected, this.controlGaleria.calcularPrecioTotal());
	}

	@Test // Realizar compra caso verdadero
	void testRealizarCompra() {
		Calendar fecha = Calendar.getInstance();
		try {
			assertTrue(this.controlGaleria.realizarCompra(
					new Cliente(3, Long.valueOf(9253620), "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426),
					new Escultura(1234567, "Machupichu", fecha.getTime(), 15000, "10x2", "Marmol", 1550)));
		} catch (ArtworkDoesntExistException | ClientDoesntExistException | TypoException e) {
			fail(e.getMessage());
		}
	}

	// Realizar compra caso falso
	@Test
	void testRealizarCompra2() {
		Calendar fecha = Calendar.getInstance();
		assertThrows(TypoException.class, () -> {
			this.controlGaleria.realizarCompra(
					new Cliente(3, Long.valueOf(9253620), "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426),
					new Escultura(726382, "Machupichu", fecha.getTime(), 15000, "10x2", "Marmol", 1550));
		});
	}

	// Exportar a XML (NO PASA NADA)
	@Test
	void exportarXMLTest() {
		String ruta = "archivo.xml";
		File archivo = new File(ruta);
		HashSet<Cliente> clientes = new HashSet<>();
		try {
			this.controlGaleria.exportarReporteXML(ruta, Cliente.class, clientes);
			assertTrue(archivo.delete());
		} catch (TypoException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			//Error eliminando
		}
	}
	// Exportar a XML (ERROR)
	@Test
	void exportarXML2Test() {
		String ruta = "archivo";
		File archivo = new File(ruta);
		HashSet<Cliente> clientes = new HashSet<>();
		try {
			this.controlGaleria.exportarReporteXML(ruta, Cliente.class, clientes);
			archivo.delete();
			//Lo elimino otra vez
			assertFalse(archivo.delete());
		} catch (TypoException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
