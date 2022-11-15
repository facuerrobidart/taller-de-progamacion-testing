package controladores;

import modelo.Empresa;
import negocio.GestionDeMozos;
import negocio.GestionDePromociones;
import vistas.IGenerica;
import vistas.VistaInicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorInicio implements ActionListener {

    private VistaInicio vistaInicio;
    private Empresa empresa;

    private static ControladorInicio controladorInicio = null;

    private ControladorInicio() {
        this.vistaInicio = new VistaInicio();
        this.vistaInicio.setActionListener(this);
        this.empresa = Empresa.getEmpresa();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public static ControladorInicio getControladorInicio(boolean mostrar) {
        if (controladorInicio == null) {
            controladorInicio = new ControladorInicio();
        }
        if( mostrar )
            controladorInicio.vistaInicio.mostrar();

        return controladorInicio;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if(comando.equals("Gestionar Mozos")){
            this.vistaInicio.esconder();
            ControladorGestionMozos controladorGestionMozos = ControladorGestionMozos.getControladorGestionMozos();
        }
        else if(comando.equals("Gestionar Operarios")){
            this.vistaInicio.esconder();
            ControladorGestionOperario controladorGestionOperario = ControladorGestionOperario.getControladorGestionOperario();
        }
        else if(comando.equals("Gestionar Mesas")){
            this.vistaInicio.esconder();
            ControladorGestionMesas controladorGestionMesas = ControladorGestionMesas.getControladorGestionMesas();
        }
        else if(comando.equals("Gestionar Productos")){
            this.vistaInicio.esconder();
            ControladorGestionProductos controladorGestionProductos = ControladorGestionProductos.getControladorGestionProductos();
        }
        else if(comando.equals("Gestionar Promociones")){
            this.vistaInicio.esconder();
            ControladorGestionPromociones controladorGestionPromociones = ControladorGestionPromociones.getControladorGestionPromociones();
        }
        else if(comando.equals("Abrir Local")){
            if(GestionDePromociones.get().hayPromocionesHoy()) {
                if (GestionDeMozos.get().hayMozosActivos()) {
                    this.vistaInicio.esconder();
                    ControladorLocalAbierto controladorLocalAbierto = ControladorLocalAbierto.getControladorLocalAbierto();
                } else
                    this.vistaInicio.failure("No hay mozos activos en el sistema");
            }
            else
                this.vistaInicio.failure("No hay suficientes promociones por producto para abrir el local");
        }
        else if(comando.equals("Cerrar Sesion")){
            this.vistaInicio.esconder();
            ControladorLogin controladorLogin = ControladorLogin.getControladorLogin();
        }


    }
}
