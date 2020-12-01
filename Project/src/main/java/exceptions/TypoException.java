package exceptions;

public class TypoException extends Exception{
    private static final long serialVersionUID = 1L;

    public TypoException() {
        super("Error en la digitación de los campos, por favor vuelva a intentarlo");
    }
    public TypoException(String fieldName) {
        super("Error en la digitación del campo "+fieldName+", por favor vuelva a intentarlo");
    }
    
}

