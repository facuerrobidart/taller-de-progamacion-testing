package excepciones;

public class OperarioNoExistenteException extends Exception{
    public OperarioNoExistenteException() {
        super("El operario que se quiere modificar no existe");
    }
}
