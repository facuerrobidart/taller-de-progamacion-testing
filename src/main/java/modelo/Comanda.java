package modelo;

import enums.EstadoComanda;

import javax.xml.validation.TypeInfoProvider;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private Instant fecha;
    private List<Pedido> pedidos;
    private EstadoComanda estado;

    public Comanda() {
        this.pedidos = new ArrayList<>();
        this.estado = EstadoComanda.ABIERTA;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }
}
