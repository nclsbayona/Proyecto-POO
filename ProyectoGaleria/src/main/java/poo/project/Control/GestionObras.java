package poo.project.Control;
import poo.project.Model.Obra;
import poo.project.Model.Artista;
import java.util.HashSet;
import java.util.Calendar;
public class GestionObras {
    /*Preguntar si se puede o no tener la misma tanto en control galeria como acá, en cuyo caso se pueden
    quitar estos comentarios y texto basura y el de control galería*/
    private HashSet<Obra> listaObras;

    public HashSet<Obra> getListaObras() {
        return this.listaObras;
    }

    private void setListaObras(HashSet<Obra> listaObras) {
        this.listaObras = listaObras;
    }
    
    public void addListaObras(HashSet<Obra> listaObras){
        this.setListaObras((this.listaObras==null)?listaObras:this.listaObras);
    }

    public Obra addObra(Obra obra){
        this.listaObras.add(obra);
        return obra;
    }
    // FALTA BUSCAR POR ESTADO
    public void buscarObra(String titulo) {
        for (Obra obra : listaObras) {
            if (obra.getTitulo().equals(titulo)) {
                System.out.println(obra);
            }
        }
    }

    // BUSCAR SOLO POR AÑO- ME FALTA ESO-Y BUSCAR ESTADO
    public void buscarObra(Calendar fecha) {
        for (Obra obra : this.listaObras) {
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
        //Puede ser estado o puede ser una búsqueda
            if()
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
