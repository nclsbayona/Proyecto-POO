package exceptions;

public class PurchaseDoesntExistException extends Exception{

    private static final long serialVersionUID = 1L;

    public PurchaseDoesntExistException(){
        super("No existe una obra similar");
    }

    public PurchaseDoesntExistException(String codigo){
        super("No existe una obra con el codigo "+codigo);
    }
}
