package negocio;

import enums.EstadoMesa;
import excepciones.StockInsuficienteException;
import modelo.Comanda;
import modelo.Empresa;
import modelo.Mesa;
import modelo.Pedido;

import java.util.Iterator;
import java.util.Set;


public class GestionDeComandas {

    private final Empresa empresa;
    private static GestionDeComandas gestionDeComandas = null;

    private GestionDeComandas() {
        this.empresa = Empresa.getEmpresa();
    }

    public static GestionDeComandas get() {
        if( gestionDeComandas == null )
            gestionDeComandas = new GestionDeComandas();

        return gestionDeComandas;
    }

    /**
     * Crea una comando de forma simultanea a la apertura de la mesa
     * precondition: mesa!=null && mesa.getEstado()!=Estado.Ocupado
     * postcondition: mesa.getEstado()==Estado.Ocupado
     * @param mesa
     */
    public void abrirComanda(Mesa mesa){

        Set<Mesa> mesas = empresa.getMesas();

        mesas.remove(mesa);
        mesa.setComanda(new Comanda());
        mesa.setEstadoMesa(EstadoMesa.OCUPADA);
        mesas.add(mesa);
        this.empresa.setMesas(mesas);

    }

    /**
     * Se agrega un pedido a una mesa
     * precondition: mesa.getEstado()==Estado.Ocupada
     * @param mesa: mesa donde se agregara el pedido
     * @param pedido: pedido a cargar
     */
    public void cargarPedido(Mesa mesa,  Pedido pedido) throws StockInsuficienteException {

        Set<Mesa> mesas = empresa.getMesas();
        Iterator<Mesa> it = mesas.iterator();

        Comanda comanda = mesa.getComanda();
        if(GestionDeProductos.get().descontarStock( pedido )){
            comanda.getPedidos().add(pedido);
            mesas.remove(mesa);
            mesa.setComanda( comanda );
            mesas.add(mesa);
            this.empresa.setMesas(mesas);
        }else{
            throw new StockInsuficienteException();
        }
        }
    }
