package exceptions;

public class ArtworkNotPurchasedException extends Exception{

    private static final long serialVersionUID = 1L;
    public ArtworkNotPurchasedException() {
        super("No existe una obra comprada");
    }

    public ArtworkNotPurchasedException(String codigo) {
        super("No existe una obra comprada con el codigo "+codigo);
    }



}
