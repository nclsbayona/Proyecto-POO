package xml;

import java.util.HashSet;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cliente;

@XmlRootElement
public class GestionXML  {

	private HashSet<Cliente> list;

	public GestionXML(HashSet<Cliente> list) {
		this.list = list;
	}

	public GestionXML() {
	}

	public HashSet<Cliente> getList() {
		return list;
	}

	public void setList(HashSet<Cliente> list) {
		this.list = list;
	}
	

}
