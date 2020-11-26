package xml;

import java.util.HashSet;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cliente;

@XmlRootElement
public class GestionXML  {

	private TreeSet<Cliente> list;

	public GestionXML(TreeSet<Cliente> list) {
		this.list = list;
	}

	public GestionXML() {
	}

	public TreeSet<Cliente> getList() {
		return list;
	}

	public void setList(TreeSet<Cliente> list) {
		this.list = list;
	}
	

}
