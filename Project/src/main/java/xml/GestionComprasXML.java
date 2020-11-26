package xml;

import java.util.HashSet;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlRootElement;
import model.Compra;
import model.Obra;

@XmlRootElement
public class GestionComprasXML {
	private TreeSet<Compra> compras;

	public GestionComprasXML(TreeSet<Compra> compras) {
		this.compras = compras;
	}
	public GestionComprasXML() {

	}
	public TreeSet<Compra> getCompras() {
		return compras;
	}
	public void setCompras(TreeSet<Compra> compras) {
		this.compras = compras;
	}
	
	
}
