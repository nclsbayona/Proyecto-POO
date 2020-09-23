package poo.project.Control;
import poo.project.Model.Obra;
import java.util.HashSet;
import java.util.Calendar;

public class GestionObras {


    public HashSet <Obra> startObras(){

        HashSet <Obra> obras = new HashSet<Obra>();
        Calendar fecha = Calendar.getInstance();
        fecha.set(2020, 11, 01);

        //Creación de instancias
        obras.add(new Obra(1324567, "A", fecha, 20000, "20x5"));
        obras.add(new Obra(2435678, "B", fecha, 20000, "10x8"));
        obras.add(new Obra(3456789, "C", fecha, 15000, "10x2"));
        obras.add(new Obra(1234567, "Mera", fecha, 20000, "20x5"));
        obras.add(new Obra(5432198, "Okaloka", fecha, 20000, "10x8"));
        obras.add(new Obra(7654321, "Machupichu", fecha, 15000, "10x2"));
        return obras;
    }

}
