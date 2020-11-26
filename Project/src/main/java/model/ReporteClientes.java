package model;
import java.util.Collection;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReporteClientes{
    public ReporteClientes() {
    }

    @XmlElement
    public Collection<Cliente> getLista() {
        return this.lista;
    }

    public void setLista(Collection<Cliente> lista) {
        this.lista = lista;
    }
    
    private Collection<Cliente> lista;
}