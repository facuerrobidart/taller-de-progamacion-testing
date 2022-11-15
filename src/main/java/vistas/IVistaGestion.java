package vistas;

import modelo.Mesa;
import modelo.Operario;

import javax.swing.*;

public interface IVistaGestion extends IGenerica{
	
	public void setModel(DefaultListModel<?> lista);

	public Object getSeleccion();

}
