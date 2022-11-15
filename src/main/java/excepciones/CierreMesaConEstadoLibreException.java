package excepciones;

public class CierreMesaConEstadoLibreException extends Exception{

    public CierreMesaConEstadoLibreException() {
        super("No se puede cerrar una mesa quue no se encuentra ocupada");
    }
}
