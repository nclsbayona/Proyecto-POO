package poo.project.Model;
import java.util.Calendar;

public class Compra {

	private Cliente cliente;
	private Obra obra;
	private long codigoCompra;
	private Calendar fecha;
	private boolean pagado;

	//Accessors
	//Cliente
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	//Obra
	public Obra getObra() {
		return this.obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}	
	//Codigo compra
	public long getCodigoCompra()
	{
		return this.codigoCompra;
	}

	public void setCodigoCompra(long codigoCompra) 
	{
		this.codigoCompra = codigoCompra;
	}

	//Fecha
	public Calendar getFecha() 
	{
		return this.fecha;
	}
	public void setFecha(Calendar fecha)
	{
		this.fecha = fecha;
	}

	//Pagado
	public boolean getPagado()
	{
		return this.pagado;
	}
	public void setPagado(boolean pagado)
	{
		this.pagado = pagado;
	}

	//Constructores
	public Compra(long codigoCompra, Calendar fecha, boolean pagado) {
		this.codigoCompra = codigoCompra;
		this.fecha = fecha;
		this.pagado = pagado;
	}

	//Argumento Compra solo con codigoCompra

	public Compra(long codigoCompra) {

		this.codigoCompra=codigoCompra;
	}


}





