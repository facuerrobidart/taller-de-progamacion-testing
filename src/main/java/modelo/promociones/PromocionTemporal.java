package modelo.promociones;

import enums.Dias;

import java.util.List;

public class PromocionTemporal extends Promocion{

    private String formaPago;
    private float porcentajeDescuento;
    private boolean esAcumulable;

    public PromocionTemporal(){}

    public PromocionTemporal(String nombre, List<Dias> diasPromo, String formaPago, float porcentajeDescuento, boolean esAcumulable) {
        super(nombre, diasPromo);
        this.formaPago = formaPago;
        this.porcentajeDescuento = porcentajeDescuento;
        this.esAcumulable = esAcumulable;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public boolean isEsAcumulable() {
        return esAcumulable;
    }

    public void setEsAcumulable(boolean esAcumulable) {
        this.esAcumulable = esAcumulable;
    }

    public String toString() {
        return "Nombre: " + nombre +
                " | Activo: " + activo;
    }


}
