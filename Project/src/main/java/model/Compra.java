package model;

import java.util.Calendar;

import jakarta.xml.bind.annotation.XmlElement;

public class Compra implements Comparable<Compra> {

	private Cliente cliente;
	private Obra obra;
	private long codigoCompra;
	private Calendar fecha;
	private boolean pagado;

	// Constructores
	public Compra(long codigoCompra, Calendar fecha, boolean pagado) {
		this.codigoCompra = codigoCompra;
		this.fecha = fecha;
		this.pagado = pagado;
	}

	public Compra(long codigoCompra) {
		this.codigoCompra = codigoCompra;
		this.pagado=true;
	}

	public Compra(long codigoCompra, Calendar fecha, boolean pagado, Obra obra, Cliente cliente) {
		this.codigoCompra = codigoCompra;
		this.fecha = fecha;
		this.pagado = pagado;
		this.obra = obra;
		this.cliente = cliente;
	}

	public Compra() {
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		try {
			Compra o = (Compra) (obj);
			return (this.codigoCompra == o.codigoCompra) ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	// Accessors
	// Codigo compra
	@XmlElement
	public long getCodigoCompra() {
		return this.codigoCompra;
	}

	// Fecha
	@XmlElement
	public Calendar getFecha() {
		return this.fecha;
	}

	// Pagado
	@XmlElement
	public boolean getPagado() {
		return this.pagado;
	}

	// Cliente
	@XmlElement
	public Cliente getCliente() {
		return this.cliente;
	}

	// Obra
	@XmlElement
	public Obra getObra() {
		return this.obra;
	}

	// Organizar para busqueda
	@Override
	public int hashCode() {
		return (int) this.codigoCompra;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setCodigoCompra(long codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	// Argumento Compra solo con codigoCompra

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	// Imprimir
	@Override
	public String toString() {
		return String.valueOf(this.codigoCompra) + ": " + this.cliente.getNombre() + " " + this.cliente.getApellidos()
				+ "->" + this.obra.getTitulo() + " " + this.fecha.get(Calendar.DATE) + '/'
				+ this.fecha.get(Calendar.MONTH) + '/' + this.fecha.get(Calendar.YEAR) + ":\t$"
				+ this.obra.getPrecioRef();
	}

	@Override
	public int compareTo(Compra o) {
		if (this.codigoCompra > o.getCodigoCompra()) {
			return 1;
		}
		if (this.codigoCompra < o.getCodigoCompra()) {
			return -1;
		} else
			return 0;
	}
}