package control;

import java.util.HashSet;

import exceptions.TypoException;
import model.Artista;
import model.Clasificacion;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
import model.Obra;

import java.util.Calendar;
import java.util.HashMap;

public class GestionObras {

        // Retorna una lista de obras para comenzar el dia
        public HashSet<Obra> listaObras() throws TypoException {

        HashSet<Obra> obras = new HashSet<Obra>();
        Calendar fecha = Calendar.getInstance();
        Calendar fecha1 = Calendar.getInstance();
        Calendar fecha2 = Calendar.getInstance();
        Calendar fecha3 = Calendar.getInstance();
        Calendar fecha4 = Calendar.getInstance();
        Calendar fecha5 = Calendar.getInstance();
        fecha.set(2020, 11, 01);
        // Creación de instancias
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
        return obras;
    }

    // Retorna una lista de artistas para comenzar el dia
    public HashMap<Long, Artista> startArtistas() {
        Calendar fecha = Calendar.getInstance();
        fecha.set(2001, 11, 11);
        HashMap<Long, Artista> artistas = new HashMap<Long, Artista>();
        artistas.put(Long.valueOf(1000471976),
                new Artista(1000471976, "Sebastian", "Herrera Guaitero", fecha, 350612646));
        artistas.put(Long.valueOf(1000512331),
                new Artista(1000512331, "Natalia", "Castro Sepulveda", fecha, 314231233));
        return artistas;
    }
}
