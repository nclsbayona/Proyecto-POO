package control;

import java.util.TreeSet;

import model.Cliente;
import excepciones.TypoException;

public class Prueba {
    public static void main(String[] args) {
        ControlGaleria cg = new ControlGaleria();
        TreeSet<Cliente> clientes = new TreeSet<>();
        try {
            clientes.add(
                    new Cliente(1, Long.valueOf(1422373), "Alfredo", "Santamaria", "2085 NW Traverse Street", 6543212));
            clientes.add(new Cliente(2, Long.valueOf(1293723), "Fred", "Jones", "20822 SW Luxury Park", 98765432));
            clientes.add(new Cliente(6, Long.valueOf(1183937), "Juan", "Acosta", "Calle 100 #20-29", 3208426));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            cg.exportarReporteXML("archivo_de_prueba.xml", model.Cliente.class, clientes);
        } catch (TypoException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
