package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class VistaInicio extends JFrame implements IGenerica {

	JFrame frmInicioOperario;
	private JButton btnApertura,btnCerrarSesion,btnGestionPromos;
	private JButton btnGestionProductos,btnGestionMesas,btnGestionOperarios,btnGestionMozos;
	private ActionListener actionListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaInicio window = new VistaInicio();
					window.frmInicioOperario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaInicio() {
		initialize();
	}

	private void initialize() {
		frmInicioOperario = new JFrame();
		frmInicioOperario.setTitle("Inicio Operario");
		frmInicioOperario.setBounds(100, 100, 532, 406);
		frmInicioOperario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInicioOperario.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelGral = new JPanel();
		frmInicioOperario.getContentPane().add(panelGral);
		panelGral.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 0, 516, 303);
		panelGral.add(panel2);
		panel2.setLayout(null);
		
		JPanel panelGrid = new JPanel();
		panelGrid.setBounds(0, 0, 516, 303);
		panel2.add(panelGrid);
		panelGrid.setLayout(null);
		
		JPanel panelMo = new JPanel();
		panelMo.setBounds(0, 11, 253, 102);
		panelGrid.add(panelMo);
		panelMo.setLayout(null);
		
		btnGestionMozos = new JButton("Gestionar Mozos");
		btnGestionMozos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionMozos.setBounds(41, 11, 170, 80);
		panelMo.add(btnGestionMozos);
		
		JPanel panelMo_1 = new JPanel();
		panelMo_1.setBounds(263, 11, 253, 102);
		panelMo_1.setLayout(null);
		panelGrid.add(panelMo_1);
		
		btnGestionOperarios = new JButton("Gestionar Operarios");
		btnGestionOperarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionOperarios.setBounds(41, 11, 170, 80);
		panelMo_1.add(btnGestionOperarios);
		
		JPanel panelMo_2 = new JPanel();
		panelMo_2.setBounds(0, 112, 253, 102);
		panelMo_2.setLayout(null);
		panelGrid.add(panelMo_2);
		
		btnGestionMesas = new JButton("Gestionar Mesas");
		btnGestionMesas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionMesas.setBounds(41, 11, 170, 80);
		panelMo_2.add(btnGestionMesas);
		
		JPanel panelMo_3 = new JPanel();
		panelMo_3.setBounds(263, 112, 253, 102);
		panelMo_3.setLayout(null);
		panelGrid.add(panelMo_3);
		
		btnGestionProductos = new JButton("Gestionar Productos");
		btnGestionProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionProductos.setBounds(41, 11, 170, 80);
		panelMo_3.add(btnGestionProductos);
		
		JPanel panelMo_4 = new JPanel();
		panelMo_4.setBounds(0, 213, 516, 102);
		panelMo_4.setLayout(null);
		panelGrid.add(panelMo_4);
		
		btnGestionPromos = new JButton("Gestionar Promociones");
		btnGestionPromos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGestionPromos.setBounds(169, 11, 190, 80);
		panelMo_4.add(btnGestionPromos);
		
		JPanel panelSur = new JPanel();
		panelSur.setBounds(0, 314, 516, 53);
		panelGral.add(panelSur);
		panelSur.setLayout(null);
		
		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBounds(0, 11, 260, 42);
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSur.add(btnCerrarSesion);
		
		btnApertura = new JButton("Abrir Local");
		btnApertura.setBounds(256, 11, 260, 42);
		btnApertura.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSur.add(btnApertura);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.btnGestionMozos.addActionListener(actionListener);
		this.btnGestionOperarios.addActionListener(actionListener);
		this.btnGestionMesas.addActionListener(actionListener);
		this.btnGestionProductos.addActionListener(actionListener);
		this.btnCerrarSesion.addActionListener(actionListener);
		this.btnGestionPromos.addActionListener(actionListener);
		this.btnApertura.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmInicioOperario.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmInicioOperario.setVisible(false);
	}

	@Override
	public void limpia() {
		// TODO Auto-generated method stub

	}

	@Override
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Resultado exitoso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
