package model;

import java.util.Calendar;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Compra {

	@XmlElement
	private Cliente cliente;
	@XmlElement
	private Obra obra;
	@XmlElement
	private long codigoCompra;
	@XmlElement
	private Calendar fecha;
	@XmlElement
	private boolean pagado;

	public Compra(long codigoCompra) {

		this.codigoCompra = codigoCompra;
	}

	// Constructores
	public Compra(long codigoCompra, Calendar fecha, boolean pagado) {
		this.codigoCompra = codigoCompra;
		this.fecha = fecha;
		this.pagado = pagado;
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
	// Cliente
	public Cliente getCliente() {
		return this.cliente;
	}

	// Codigo compra
	public long getCodigoCompra() {
		return this.codigoCompra;
	}

	// Fecha
	public Calendar getFecha() {
		return this.fecha;
	}

	// Obra
	public Obra getObra() {
		return this.obra;
	}

	// Pagado
	public boolean getPagado() {
		return this.pagado;
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
		return String.valueOf(this.codigoCompra) + ": " + this.cliente.retNombre() + " " + this.cliente.retApellidos()
				+ "->" + this.obra.getTitulo() + " " + this.fecha.get(Calendar.DATE) + '/'
				+ this.fecha.get(Calendar.MONTH) + '/' + this.fecha.get(Calendar.YEAR) + ":\t$"
				+ this.obra.getPrecioRef();
	}
}