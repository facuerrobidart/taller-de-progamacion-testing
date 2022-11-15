package excepciones;

public class MesaNoExistenteException extends Exception{
    public MesaNoExistenteException() {
        super("La mesa ingresada no existe en el sistema");
    }
}
