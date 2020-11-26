package reportes;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Artista;

@XmlRootElement
public class GestionArtistasReporte {
	private TreeSet<Artista> list;

	public GestionArtistasReporte(TreeSet<Artista> list) {
		this.list = list;
	}

	public GestionArtistasReporte() {

	}

	@XmlElement(name="Artista")
	public TreeSet<Artista> getList() {
		return list;
	}

	public void setList(TreeSet<Artista> list) {
		this.list = list;
	}



}
