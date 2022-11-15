package modelo;

import enums.EstadoComanda;
import modelo.promociones.Promocion;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

    private List<Pedido> pedidos;
    private Mesa mesa;
    private String formaDePago;
    private List<Promocion> promociones;
    private Float total;
    private Instant fecha;

    public Cuenta(Mesa mesa, String formaDePago, Float total, List<Promocion> promociones) {
        pedidos = new ArrayList<>();
        this.mesa = mesa;
        this.formaDePago = formaDePago;
        this.promociones = promociones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public List<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(List<Promocion> promociones) {
        this.promociones = promociones;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
}
