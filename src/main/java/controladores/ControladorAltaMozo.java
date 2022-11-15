package controladores;


import dto.MozoDTO;
import excepciones.MesaExistenteException;
import excepciones.MozoExistenteException;
import excepciones.PermisoDenegadoException;
import modelo.Mozo;
import negocio.GestionDeMozos;
import vistas.VistaAltaMozo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAltaMozo implements ActionListener {
    private static ControladorAltaMozo controladorAltaMozo = null;
    private final GestionDeMozos gestionDeMozos;
    private final VistaAltaMozo vistaAltaMozo;
    private String op;
    private Mozo mozo;

    private ControladorAltaMozo() {
        this.vistaAltaMozo = new VistaAltaMozo();
        this.vistaAltaMozo.setActionListener(this);
        this.gestionDeMozos = GestionDeMozos.get();
    }

    public static ControladorAltaMozo getControladorAltaMozo(String op) {
        if (controladorAltaMozo == null) {
            controladorAltaMozo = new ControladorAltaMozo();
        }
        controladorAltaMozo.op = op;
        controladorAltaMozo.vistaAltaMozo.mostrar();

        return controladorAltaMozo;
    }

    public static ControladorAltaMozo getControladorAltaMozo(String op, Mozo mozo) {
        if (controladorAltaMozo == null) {
            controladorAltaMozo = new ControladorAltaMozo();
        }
        controladorAltaMozo.op = op;
        controladorAltaMozo.mozo = mozo;
        controladorAltaMozo.vistaAltaMozo.mostrar();

        return controladorAltaMozo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if( comando.equalsIgnoreCase("Aceptar") ){
            String nombre = this.vistaAltaMozo.getNombre();
            int hijos = this.vistaAltaMozo.getCantHijos();
            String fecha = this.vistaAltaMozo.getFechaNacimiento();
            MozoDTO mozoDTO = new MozoDTO(nombre,fecha,hijos);
            if( hijos < 0 ){
                vistaAltaMozo.failure("La CANTIDAD DE HIJOS deber ser mayor o igual a cero");
                vistaAltaMozo.getTxtCantHijos().setText("");
            }else{
                try {
                    if(op.equalsIgnoreCase("Alta")){
                        gestionDeMozos.altaMozo(mozoDTO);
                        this.vistaAltaMozo.success("El mozo: " + mozoDTO.getNombreCompleto() + " fue dado de alta con exito");
                    }else{
                        boolean existeMozo = gestionDeMozos.getMozos().stream().anyMatch(m -> m.getNombreCompleto().equalsIgnoreCase(mozoDTO.getNombreCompleto()) );
                        if( !existeMozo ){
                            gestionDeMozos.bajaMozo(mozo);
                            gestionDeMozos.altaMozo(mozoDTO);
                            this.vistaAltaMozo.success("El mozo: " + mozoDTO.getNombreCompleto() + " fue modificado con exito");
                        }else
                            throw new MozoExistenteException();
                    }
                } catch (MozoExistenteException ex) {
                    this.vistaAltaMozo.failure("El mozo: " + mozoDTO.getNombreCompleto() + " ya se encuentra en el sistema");
                }
                this.vistaAltaMozo.esconder();
            }
        }else if( comando.equalsIgnoreCase("Volver") ){
            this.vistaAltaMozo.esconder();
        }
        ControladorGestionMozos CMosos = ControladorGestionMozos.getControladorGestionMozos();
    }
}
