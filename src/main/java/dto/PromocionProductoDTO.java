package dto;

import enums.Dias;
import modelo.Producto;

import java.util.List;

public class PromocionProductoDTO extends PromocionDTO{

    Producto producto;
    boolean dosPorUno;
    boolean dtoPorCant;
    int dtoPorCantMin;
    double dtoPorCantPrecioU;

    public PromocionProductoDTO(String nombre,boolean activo, List<Dias> diasPromo, Producto producto, boolean dosPorUno, boolean dtoPorCant, int dtoPorCantMin, double dtoPorCantPrecioU) {
        super(nombre,activo, diasPromo);
        this.producto = producto;
        this.dosPorUno = dosPorUno;
        this.dtoPorCant = dtoPorCant;
        this.dtoPorCantMin = dtoPorCantMin;
        this.dtoPorCantPrecioU = dtoPorCantPrecioU;
    }

    public Producto getProducto() {
        return producto;
    }

    public boolean isDosPorUno() {
        return dosPorUno;
    }

    public boolean isDtoPorCant() {
        return dtoPorCant;
    }

    public int getDtoPorCantMin() {
        return dtoPorCantMin;
    }

    public double getDtoPorCantPrecioU() {
        return dtoPorCantPrecioU;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setDosPorUno(boolean dosPorUno) {
        this.dosPorUno = dosPorUno;
    }

    public void setDtoPorCant(boolean dtoPorCant) {
        this.dtoPorCant = dtoPorCant;
    }

    public void setDtoPorCantMin(int dtoPorCantMin) {
        this.dtoPorCantMin = dtoPorCantMin;
    }

    public void setDtoPorCantPrecioU(double dtoPorCantPrecioU) {
        this.dtoPorCantPrecioU = dtoPorCantPrecioU;
    }


}
