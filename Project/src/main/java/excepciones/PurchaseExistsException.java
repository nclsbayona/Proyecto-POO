package excepciones;

public class PurchaseExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public PurchaseExistsException(){
        super("Ya existe una obra similar");
    }

    public PurchaseExistsException(String codigo){
        super("Existe una obra con el codigo "+codigo);
    }
}
