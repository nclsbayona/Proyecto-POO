package xml;

import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlRootElement;
import model.Compra;
import model.Obra;

@XmlRootElement
public class GestionComprasXML {
	private HashSet<Compra> compras;

	public GestionComprasXML(HashSet<Compra> compras) {
		this.compras = compras;
	}
	public GestionComprasXML() {

	}
	public HashSet<Compra> getCompras() {
		return compras;
	}
	public void setCompras(HashSet<Compra> compras) {
		this.compras = compras;
	}
	
	
}
