package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VistaAltaMesa extends JFrame implements IGenerica, KeyListener{

	private JFrame frmAltaDeMesa;
	private JTextField txtNumeroMesa;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JTextField txtCantSillas;
	
	private int numeroMesa;
	private int cantSillas;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAltaMesa window = new VistaAltaMesa();
					window.frmAltaDeMesa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaAltaMesa() {
		initialize();
	}

	private void initialize() {
		frmAltaDeMesa = new JFrame();
		frmAltaDeMesa.setTitle("Nueva Mesa");
		frmAltaDeMesa.setBounds(100, 100, 450, 300);
		frmAltaDeMesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaDeMesa.getContentPane().setLayout(null);
		
		JLabel lblNumeroMesa = new JLabel("Numero de mesa");
		lblNumeroMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumeroMesa.setBounds(39, 24, 110, 21);
		frmAltaDeMesa.getContentPane().add(lblNumeroMesa);
		
		JLabel lblCantSillas = new JLabel("Cantidad de sillas\r\n");
		lblCantSillas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCantSillas.setBounds(41, 124, 126, 20);
		frmAltaDeMesa.getContentPane().add(lblCantSillas);
		
		txtNumeroMesa = new JTextField();
		txtNumeroMesa.setBounds(200, 20, 184, 32);
		frmAltaDeMesa.getContentPane().add(txtNumeroMesa);
		txtNumeroMesa.setColumns(10);
		txtNumeroMesa.addKeyListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(274, 205, 110, 32);
		frmAltaDeMesa.getContentPane().add(btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(39, 205, 110, 32);
		frmAltaDeMesa.getContentPane().add(btnVolver);
		
		txtCantSillas = new JTextField();
		txtCantSillas.setColumns(10);
		txtCantSillas.setBounds(200, 120, 184, 32);
		frmAltaDeMesa.getContentPane().add(txtCantSillas);
		txtCantSillas.addKeyListener(this);
		
		limpia();
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAceptar.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmAltaDeMesa.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmAltaDeMesa.setVisible(false);
		this.limpia();
	}

	@Override
	public void limpia() {
		this.btnAceptar.setEnabled(false);
		this.txtNumeroMesa.setText("");
		this.txtCantSillas.setText("");
	}

	@Override
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	@Override
	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
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
		this.btnAceptar.setEnabled( this.txtCantSillas.getText().length() > 0 && this.txtNumeroMesa.getText().length() > 0 );
	}

	public int getNumeroMesa() {
		try {
			this.numeroMesa = Integer.parseInt(this.txtNumeroMesa.getText());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ingreso mal el numero de mesa", "Error", JOptionPane.ERROR_MESSAGE);
			this.txtNumeroMesa.setText("");
			return 0;
		}
		return numeroMesa;
	}

	public int getCantSillas() {
		try {
			this.cantSillas = Integer.parseInt(this.txtCantSillas.getText());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ingreso mal la cantidad de sillas", "Error", JOptionPane.ERROR_MESSAGE);
			this.txtCantSillas.setText("");
			return 0;
		}
		return cantSillas;
	}

	public JTextField getTxtNumeroMesa() {
		return txtNumeroMesa;
	}

	public void setTxtNumeroMesa(JTextField txtNumeroMesa) {
		this.txtNumeroMesa = txtNumeroMesa;
	}

	public JTextField getTxtCantSillas() {
		return txtCantSillas;
	}

	public void setTxtCantSillas(JTextField txtCantSillas) {
		this.txtCantSillas = txtCantSillas;
	}
}
