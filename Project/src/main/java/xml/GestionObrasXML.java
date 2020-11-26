package xml;

import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cuadro;
import model.Obra;
@XmlRootElement
public class GestionObrasXML {
	private HashSet<Cuadro> list;

	public GestionObrasXML(HashSet<Cuadro> list) {
		this.list = list;
	}

	public GestionObrasXML() {
	}

	public void addCuadro(Cuadro c){
		this.list.add(c);
	}
	@XmlElement
	public HashSet<Cuadro> getList() {
		return list;
	}

	public void setList(HashSet<Cuadro> list) {
		this.list = list;
	}
}
