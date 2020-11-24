package model;

import java.util.Calendar;
import java.util.HashSet;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Artista {

	public Artista() {
	}
	@XmlElement
	private HashSet<Obra> obras;
	@XmlElement
	private long codigoArtista;
	@XmlElement
	private long cedula;
	@XmlElement
	private String nombre;
	@XmlElement
	private String apellidos;
	@XmlElement
	private Calendar fechaNacimiento;
	@XmlElement
	private long telefono;

	// Argumentos con cedula y código
	public Artista(long cedula, long codigoArtista) {
		this.obras = new HashSet<Obra>();
		this.cedula = cedula;
		this.codigoArtista = codigoArtista;
	}

	// Constructores
	// Todos los Argumentos
	public Artista(long cedula, String nombre, String apellido, Calendar fechaNa, long telefono) {
		this.obras = new HashSet<Obra>();
		this.nombre = nombre;
		this.apellidos = apellido;
		this.cedula = cedula;
		this.fechaNacimiento = fechaNa;
		this.telefono = telefono;
	}

	// Todos los Argumentos
	public Artista(long cedula, String nombre, String apellido, long telefono) {
		this.obras = new HashSet<Obra>();
		this.nombre = nombre;
		this.apellidos = apellido;
		this.cedula = cedula;
		this.telefono = telefono;
	}

	// Constructor
	public Artista(String nombre, String apellidos, long cedula, long codigoArtista) {
		this.obras = new HashSet<Obra>();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.cedula = cedula;
		this.codigoArtista = codigoArtista;
	}

	// Verifica que no exista un artista con esa cédula para agregar uno nuevo
	@Override
	public boolean equals(Object obj) {
		try {
			Artista c = (Artista) (obj);
			return (this.cedula == c.cedula) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	// Accessors de apellidos
	public String nGetApellidos() {
		return this.apellidos;
	}

	public void nGetApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	// Accessors de cedula
	public long nGetCedula() {
		return this.cedula;
	}

	// Accessors de codigoArtista
	public long nGetCodigoArtista() {
		return this.codigoArtista;
	}

	// Accessors de fechaNacimento
	public Calendar nGetFechaNacimiento() {
		return this.fechaNacimiento;
	}

	// Accessors de nombre
	public String nGetNombre() {
		return this.nombre;
	}

	// Métodos
	// Accessors de setDeObras
	public HashSet<Obra> nGetObras() {
		return this.obras;
	}

	// Accessors de telefono
	public long nGetTelefono() {
		return this.telefono;
	}

	// Organizacion para la busqueda de elementos
	@Override
	public int hashCode() {
		return (int) this.codigoArtista;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public void setCodigoArtista(long codigoArtista) {
		this.codigoArtista = codigoArtista;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setObras(HashSet<Obra> obras) {
		this.obras = obras;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	// Imprimir un artista
	@Override
	public String toString() {
		return this.cedula + "\t" + this.nombre + " " + this.apellidos;
	}
}