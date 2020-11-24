package exceptions;

public class ArtworkExistsException extends Exception{
    private static final long serialVersionUID = 1L;

    public ArtworkExistsException() {
        super("Ya existe una obra con el codigo especificado");
    }
    public ArtworkExistsException(String code) {
        super("Ya existe una obra con el codigo "+code);
    }
    
}
