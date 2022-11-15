package controladores;

import enums.EstadoMozo;
import excepciones.MozoNoExistenteException;
import modelo.Mozo;
import negocio.GestionDeMozos;
import vistas.VistaCambioEstadoMozo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorCambioEstadoMozo implements ActionListener {

    private static ControladorCambioEstadoMozo controladorCambioEstadoMozo = null;
    private final GestionDeMozos gestionDeMozos;
    private static VistaCambioEstadoMozo vistaCambioEstadoMozo;

    private static Mozo mozo;

    private ControladorCambioEstadoMozo() {
        this.vistaCambioEstadoMozo = new VistaCambioEstadoMozo();
        this.vistaCambioEstadoMozo.setActionListener(this);
        this.gestionDeMozos = GestionDeMozos.get();
    }

    public static ControladorCambioEstadoMozo getControladorCambioEstadoMozo(Mozo mozo){
        if(controladorCambioEstadoMozo==null)
            controladorCambioEstadoMozo = new ControladorCambioEstadoMozo();
        controladorCambioEstadoMozo.mozo = mozo;
        vistaCambioEstadoMozo.mostrar();

        return controladorCambioEstadoMozo;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if(comando.equalsIgnoreCase("Aceptar")) {
            String nuevoEstado = vistaCambioEstadoMozo.getSelection();
            if (nuevoEstado.equalsIgnoreCase("Activo"))
                gestionDeMozos.modEstadoMozo(mozo, EstadoMozo.ACTIVO);

            else if (nuevoEstado.equalsIgnoreCase("Ausente"))
                gestionDeMozos.modEstadoMozo(mozo, EstadoMozo.AUSENTE);

            else
                gestionDeMozos.modEstadoMozo(mozo, EstadoMozo.FRANCO);

            vistaCambioEstadoMozo.success("Se actualizo el estado de mozo a "+nuevoEstado);
            vistaCambioEstadoMozo.esconder();
            ControladorGestionMozos.getControladorGestionMozos();
        }
        else {
            vistaCambioEstadoMozo.esconder();
            ControladorGestionMozos.getControladorGestionMozos();
        }
    }
}
