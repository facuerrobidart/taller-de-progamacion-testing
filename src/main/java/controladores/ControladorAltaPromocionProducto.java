package controladores;

import dto.PromocionProductoDTO;
import enums.Dias;
import excepciones.PromocionExistenteException;
import modelo.Mesa;
import modelo.Producto;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;
import negocio.GestionDeProductos;
import negocio.GestionDePromociones;
import vistas.VistaAltaPromocionProducto;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControladorAltaPromocionProducto implements ActionListener {

    private GestionDePromociones gestionDePromociones;
    private static GestionDeProductos gestionDeProductos;
    private static ControladorAltaPromocionProducto controladorAltaPromocionProducto = null;
    private static VistaAltaPromocionProducto vistaAltaPromocionProducto;
    private String op;
    private PromocionProductoDTO promocionFija;

    private ControladorAltaPromocionProducto(){
        this.gestionDePromociones = GestionDePromociones.get();
        this.gestionDeProductos = GestionDeProductos.get();
        vistaAltaPromocionProducto = new VistaAltaPromocionProducto();
        vistaAltaPromocionProducto.setActionListener(this);
    }

    public static ControladorAltaPromocionProducto getControladorAltaPromocionProducto(String op) {
        if (controladorAltaPromocionProducto == null) {
            controladorAltaPromocionProducto = new ControladorAltaPromocionProducto();
        }
        Set<Producto> productos = gestionDeProductos.getProductos();
        JComboBox<Producto> comboP = vistaAltaPromocionProducto.getComboBox();
        comboP.removeAllItems();
        for(Producto prod : productos){
            comboP.addItem(prod);
        }
        vistaAltaPromocionProducto.setComboBox(comboP);

        controladorAltaPromocionProducto.op = op;
        vistaAltaPromocionProducto.mostrar();

        return controladorAltaPromocionProducto;
    }

    public static ControladorAltaPromocionProducto getControladorAltaPromocionProducto(String op, PromocionProductoDTO promocionFija) {
        if (controladorAltaPromocionProducto == null) {
            controladorAltaPromocionProducto = new ControladorAltaPromocionProducto();
        }
        Set<Producto> productos = gestionDeProductos.getProductos();
        JComboBox<Producto> comboP = vistaAltaPromocionProducto.getComboBox();
        comboP.removeAllItems();
        for(Producto prod : productos){
            comboP.addItem(prod);
        }
        vistaAltaPromocionProducto.setComboBox(comboP);

        controladorAltaPromocionProducto.op = op;
        controladorAltaPromocionProducto.promocionFija = promocionFija;
        vistaAltaPromocionProducto.limpiaCampos();
        vistaAltaPromocionProducto.mostrar();

        return controladorAltaPromocionProducto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        ControladorAltaPromocionProducto controladorAltaPromocionProducto1;

        if(comando.equalsIgnoreCase("Finalizar")){
            float precioPromo;
            int cantMinima;
            String nombre = vistaAltaPromocionProducto.getNombre();
            boolean activa = vistaAltaPromocionProducto.getChckbxActiva().isSelected();
            List<Dias> dias = new ArrayList<>();
            if(vistaAltaPromocionProducto.getChckbxLunes().isSelected())
                dias.add(Dias.LUNES);
            if(vistaAltaPromocionProducto.getChckbxMartes().isSelected())
                dias.add(Dias.MARTES);
            if(vistaAltaPromocionProducto.getChckbxMiercoles().isSelected())
                dias.add(Dias.MIERCOLES);
            if(vistaAltaPromocionProducto.getChckbxJueves().isSelected())
                dias.add(Dias.JUEVES);
            if(vistaAltaPromocionProducto.getChckbxViernes().isSelected())
                dias.add(Dias.VIERNES);
            if(vistaAltaPromocionProducto.getChckbxSabado().isSelected())
                dias.add(Dias.SABADO);
            if(vistaAltaPromocionProducto.getChckbxDomingo().isSelected())
                dias.add(Dias.DOMINGO);
            Producto prod = (Producto) vistaAltaPromocionProducto.getComboBox().getSelectedItem();
            PromocionProductoDTO prom;
            if(vistaAltaPromocionProducto.getChckbxDesc().isSelected()) {
                precioPromo = vistaAltaPromocionProducto.getPrecioPromo();
                cantMinima = vistaAltaPromocionProducto.getCantMinima();
                if (precioPromo <= 0 || cantMinima <= 0){
                    if( precioPromo <= 0 ){
                        vistaAltaPromocionProducto.failure("El PRECIO DE LA PROMOCION debe ser un numero mayor a cero");
                        vistaAltaPromocionProducto.getTextPrecio().setText("");
                    }
                    if( cantMinima <= 0 ){
                        vistaAltaPromocionProducto.failure("La CANTIDAD MINIMA debe ser un numero mayor a cero");
                        vistaAltaPromocionProducto.getTxtCantMinima().setText("");
                    }
                }
                else {
                    prom = new PromocionProductoDTO(nombre, activa, dias, prod, false, true, cantMinima, precioPromo);
                    try {
                        if(op.equalsIgnoreCase("Alta")){
                            gestionDePromociones.altaPromocionFija(prom);
                            vistaAltaPromocionProducto.success("La promocion fija: " + prom.getNombre() + " se ha dado de alta con exito");
                        }else{
                            boolean existePromocion = gestionDePromociones.getPromocionesFijas().stream().anyMatch(p -> p.getNombre().equals(prom.getNombre()));
                            if( !existePromocion ){
                                gestionDePromociones.bajaPromocionFija(promocionFija.getId());
                                gestionDePromociones.altaPromocionFija(prom);
                                vistaAltaPromocionProducto.success("La promocion fija: " + prom.getNombre() + " se ha modificado con exito");
                            }else
                                throw new PromocionExistenteException(prom.getNombre());
                        }
                    } catch (PromocionExistenteException ex) {
                        vistaAltaPromocionProducto.failure("La promocion fija:" + prom.getNombre() + " ya se encuentra en el sistema");
                    }
                    vistaAltaPromocionProducto.esconder();
                }
            }
            else{
                prom = new PromocionProductoDTO(nombre, activa, dias, prod, true, true, 0, 0);
                try {
                    if(op.equalsIgnoreCase("Alta")){
                        gestionDePromociones.altaPromocionFija(prom);
                        vistaAltaPromocionProducto.success("La promocion fija: " + prom.getNombre() + " se ha dado de alta con exito");
                    }else{
                        boolean existePromocion = gestionDePromociones.getPromocionesFijas().stream().anyMatch(p -> p.getNombre().equals(prom.getNombre()));
                        if( !existePromocion ){
                            gestionDePromociones.bajaPromocionFija(promocionFija.getId());
                            gestionDePromociones.altaPromocionFija(prom);
                            vistaAltaPromocionProducto.success("La promocion fija: " + prom.getNombre() + " se ha modificado con exito");
                        }else
                            throw new PromocionExistenteException(prom.getNombre());
                    }
                } catch (PromocionExistenteException ex) {
                    vistaAltaPromocionProducto.failure("La promocion fija:" + prom.getNombre() + " ya se encuentra en el sistema");
                }
                vistaAltaPromocionProducto.esconder();
            }
        }
        else if( comando.equals("Volver") ){
            vistaAltaPromocionProducto.esconder();
        }
            ControladorGestionPromociones controladorGestionPromociones = ControladorGestionPromociones.getControladorGestionPromociones();
    }
}
