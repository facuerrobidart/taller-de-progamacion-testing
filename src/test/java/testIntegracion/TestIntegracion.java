package testIntegracion;

import enums.Dias;
import excepciones.StockInsuficienteException;
import modelo.*;
import modelo.promociones.PromocionFija;
import modelo.promociones.PromocionTemporal;
import negocio.GestionDeComandas;
import negocio.GestionDeMesas;
import negocio.GestionDeMozos;
import negocio.GestionDeProductos;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestIntegracion {
    private GestionDeMesas gestionDeMesas;
    private GestionDeComandas gestionDeComandas;
    private Empresa empresa;
    private Producto prod1, prod2;
    private PromocionFija promo1;
    private PromocionTemporal promo2;

    @Before
    public void setUp() {
        this.empresa = Empresa.getEmpresa();
        this.gestionDeMesas = GestionDeMesas.get();
        this.gestionDeComandas = GestionDeComandas.get();
        this.prod1 = new Producto("Coca cola", 100F, 50F, 10);
        this.prod2 = new Producto("Pepsi", 100F, 50F, 10);
        this.empresa.getProductos().add(prod1);
        this.empresa.getProductos().add(prod2);
        this.promo1 = new PromocionFija("Promo 1 Coca", List.of(Dias.LUNES, Dias.MARTES), prod1, true, false, 0, 0.0);
        this.promo2 = new PromocionTemporal("Promo 2 Coca", List.of(Dias.LUNES, Dias.MARTES), "Efectivo", 25, true);
        this.empresa.getPromocionesFijas().add(promo1);
        this.empresa.getPromocionesTemporales().add(promo2);
    }

    @Test
    public void testAgregarPedidos() throws StockInsuficienteException {
        Mesa mesa = new Mesa(1000, 4);
        Mozo mozo = new Mozo("Mozo", "2022-01-01", 10);
        mesa.setMozoAsignado(mozo);
        gestionDeComandas.abrirComanda(mesa);

        gestionDeComandas.cargarPedido(mesa, new Pedido(prod1, 2));
        gestionDeComandas.cargarPedido(mesa, new Pedido(prod2, 1));

        gestionDeMesas.totalMesa(mesa, "Efectivo");
    }

    @After
    public void tearDown() {
        this.empresa.getProductos().remove(prod1);
        this.empresa.getProductos().remove(prod2);
        this.empresa.getPromocionesFijas().remove(promo1);
        this.empresa.getPromocionesTemporales().remove(promo2);
    }


}
