package exceptions;
public class CodeSizeException extends Exception {

    private static final long serialVersionUID = 1L;

    public CodeSizeException() {
        super("El codigo no cumple con el tamaño definido");
    }
}