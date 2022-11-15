package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

public class VistaAltaPromocionTemporal extends JFrame implements IGenerica, KeyListener, MouseListener{

	private JFrame frmAltaPromocionTemporal;
	private JTextField txtNombrePromocion,txtPorcentajeDesc;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JLabel lblFormaDePago;
	private JCheckBox chckbxEfectivo;
	private JCheckBox chckbxTarjeta;
	private JCheckBox chckbxMercadoPago;
	private JCheckBox chckbxCuentaDNI;
	private JCheckBox chckbxLunes;
	private JCheckBox chckbxMartes;
	private JCheckBox chckbxMiercoles;
	private JCheckBox chckbxJueves;
	private JCheckBox chckbxViernes;
	private JCheckBox chckbxSabado;
	private JCheckBox chckbxDomingo;
	private JCheckBox chckbxPromoActiva;
	private JCheckBox chckbxPromoAcumulable;
	
	
	private String nombre;
	private float porcentajeDesc=0;
	private ButtonGroup formaDePago;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAltaPromocionTemporal window = new VistaAltaPromocionTemporal();
					window.frmAltaPromocionTemporal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaAltaPromocionTemporal() {
		initialize();
	}

	private void initialize() {
		frmAltaPromocionTemporal = new JFrame();
		frmAltaPromocionTemporal.setTitle("Promocion temporal");
		frmAltaPromocionTemporal.setBounds(100, 100, 507, 343);
		frmAltaPromocionTemporal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaPromocionTemporal.getContentPane().setLayout(null);
		
		JLabel lblNombrePromocion = new JLabel("Nombre promocion");
		lblNombrePromocion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombrePromocion.setBounds(23, 15, 126, 21);
		frmAltaPromocionTemporal.getContentPane().add(lblNombrePromocion);
		
		txtNombrePromocion = new JTextField();
		txtNombrePromocion.setBounds(197, 11, 192, 32);
		frmAltaPromocionTemporal.getContentPane().add(txtNombrePromocion);
		txtNombrePromocion.setColumns(10);
		txtNombrePromocion.addKeyListener(this);
		
		btnAceptar = new JButton("Aceptar");

		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(373, 263, 110, 32);
		frmAltaPromocionTemporal.getContentPane().add(btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(10, 263, 110, 32);
		frmAltaPromocionTemporal.getContentPane().add(btnVolver);
		
		lblFormaDePago = new JLabel("Formas de pago");
		lblFormaDePago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePago.setBounds(23, 76, 126, 21);
		frmAltaPromocionTemporal.getContentPane().add(lblFormaDePago);

		chckbxEfectivo = new JCheckBox("Efectivo");
		chckbxEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxEfectivo.setBounds(197, 56, 88, 23);
		chckbxEfectivo.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxEfectivo);

		chckbxTarjeta = new JCheckBox("Tarjeta");
		chckbxTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxTarjeta.setBounds(197, 82, 72, 23);
		chckbxTarjeta.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxTarjeta);
		
