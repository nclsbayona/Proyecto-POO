package model;

import java.util.Calendar;
import java.util.HashSet;

public class Artista {
	private HashSet<Obra> obras;
	private long codigoArtista;
	private long cedula;
	private String nombre;
	private String apellidos;
	private Calendar fechaNacimiento;
	private long telefono;

	// Métodos
	// Accessors de setDeObras
	public HashSet<Obra> getObras() {
		return this.obras;
	}

	public void setObras(HashSet<Obra> obras) {
		this.obras = obras;
	}

	// Accessors de cedula
	public long getCedula() {
		return this.cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	// Accessors de codigoArtista
	public long getCodigoArtista() {
		return this.codigoArtista;
	}

	public void setCodigoArtista(long codigoArtista) {
		this.codigoArtista = codigoArtista;
	}

	// Accessors de telefono
	public long getTelefono() {
		return this.telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	// Accessors de nombre
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Accessors de apellidos
	public String getApellidos() {
		return this.apellidos;
	}

	public void getApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	// Accessors de fechaNacimento
	public Calendar getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	// Argumentos con cedula y código
	public Artista(long cedula, long codigoArtista) {
		this.obras = new HashSet<Obra>();
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

	// Organizacion para la busqueda de elementos
	@Override
	public int hashCode() {
		return (int) this.codigoArtista;
	}

	// Imprimir un artista
	@Override
	public String toString() {
		return this.cedula + "\t" + this.nombre + " " + this.apellidos;
	}
}