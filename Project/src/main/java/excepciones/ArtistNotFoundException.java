package excepciones;

public class ArtistNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ArtistNotFoundException() {
        super("El artista no ya existe");
    }
    public ArtistNotFoundException(String cc) {
        super("El artista con la cedula "+cc+" ya existe");
    }
}