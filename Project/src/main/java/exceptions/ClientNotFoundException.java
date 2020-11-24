package exceptions;

public class ClientNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException() {
        super("No se encontró un cliente con las condiciones especificadas");
    }

    public ClientNotFoundException(String msg) {
        super("No se encontró un cliente con el dato: " + msg + " especificado");
    }
}
