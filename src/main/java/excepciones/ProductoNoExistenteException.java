package excepciones;

public class ProductoNoExistenteException extends Exception{

    public ProductoNoExistenteException() {
        super("El producto ingresado no se encuentra en el sistema");
    }
}
