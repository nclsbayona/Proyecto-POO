package excepciones;

public class ArtworkDoesntExistException extends Exception{
    private static final long serialVersionUID = 1L;

    public ArtworkDoesntExistException() {
        super("No existe una obra con el codigo especificado");
    }
    public ArtworkDoesntExistException(String code) {
        super("No existe una obra con el codigo "+code);
    }
}
