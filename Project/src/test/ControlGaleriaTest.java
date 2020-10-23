package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import poo.Control.ControlGaleria;
import poo.Model.Cliente;
import poo.Model.Obra;

class ControlGaleriaTest {

	ControlGaleria controlGaleria=new ControlGaleria();
	
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
	//Busca una obra que ya deberia estar
	void testBuscarObraCodigo() {
		Calendar fecha3 = Calendar.getInstance();
		Obra expected=new Obra(1234567, "Michuelo",fecha3, 20000, "20x5");
		Assertions.assertEquals(expected,this.controlGaleria.buscarObra(1234567));
	}
	@Test
	//Busca una obra que no se encuentra en la lista
	void testBuscarObraCodigo2()
	{
		Assertions.assertNull(this.controlGaleria.buscarObra(7918273));
	}
	@Test
	//Mirar si una obra ya fue comprada,no deberia estar 
	void testObreEnCompra()
	{
		boolean expected=false;
		Obra obra=this.controlGaleria.buscarObra(1234567);
		Assertions.assertEquals(expected,this.controlGaleria.obraEnCompra(obra));
	}
	@Test
	//Agregar obra a la lista
	void testAnadirObra()
	{
		Calendar fecha3 = Calendar.getInstance();
		Obra expected=new Obra(1111111, "Prueba",fecha3, 20000, "20x5");
		Assertions.assertEquals(expected,this.controlGaleria.addObra(expected));
	}
	@Test
	//Agregar repetida a la lista
	void testAnadirObra2()
	{
		Calendar fecha3 = Calendar.getInstance();
		Obra expected=new Obra(1234567, "Prueba",fecha3, 20000, "20x5");
		Assertions.assertNull(this.controlGaleria.addObra(expected));
	}
	@Test
	//Rectificar si existe compra, en este caso deberia ser falso
	void existeCodCompra()
	{
		Assertions.assertFalse(this.controlGaleria.existeCodCompra(1));
	}

	@Test
	  //Busca un cliente en las compras
	void buscarClienteYObraEnCompra() {
		Calendar fecha3 = Calendar.getInstance();
		Obra o=new Obra(1234567, "Michuelo",fecha3, 20000, "20x5");
		Cliente c=new Cliente(2, 1293723, "Fred", "Jones", "20822 SW Luxury Park", 98765432);

	}
	
	

}
