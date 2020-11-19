package model;

public enum Clasificacion {

    OBRA_MAESTRA("Obra maestra"), OBRA_REPRESENTATIVA("Obra representativa");

    private String nombre;

    public Object getOBRA_MAESTRA() {
        return Clasificacion.OBRA_MAESTRA;
    }

    public Object getOBRA_REPRESENTATIVA() {
        return Clasificacion.OBRA_REPRESENTATIVA;
    }

    private Clasificacion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre + "\n";
    }
}
