package reportes;

import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Cuadro;
import model.Escultura;
import model.Instalacion;
@XmlRootElement (name = "Obras")
public class GestionObrasReporte {
	private TreeSet<Cuadro> listaCuadros;
	private TreeSet<Escultura> listaEsculturas;
	private TreeSet<Instalacion> listaInstalaciones;

	public GestionObrasReporte(TreeSet <Cuadro> listaC,TreeSet <Escultura> listaE,TreeSet <Instalacion> listaI) {
		this.listaCuadros = listaC;
		this.listaEsculturas=listaE;
		this.listaInstalaciones=listaI;
	}

	public GestionObrasReporte() {
	}


	@XmlElement (name = "Cuadro" )
	public TreeSet<Cuadro> getListaCuadros() {
		return this.listaCuadros;
	}

	public void setListaCuadros(TreeSet<Cuadro> listaCuadros) {
		this.listaCuadros = listaCuadros;
	}

	@XmlElement ( name = "Escultura" )
	public TreeSet<Escultura> getListaEsculturas() {
		return this.listaEsculturas;
	}

	public void setListaEsculturas(TreeSet<Escultura> listaEsculturas) {
		this.listaEsculturas = listaEsculturas;
	}

	@XmlElement ( name = "Instalacion" )
	public TreeSet<Instalacion> getListaInstalaciones() {
		return this.listaInstalaciones;
	}

	public void setListaInstalaciones(TreeSet<Instalacion> listaInstalaciones) {
		this.listaInstalaciones = listaInstalaciones;
	}
	
}
