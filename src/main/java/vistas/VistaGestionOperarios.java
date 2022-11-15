package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import modelo.Operario;

public class VistaGestionOperarios extends JFrame implements MouseListener,IVistaGestion{

	private JFrame frmGestionDeOperarios;
	private JButton btnAltaOperario,btnBajaOperario,btnModificarOperario,btnVolver;
	private JList<Operario> listaOperarios;
	private ActionListener actionListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGestionOperarios window = new VistaGestionOperarios();
					window.frmGestionDeOperarios.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaGestionOperarios() {
		initialize();
	}

	private void initialize() {
		frmGestionDeOperarios = new JFrame();
		frmGestionDeOperarios.setTitle("Gestion de Operarios");
		frmGestionDeOperarios.setBounds(100, 100, 450, 300);
		frmGestionDeOperarios.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeOperarios.getContentPane().setLayout(null);
		
		JLabel lblListaOperarios = new JLabel("Listado de operarios");
		lblListaOperarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaOperarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaOperarios.setBounds(72, 21, 130, 14);
		frmGestionDeOperarios.getContentPane().add(lblListaOperarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 46, 221, 142);
		frmGestionDeOperarios.getContentPane().add(scrollPane);
		
		listaOperarios = new JList();
		scrollPane.setViewportView(listaOperarios);
		listaOperarios.addMouseListener(this);
		
		btnAltaOperario = new JButton("Alta Operario");
		btnAltaOperario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAltaOperario.setBounds(276, 46, 141, 32);
		frmGestionDeOperarios.getContentPane().add(btnAltaOperario);
		
		btnBajaOperario = new JButton("Baja Operario");
		btnBajaOperario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBajaOperario.setBounds(276, 101, 141, 32);
		btnBajaOperario.setEnabled(false);
		frmGestionDeOperarios.getContentPane().add(btnBajaOperario);
		
		btnModificarOperario = new JButton("Modificar Operario");
		btnModificarOperario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarOperario.setBounds(276, 156, 141, 32);
		btnModificarOperario.setEnabled(false);
		frmGestionDeOperarios.getContentPane().add(btnModificarOperario);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(26, 211, 89, 32);
		frmGestionDeOperarios.getContentPane().add(btnVolver);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.listaOperarios.getSelectedValue()!=null) {
			this.btnBajaOperario.setEnabled(true);
			this.btnModificarOperario.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		btnAltaOperario.addActionListener(actionListener);
		btnModificarOperario.addActionListener(actionListener);
		btnBajaOperario.addActionListener(actionListener);
		btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmGestionDeOperarios.setVisible(true);

	}

	@Override
	public void esconder() {
		this.frmGestionDeOperarios.setVisible(false);
		this.limpia();
	}

	@Override
	public void limpia() {
		this.btnBajaOperario.setEnabled(false);
		this.btnModificarOperario.setEnabled(false);
	}

	@Override
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Resultado exitoso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void setModel(DefaultListModel<?> lista) {
		this.listaOperarios.setModel((ListModel<Operario>) lista);
	}

	@Override
	public Object getSeleccion() {
		return this.listaOperarios.getSelectedValue();
	}
}
