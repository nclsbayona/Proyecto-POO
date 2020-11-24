package exceptions;

public class PurchaseNotFoundException extends Exception {

    public PurchaseNotFoundException() {
        super("La compra no se encuentra");
    }
    private static final long serialVersionUID = 1L;

}