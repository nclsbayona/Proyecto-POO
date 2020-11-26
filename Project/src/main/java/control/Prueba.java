package control;

import java.util.Calendar;
import java.io.File;
import java.util.ArrayList;

import model.Clasificacion;
import model.Cliente;
import model.Compra;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
import model.Obra;
import exceptions.TypoException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class Prueba {
    public static void main(String[] args) throws TypoException {
        ControlGaleria cg = new ControlGaleria();
        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.add(
                new Cliente(1, Long.valueOf(1451231), "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212));
        clientes.add(new Cliente(2, Long.valueOf(1112221131), "Fred", "Jones", "20822 SW Luxury Park", 98765432));
        clientes.add(new Cliente(6, Long.valueOf(1111183937), "Juan", "Acosta", "Calle 100 #20-29", 3208426));
        try {
            cg.exportarReporteXML("archivo_de_pruebaClientes.xml", clientes);
            /*JAXBContext context = JAXBContext.newInstance(Cliente.class);
            Unmarshaller um = context.createUnmarshaller();
            Cliente c100=(Cliente) (um.unmarshal(new File("archivo_de_pruebaClientes.xml")));
            System.out.println(c100);*/
        } catch (TypoException e) {
            System.out.println(e.getLocalizedMessage());
        }
        /*ArrayList<Obra> obras = new ArrayList<>();
        Calendar fecha = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        fecha.set(2020, 11, 01);
        // Creación de instancias
        obras.add(new Instalacion(1324567, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"));
        fecha1.set(2010, 5, 20);
        obras.add(new Cuadro(2435678, "Eva pilla", fecha1.getTime(), 20000, "10x8", "Polimorfismo", "Acuarela",
                Clasificacion.OBRA_MAESTRA));
        fecha2.set(2001, 8, 6);
        obras.add(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
                
        try {
            cg.exportarReporteXML("archivo_de_pruebaObras.xml", obras);
        } catch (TypoException e) {
            //
        }

        ArrayList<Compra> compras = new ArrayList<>();
        compras.add(new Compra(1, fecha, false, obras.get(0), clientes.get(0)));
        compras.add(new Compra(2, fecha, false, obras.get(1), clientes.get(1)));
        compras.add(new Compra(3, fecha, false, obras.get(2), clientes.get(2)));
        try {
                cg.exportarReporteXML("archivo_de_pruebaCompras.xml", compras);
        } catch (TypoException e) {
            System.out.println(e.getLocalizedMessage());
        }*/
    }
}
