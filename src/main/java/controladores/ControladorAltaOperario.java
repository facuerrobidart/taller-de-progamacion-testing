package controladores;

import dto.OperarioDTO;
import excepciones.OperarioExistenteException;
import excepciones.PermisoDenegadoException;
import modelo.Empresa;
import modelo.Operario;
import negocio.GestionDeOperarios;
import vistas.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAltaOperario implements ActionListener {

    private static ControladorAltaOperario controladorAltaOperario = null;
    private Empresa empresa;
    private IAltaOperario vistaAltaOperario;
    private GestionDeOperarios gestionDeOperarios;
    private String op;
    private Operario operario;

    private ControladorAltaOperario() {
        this.vistaAltaOperario = new VistaAltaOperario();
        this.gestionDeOperarios = GestionDeOperarios.get();
        this.vistaAltaOperario.setActionListener(this);
    }

    public static ControladorAltaOperario getControladorAltaOperario(String op) {
        if (controladorAltaOperario == null) {
            controladorAltaOperario = new ControladorAltaOperario();
        }
        controladorAltaOperario.op = op;
        controladorAltaOperario.vistaAltaOperario.mostrar();

        return controladorAltaOperario;
    }

    public static ControladorAltaOperario getControladorAltaOperario(String op, Operario operario) {
        if (controladorAltaOperario == null) {
            controladorAltaOperario = new ControladorAltaOperario();
        }
        controladorAltaOperario.op = op;
        controladorAltaOperario.operario = operario;
        controladorAltaOperario.vistaAltaOperario.mostrar();

        return controladorAltaOperario;
    }


    public IGenerica getVistaAltaOperario() {
        return vistaAltaOperario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if(comando.equalsIgnoreCase("Aceptar")){
            String nombre = this.vistaAltaOperario.getNombre();
            String username = this.vistaAltaOperario.getUsername();
            String password = this.vistaAltaOperario.getPassword();
            boolean activo;
            if( this.vistaAltaOperario.getEstado().equalsIgnoreCase("activo") )
                activo = true;
            else
                activo = false;
            OperarioDTO operarioDTO = new OperarioDTO(nombre, username, password, activo);
            try {
                if(op.equalsIgnoreCase("Alta")){
                    gestionDeOperarios.altaOperario(operarioDTO);
                    this.vistaAltaOperario.success("El operario: " + operarioDTO.getNombreCompleto() + " fue dado de alta con exito");
                }else{
                    boolean existeOperario = gestionDeOperarios.getOperarios().stream().anyMatch(o -> o.getNombreCompleto().equalsIgnoreCase(operarioDTO.getNombreCompleto()) );
                    if( !existeOperario ){
                        gestionDeOperarios.bajaOperario(operario.getNombreCompleto());
                        gestionDeOperarios.altaOperario(operarioDTO);
                        this.vistaAltaOperario.success("El operario: " + operarioDTO.getNombreCompleto() + " fue modificado con exito");
                    }else
                        throw new OperarioExistenteException();
                }
            } catch (OperarioExistenteException | PermisoDenegadoException ex) {
                if( ex instanceof OperarioExistenteException ){
                    this.vistaAltaOperario.failure("El operario ya se encuentra ingresado en el sistema");
                    ControladorGestionOperario.getControladorGestionOperario();
                }else{
                    this.vistaAltaOperario.failure("No tiene permisos para realizar el alta de un operario");
                    ControladorGestionOperario.getControladorGestionOperario();
                }
            }
            this.vistaAltaOperario.esconder();
        }
        else{
            this.vistaAltaOperario.esconder();
        }
        ControladorGestionOperario con = ControladorGestionOperario.getControladorGestionOperario();
    }
}
