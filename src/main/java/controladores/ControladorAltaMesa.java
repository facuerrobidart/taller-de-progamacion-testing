package controladores;

import dto.MesaDTO;
import dto.MozoDTO;
import excepciones.MesaExistenteException;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import negocio.GestionDeMesas;
import vistas.VistaAltaMesa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAltaMesa implements ActionListener {

    private static ControladorAltaMesa controladorAltaMesa = null;
    private final GestionDeMesas gestionDeMesas;
    private final VistaAltaMesa vistaAltaMesa;
    private String op;
    private Mesa mesa;

    private ControladorAltaMesa() {
        this.vistaAltaMesa = new VistaAltaMesa();
        this.vistaAltaMesa.setActionListener(this);
        this.gestionDeMesas = GestionDeMesas.get();
    }

    public static ControladorAltaMesa getControladorAltaMesa(String op) {
        if (controladorAltaMesa == null) {
            controladorAltaMesa = new ControladorAltaMesa();
        }
        controladorAltaMesa.op = op;
        controladorAltaMesa.vistaAltaMesa.mostrar();

        return controladorAltaMesa;
    }

    public static ControladorAltaMesa getControladorAltaMesa(String op, Mesa mesa) {
        if (controladorAltaMesa == null) {
            controladorAltaMesa = new ControladorAltaMesa();
        }
        controladorAltaMesa.op = op;
        controladorAltaMesa.mesa = mesa;
        controladorAltaMesa.vistaAltaMesa.mostrar();

        return controladorAltaMesa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if( comando.equalsIgnoreCase("Aceptar") ){
            int nroMesa = vistaAltaMesa.getNumeroMesa();
            int cantSilla = vistaAltaMesa.getCantSillas();
            if( nroMesa < 0 || cantSilla <= 0 )
                if(op.equalsIgnoreCase("Alta")){
                    if( nroMesa <  0 ){
                        vistaAltaMesa.failure("El NUMERO DE MESA debe ser mayor o igual a cero");
                        this.vistaAltaMesa.getTxtNumeroMesa().setText("");
                    }
                    if( cantSilla <= 0 ){
                        vistaAltaMesa.failure("La CANTIDAD DE SILLAS debe ser mayor a cero");
                        this.vistaAltaMesa.getTxtCantSillas().setText("");
                    }
                }
                else
                    ControladorGestionMesas.getControladorGestionMesas();
            else {
                try {
                    if (op.equalsIgnoreCase("Alta")) {
                        MesaDTO mesaDTO = new MesaDTO(nroMesa, cantSilla);
                        gestionDeMesas.altaMesa(mesaDTO);
                        this.vistaAltaMesa.success("La mesa: " + mesaDTO.getNroMesa() + " fue dada de alta con exito");
                        this.vistaAltaMesa.esconder();
                        ControladorGestionMesas CMesas = ControladorGestionMesas.getControladorGestionMesas();
                    } else {
                        MesaDTO mesaDTO = new MesaDTO(nroMesa, cantSilla);
                        Mozo mozoA = mesa.getMozoAsignado();
                        boolean existeMesa = gestionDeMesas.getMesas().stream().anyMatch(m -> m.getNroMesa() == mesaDTO.getNroMesa());
                        if (!existeMesa) {
                            gestionDeMesas.bajaMesa(mesa.getNroMesa());
                            if (mozoA != null)
                                gestionDeMesas.asignarMozoMesa(new MozoDTO(mozoA.getNombreCompleto(), mozoA.getFechaNacimiento(), mozoA.getCantidadHijos()), mesaDTO);
                            gestionDeMesas.altaMesa(mesaDTO);
                            this.vistaAltaMesa.success("La mesa: " + mesaDTO.getNroMesa() + " fue modificada con exito");
                            this.vistaAltaMesa.esconder();
                            ControladorGestionMesas CMesas = ControladorGestionMesas.getControladorGestionMesas();
                        } else
                            throw new MesaExistenteException();
                    }
                } catch (MesaExistenteException ex) {
                    this.vistaAltaMesa.failure("La mesa: " + nroMesa + " ya se encuentra en el sistema");
                    ControladorGestionMesas CMesas = ControladorGestionMesas.getControladorGestionMesas();
                }
            }
        }else if( comando.equalsIgnoreCase("Volver") ){
            this.vistaAltaMesa.esconder();
            ControladorGestionMesas CMesas = ControladorGestionMesas.getControladorGestionMesas();
        }
    }

}
