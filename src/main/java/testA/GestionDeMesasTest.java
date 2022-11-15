package testA;

import dto.MesaDTO;
import dto.MozoDTO;

import excepciones.MesaExistenteException;
import excepciones.StockInsuficienteException;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Pedido;
import modelo.Producto;
import negocio.GestionDeComandas;
import negocio.GestionDeMesas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.Optional;

import static org.junit.Assert.*;

public class GestionDeMesasTest {

    private EscenarioMesaConDatos escenario;
    private GestionDeMesas gestionDeMesas;

    @Before
    public void setUp() throws Exception {
        this.gestionDeMesas = GestionDeMesas.get();
        escenario = new EscenarioMesaConDatos();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void altaMesaOk() {

        this.escenario.aplicaEscenarioMesaConDatos();

        MesaDTO mesaDTO = new MesaDTO( 1 , 3 );
        try {
            this.gestionDeMesas.altaMesa(mesaDTO);
        } catch (MesaExistenteException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

        Boolean seAgrego = this.gestionDeMesas.getMesas().stream().anyMatch( m -> m.getNroMesa() == mesaDTO.getNroMesa() );
        this.gestionDeMesas.bajaMesa(mesaDTO.getNroMesa());
        assertEquals( "La mesa no se ha agregado", seAgrego, true );

    }

    @Test
    public void altaMesaFail() {

        this.escenario.aplicaEscenarioMesaConDatos();

        Mesa mesaEscenario = this.escenario.getMesa();

        MesaDTO mesaDTO = new MesaDTO( mesaEscenario.getNroMesa() , mesaEscenario.getCantSillas() );

        assertThrows( "No ha lanzado correctamente la excepcion", MesaExistenteException.class, () -> this.gestionDeMesas.altaMesa(mesaDTO));
        this.gestionDeMesas.bajaMesa(mesaDTO.getNroMesa());

    }

    @Test
    public void cerrarMesa() {

        this.escenario.aplicaEscenarioMesaConDatos();

        Mesa mesaEscenario = this.escenario.getMesa();
        Producto productoEscenario = this.escenario.getProd();

        MesaDTO mesaDTO = new MesaDTO(mesaEscenario.getNroMesa() , mesaEscenario.getCantSillas() );
        Pedido pedido = new Pedido(new Producto(productoEscenario.getNombre() ,productoEscenario.getPrecio(), productoEscenario.getCosto(), productoEscenario.getStock()),3);

        GestionDeComandas gestionDeComandas = GestionDeComandas.get();

        gestionDeComandas.abrirComanda(mesaEscenario);
        try {
            gestionDeComandas.cargarPedido(mesaEscenario,pedido);
        } catch (StockInsuficienteException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

        mesaDTO.setComanda(mesaEscenario.getComanda());
        mesaDTO.setMozoAsignado(mesaEscenario.getMozoAsignado());

        double total = gestionDeMesas.cerrarMesa(mesaDTO,"Efectivo");

        float expected = productoEscenario.getPrecio() * pedido.getCantidad();

        assertEquals("No realizo el calculo del total correctamente",total, expected,0.0f);

    }

    @Test
    public void asignarMozoMesa() {

        String nombreMozo = "Pedro";

        MesaDTO mesa = new MesaDTO(5 , 3);
        MozoDTO mozo = new MozoDTO(nombreMozo, "18/08/2000", 6);

        gestionDeMesas.asignarMozoMesa(mozo, mesa);

        Optional<Mesa> mesaEncontrada = gestionDeMesas.getMesas().stream().filter(m -> m.getNroMesa() == mesa.getNroMesa()).findFirst();

        if(mesaEncontrada.isPresent())
            assertEquals("El mozo no fue asignado correctamente", mesaEncontrada.get().getMozoAsignado(), nombreMozo);

    }

}