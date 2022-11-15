package excepciones;

public class StockInsuficienteException extends Exception{

    public StockInsuficienteException(){
        super("Stock insuficiente");
    }
}
