import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Clasificacion;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
import model.Material;

class CuadroTest {

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
	void testProbarObraMaestra() {
		Cuadro oCuadro = new Cuadro(123, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto",
				"Hola", Clasificacion.OBRA_MAESTRA);
		assertEquals(Clasificacion.OBRA_MAESTRA, oCuadro.getClasificacion());
	}

	@Test
	void testProbarObraSignificativa() {
		Cuadro o = new Cuadro(123, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto", "Hola",
				Clasificacion.OBRA_REPRESENTATIVA);
		assertEquals(Clasificacion.OBRA_REPRESENTATIVA, o.getClasificacion());
	}

	@Test
	void testProbarEscultura() {
		Escultura e = new Escultura((123), "titulo creado", Calendar.getInstance().getTime(), 10000, "10X10",
				"Cuarzo", (2));
		assertFalse(e.getPeso() >= 10);
	}

	@Test
	void testProbarEscultura2() {
		Escultura e = new Escultura((123), "titulo creado", Calendar.getInstance().getTime(), 10000, "10X10",
				"Cuarzo", (12));
		assertFalse(e.getPeso() <= 10);
	}

	@Test
	void testProbar4Materiales() {
		Instalacion i = new Instalacion(1234151, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
				"Descripcion");
		Material m = new Material(1, "Hola", "Este es un material 1");
		Material m2 = new Material(2, "Hola", "Este es un material 2");
		Material m3 = new Material(3, "Hola", "Este es un material 3");
		Material m4 = new Material(4, "Hola", "Este es un material 4");
		HashSet<Material> materiales = new HashSet<>();
		materiales.add(m);
		materiales.add(m2);
		materiales.add(m3);
		materiales.add(m4);
		i.setMateriales(materiales);
		assertEquals(materiales, i.getMateriales());
	}

	@Test
	void testProbarMateriales_1Material() {
		Instalacion i = new Instalacion(1231145, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
				"Descripcion");
		Material m = new Material(1, "Hola", "Este es un material 1");
		i.getMateriales().add(m);
		assertEquals(i.getMateriales().size(), 1);
	}

	// No hay materiales
	@Test
	void testProbarMateriales_0Materiales() {
		Instalacion i = new Instalacion(1112345, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
				"Descripcion");
		assertEquals(i.getMateriales().size(), 0);
	}

	// Casos anormales
	@Test
	void testAnormal() {
		Instalacion i = new Instalacion(1234567, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
				"Descripcion");
		Instalacion i2 = new Instalacion(1232234, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
				"Descripcion");
		assertNotEquals(i, i2);
	}

	@Test
	void testAnormal1() {
		Cuadro o = new Cuadro(1234576, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto", "Hola",
				Clasificacion.OBRA_REPRESENTATIVA);
		Cuadro o1 = new Cuadro(1236510, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto", "Hola",
				Clasificacion.OBRA_REPRESENTATIVA);
		assertNotEquals(o, o1);
	}
}
