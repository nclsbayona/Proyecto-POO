package exceptions;

public class ArtistExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    public ArtistExistsException() {
        super("El artista ya existe");
    }
    
}
