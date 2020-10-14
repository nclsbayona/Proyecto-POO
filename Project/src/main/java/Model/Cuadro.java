package Model;

import java.util.Date;

public class Cuadro extends Obra 
{
    private String tema;
    private String tecnica;
    private Clasificacion clasificacion;

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTecnica() {
        return this.tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public Clasificacion getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Cuadro(long _codigoObra, String _titulo, Date _fecha, float _precioRef, String _dimensiones, String tema, String tecnica) 
    {
        super(_codigoObra, _titulo, _fecha, _precioRef, _dimensiones);
        this.tema=tema;
        this.tecnica=tecnica;
    }    
}
