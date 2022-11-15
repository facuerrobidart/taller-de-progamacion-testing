package excepciones;

public class CredencialesInvalidasException extends Exception{

    public CredencialesInvalidasException() {
        System.out.println("Las credenciales ingresadas son invalidas");
    }
}
