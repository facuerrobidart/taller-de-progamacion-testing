package excepciones;

public class CambioNombreException extends Exception{

    public CambioNombreException() {

        super("No se puede cambiar el nombre del producto");
    }
}
