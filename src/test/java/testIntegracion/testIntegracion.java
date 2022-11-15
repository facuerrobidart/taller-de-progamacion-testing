package testIntegracion;

import modelo.Mesa;
import modelo.Mozo;
import modelo.Pedido;

public class testIntegracion {


    @test
    public void flujoAgregarPedidoaLaMesa(){
        Producto producto= new Producto("CheeseBurguer", 1000,500,100);

        Mozo mozo= new Mozo("Juan Perez", "15/03/2000",1);

        Mesa mesa= new Mesa(120,10);

        Pedido pedido= new Pedido(producto,2);

        Operario operario=new Operario("Lionel Messi","L10","AguanteLaSeleccion");

        Empresa.setUsuariologueado(operario);









    }


}
