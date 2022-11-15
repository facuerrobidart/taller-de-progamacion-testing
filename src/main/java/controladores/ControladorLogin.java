package controladores;

import excepciones.ContrasenaIncorrectaException;
import excepciones.UsuarioIncorrectoException;
import modelo.Empresa;
import modelo.Operario;
import vistas.ILogin;
import vistas.VistaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {

	private static ControladorLogin controladorLogin = null;
	private Empresa empresa;
	private ILogin vistaLogin;
	private static Operario logueado;

	private ControladorLogin() {
		this.vistaLogin = new VistaLogin();
		this.empresa = Empresa.getEmpresa();
		this.vistaLogin.setActionListener(this);

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public static ControladorLogin getControladorLogin() {
		if (controladorLogin == null) {
			controladorLogin = new ControladorLogin();
		}
		logueado = null;

		controladorLogin.vistaLogin.mostrar();

		return controladorLogin;
	}
	
	public ILogin getVistaLogin() {
		return vistaLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
			try {
				if( logueado != null )
					logueado = null;
				logueado = this.empresa.login(vistaLogin.getUsername(), vistaLogin.getContrasena());
				if (logueado != null) {
					this.vistaLogin.esconder();
					this.vistaLogin.limpiaCampos();
					ControladorInicio con = ControladorInicio.getControladorInicio(true);
				}
			} catch (UsuarioIncorrectoException err) {
				this.vistaLogin.usuarioNoEncontrado();
			} catch (ContrasenaIncorrectaException err) {
				this.vistaLogin.contrasenaIncorrecta();
			}


	}

	public void setLogueado(Operario logueado) {
		this.logueado = logueado;
	}

	public Operario getLogueado() {
		return logueado;
	}   	

}
