package excepciones;

public class OperarioExistenteException extends Exception{

    public OperarioExistenteException() {
        super("El operario ingresado ya esta en el sistema");
    }
}
