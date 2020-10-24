import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import poo.Control.ControlGaleria;
import poo.Model.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControlGaleriaTest {

	ControlGaleria controlGaleria = new ControlGaleria();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
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

	@Test
	// Mirar si una obra ya fue comprada,no deberia estar
	void testObreEnCompra() {
		boolean expected = false;
		Obra obra = this.controlGaleria.buscarObra(1234567);
		assertEquals(expected, this.controlGaleria.obraEnCompra(obra));
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

	@Test
	// Rectificar si existe compra, en este caso deberia ser falso
	void existeCodCompra() {
		assertFalse(this.controlGaleria.existeCodCompra(1));
	}

	@Test
	// Busca un cliente en las compras
	void buscarClienteYObraEnCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o = new Cuadro(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Cubismo", "Pastel",
                Clasificacion.OBRA_REPRESENTATIVA);
		Cliente c = new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);
		assertFalse(this.controlGaleria.buscarClienteYObraEnCompra(c, o));
	}

}