		chckbxMercadoPago = new JCheckBox("Mercado Pago");
		chckbxMercadoPago.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMercadoPago.setBounds(298, 56, 133, 23);
		chckbxMercadoPago.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxMercadoPago);
		
		chckbxCuentaDNI = new JCheckBox("Cuenta DNI");
		chckbxCuentaDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCuentaDNI.setBounds(298, 82, 110, 23);
		chckbxCuentaDNI.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxCuentaDNI);
		
		JLabel lblFormaDePago_1 = new JLabel("Dias Promo");
		lblFormaDePago_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePago_1.setBounds(23, 124, 97, 24);
		frmAltaPromocionTemporal.getContentPane().add(lblFormaDePago_1);
		
		formaDePago = new ButtonGroup();
		formaDePago.add(chckbxEfectivo);
		formaDePago.add(chckbxTarjeta);
		formaDePago.add(chckbxMercadoPago);
		formaDePago.add(chckbxCuentaDNI);
		chckbxEfectivo.addMouseListener(this);
		chckbxTarjeta.addMouseListener(this);
		chckbxMercadoPago.addMouseListener(this);
		chckbxCuentaDNI.addMouseListener(this);
		
		
		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxLunes.setBounds(117, 111, 70, 23);
		chckbxLunes.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxLunes);
		
		chckbxJueves = new JCheckBox("Jueves");
		chckbxJueves.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxJueves.setBounds(117, 137, 70, 23);
		chckbxJueves.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxJueves);
		
		chckbxMartes = new JCheckBox("Martes");
		chckbxMartes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMartes.setBounds(197, 111, 83, 23);
		chckbxMartes.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxMartes);
		
		chckbxViernes = new JCheckBox("Viernes");
		chckbxViernes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxViernes.setBounds(197, 135, 72, 23);
		chckbxViernes.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxViernes);
		
		chckbxMiercoles = new JCheckBox("Miercoles");
		chckbxMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMiercoles.setBounds(298, 108, 83, 23);
		chckbxMiercoles.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxMiercoles);
		
		chckbxSabado = new JCheckBox("Sabado");
		chckbxSabado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSabado.setBounds(298, 137, 81, 23);
		chckbxSabado.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxSabado);
		
		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDomingo.setBounds(390, 125, 97, 23);
		chckbxDomingo.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxDomingo);
		
		chckbxPromoActiva = new JCheckBox("");
		chckbxPromoActiva.setBounds(117, 219, 27, 23);
		chckbxPromoActiva.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxPromoActiva);
		
		JLabel lblFormaDePago_1_1 = new JLabel("Activa");
		lblFormaDePago_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePago_1_1.setBounds(56, 221, 45, 21);
		frmAltaPromocionTemporal.getContentPane().add(lblFormaDePago_1_1);
		
		chckbxPromoAcumulable = new JCheckBox("");
		chckbxPromoAcumulable.setBounds(439, 219, 27, 23);
		chckbxPromoAcumulable.addMouseListener(this);
		frmAltaPromocionTemporal.getContentPane().add(chckbxPromoAcumulable);
		
		JLabel lblFormaDePago_1_1_1 = new JLabel("Acumulable");
		lblFormaDePago_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFormaDePago_1_1_1.setBounds(355, 220, 105, 23);
		frmAltaPromocionTemporal.getContentPane().add(lblFormaDePago_1_1_1);
		
		JLabel lblNewLabel = new JLabel("Porcentaje de descuento:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 177, 164, 32);
		frmAltaPromocionTemporal.getContentPane().add(lblNewLabel);
		
		txtPorcentajeDesc = new JTextField();
		txtPorcentajeDesc.setBounds(197, 177, 192, 32);
		frmAltaPromocionTemporal.getContentPane().add(txtPorcentajeDesc);
		txtPorcentajeDesc.addKeyListener(this);
		txtPorcentajeDesc.setColumns(10);
		
		limpia();
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
		this.nombre = this.txtNombrePromocion.getText();
		this.btnAceptar.setEnabled(formaDePago.getSelection()!=null
				&&
				( this.chckbxLunes.isSelected()       ||
						this.chckbxMartes.isSelected()      ||
						this.chckbxMiercoles.isSelected()   ||
						this.chckbxJueves.isSelected()      ||
						this.chckbxViernes.isSelected()     ||
						this.chckbxSabado.isSelected()      ||
						this.chckbxDomingo.isSelected() )
				&&
				this.nombre.length() > 0
				&& this.txtPorcentajeDesc.getText().length() > 0);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.nombre = this.txtNombrePromocion.getText();
		this.btnAceptar.setEnabled(formaDePago.getSelection()!=null
									 &&
								   ( this.chckbxLunes.isSelected()       || 
									 this.chckbxMartes.isSelected()      || 
									 this.chckbxMiercoles.isSelected()   || 
									 this.chckbxJueves.isSelected()      || 
									 this.chckbxViernes.isSelected()     || 
									 this.chckbxSabado.isSelected()      || 
									 this.chckbxDomingo.isSelected() )
									 && 
									 this.nombre.length() > 0 
									 && this.txtPorcentajeDesc.getText().length() > 0);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAceptar.addActionListener(actionListener);	
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.frmAltaPromocionTemporal.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmAltaPromocionTemporal.setVisible(false);
		limpia();
	}

	@Override
	public void limpia() {
		this.txtNombrePromocion.setText("");
		this.txtPorcentajeDesc.setText("");
		this.btnAceptar.setEnabled(false);
		
		this.chckbxEfectivo.setSelected(false);
		this.chckbxTarjeta.setSelected(false);
		this.chckbxMercadoPago.setSelected(false);
		this.chckbxCuentaDNI.setSelected(false);
		
		this.chckbxLunes.setSelected(false);
		this.chckbxMartes.setSelected(false);
		this.chckbxMiercoles.setSelected(false);
		this.chckbxJueves.setSelected(false);
		this.chckbxViernes.setSelected(false);
		this.chckbxSabado.setSelected(false);
		this.chckbxDomingo.setSelected(false);
		
		this.chckbxPromoActiva.setSelected(false);
		this.chckbxPromoAcumulable.setSelected(false);
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

	public JCheckBox getChckbxEfectivo() {
		return chckbxEfectivo;
	}

	public void setChckbxEfectivo(JCheckBox chckbxEfectivo) {
		this.chckbxEfectivo = chckbxEfectivo;
	}

	public JCheckBox getChckbxTarjeta() {
		return chckbxTarjeta;
	}

	public void setChckbxTarjeta(JCheckBox chckbxTarjeta) {
		this.chckbxTarjeta = chckbxTarjeta;
	}

	public JCheckBox getChckbxMercadoPago() {
		return chckbxMercadoPago;
	}

	public void setChckbxMercadoPago(JCheckBox chckbxMercadoPago) {
		this.chckbxMercadoPago = chckbxMercadoPago;
	}

	public JCheckBox getChckbxCuentaDNI() {
		return chckbxCuentaDNI;
	}

	public void setChckbxCuentaDNI(JCheckBox chckbxCuentaDNI) {
		this.chckbxCuentaDNI = chckbxCuentaDNI;
	}

	public JCheckBox getChckbxLunes() {
		return chckbxLunes;
	}

	public void setChckbxLunes(JCheckBox chckbxLunes) {
		this.chckbxLunes = chckbxLunes;
	}

	public JCheckBox getChckbxMartes() {
		return chckbxMartes;
	}

	public void setChckbxMartes(JCheckBox chckbxMartes) {
		this.chckbxMartes = chckbxMartes;
	}

	public JCheckBox getChckbxMiercoles() {
		return chckbxMiercoles;
	}

	public void setChckbxMiercoles(JCheckBox chckbxMiercoles) {
		this.chckbxMiercoles = chckbxMiercoles;
	}

	public JCheckBox getChckbxJueves() {
		return chckbxJueves;
	}

	public void setChckbxJueves(JCheckBox chckbxJueves) {
		this.chckbxJueves = chckbxJueves;
	}

	public JCheckBox getChckbxViernes() {
		return chckbxViernes;
	}

	public void setChckbxViernes(JCheckBox chckbxViernes) {
		this.chckbxViernes = chckbxViernes;
	}

	public JCheckBox getChckbxSabado() {
		return chckbxSabado;
	}

	public void setChckbxSabado(JCheckBox chckbxSabado) {
		this.chckbxSabado = chckbxSabado;
	}

	public JCheckBox getChckbxDomingo() {
		return chckbxDomingo;
	}

	public void setChckbxDomingo(JCheckBox chckbxDomingo) {
		this.chckbxDomingo = chckbxDomingo;
	}

	public JCheckBox getChckbxPromoActiva() {
		return chckbxPromoActiva;
	}

	public void setChckbxPromoActiva(JCheckBox chckbxPromoActiva) {
		this.chckbxPromoActiva = chckbxPromoActiva;
	}

	public JCheckBox getChckbxPromoAcumulable() {
		return chckbxPromoAcumulable;
	}

	public void setChckbxPromoAcumulable(JCheckBox chckbxPromoAcumulable) {
		this.chckbxPromoAcumulable = chckbxPromoAcumulable;
	}
	
	public String getSelection() {
		AbstractButton button=null;
		for (Enumeration<AbstractButton> buttons = formaDePago.getElements(); buttons.hasMoreElements();) {
			button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return button.getText();
	}

	public float getPorcentajeDesc() {
		try {
			porcentajeDesc = Float.parseFloat(this.txtPorcentajeDesc.getText());
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, "Ingreso mal el Porcentaje de descuento", "Error", JOptionPane.ERROR_MESSAGE);
			this.txtPorcentajeDesc.setText("");
			return 0;
		}
		return porcentajeDesc;
	}

	public void setPorcentajeDesc(float porcentajeDesc) {
		this.porcentajeDesc = porcentajeDesc;
	}

	public JTextField getTxtNombrePromocion() {
		return txtNombrePromocion;
	}

	public void setTxtNombrePromocion(JTextField txtNombrePromocion) {
		this.txtNombrePromocion = txtNombrePromocion;
	}

	public JTextField getTxtPorcentajeDesc() {
		return txtPorcentajeDesc;
	}

	public void setTxtPorcentajeDesc(JTextField txtPorcentajeDesc) {
		this.txtPorcentajeDesc = txtPorcentajeDesc;
	}
}
