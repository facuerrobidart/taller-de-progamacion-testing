package vistas;

import java.awt.event.ActionListener;

public interface IGenerica {
	
	void setActionListener(ActionListener actionListener);
	
	void mostrar();

	void esconder();
	
	void limpia();

	void success(String msg);

	void failure(String msg);
	
}
