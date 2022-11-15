package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import modelo.Producto;

public class VistaGestionProductos extends JFrame implements IVistaGestion, MouseListener{

	private JFrame frmGestionDeMozos;
	private JButton btnAltaProducto,btnBajaProducto,btnModificarProducto,btnVolver;
	private JList<Producto> listaProductos;
	private ActionListener actionListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGestionProductos window = new VistaGestionProductos();
					window.frmGestionDeMozos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaGestionProductos() {
		initialize();
	}

	private void initialize() {
		frmGestionDeMozos = new JFrame();
		frmGestionDeMozos.setTitle("Gestion de Productos");
		frmGestionDeMozos.setBounds(100, 100, 450, 300);
		frmGestionDeMozos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeMozos.getContentPane().setLayout(null);
		
		JLabel lblListadoProductos = new JLabel("Listado de productos");
		lblListadoProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListadoProductos.setBounds(70, 11, 130, 25);
		frmGestionDeMozos.getContentPane().add(lblListadoProductos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 46, 221, 142);
		frmGestionDeMozos.getContentPane().add(scrollPane);
		
		listaProductos = new JList();
		scrollPane.setViewportView(listaProductos);
		listaProductos.addMouseListener(this);
		
		btnAltaProducto = new JButton("Alta Producto");
		btnAltaProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAltaProducto.setBounds(270, 46, 147, 32);
		frmGestionDeMozos.getContentPane().add(btnAltaProducto);
		
		btnBajaProducto = new JButton("Baja Producto");
		btnBajaProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBajaProducto.setBounds(270, 101, 147, 32);
		btnBajaProducto.setEnabled(false);
		frmGestionDeMozos.getContentPane().add(btnBajaProducto);
		
		btnModificarProducto = new JButton("Modificar Producto");
		btnModificarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarProducto.setBounds(270, 156, 147, 32);
		btnModificarProducto.setEnabled(false);
		frmGestionDeMozos.getContentPane().add(btnModificarProducto);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(26, 211, 89, 32);
		frmGestionDeMozos.getContentPane().add(btnVolver);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		btnAltaProducto.addActionListener(actionListener);
		btnModificarProducto.addActionListener(actionListener);
		btnBajaProducto.addActionListener(actionListener);
		btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmGestionDeMozos.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmGestionDeMozos.setVisible(false);
		this.limpia();
	}

	@Override
	public void limpia() {
		this.btnBajaProducto.setEnabled(false);
		this.btnModificarProducto.setEnabled(false);
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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.listaProductos.getSelectedValue()!=null) {
			this.btnBajaProducto.setEnabled(true);
			this.btnModificarProducto.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(DefaultListModel<?> lista) {
		this.listaProductos.setModel((ListModel<Producto>) lista);
	}

	@Override
	public Object getSeleccion() {
		return this.listaProductos.getSelectedValue();
	}
}
