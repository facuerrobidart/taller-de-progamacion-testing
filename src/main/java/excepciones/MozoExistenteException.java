package excepciones;

public class MozoExistenteException extends Exception{

    public MozoExistenteException() {
        super("El Mozo ingresado ya se encuentra cargado en el sistema");
    }
}
