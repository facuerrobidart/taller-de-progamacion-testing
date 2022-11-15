package testA;

import enums.EstadoMesa;

import excepciones.StockInsuficienteException;
import modelo.Empresa;
import modelo.Mesa;
import modelo.Pedido;
import modelo.Producto;
import negocio.GestionDeComandas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GestionDeComandasTest {


    EscenarioMesaConDatos escenario;
    GestionDeComandas gestionDeComandas;

    @Before
    public void setUp() throws Exception {
        gestionDeComandas = GestionDeComandas.get();
        escenario = new EscenarioMesaConDatos();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void abrirComanda() {
        Mesa mesa = new Mesa(10, 5);
        gestionDeComandas.abrirComanda(mesa);
        assertEquals("No se modifico el estado correctamente",mesa.getEstadoMesa(), EstadoMesa.OCUPADA);
        assertNotEquals("No se creo la comanda",mesa.getComanda(),null);
    }

    @Test
    public void cargarPedido(){
        Mesa mesa = new Mesa(10, 5);
        Pedido pedido = new Pedido(new Producto("Morcilla",90,25,30),3);

        gestionDeComandas.abrirComanda(mesa);

        try {
            gestionDeComandas.cargarPedido(mesa,pedido);
        } catch (StockInsuficienteException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

        Pedido segundoPedido = mesa.getComanda().getPedidos().get(0);
        assertEquals("No se cargo correctamente el pedido",pedido,segundoPedido);


    }
}