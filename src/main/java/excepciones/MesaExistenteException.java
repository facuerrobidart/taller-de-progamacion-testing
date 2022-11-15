package excepciones;

public class MesaExistenteException extends Exception{

    public MesaExistenteException() {
        super("La mesa ingresada ya esta en el sistema");
    }
}
