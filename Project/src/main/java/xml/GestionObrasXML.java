package xml;

import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlRootElement;
import model.Obra;
@XmlRootElement
public class GestionObrasXML {
	private HashSet<Obra> list;

	public GestionObrasXML(HashSet<Obra> list) {
		this.list = list;
	}

	public GestionObrasXML() {
	}

	public HashSet<Obra> getList() {
		return list;
	}

	public void setList(HashSet<Obra> list) {
		this.list = list;
	}
}
