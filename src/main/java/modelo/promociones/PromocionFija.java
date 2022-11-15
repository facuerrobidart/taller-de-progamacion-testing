package modelo.promociones;

import enums.Dias;
import modelo.Producto;

import java.util.List;

public class PromocionFija extends Promocion{

    Producto producto;
    boolean dosPorUno;
    boolean dtoPorCant;
    int dtoPorCantMin;
    double dtoPorCantPrecioU;

    public PromocionFija(){}

    public PromocionFija(String nombre, List<Dias> diasPromo, Producto producto, boolean dosPorUno, boolean dtoPorCant, int dtoPorCantMin, double dtoPorCantPrecioU) {
        super(nombre, diasPromo);
        this.producto = producto;
        this.dosPorUno = dosPorUno;
        this.dtoPorCant = dtoPorCant;
        this.dtoPorCantMin = dtoPorCantMin;
        this.dtoPorCantPrecioU = dtoPorCantPrecioU;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public boolean isDosPorUno() {
        return dosPorUno;
    }

    public void setDosPorUno(boolean dosPorUno) {
        this.dosPorUno = dosPorUno;
    }

    public boolean isDtoPorCant() {
        return dtoPorCant;
    }

    public void setDtoPorCant(boolean dtoPorCant) {
        this.dtoPorCant = dtoPorCant;
    }

    public int getDtoPorCantMin() {
        return dtoPorCantMin;
    }

    public void setDtoPorCantMin(int dtoPorCantMin) {
        this.dtoPorCantMin = dtoPorCantMin;
    }

    public double getDtoPorCantPrecioU() {
        return dtoPorCantPrecioU;
    }

    public void setDtoPorCantPrecioU(double dtoPorCantPrecioU) {
        this.dtoPorCantPrecioU = dtoPorCantPrecioU;
    }

    public String toString() {
        return "Nombre: " + nombre +
                " | Activo: " + activo;
    }




}
