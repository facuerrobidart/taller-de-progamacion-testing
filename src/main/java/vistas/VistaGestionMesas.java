package vistas;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import modelo.Mesa;
import modelo.Operario;

public class VistaGestionMesas extends JFrame implements IVistaGestion, MouseListener{

	private JFrame frmGestionDeMesas;
	private JButton btnAltaMesa;
	private JButton btnModificarMesa;
	private JButton btnBajaMesa;
	private JButton btnAsignarMozo;
	private JButton btnVolver;
	
	private DefaultListModel<Mesa> mesas;
	private JList<Mesa> listMesas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGestionMesas window = new VistaGestionMesas();
					window.frmGestionDeMesas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VistaGestionMesas() {
		initialize();
	}

	private void initialize() {
		frmGestionDeMesas = new JFrame();
		frmGestionDeMesas.setTitle("Gestion de Mesas");
		frmGestionDeMesas.setBounds(100, 100, 450, 300);
		frmGestionDeMesas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeMesas.getContentPane().setLayout(null);
		
		JLabel lblListadoMesas = new JLabel("Listado de mesas");
		lblListadoMesas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoMesas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListadoMesas.setBounds(83, 21, 111, 14);
		frmGestionDeMesas.getContentPane().add(lblListadoMesas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 46, 221, 142);
		frmGestionDeMesas.getContentPane().add(scrollPane);
		
		listMesas = new JList();
		scrollPane.setViewportView(listMesas);
		listMesas.addMouseListener(this);
		
		btnAltaMesa = new JButton("Alta Mesa");
		btnAltaMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnAltaMesa.setBounds(276, 46, 130, 32);
		frmGestionDeMesas.getContentPane().add(btnAltaMesa);
		
		btnBajaMesa = new JButton("Baja Mesa");
		btnBajaMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBajaMesa.setBounds(276, 101, 130, 32);
		frmGestionDeMesas.getContentPane().add(btnBajaMesa);
		
		btnModificarMesa = new JButton("Modificar Mesa");
		btnModificarMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarMesa.setBounds(276, 156, 130, 32);
		frmGestionDeMesas.getContentPane().add(btnModificarMesa);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnVolver.setBounds(26, 211, 89, 32);
		frmGestionDeMesas.getContentPane().add(btnVolver);
		
		btnAsignarMozo = new JButton("Asignar Mozo");
		btnAsignarMozo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAsignarMozo.setBounds(276, 211, 130, 30);
		frmGestionDeMesas.getContentPane().add(btnAsignarMozo);
		
		limpia();
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAltaMesa.addActionListener(actionListener);
		this.btnAsignarMozo.addActionListener(actionListener);
		this.btnBajaMesa.addActionListener(actionListener);
		this.btnModificarMesa.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmGestionDeMesas.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmGestionDeMesas.setVisible(false);
		this.limpia();
	}
	
	public void setModel(DefaultListModel<?> model) {
		this.listMesas.setModel((ListModel<Mesa>) model);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if( this.listMesas.getSelectedValue() != null ) {
			this.btnBajaMesa.setEnabled(true);
			this.btnModificarMesa.setEnabled(true);
			this.btnAsignarMozo.setEnabled(true);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void limpia() {
		this.btnBajaMesa.setEnabled(false);
		this.btnModificarMesa.setEnabled(false);
		this.btnAsignarMozo.setEnabled(false);
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
	public Object getSeleccion() {
		return this.listMesas.getSelectedValue();
	}
}
