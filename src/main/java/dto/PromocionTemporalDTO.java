package dto;

import enums.Dias;

import java.util.List;

public class PromocionTemporalDTO extends PromocionDTO{

    private String formaPago;
    private float porcentajeDescuento;
    private boolean acumulable;

    public PromocionTemporalDTO(String nombre,boolean activo, List<Dias> diasPromo, String formaPago, float porcentajeDescuento, boolean esAcumulable) {
        super(nombre,activo, diasPromo);
        this.formaPago = formaPago;
        this.porcentajeDescuento = porcentajeDescuento;
        this.acumulable = esAcumulable;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public boolean isAcumulable() {
        return acumulable;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public void setAcumulable(boolean acumulable) {
        this.acumulable = acumulable;
    }
}
