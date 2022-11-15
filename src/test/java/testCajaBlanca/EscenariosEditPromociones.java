package testCajaBlanca;

import enums.Dias;
import modelo.Empresa;
import modelo.Producto;
import modelo.promociones.PromocionFija;

import java.util.List;

public class EscenariosEditPromociones {
    private Empresa empresa;


    public void cargarListaProductos(){
        empresa = Empresa.getEmpresa();
        Producto prod1 = new Producto("Coca Cola", 100, 75, 20);
        empresa.getProductos().add(prod1);
        Producto prod2 = new Producto("Pepsi", 100, 75, 20);
        empresa.getProductos().add(prod2);

        PromocionFija promo1 = new PromocionFija("Promo 1 Coca", List.of(Dias.LUNES,Dias.MARTES), prod1, true, false, 0, 0.0);
        PromocionFija promo2 = new PromocionFija("Promo 2 Coca", List.of(Dias.LUNES,Dias.MARTES), prod1, false, true, 3, 10.0);
        PromocionFija promo3 = new PromocionFija("Promo 1 Pepsi", List.of(Dias.LUNES,Dias.MARTES), prod2, true, false, 0, 0.0);
        PromocionFija promo4 = new PromocionFija("Promo 2 Pepsi", List.of(Dias.LUNES,Dias.MARTES), prod2, false, true, 3, 10.0);

        empresa.getPromocionesFijas().addAll(List.of(promo1, promo2, promo3, promo4));

    }

}
