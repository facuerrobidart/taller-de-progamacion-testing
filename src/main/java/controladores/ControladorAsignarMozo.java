package controladores;

import dto.MesaDTO;
import dto.MozoDTO;
import enums.EstadoMozo;
import excepciones.MozoExistenteException;
import excepciones.PermisoDenegadoException;
import modelo.Empresa;
import modelo.Mesa;
import modelo.Mozo;
import negocio.GestionDeMesas;
import vistas.VistaAsignarMozo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class ControladorAsignarMozo implements ActionListener {

    private static ControladorAsignarMozo controladorAsignarMozo = null;
    private final GestionDeMesas gestionDeMesas;
    private final VistaAsignarMozo vistaAsignarMozo;
    private MesaDTO mesa;

    private ControladorAsignarMozo() {
        this.vistaAsignarMozo = new VistaAsignarMozo();
        this.vistaAsignarMozo.setActionListener(this);
        this.gestionDeMesas = GestionDeMesas.get();
    }

    public static ControladorAsignarMozo getControladorAsignarMozo(MesaDTO mesa) {
        if (controladorAsignarMozo == null) {
            controladorAsignarMozo = new ControladorAsignarMozo();
        }
        controladorAsignarMozo.vistaAsignarMozo.mostrar();

        controladorAsignarMozo.mesa = mesa;

        Set<Mozo> mozos = controladorAsignarMozo.gestionDeMesas.getMozos();
        JComboBox<Mozo> comboMozo = controladorAsignarMozo.vistaAsignarMozo.getComboBox();
        comboMozo.removeAllItems();
        for(Mozo mozo : mozos){
            if( mozo.getEstadoMozo() == EstadoMozo.ACTIVO )
                comboMozo.addItem(mozo);
        }

        controladorAsignarMozo.vistaAsignarMozo.setComboBox(comboMozo);
        controladorAsignarMozo.vistaAsignarMozo.limpia();

        return controladorAsignarMozo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if( comando.equalsIgnoreCase("Asignar") ){
            Mozo mozo = (Mozo) this.vistaAsignarMozo.getComboBox().getSelectedItem();
            MozoDTO mozoDTO = new MozoDTO(mozo.getNombreCompleto(),mozo.getFechaNacimiento(),mozo.getCantidadHijos());
            gestionDeMesas.asignarMozoMesa(mozoDTO, this.mesa);
            this.vistaAsignarMozo.success("El mozo " + mozoDTO.getNombreCompleto() + " fue asignado a la mesa " + this.mesa.getNroMesa());
            this.vistaAsignarMozo.esconder();
        }
        else if( comando.equalsIgnoreCase("Volver") ){
            this.vistaAsignarMozo.esconder();
        }
        ControladorGestionMesas controladorGestionMesas = ControladorGestionMesas.getControladorGestionMesas();
    }
}
