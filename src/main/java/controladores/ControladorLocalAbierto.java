package controladores;

import dto.MesaDTO;
import enums.EstadoMesa;
import excepciones.MesaNoExistenteException;
import excepciones.StockInsuficienteException;
import modelo.*;
import negocio.GestionDeComandas;
import negocio.GestionDeMesas;
import negocio.GestionDeMozos;
import negocio.GestionDeProductos;
import vistas.VistaLocalAbierto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public class ControladorLocalAbierto implements ActionListener {

    private static ControladorLocalAbierto controladorLocalAbierto = null;
    private static GestionDeMozos gestionDeMozos;
    private static GestionDeProductos gestionDeProductos;
    private static  GestionDeMesas gestionDeMesas;
    private static GestionDeComandas gestionDeComandas;
    private static VistaLocalAbierto vistaLocalAbierto;


    private ControladorLocalAbierto() {
        vistaLocalAbierto = new VistaLocalAbierto();
        vistaLocalAbierto.setActionListener(this);
        gestionDeMesas = GestionDeMesas.get();
        gestionDeMozos = GestionDeMozos.get();
        gestionDeProductos = GestionDeProductos.get();
        gestionDeComandas = GestionDeComandas.get();
    }

    public static ControladorLocalAbierto getControladorLocalAbierto() {
        if (controladorLocalAbierto == null) {
            controladorLocalAbierto = new ControladorLocalAbierto();
        }

        //Carga de Combo Boxes
        Set<Mesa> mesas = gestionDeMesas.getMesas();
        JComboBox<Mesa> comboM = vistaLocalAbierto.getComboBoxAbrir();
        comboM.removeAllItems();
        for(Mesa mesa : mesas){
            comboM.addItem(mesa);
        }
        vistaLocalAbierto.setComboBoxAbrir(comboM);

       comboM = vistaLocalAbierto.getComboBoxCerrar();
       comboM.removeAllItems();
        for(Mesa mesa : mesas){
            comboM.addItem(mesa);
        }
        vistaLocalAbierto.setComboBoxCerrar(comboM);

       comboM = vistaLocalAbierto.getComboBoxMesa();
       comboM.removeAllItems();
        for(Mesa mesa : mesas){
            comboM.addItem(mesa);
        }
        vistaLocalAbierto.setComboBoxMesa(comboM);

        Set<Producto> productos = gestionDeProductos.getProductos();
        JComboBox<Producto> comboP = vistaLocalAbierto.getComboBoxProducto();
        comboP.removeAllItems();
        for(Producto prod : productos){
            comboP.addItem(prod);
        }
        vistaLocalAbierto.setComboBoxProducto(comboP);

        int[] intArr = {1,2,3,4,5};
        JComboBox<Integer> comboI = vistaLocalAbierto.getComboBoxCantidad();
        comboI.removeAllItems();
        for(int i : intArr) {
            comboI.addItem(i);
        }
        vistaLocalAbierto.setComboBoxCantidad(comboI);

        String[] medios = {"Efectivo","Cuenta DNI","Mercado Pago","Tarjeta"};
        JComboBox<String> comboMedios = vistaLocalAbierto.getComboBoxMedioDePago();
        comboMedios.removeAllItems();
        for(String medio : medios) {
            comboMedios.addItem(medio);
        }
        vistaLocalAbierto.setComboBoxMedioDePago(comboMedios);

        vistaLocalAbierto.limpia();

        vistaLocalAbierto.mostrar();

        return controladorLocalAbierto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if(comando.equalsIgnoreCase("Consumo promedio por mesa")){
            Set<Mesa> mesas = gestionDeMesas.getMesas();
            Iterator<Mesa> it = mesas.iterator();
            String txt ="";
            Mesa mesa;
            while (it.hasNext()){
                mesa = it.next();
                try {
                    txt += "La mesa "+ mesa.getNroMesa() + " tiene un consumo promedio de: " + gestionDeMesas.calculaConsumoPromedio(mesa.getNroMesa())+"\n";
                } catch (MesaNoExistenteException ex) {
                    throw new RuntimeException(ex);
                }
            }
            vistaLocalAbierto.success(txt);
        }
        else if(comando.equalsIgnoreCase("Mozo con menos ventas")){
            String msg = "El mozo con menos ventas es " + gestionDeMozos.mozoMinVentas();
            vistaLocalAbierto.success(msg);
        }
        else if(comando.equalsIgnoreCase("Mozo con mas Ventas")){
            String msg = "El mozo con mas ventas es " + gestionDeMozos.mozoMaxVentas();
            vistaLocalAbierto.success(msg);
        }
        else if(comando.equalsIgnoreCase("Cerrar Mesa")) {
            Mesa mesa = vistaLocalAbierto.getMesaApertura();
            if ( mesa.getEstadoMesa() == EstadoMesa.LIBRE) {
                vistaLocalAbierto.failure("La mesa nunca fue abierta");
                vistaLocalAbierto.limpia();
                ControladorLocalAbierto con = ControladorLocalAbierto.getControladorLocalAbierto();
            } else {
                MesaDTO mesaDTO = new MesaDTO( mesa.getNroMesa(), mesa.getCantSillas());
                mesaDTO.setCantCuentasCerradas( mesa.getCantCuentasCerradas()  );
                mesaDTO.setComanda(mesa.getComanda());
                mesaDTO.setMozoAsignado(mesa.getMozoAsignado());
                mesaDTO.setVentas( mesa.getVentas() );
                String medioDePago = vistaLocalAbierto.getMedioDePago();
                double total = gestionDeMesas.cerrarMesa( mesaDTO, medioDePago);
                vistaLocalAbierto.success("Mesa cerrada con exito con un total de: " + total);
            }
        }
        else if(comando.equalsIgnoreCase("Cargar Pedido")){
            Producto prod = vistaLocalAbierto.getProductoElegido();
            Mesa mesa = vistaLocalAbierto.getMesaPedido();
            int cantidad = vistaLocalAbierto.getCantidad();

            if( mesa.getEstadoMesa() == EstadoMesa.OCUPADA ){
                Pedido pedido = new Pedido(prod, cantidad);
                try {
                    gestionDeComandas.cargarPedido( mesa , pedido );
                } catch (StockInsuficienteException ex) {
                    vistaLocalAbierto.failure("Stock insuficiente del producto " + pedido.getProducto().getNombre());
                }
                vistaLocalAbierto.success("Pedido agregado con exito a la mesa " + mesa.getNroMesa());
            }else{
                vistaLocalAbierto.failure("La mesa " + mesa.getNroMesa() + " no se encuentra ocupada. Presione Abrir Mesa");
            }
        }
        else if(comando.equalsIgnoreCase("Abrir Mesa")){
            Mesa mesa = vistaLocalAbierto.getMesaApertura();
            if( mesa.getMozoAsignado() == null ) {
                vistaLocalAbierto.failure("Esta mesa no tiene mozo asignado");
                vistaLocalAbierto.limpia();
            }else{
                if( mesa.getEstadoMesa() == EstadoMesa.OCUPADA) {
                    vistaLocalAbierto.failure("La mesa esta ocupada");
                    vistaLocalAbierto.limpia();
                }
                else {
                    gestionDeComandas.abrirComanda( mesa );
                    vistaLocalAbierto.success("Mesa abierta con exito");
                }
            }
        }
        else if(comando.equalsIgnoreCase("Cerrar Local")){
            vistaLocalAbierto.esconder();
            ControladorInicio controladorInicio = ControladorInicio.getControladorInicio(true);
        }
    }
}
