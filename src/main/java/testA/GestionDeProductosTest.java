package testA;


import modelo.Mesa;
import modelo.Operario;
import modelo.Pedido;
import modelo.Producto;
import negocio.GestionDeProductos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class GestionDeProductosTest {

    private EscenarioMesaConDatos escenario;
    private GestionDeProductos gestionDeProductos;

    @Before
    public void setUp() throws Exception {
        this.gestionDeProductos = GestionDeProductos.get();
        escenario = new EscenarioMesaConDatos();
        escenario.aplicaEscenarioMesaConDatos();
    }

    @After
    public void tearDown() throws Exception {
        escenario.borrarEscenario();
    }


    @Test
    public void descontarStock() {
        Producto productoEscenario = this.escenario.getProd();
        int stockInicial = productoEscenario.getStock();

        Pedido pedido = new Pedido( productoEscenario , 5);

        gestionDeProductos.descontarStock(pedido);

        Optional<Producto> producto = gestionDeProductos.getProductos().stream().filter(p -> p.getNombre().equals(productoEscenario.getNombre())).findFirst();

        int expectedStock = stockInicial - pedido.getCantidad();


        if( producto.isPresent() ){
            assertTrue("Falla en la actualizacion del stock", producto.get().getStock() == expectedStock);

        }


    }
}