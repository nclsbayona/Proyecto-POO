package excepciones;

public class ClientDoesntExistException extends Exception {
    private static final long serialVersionUID = 1L;

    public ClientDoesntExistException() {
        super("El cliente no existe");
    }
    public ClientDoesntExistException(String cc) {
        super("El cliente con la cedula "+cc+" no existe");
    }
}