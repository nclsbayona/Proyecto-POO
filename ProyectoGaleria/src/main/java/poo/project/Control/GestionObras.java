package poo.project.Control;

import poo.project.Model.Obra;
import poo.project.Model.Artista;
import java.util.Calendar;

public class GestionObras {

    public Obra buscarObra(HashSet<Obra> listaObras, String titulo) {
        for (Obra obra : listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                return obra;
            }
        }
        return null;
    }

    public Obra buscarObra(HashSet<Obra> listaObras, Calendar fecha) {
        for (Obra obra : listaObras) {
            if (obra.getFecha().equals(fecha)) {
                System.out.println(obra);
            }
        }
        return null;
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
    public void verListadoObras(HashSet<Obra> listaObras) {
        for (Obra obra : listaObras) {
            System.out.println(obra);
        }
    }
    //Crear Obra
    public void crearObra(HashSet<Obra> listaObras,Obra obra)
    {

        listaObras.add(obra);
    }
}
