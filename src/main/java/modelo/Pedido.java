package modelo;

import java.time.Instant;

public class Pedido {

    private Producto producto;
    private int cantidad;
    private Instant fecha; // DD/MM/YYYY HH:MM:SS

    public Pedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = Instant.now();
    }

    public Pedido() {
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

    public Pedido(Producto producto, int cantidad, Instant fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
}
