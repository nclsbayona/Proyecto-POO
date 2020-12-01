package exceptions;

public class EmptyPurchasesListException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmptyPurchasesListException() {
        super("Lista de compras vacia");
    }
}