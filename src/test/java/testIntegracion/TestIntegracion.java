package testIntegracion;

import modelo.Empresa;
import modelo.Mesa;
import modelo.Producto;
import negocio.GestionDeComandas;
import negocio.GestionDeMesas;
import negocio.GestionDeMozos;
import negocio.GestionDeProductos;
import org.junit.Before;
import org.junit.Test;

public class TestIntegracion {
    private GestionDeMesas gestionDeMesas;
    private GestionDeProductos gestionDeProductos;
    private GestionDeComandas gestionDeComandas;
    private GestionDeMozos gestionDeMozos;
    private Empresa empresa;
    private Producto prod1, prod2;

    @Before
    public void setUp() {
        this.empresa = Empresa.getEmpresa();
        this.gestionDeProductos = GestionDeProductos.get();
        this.gestionDeComandas = GestionDeComandas.get();
        this.gestionDeMozos = GestionDeMozos.get();
        this.prod1 = new Producto("Coca cola", 100F, 50F, 10);
        this.prod2 = new Producto("Pepsi", 100F, 50F, 10);
        this.empresa.getProductos().add(prod1);
        this.empresa.getProductos().add(prod2);
    }

    @Test
    public void testAgregarPedidos(){
        Mesa mesa = new Mesa(1000, 4);
        gestionDeComandas.abrirComanda(mesa);


    }


}
