package testCajaBlanca;

import enums.Dias;
import modelo.Empresa;
import modelo.Producto;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EscenariosEditPromociones {
    private Empresa empresa = Empresa.getEmpresa();
    private Producto prod1;
    private Producto prod2;
    private PromocionFija promo1 = new PromocionFija("Promo 1 Coca", List.of(Dias.LUNES,Dias.MARTES), prod1, true, false, 0, 0.0);
    private PromocionFija promo2 = new PromocionFija("Promo 2 Coca", List.of(Dias.LUNES,Dias.MARTES), prod1, false, true, 3, 10.0);
    private PromocionFija promo3 = new PromocionFija("Promo 1 Pepsi", List.of(Dias.LUNES,Dias.MARTES), prod2, true, false, 0, 0.0);
    private PromocionFija promo4 = new PromocionFija("Promo 2 Pepsi", List.of(Dias.LUNES,Dias.MARTES), prod2, false, true, 3, 10.0);
    Set<PromocionFija> promocionesFijas = new HashSet<>();


    public void cargarListaPromociones() {
        prod1 = new Producto("Coca Cola", 100, 75, 20);
        empresa.getProductos().add(prod1);
        prod2 = new Producto("Pepsi", 100, 75, 20);
        empresa.getProductos().add(prod2);

        empresa.getPromocionesFijas().addAll(List.of(promo1, promo2, promo3, promo4));
    }

    public void cargarListaVacia() {
        promocionesFijas = empresa.getPromocionesFijas();
        empresa.setPromocionesFijas(new HashSet<>());
    }

    public void removerListaPromociones() {
        empresa.getProductos().remove(prod1);
        empresa.getProductos().remove(prod2);
        empresa.getPromocionesFijas().remove(promo1);
        empresa.getPromocionesFijas().remove(promo2);
        empresa.getPromocionesFijas().remove(promo3);
        empresa.getPromocionesFijas().remove(promo4);
    }

    public void restaurarListaPromociones() {
        empresa.getProductos().add(prod1);
        empresa.getProductos().add(prod2);
        empresa.getPromocionesFijas().addAll(promocionesFijas);
    }

    public PromocionFija getPromo1() {
        return promo1;
    }

    public Producto getProd1() {
        return prod1;
    }

}
