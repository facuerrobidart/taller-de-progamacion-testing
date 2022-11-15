package excepciones;

public class PermisoDenegadoException extends Exception{

    public PermisoDenegadoException() {
        super("Operario no admin");
    }
}
