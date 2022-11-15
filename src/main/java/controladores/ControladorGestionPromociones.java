package controladores;

import dto.PromocionProductoDTO;
import dto.PromocionTemporalDTO;
import excepciones.ProductoNoExistenteException;
import excepciones.PromocionExistenteException;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;
import modelo.promociones.PromocionTemporal;
import negocio.GestionDePromociones;
import vistas.VistaGestionPromociones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ControladorGestionPromociones implements ActionListener {

    private static VistaGestionPromociones vistaGestionPromociones;
    private static GestionDePromociones gestionPromociones;

    private static ControladorGestionPromociones controladorGestionPromociones = null;


    private ControladorGestionPromociones() {
        vistaGestionPromociones = new VistaGestionPromociones();
        vistaGestionPromociones.setActionListener(this);
        gestionPromociones = GestionDePromociones.get();
    }

    public static ControladorGestionPromociones getControladorGestionPromociones() {
        if (controladorGestionPromociones == null)
            controladorGestionPromociones = new ControladorGestionPromociones();

        controladorGestionPromociones.actualizarListaPromos();

        vistaGestionPromociones.mostrar();

        return controladorGestionPromociones;
    }

    public void actualizarListaPromos() {
        Set<PromocionFija> promocionesFijas = gestionPromociones.getPromocionesFijas();
        Set<PromocionTemporal> promocionesTemporales = gestionPromociones.getPromocionesTemporales();
        DefaultListModel<Promocion> listaPromociones = new DefaultListModel<>();
        promocionesFijas.forEach(listaPromociones::addElement);
        promocionesTemporales.forEach(listaPromociones::addElement);
        vistaGestionPromociones.setModel(listaPromociones);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String comando =  e.getActionCommand();

        if(comando.equals("Alta Promocion Temporal")) {
            vistaGestionPromociones.esconder();
            ControladorAltaPromocionTemporal con = ControladorAltaPromocionTemporal.getControladorAltaPromocionTemporal("Alta");
        }
        else if(comando.equals("Alta promocion por producto")){
            vistaGestionPromociones.esconder();
            ControladorAltaPromocionProducto con = ControladorAltaPromocionProducto.getControladorAltaPromocionProducto("Alta");
        }
        else if( comando.equals("Modificar Promocion")) {

            Promocion promocion = (Promocion) vistaGestionPromociones.getSeleccion();
            PromocionTemporalDTO promocionTemporalDTO = null;
            PromocionProductoDTO promocionProductoDTO = null;

            vistaGestionPromociones.esconder();

            if (promocion instanceof PromocionTemporal) {
                PromocionTemporal promocionTemporal = (PromocionTemporal) promocion;
                promocionTemporalDTO = new PromocionTemporalDTO(promocionTemporal.getNombre(),
                        promocionTemporal.isActivo(),
                        promocionTemporal.getDiasPromo(),
                        promocionTemporal.getFormaPago(),
                        promocionTemporal.getPorcentajeDescuento(),
                        promocionTemporal.isEsAcumulable()
                );
                promocionTemporalDTO.setId(promocion.getId());

                ControladorAltaPromocionTemporal controladorTemporal = ControladorAltaPromocionTemporal.getControladorAltaPromocionTemporal("Modificar", promocionTemporalDTO);

            } else {
                PromocionFija promocionFija = (PromocionFija) promocion;
                promocionProductoDTO = new PromocionProductoDTO(promocionFija.getNombre(),
                        promocionFija.isActivo(),
                        promocionFija.getDiasPromo(),
                        promocionFija.getProducto(),
                        promocionFija.isDosPorUno(),
                        promocionFija.isDtoPorCant(),
                        promocionFija.getDtoPorCantMin(),
                        promocionFija.getDtoPorCantPrecioU()
                );
                promocionProductoDTO.setId(promocion.getId());
                ControladorAltaPromocionProducto controladorProducto = ControladorAltaPromocionProducto.getControladorAltaPromocionProducto("Modificar", promocionProductoDTO);

            }
        }
        else if (comando.equals("Baja Promocion")) {

            Promocion promocion = (Promocion) vistaGestionPromociones.getSeleccion();

            if (promocion instanceof PromocionTemporal) {
                gestionPromociones.bajaPromocionTemporal(promocion.getId());
                vistaGestionPromociones.success("Promocion temporal: " + promocion.getNombre() + "dada de baja");
            } else {
                gestionPromociones.bajaPromocionFija(promocion.getId());
                vistaGestionPromociones.success("Promocion fija: " + promocion.getNombre() + "dada de baja");
            }
            vistaGestionPromociones.limpia();
            this.actualizarListaPromos();

        }else if( comando.equals("Volver") ){
            vistaGestionPromociones.esconder();
            ControladorInicio controladorInicio = ControladorInicio.getControladorInicio(true);
        }
    }
}
