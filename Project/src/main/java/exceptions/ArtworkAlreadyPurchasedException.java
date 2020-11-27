package exceptions;

public class ArtworkAlreadyPurchasedException extends Exception{

    public ArtworkAlreadyPurchasedException() {
        super("La obra ya fue comprada");
    }

    private static final long serialVersionUID = 1L;
    
}
