package xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import jakarta.xml.bind.annotation.XmlRootElement;
import model.Artista;

@XmlRootElement
public class GestionArtistasXML {
	private TreeSet<Artista> list;

	public GestionArtistasXML(TreeSet<Artista> list) {
		this.list = list;
	}

	public GestionArtistasXML() {

	}

	public TreeSet<Artista> getList() {
		return list;
	}

	public void setList(TreeSet<Artista> list) {
		this.list = list;
	}



}
