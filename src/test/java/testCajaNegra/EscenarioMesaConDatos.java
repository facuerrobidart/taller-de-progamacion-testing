package testCajaNegra;

import enums.EstadoMesa;
import modelo.Empresa;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Producto;

public class EscenarioMesaConDatos {

    Empresa empresa;
    Producto prod;
    Mesa mesa;

    public EscenarioMesaConDatos( ) {
        this.empresa = Empresa.getEmpresa();
    }

    public void aplicaEscenarioMesaConDatos() {

        Mozo mozo = new Mozo("Juan", "28/08/2000", 2);

        this.mesa = new Mesa(10, 5);
        mesa.setEstadoMesa(EstadoMesa.LIBRE);
        mesa.setMozoAsignado(mozo);

        this.prod = new Producto("Morcilla",90,25,30);

        this.empresa.getProductos().add(prod);
        this.empresa.getMesas().add(mesa);

    }

    public void borrarEscenario(){
        empresa.getProductos().remove(this.prod);
        empresa.getMesas().remove(this.mesa);
    }


    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
}
