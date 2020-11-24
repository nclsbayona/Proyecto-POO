package exceptions;

public class ClientExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public ClientExistsException() {
        super("El cliente ya existe");
    }
    public ClientExistsException(String cc) {
        super("El cliente con la cedula "+cc+" ya existe");
    }
}