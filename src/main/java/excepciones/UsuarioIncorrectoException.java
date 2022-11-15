package excepciones;

public class UsuarioIncorrectoException extends Exception {
	private String dato;

	public UsuarioIncorrectoException(String nombreUsuario, String message) {
		super(message);
		this.dato = nombreUsuario;
	}

}
