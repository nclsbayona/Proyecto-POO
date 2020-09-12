package poo.project.Control;

import poo.project.Model.Obra;
import poo.project.Model.Artista;
import java.util.HashSet;
import java.util.Calendar;

public class GestionObras {

    // FALTA BUSCAR POR ESTADO
    public void buscarObra(HashSet<Obra> listaObras, String titulo) {
        for (Obra obra : listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                System.out.println(obra);
            }
        }
    }

    // BUSCAR SOLO POR AÑO- ME FALTA ESO-Y BUSCAR ESTADO
    public void buscarObra(HashSet<Obra> listaObras, Calendar fecha) {
        for (Obra obra : listaObras) {
            if (obra.getFecha().equals(fecha)) {
                System.out.println(obra);
            }
        }
    }
    // FALTA BUSCAR POR ESTADO
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
        //Solo deben salir las Obras que están disponibles para la Compra
            if(/*Puede ser estado o puede ser una búsqueda*/)
            {
                System.out.println(obra);
            }
        }
    }
    //Buscar obra por código
    public boolean buscarObraCodigo(HashSet<Obra> listaObras, long codigo)
    {
        for (Obra obra : listaObras)
       {
            if(codigo == obra.getCodigoObra()){
                return true;
            }
       }
        return false;
    }
    // Crear Obra
    public void crearObra(HashSet<Obra> listaObras, Obra obra) {
        if (!this.buscarObraCodigo(listaObras, obra.getCodigoObra()))
        {
            listaObras.add(obra);
        }
    }
}
