package modelo;

import enums.EstadoMozo;

import java.util.Date;
import java.util.UUID;

public class Mozo {

    private String nombreCompleto;
    private String fechaNacimiento;
    private int cantidadHijos;
    private EstadoMozo estadoMozo;
    private String id;
    private double ventas;

    public Mozo() {
    }

    public Mozo(String nombreCompleto, String fechaNacimiento, int cantidadHijos) {
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.cantidadHijos = cantidadHijos;
        this.estadoMozo = EstadoMozo.ACTIVO;
        this.id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCantidadHijos() {
        return cantidadHijos;
    }

    public void setCantidadHijos(int cantidadHijos) {
        this.cantidadHijos = cantidadHijos;
    }

    public EstadoMozo getEstadoMozo() {
        return estadoMozo;
    }

    public void setEstadoMozo(EstadoMozo estadoMozo) {
        this.estadoMozo = estadoMozo;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombreCompleto +
                " | Cantidad de hijos: " + cantidadHijos +
                " | Fecha de nacimiento: " +  fechaNacimiento +
                " | Estado: " + estadoMozo;
    }
}
