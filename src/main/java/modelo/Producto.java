package modelo;

import java.util.UUID;

public class Producto {

    private String id;
    private String nombre;
    private float precio;
    private float costo;
    private Integer stock;

    public Producto() {
    }

    public Producto(String nombre, float precio, float costo, Integer stock) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public float getCosto() {
        return costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "Nombre: " + nombre +
                " | Stock: " + stock;
    }
}
