package controladores;

import excepciones.MesaNoExistenteException;
import excepciones.ProductoNoExistenteException;
import modelo.Mesa;
import modelo.Producto;
import negocio.GestionDeMesas;
import negocio.GestionDeProductos;
import vistas.IVistaGestion;
import vistas.VistaGestionMesas;
import vistas.VistaGestionProductos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ControladorGestionProductos implements ActionListener {

    private static IVistaGestion vistaGestionProductos;
    private static GestionDeProductos gestionProductos;

    private static ControladorGestionProductos controladorGestionProductos = null;


    private ControladorGestionProductos() {
        vistaGestionProductos = new VistaGestionProductos();
        vistaGestionProductos.setActionListener(this);
        gestionProductos = GestionDeProductos.get();
    }

    public static ControladorGestionProductos getControladorGestionProductos() {
        if (controladorGestionProductos == null)
            controladorGestionProductos = new ControladorGestionProductos();

        controladorGestionProductos.actualizarListaProductos();

        vistaGestionProductos.mostrar();

        return controladorGestionProductos;
    }

    public void actualizarListaProductos(){
        Set<Producto> productos = gestionProductos.getProductos();
        DefaultListModel<Producto> listaProductos = new DefaultListModel<>();
        productos.forEach(listaProductos::addElement);
        ControladorGestionProductos.vistaGestionProductos.setModel(listaProductos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando =  e.getActionCommand();
        if(comando.equals("Alta Producto")) {
            vistaGestionProductos.esconder();
            ControladorAltaProducto controladorAltaProducto = ControladorAltaProducto.getControladorAltaProducto("Alta");
        }
        else  if( comando.equals("Baja Producto") ){
            Producto producto = (Producto) vistaGestionProductos.getSeleccion();
            gestionProductos.bajaProducto(producto.getId());
            vistaGestionProductos.success("Producto " + producto.getNombre() + "dada de baja");
            vistaGestionProductos.limpia();
            this.actualizarListaProductos();
        }
        else if( comando.equals("Modificar Producto") ){
            Producto producto = (Producto) vistaGestionProductos.getSeleccion();
            vistaGestionProductos.esconder();
            ControladorAltaProducto controladorAltaProducto = ControladorAltaProducto.getControladorAltaProducto("Modificar", producto);
        }
        else if( comando.equals("Volver") ){
            vistaGestionProductos.esconder();
            ControladorInicio controladorInicio = ControladorInicio.getControladorInicio(true);
        }
    }

}
