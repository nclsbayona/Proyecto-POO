package xml;

import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cuadro;
import model.Obra;
@XmlRootElement
public class GestionObrasXML {
	private TreeSet<Obra> list;

	public GestionObrasXML(TreeSet<Obra> list) {
		this.list = list;
	}

	public GestionObrasXML() {
	}

	public TreeSet<Obra> getList() {
		return list;
	}

	public void setList(TreeSet<Obra> list) {
		this.list = list;
	}
}
