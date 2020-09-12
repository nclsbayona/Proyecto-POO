package poo.project.Control;

import poo.project.Model.Obra;
import poo.project.Model.Artista;
import java.util.HashSet;
import java.util.Calendar;

public class GestionObras {
    private HashSet<Obra> listaObras = new HashSet<Obra>();

    public Obra buscarObra(String titulo) {
        for (Obra obra : listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                return obra;
            }
        }
        return null;
    }

    public Obra buscarObra(Calendar fecha) {
        for (Obra obra : listaObras) {
            if (obra.getFecha().equals(fecha)) 
                System.out.println(obra);
                return obra;
            }
        }return null;

    }
    /*
     * public Obra buscarObra(Artista artista) { for (Obra obra : listaObras) { if
     * (obra.getArtista() != null) { for (Artista art : obra.getArtista()) { if (art
     * == artista) {
     * 
     * } }
     * 
     * } } }
     */

    // Ver listado de obras
    public void verListadoObras() {
        for (Obra obra : listaObras) {
        }
    }
}
