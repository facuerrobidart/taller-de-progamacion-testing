package dto;

import modelo.Producto;

import java.time.Instant;

public class PedidoDTO {

    private Producto producto;
    private int cantidad;
    private Instant fecha; // DD/MM/YYYY HH:MM:SS

    public PedidoDTO(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = Instant.now();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
}
