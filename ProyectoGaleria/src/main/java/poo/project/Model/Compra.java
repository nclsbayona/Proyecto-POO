package poo.project.Model;
import java.util.Calendar;
import java.util.HashSet;

public class Compra {

	private HashSet<Cliente> clientes;
	private HashSet<Obra> obras;
	private long codigoCompra;
	private Calendar fecha;
	private boolean pagado;

	//Accessor

	public HashSet<Cliente> getClientes()
    {
		return this.clientes;
    }

	public void setClientes()
	{
		this.clientes = clientes;
    }

    public HashSet<Obra> getObras()
	{
		return this.obras;
	}
    public void setObras(HashSet<Obra> obras)
	{
		this.obras = obras;
	}


	public long getCodigoCompra()
	{
		return this.codigoCompra;
	}

	public void setCodigoCompra(long codigoCompra) 
	{
		this.codigoCompra = codigoCompra;
	}


	public Calendar getFecha() 
	{
		return this.fecha;
	}
	public void setFecha(Calendar fecha)
	{
		this.fecha = fecha;
	}

	public boolean Pagado()
	{
		return this.pagado;
	}

	public void setPagado(boolean pagado)
	{
		this.pagado = pagado;
	}

	//Constructores

	public Compra()


}





