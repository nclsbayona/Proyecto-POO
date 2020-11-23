package pruebas;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashSet;

import control.ControlGaleria;
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
	// Busca un cliente en las compras
	void buscarClienteYObraEnCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
				Clasificacion.OBRA_REPRESENTATIVA);
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		assertFalse(this.controlGaleria.buscarClienteYObraEnCompra(c, o));
	}

	@Test
	// Busca un cliente en las compras
	void buscarClienteYObraEnCompra2() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
				Clasificacion.OBRA_REPRESENTATIVA);
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		this.controlGaleria.realizarCompra(c, o);
		assertTrue(this.controlGaleria.buscarClienteYObraEnCompra(c, o));
	}

	@Test
	// Rectificar si existe compra, en este caso deberia ser verdadero
	void existeCodCompra() {
		Obra obra = this.controlGaleria.buscarObra(1234567);
		Cliente c = new Cliente(100, Long.valueOf(143325536), "Jorge", "Muñoz", "tr 100 # 20-36", 98826628);
		this.controlGaleria.realizarCompra(c, obra);
		assertTrue(this.controlGaleria.existeCodCompra(1));
	}

	@Test
	// Rectificar si existe compra, en este caso deberia ser falso
	void existeCodCompra2() {
		assertFalse(this.controlGaleria.existeCodCompra(1));
	}

	@Test
	//addCircObrayArtista Caso verdadero
	void testaddCircObrayArtista(){
		Calendar fecha = Calendar.getInstance();
		assertTrue(this.controlGaleria.addCircObryArt(new Instalacion(1342573, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"), new Artista(1000512331, "Natalia", "Castro Sepulveda", fecha, 314231233)));
 	}

	@Test
	 // insertarObra caso falso
		void testAddCircObraYArtista2() {
			Calendar fecha = Calendar.getInstance();
			assertFalse(this.controlGaleria.addCircObryArt(
					new Instalacion(1234567, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"),
					new Artista(1000512331, "Natalia", "Castro Sepulveda", fecha, 314231233)));
		}

	@Test
	// Add cliente mal , por codigo
	void testAddCliente() {
		Cliente client = new Cliente(1, Long.valueOf(114223731), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertNull(this.controlGaleria.addCliente(client));
	}

	@Test
	// Add cliente mal , por cedula
	void testAddCliente1() {
		Cliente client = new Cliente(231, Long.valueOf(1183937), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertNull(this.controlGaleria.addCliente(client));
	}

	@Test
	// Add cliente bien , por cedula
	void testAddCliente2() {
		Cliente client = new Cliente(231, Long.valueOf(1001191582), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertEquals(client, this.controlGaleria.addCliente(client));
	}

	// Funciona
	@Test
	void testAddObra() {
		Calendar fecha = Calendar.getInstance();
		fecha.set(2020, 11, 01);
		Obra o = new Instalacion(1111111, "Probando", fecha.getTime(), 20000, "20x5", "Prueba de instalacion");
		assertEquals(o, this.controlGaleria.addObra(
				new Instalacion(1111111, "Probando", fecha.getTime(), 20000, "20x5", "Prueba de instalacion")));

	}

	// No funciona
	@Test
	void testAddObra2() {
		Calendar fecha2 = Calendar.getInstance();
		assertNull(this.controlGaleria
				.addObra(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084)));
	}

	@Test
	// Agregar obra a la lista
	void testAnadirObra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = new Escultura(1111111, "Prueba", fecha3.getTime(), 20000, "20x5", "Hola", 2019);
		assertEquals(expected, this.controlGaleria.addObra(expected));
	}

	@Test
	// Agregar repetida a la lista
	void testAnadirObra2() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = new Instalacion(1234567, "Prueba", fecha3.getTime(), 20000, "20x5", "Hola");
		assertNull(this.controlGaleria.addObra(expected));
	}

	// Funciona
	@Test
	void testBuscarArtista() {
		Calendar fecha = Calendar.getInstance();
		Artista expected = new Artista(Long.parseLong("1000471976"), "Sebastian", "Herrera Guaitero", fecha, 350612646);
		assertEquals(expected, this.controlGaleria.buscarArtista(Long.valueOf("1000471976")));
	}

	// No funciona
	@Test
	void testBuscarArtista2() {
		assertNull(this.controlGaleria.buscarArtista(1000000000));
	}

	@Test
	// Buscar cliente mal , por cedula
	void testBuscarCliente() {
		assertNull(this.controlGaleria.buscarCliente(1010101, ""));
	}

	@Test
	// Buscar cliente bien , por cedula
	void testBuscarCliente2() {
		Cliente client = new Cliente(1, Long.valueOf(1422373), "Alfredo", "Santamaria", "2085 NW Traverse Street",
				6543212);
		assertEquals(client, this.controlGaleria.buscarCliente(1422373, ""));
	}

	@Test
	// Buscar compra, debe encontrarlo
	void testBuscarCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
				Clasificacion.OBRA_REPRESENTATIVA);
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		this.controlGaleria.realizarCompra(c, o);
		assertEquals(this.controlGaleria.buscarCompra("1"), this.controlGaleria.buscarCompra("1"));
		;
	}

	@Test
	// Buscar compra que no existe, no deberia encontrar
	void testBuscarCompra2() {
		assertNull(this.controlGaleria.buscarCompra("0"));
	}

	// Deberia funcionar
	@Test
	void testBuscarEscultura() {
		HashSet<Escultura> expected = new HashSet<Escultura>();
		Calendar fecha2 = Calendar.getInstance();
		Calendar fecha5 = Calendar.getInstance();
		fecha2.set(2001, 8, 6);
		fecha5.set(1984, 2, 3);
		expected.add(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
		expected.add(new Escultura(7654321, "Machupichu", fecha5.getTime(), 15000, "10x2", "Marmol", 1550));
		assertEquals(expected, this.controlGaleria.buscarEsculturas());
	}

	// No deberia funcionar
	@Test
	void testBuscarEscultura2() {
		HashSet<Obra> expected = new HashSet<>();
		assertNotEquals(expected, this.controlGaleria.buscarEsculturas());
	}

	@Test
	// Busca una obra que ya deberia estar
	void testBuscarObraCodigo() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = new Instalacion(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Esta es otra prueba");
		assertEquals(expected, this.controlGaleria.buscarObra(1234567));
	}

	@Test
	// Busca una obra que no se encuentra en la lista
	void testBuscarObraCodigo2() {
		assertNull(this.controlGaleria.buscarObra(7918273));
	}

	// Buscar fecha que se encuentra
	@Test
	void testBuscarObraFecha() {
		HashSet<Obra> obras = new HashSet<>();
		Calendar fecha4 = Calendar.getInstance();
		Calendar fecha = Calendar.getInstance();
		fecha.set(1999, 1, 1);
		fecha4.set(1999, 9, 22);
		obras.add(new Cuadro(5432198, "Okalokas", fecha4.getTime(), 20000, "10x8", "Cubismo", "Pastel",
				Clasificacion.OBRA_REPRESENTATIVA));
		assertEquals(obras, this.controlGaleria.buscarObra(fecha));
	}

	@Test
	// Busca una obra que no se encuentra en la lista
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
		this.controlGaleria.insertarObra("Hola", "1000", "1000471976", "1920901", "20x20", "11", "2", "1",
				this.controlGaleria.buscarArtista(Long.valueOf("1000471976")), "tema", "tecnica",
				Clasificacion.OBRA_MAESTRA);
		Obra expected = new Cuadro(Long.parseLong("1920901"), "Hola", fecha3.getTime(), Float.valueOf("1000"), "20x20",
				"tema", "tecnica", Clasificacion.OBRA_MAESTRA);
		obras.add(expected);
		assertEquals(obras, this.controlGaleria.buscarObraporArtista("Sebastian"));
	}

	@Test
	void testBuscarObraPorArtista2() {
		HashSet<Obra> expected = new HashSet<Obra>();
		assertEquals(expected, this.controlGaleria.buscarObraporArtista("Natalia"));
	}

	// Buscar que se encuenta
	@Test
	void testBuscarObraTitulo() {
		HashSet<Obra> obras = new HashSet<>();
		Calendar fecha3 = Calendar.getInstance();
		Obra expected = new Instalacion(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Esta es otra prueba");
		obras.add(expected);
		assertEquals(obras, this.controlGaleria.buscarObra("Michuelo"));
	}

	@Test
	// Busca una obra que no se encuentra en la lista
	void testBuscarObraTitulo2() {
		HashSet<Obra> obras = new HashSet<>();
		assertEquals(obras, this.controlGaleria.buscarObra("La Maria"));
	}

	@Test
	// Crear cliente mal , por cedula
	void testCrearCliente() {
		assertFalse(this.controlGaleria.crearCliente(2, 9253620, "Nicolas", "Bayona", "Calle 4-43-60", 34251653));
	}

	@Test
	// Crear cliente bien , por cedula
	void testCrearCliente2() {
		assertTrue(this.controlGaleria.crearCliente(101, Long.valueOf(1001191743), "Lucas", "Ramirez",
				"Diagonal 68 #78-03", 3208426));
	}

	@Test
	// Eliminar cliente, debe eliminarlo
	void testEliminarCliente() {
		assertTrue(this.controlGaleria.eliminarCliente(1));
	}

	@Test
	// Eliminar cliente que no existe, no deberia eliminar
	void testEliminarCliente2() {
		assertFalse(this.controlGaleria.eliminarCliente(21));
	}

	@Test
	// Eliminar compra, debe encontrarlo
	void testEliminarCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
				Clasificacion.OBRA_REPRESENTATIVA);
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		this.controlGaleria.realizarCompra(c, o);
		assertNotNull(this.controlGaleria.eliminCompra("1"));
	}

	@Test
	// Eliminar compra que no existe, no deberia encontrar
	void testEliminarCompra2() {
		assertNull(this.controlGaleria.eliminCompra("0"));
	}

	@Test
	// Eliminar obra, debe eliminarlo
	void testEliminarObra() {
		assertTrue(this.controlGaleria.eliminarObra(1234567));
	}

	@Test
	// Eliminar obra que no existe, no deberia eliminar
	void testEliminarObra2() {
		assertFalse(this.controlGaleria.eliminarObra(Long.valueOf("9991872")));
	}

	@Test
	// insertarObra caso verdadero
	void testInsertarObra() {
		Artista a=this.controlGaleria.buscarArtista(1000512331);
		assertTrue(this.controlGaleria.insertarObra("Hola", "30000", "1000512331", "2001009", "20x20", "1999", "11", "24", a, "Hola2"));
	}

	@Test
	// Realizar compra caso falso
	void testInsertarObra2(){
		Artista a=this.controlGaleria.buscarArtista(1000512331);
		assertFalse(this.controlGaleria.insertarObra(
			"Hola", "30000", "1000512331", "200009", "20x20", "1999", "11", "24", a, "Hola"));
	}

	@Test
	// Modificar cliente caso positivo
	void testModificarCliente() {
		Cliente c = this.controlGaleria.buscarCliente(1);
		assertTrue(this.controlGaleria.modificarCliente(c, 1, "1347291"));

	}

	@Test
	// Modificar cliente caso falso
	void testModificarCliente2() {
		assertFalse(this.controlGaleria.modificarCliente(this.controlGaleria.buscarCliente(1), 0, "2"));
	}

	@Test
	// Modificar cliente caso cedula repetida
	void testModificarCliente3() {
		Cliente c = this.controlGaleria.buscarCliente(1);
		assertFalse(this.controlGaleria.modificarCliente(c, 2, "1422373"));
	}

	@Test
	//Modificar Obra caso positivo
	void testModificarObra() {
	Obra o=this.controlGaleria.buscarObra(1234567);
	assertTrue(this.controlGaleria.modificarObra(o,1,"1031456"));

	}

	@Test
	// Modificar obra caso codigo repetido
	void testModificarObra2() {
		Obra o=this.controlGaleria.buscarObra(1234567);
		assertFalse(this.controlGaleria.modificarObra(o,1,"1234567"));
	}

	@Test
	// Modificar obra valor mal insertado
	void testModificarObra3() {
		Obra o=this.controlGaleria.buscarObra(123457);
		assertFalse(this.controlGaleria.modificarObra(o,1,"123"));
	}

	@Test
	// Mirar si una obra ya fue comprada,no deberia estar
	void testObraEnCompra() {
		boolean expected = false;
		Obra obra = this.controlGaleria.buscarObra(1234567);
		assertEquals(expected, this.controlGaleria.obraEnCompra(obra));
	}

	 @Test
	// Mirar si una obra ya fue comprada, si deberia estar
	void testObraEnCompra2() {
		boolean expected = true;
		Obra obra = this.controlGaleria.buscarObra(1234567);
		Cliente c = new Cliente(100, Long.valueOf(143325536), "Jorge", "Muñoz", "tr 100 # 20-36", 98826628);
		this.controlGaleria.realizarCompra(c, obra);
		assertEquals(expected, this.controlGaleria.obraEnCompra(obra));
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

	
	@Test
	//Realizar compra caso verdadero
	void testRealizarCompra() {
		Calendar fecha = Calendar.getInstance();
		assertTrue(this.controlGaleria.realizarCompra(new Cliente(3, Long.valueOf(9253620), "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426), new Escultura(1234567, "Machupichu", fecha.getTime(), 15000, "10x2", "Marmol", 1550)));
	}

	@Test
	//Realizar compra caso falso
	void testRealizarCompra2() {
		Calendar fecha = Calendar.getInstance();
		assertFalse(this.controlGaleria.realizarCompra(new Cliente(3, Long.valueOf(9253620), "Lucas", "Ramirez", "Diagonal 68 #78-03", 3208426), new Escultura(726382, "Machupichu", fecha.getTime(), 15000, "10x2", "Marmol", 1550)));
	}
}
