package xml;

import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.Artista;
@XmlRootElement
public class GestionArtistasXML {
	private HashSet<Artista> list;

	public GestionArtistasXML(HashSet<Artista> list) {
		this.list = list;
	}

	public GestionArtistasXML() {
	
	}
	@XmlElement
	public HashSet<Artista> getList() {
		return list;
	}

	public void setList(HashSet<Artista> list) {
		this.list = list;
	}
	
}
