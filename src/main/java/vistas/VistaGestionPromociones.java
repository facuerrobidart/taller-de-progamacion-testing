package vistas;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import modelo.promociones.Promocion;

public class VistaGestionPromociones extends JFrame implements IVistaGestion, MouseListener{

	private JFrame frmGestionDePromociones;
	private JButton btnAltaPromoTemporal,btnBajaPromo, btnAltaPromoProducto, btnModificarPromocion,btnVolver;
	private JList<Promocion> listaPromos;
	private ActionListener actionListener;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGestionPromociones window = new VistaGestionPromociones();
					window.frmGestionDePromociones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaGestionPromociones() {
		initialize();
	}

	private void initialize() {
		frmGestionDePromociones = new JFrame();
		frmGestionDePromociones.setTitle("Gestion de promociones");
		frmGestionDePromociones.setBounds(100, 100, 502, 326);
		frmGestionDePromociones.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDePromociones.getContentPane().setLayout(null);
		
		JLabel lblListaPromos = new JLabel("Listado de promociones");
		lblListaPromos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPromos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListaPromos.setBounds(65, 11, 152, 24);
		frmGestionDePromociones.getContentPane().add(lblListaPromos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 46, 221, 142);
		frmGestionDePromociones.getContentPane().add(scrollPane);
		
		listaPromos = new JList();
		scrollPane.setViewportView(listaPromos);
		listaPromos.addMouseListener(this);
		
		btnAltaPromoTemporal = new JButton("Alta Promocion Temporal");
		btnAltaPromoTemporal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAltaPromoTemporal.addActionListener(actionListener);
		btnAltaPromoTemporal.setBounds(287, 183, 191, 42);
		frmGestionDePromociones.getContentPane().add(btnAltaPromoTemporal);
		
		btnBajaPromo = new JButton("Baja Promocion");
		btnBajaPromo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBajaPromo.setBounds(306, 117, 172, 42);
		frmGestionDePromociones.getContentPane().add(btnBajaPromo);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(26, 236, 100, 42);
		frmGestionDePromociones.getContentPane().add(btnVolver);
		
		btnModificarPromocion = new JButton("Modificar Promocion");
		btnModificarPromocion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarPromocion.setBounds(306, 65, 172, 42);
		frmGestionDePromociones.getContentPane().add(btnModificarPromocion);
		
		btnAltaPromoProducto = new JButton("Alta promocion por producto");
		btnAltaPromoProducto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAltaPromoProducto.setBounds(237, 236, 241, 42);
		frmGestionDePromociones.getContentPane().add(btnAltaPromoProducto);

		btnBajaPromo.setEnabled(false);
		btnModificarPromocion.setEnabled(false);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		btnAltaPromoTemporal.addActionListener(actionListener);
		btnBajaPromo.addActionListener(actionListener);
		btnModificarPromocion.addActionListener(actionListener);
		btnVolver.addActionListener(actionListener);
		btnAltaPromoProducto.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmGestionDePromociones.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmGestionDePromociones.setVisible(false);
		this.limpia();
	}

	@Override
	public void limpia() {
		this.btnBajaPromo.setEnabled(false);
		this.btnModificarPromocion.setEnabled(false);
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
		if(this.listaPromos.getSelectedValue()!=null){
			this.btnBajaPromo.setEnabled(true);
			this.btnModificarPromocion.setEnabled(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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
		this.listaPromos.setModel((ListModel<Promocion>) lista);
	}

	@Override
	public Object getSeleccion() {
		return this.listaPromos.getSelectedValue();
	}
}
