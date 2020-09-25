package poo.project.Control;
import poo.project.Model.Artista;
import poo.project.Model.Obra;
import java.util.HashSet;
import java.util.Calendar;

public class GestionObras {


    public HashSet <Obra> startObras(){

        HashSet <Obra> obras = new HashSet<Obra>();
        Calendar fecha = Calendar.getInstance();
        fecha.set(2020, 11, 01);
        //Creación de instancias
        obras.add(new Obra(1324567, "La gorda", fecha, 20000, "20x5"));
        fecha.set(2010, 5, 20);
        obras.add(new Obra(2435678, "Eva la pilla", fecha, 20000, "10x8"));
        fecha.set(2001, 8, 6);
        obras.add(new Obra(3456789, "Sociopata", fecha, 15000, "10x2"));
        fecha.set(2000, 10, 10);
        obras.add(new Obra(1234567, "Michuelo", fecha, 20000, "20x5"));
        fecha.set(1999, 9, 22); 
        obras.add(new Obra(5432198, "Okalokas", fecha, 20000, "10x8"));
        fecha.set(1984, 2, 3);
        obras.add(new Obra(7654321, "Machupichu", fecha, 15000, "10x2"));
        return obras;
    }
    public HashSet <Artista> startArtistas(){
    	Calendar fecha=Calendar.getInstance();
    	fecha.set(2001, 11, 11);
    	HashSet<Artista>artistas=new HashSet<Artista>();
        artistas.add(new Artista(1000471976,"Sebastian","Herrera Guaitero",fecha,350612646));
        artistas.add(new Artista(1000512331,"Natalia","Castro Sepulveda",fecha,314231233));
        return artistas;
    }
    
}
