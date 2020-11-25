package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.TypoException;
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

	// Casos anormales
	@Test
	void testAnormal() {
		Instalacion i;
		try {
			i = new Instalacion(1234567, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
					"Descripcion");

			Instalacion i2 = new Instalacion(1232234, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
					"Descripcion");
			assertNotEquals(i, i2);
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAnormal1() {
		Cuadro o;
		try {
			o = new Cuadro(1234576, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto",
					"Hola", Clasificacion.OBRA_REPRESENTATIVA);

			Cuadro o1 = new Cuadro(1236510, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20",
					"Cuarto", "Hola", Clasificacion.OBRA_REPRESENTATIVA);
			assertNotEquals(o, o1);
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testProbar4Materiales() {
		Instalacion i = null;
		try {
			i = new Instalacion(1234151, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
					"Descripcion");
		} catch (TypoException e) {
			fail(e.getMessage());
		}
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
	void testProbarEscultura() {
		Escultura e = null;
		try {
			e = new Escultura(1231241, "titulo creado", Calendar.getInstance().getTime(), 10000, "10X10", "Cuarzo", (2));
		} catch (TypoException e1) {
			fail(e1.getMessage());
		}
		assertFalse(e.getPeso() >= 10);
	}

	@Test
	void testProbarEscultura2() {
		Escultura e;
		try {
			e = new Escultura(1231241, "titulo creado", Calendar.getInstance().getTime(), 10000, "10X10", "Cuarzo", (12));
			assertFalse(e.getPeso() <= 10);
		} catch (TypoException e1) {
			fail(e1.getMessage());
		}
	}

	// No hay materiales
	@Test
	void testProbarMateriales_0Materiales() {
		Instalacion i;
		try {
			i = new Instalacion(1112345, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
					"Descripcion");
			assertEquals(i.getMateriales().size(), 0);
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testProbarMateriales_1Material() {
		Instalacion i;
		try {
			i = new Instalacion(1231145, "Titulo creado", Calendar.getInstance().getTime(), 10000, "10x10",
					"Descripcion");
			Material m = new Material(1, "Hola", "Este es un material 1");
			i.getMateriales().add(m);
			assertEquals(i.getMateriales().size(), 1);
		} catch (TypoException e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testProbarObraMaestra() {
		Cuadro oCuadro;
		try {
			oCuadro = new Cuadro(1231241, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto",
					"Hola", Clasificacion.OBRA_MAESTRA);
			assertEquals(Clasificacion.OBRA_MAESTRA, oCuadro.getClasificacion());
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testProbarObraSignificativa() {
		Cuadro o;
		try {
			o = new Cuadro(1231423, "Titulo creado", Calendar.getInstance().getTime(), 200000, "20x20", "Cuarto", "Hola",
					Clasificacion.OBRA_REPRESENTATIVA);
			assertEquals(Clasificacion.OBRA_REPRESENTATIVA, o.getClasificacion());
		} catch (TypoException e) {
			fail(e.getMessage());
		}
	}
}
