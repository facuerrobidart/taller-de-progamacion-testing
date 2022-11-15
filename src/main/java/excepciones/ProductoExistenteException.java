package excepciones;

public class ProductoExistenteException extends Exception{

    public ProductoExistenteException() {
        super("El producto ingresado ya esta en el sistema");
    }
}
