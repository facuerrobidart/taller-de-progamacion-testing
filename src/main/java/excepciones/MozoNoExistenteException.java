package excepciones;

public class MozoNoExistenteException extends Exception{

    public MozoNoExistenteException() {
        super("El Mozo ingresado no se encuentra cargado en el sistema");
    }

}
