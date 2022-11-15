package dto;

public class ProductoDTO {

    private String nombre;
    private float precio;
    private float costo;
    private Integer stock;

    public ProductoDTO(String nombre, float precio, float costo, Integer stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
