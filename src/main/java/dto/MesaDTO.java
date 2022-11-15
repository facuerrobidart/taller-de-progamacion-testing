package dto;

import enums.EstadoMesa;
import modelo.Comanda;
import modelo.Mozo;

public class MesaDTO {

    /*
    No tendra sentido persistir el Estado de la mesa
    o bien el Mozo asignado
     */
    private int nroMesa;
    private int cantSillas;
    private EstadoMesa estadoMesa;
    private Mozo mozoAsignado;
    private Comanda comanda;
    private double Ventas;
    private int cantCuentasCerradas;

    public MesaDTO(int nroMesa, int cantSillas) {
        this.nroMesa = nroMesa;
        this.cantSillas = cantSillas;
    }

    public int getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(int nroMesa) {
        this.nroMesa = nroMesa;
    }

    public int getCantSillas() {
        return cantSillas;
    }

    public void setCantSillas(int cantSillas) {
        this.cantSillas = cantSillas;
    }

    public Mozo getMozoAsignado() {
        return mozoAsignado;
    }

    public void setMozoAsignado(Mozo mozoAsignado) {
        this.mozoAsignado = mozoAsignado;
    }

    public EstadoMesa getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(EstadoMesa estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public double getVentas() {
        return Ventas;
    }

    public void setVentas(double ventas) {
        Ventas = ventas;
    }

    public int getCantCuentasCerradas() {
        return cantCuentasCerradas;
    }

    public void setCantCuentasCerradas(int cantCuentasCerradas) {
        this.cantCuentasCerradas = cantCuentasCerradas;
    }
}
