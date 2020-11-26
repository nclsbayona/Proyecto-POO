package reportes;

import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cliente;

@XmlRootElement
public class GestionClientesReporte  {

	private TreeSet<Cliente> list;

	public GestionClientesReporte(TreeSet<Cliente> list) {
		this.list = list;
	}

	public GestionClientesReporte() {
	}

	@XmlElement (name="Cliente")
	public TreeSet<Cliente> getList() {
		return list;
	}

	public void setList(TreeSet<Cliente> list) {
		this.list = list;
	}
}
