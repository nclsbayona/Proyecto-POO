package model;

import java.util.Calendar;

public class Compra {

	private Cliente cliente;
	private Obra obra;
	private long codigoCompra;
	private Calendar fecha;
	private boolean pagado;

	// Accessors
	// Cliente
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Obra
	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	// Codigo compra
	public long getCodigoCompra() {
		return this.codigoCompra;
	}

	public void setCodigoCompra(long codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	// Fecha
	public Calendar getFecha() {
		return this.fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	// Pagado
	public boolean getPagado() {
		return this.pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	// Constructores
	public Compra(long codigoCompra, Calendar fecha, boolean pagado) {
		this.codigoCompra = codigoCompra;
		this.fecha = fecha;
		this.pagado = pagado;
	}

	// Argumento Compra solo con codigoCompra

	public Compra(long codigoCompra) {

		this.codigoCompra = codigoCompra;
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

	// Organizar para busqueda
	@Override
	public int hashCode() {
		return (int) this.codigoCompra;
	}

	// Imprimir
	@Override
	public String toString() {
		return String.valueOf(this.codigoCompra) + ": " + this.cliente.getNombre() + " " + this.cliente.getApellidos()
				+ "->" + this.obra.getTitulo() + " " + this.fecha.get(Calendar.DATE) + '/'
				+ this.fecha.get(Calendar.MONTH) + '/' + this.fecha.get(Calendar.YEAR) + ":\t$"
				+ this.obra.getPrecioRef();
	}
}