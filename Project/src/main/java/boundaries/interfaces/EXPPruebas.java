package boundaries.interfaces;

import java.io.File;
import java.util.*;

import javax.swing.JFileChooser;

import exceptions.TypoException;
import model.Clasificacion;
import model.Compra;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
import model.Obra;

public class EXPPruebas {
    public static void main(String[] args) {
        HashSet<Obra> obras = new HashSet<Obra>();
        Calendar fecha = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        Calendar fecha3 = Calendar.getInstance();
        Calendar fecha4 = Calendar.getInstance();
        Calendar fecha5 = Calendar.getInstance();
        fecha.set(2020, 11, 01);
        // Creación de instancias
        try {
            String ruta=null;
            JFileChooser fchooser=new JFileChooser();
            fchooser.setCurrentDirectory(new File("."));
            if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                ruta=fchooser.getSelectedFile().toString();
            Exportacion exportacion=new Exportacion();
            obras.add(new Instalacion(1324567, "La gorda", fecha.getTime(), 20000, "20x5", "Prueba de instalacion"));
            fecha1.set(2010, 5, 20);
        obras.add(new Cuadro(2435678, "Eva pilla", fecha1.getTime(), 20000, "10x8", "Polimorfismo", "Acuarela",
                Clasificacion.OBRA_MAESTRA));
                fecha2.set(2001, 8, 6);
                obras.add(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
                fecha3.set(2000, 10, 10);
                fecha4.set(1999, 10, 10);
                obras.add(new Instalacion(1234567, "Michuelo", fecha3.getTime(), 20000, "20x5", "Esta es otra prueba"));
                obras.add(new Cuadro(5432198, "Okalokas", fecha4.getTime(), 20000, "10x8", "Cubismo", "Pastel",
                        Clasificacion.OBRA_REPRESENTATIVA));
                fecha5.set(1984, 2, 3);
                obras.add(new Escultura(7654321, "Machupichu", fecha5.getTime(), 15000, "10x2", "Marmol", 1550));
                exportacion.exportarXML(ruta,"a", obras);
                HashSet<Obra>cuadros=new HashSet<>();
                cuadros.add(new Escultura(3456789, "Sociopata", fecha2.getTime(), 15000, "10x2", "Cemento", 2084));
                if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                ruta=fchooser.getSelectedFile().toString();
                exportacion.exportarXML(ruta,"p", cuadros);
                HashSet<Compra>compras=new HashSet<>();
                compras.add(new Compra(1));
                compras.add(new Compra(2));
                if (fchooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                ruta=fchooser.getSelectedFile().toString();
                exportacion.exportarXML(ruta, "m", compras);
        } catch (TypoException e) {
            System.out.println(e.getMessage());
        }   
    }
}
