package excepciones;

public class ContrasenaIncorrectaException extends Exception {
	private String dato;

	public ContrasenaIncorrectaException(String nombreUsuario, String message) {
		super(message);
		this.dato = nombreUsuario;
	}

}
