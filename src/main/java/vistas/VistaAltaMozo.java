package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VistaAltaMozo extends JFrame implements IGenerica, KeyListener{

	private JFrame frmAltaDeMozo;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JTextField txtNombre;
	private JTextField txtFechaNacimiento;
	private JTextField txtCantHijos;
	
	private String nombre = null;
	private String fechaNacimiento = null;
	private int cantHijos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAltaMozo window = new VistaAltaMozo();
					window.frmAltaDeMozo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaAltaMozo() {
		initialize();
	}

	private void initialize() {
		frmAltaDeMozo = new JFrame();
		frmAltaDeMozo.setTitle("Nuevo Mozo");
		frmAltaDeMozo.setBounds(100, 100, 450, 300);
		frmAltaDeMozo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaDeMozo.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre completo");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(39, 24, 110, 21);
		frmAltaDeMozo.getContentPane().add(lblNombre);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento");
		lblFechaDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaDeNacimiento.setBounds(39, 83, 126, 20);
		frmAltaDeMozo.getContentPane().add(lblFechaDeNacimiento);
		
		JLabel lblCantHijos = new JLabel("Cantidad de Hijos");
		lblCantHijos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantHijos.setBounds(39, 138, 126, 32);
		frmAltaDeMozo.getContentPane().add(lblCantHijos);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 20, 184, 32);
		frmAltaDeMozo.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(274, 218, 110, 32);
		frmAltaDeMozo.getContentPane().add(btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(39, 218, 110, 32);
		frmAltaDeMozo.getContentPane().add(btnVolver);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(200, 80, 184, 32);
		txtFechaNacimiento.addKeyListener(this);
		frmAltaDeMozo.getContentPane().add(txtFechaNacimiento);
		
		txtCantHijos = new JTextField();
		txtCantHijos.setColumns(10);
		txtCantHijos.setBounds(200, 140, 184, 32);
		txtCantHijos.addKeyListener(this);
		frmAltaDeMozo.getContentPane().add(txtCantHijos);
		
		limpia();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

		this.nombre = this.txtNombre.getText();
		this.fechaNacimiento = this.txtFechaNacimiento.getText();
		if( nombre.length() > 0 && fechaNacimiento.length() > 0 && txtCantHijos.getText().length() > 0) {
			this.btnAceptar.setEnabled(true);
		}
	}



	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAceptar.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmAltaDeMozo.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmAltaDeMozo.setVisible(false);
		limpia();
	}

	@Override
	public void limpia() {
		this.btnAceptar.setEnabled(false);
		this.txtNombre.setText("");
		this.txtFechaNacimiento.setText("");
		this.txtCantHijos.setText("");
	}

	@Override
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Resultado exitoso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public String getNombre() {
		return nombre;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public int getCantHijos() {
		try {
			cantHijos = Integer.parseInt(txtCantHijos.getText());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ingreso mal el costo del producto", "Error", JOptionPane.ERROR_MESSAGE);
			this.txtCantHijos.setText("");
		}

		return cantHijos;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtFechaNacimiento() {
		return txtFechaNacimiento;
	}

	public void setTxtFechaNacimiento(JTextField txtFechaNacimiento) {
		this.txtFechaNacimiento = txtFechaNacimiento;
	}

	public JTextField getTxtCantHijos() {
		return txtCantHijos;
	}

	public void setTxtCantHijos(JTextField txtCantHijos) {
		this.txtCantHijos = txtCantHijos;
	}
}
