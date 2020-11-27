package boundaries.interfaces;

import java.util.Collection;
import java.util.TreeSet;

import control.ControlGaleria;
import exceptions.TypoException;
import model.Escultura;
import model.Instalacion;
import reportes.GestionArtistasReporte;
import reportes.GestionClientesReporte;
import reportes.GestionComprasReporte;
import reportes.GestionObrasReporte;
import model.Artista;
import model.Cliente;
import model.Compra;
import model.Cuadro;;

public class Exportacion {


    public <T> boolean exportarXML(String route, String opc, Collection<T> coleccion) throws TypoException {
        ControlGaleria cg = new ControlGaleria();
        //Ubicación
        // Obras: a, p
        // Clientes: f
        // Compras: m, n, q
        // Artistas: o
        if ((opc.equals("a")) || (opc.equals("p"))) {
            TreeSet<Cuadro> cuadros = new TreeSet<>();
            TreeSet<Escultura> esculturas = new TreeSet<>();
            TreeSet<Instalacion> instalacions = new TreeSet<>();
            for (T co : coleccion) {
                if (co instanceof Cuadro)
                    cuadros.add((Cuadro) (co));
                else if (co instanceof Escultura)
                    esculturas.add((Escultura) (co));
                else if (co instanceof Instalacion)
                    instalacions.add((Instalacion) (co));
            }
            GestionObrasReporte goR = new GestionObrasReporte(cuadros, esculturas, instalacions);
            cg.exportarReporteXML(route, GestionObrasReporte.class, goR);
        }
        else if (opc.equals("f")){
            TreeSet<Cliente> clientes = new TreeSet<>();
            for (T co : coleccion) {
                clientes.add((Cliente)(co));
            }
            GestionClientesReporte gcR = new GestionClientesReporte(clientes);
            cg.exportarReporteXML(route, GestionClientesReporte.class, gcR);
        }
        else if (opc.equals("o")){
            TreeSet<Artista> artistas = new TreeSet<>();
            for (T co : coleccion) {
                artistas.add((Artista)(co));
            }
            GestionArtistasReporte gaR = new GestionArtistasReporte(artistas);
            cg.exportarReporteXML(route, GestionArtistasReporte.class, gaR);
        }
        else{
            TreeSet<Compra> compras = new TreeSet<>();
            for (T co : coleccion) {
                compras.add((Compra)(co));
            }
            GestionComprasReporte gcoR = new GestionComprasReporte(compras);
            cg.exportarReporteXML(route, GestionComprasReporte.class, gcoR);
        }
        return true;
    }
}
