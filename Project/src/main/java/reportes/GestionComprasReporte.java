package reportes;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Compra;

@XmlRootElement (name = "Compras")
public class GestionComprasReporte {
	private TreeSet<Compra> compras;

	public GestionComprasReporte(TreeSet<Compra> compras) {
		this.compras = compras;
	}
	public GestionComprasReporte() {

	}
	@XmlElement (name="Compra")
	public TreeSet<Compra> getCompras() {
		return compras;
	}
	public void setCompras(TreeSet<Compra> compras) {
		this.compras = compras;
	}
	
	
}
