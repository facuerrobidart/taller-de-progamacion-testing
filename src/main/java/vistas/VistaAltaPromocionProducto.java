package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;

public class VistaAltaPromocionProducto extends JFrame implements MouseListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCantMinima;
	private JCheckBox chckbxLunes,chckbxMartes,chckbxMiercoles,chckbxJueves,chckbxViernes,chckbxSabado,chckbxDomingo;
	private JCheckBox chckbx2x1,chckbxDesc,chckbxActiva;
	private JButton btnFinalizar,btnVolver;
	private JTextField textPrecio;
	private ButtonGroup tipoDesc;
	private String nombre,cantMin,precio;
	private int cantMinima = 0;
	private float precioPromo = 0;

	private JComboBox comboBox;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAltaPromocionProducto frame = new VistaAltaPromocionProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaAltaPromocionProducto() {
		setTitle("Promocion por Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de la Promocion:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 26, 189, 38);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNombre.setBounds(220, 26, 221, 38);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		JLabel lblNewLabel_1 = new JLabel("Elija el producto");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 100, 120, 26);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setBounds(220, 94, 221, 38);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Elija el/los dias:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(10, 183, 120, 26);
		contentPane.add(lblNewLabel_2);
		
		chckbxLunes = new JCheckBox("Lunes");
		chckbxLunes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxLunes.setBounds(220, 168, 99, 23);
		contentPane.add(chckbxLunes);
		
		chckbxMartes = new JCheckBox("Martes");
		chckbxMartes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMartes.setBounds(220, 213, 99, 23);
		contentPane.add(chckbxMartes);
		
		chckbxMiercoles = new JCheckBox("Miercoles");
		chckbxMiercoles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMiercoles.setBounds(327, 168, 99, 23);
		contentPane.add(chckbxMiercoles);
		
		chckbxJueves = new JCheckBox("Jueves");
		chckbxJueves.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxJueves.setBounds(327, 213, 99, 23);
		contentPane.add(chckbxJueves);
		
		chckbxViernes = new JCheckBox("Viernes");
		chckbxViernes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxViernes.setBounds(441, 168, 99, 23);
		contentPane.add(chckbxViernes);
		
		chckbxSabado = new JCheckBox("Sabado");
		chckbxSabado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSabado.setBounds(441, 213, 99, 23);
		contentPane.add(chckbxSabado);
		
		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDomingo.setBounds(557, 185, 99, 23);
		contentPane.add(chckbxDomingo);
		
		chckbx2x1 = new JCheckBox("Aplica 2x1");
		chckbx2x1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbx2x1.setBounds(25, 297, 99, 23);
		contentPane.add(chckbx2x1);
		
		chckbxDesc = new JCheckBox("Aplica descuento por Cantidad");
		chckbxDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxDesc.setBounds(25, 328, 239, 23);
		contentPane.add(chckbxDesc);
		
		txtCantMinima = new JTextField();
		txtCantMinima.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCantMinima.setColumns(10);
		txtCantMinima.setBounds(293, 327, 126, 26);
		contentPane.add(txtCantMinima);

		tipoDesc = new ButtonGroup();
		tipoDesc.add(chckbxDesc);
		tipoDesc.add(chckbx2x1);
		chckbxDesc.addMouseListener(this);
		chckbx2x1.addMouseListener(this);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(10, 401, 107, 33);
		contentPane.add(btnVolver);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFinalizar.setBounds(567, 401, 107, 33);
		contentPane.add(btnFinalizar);
		btnFinalizar.setEnabled(false);

		JLabel lblNewLabel_3 = new JLabel("Cantidad Minima:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(292, 303, 120, 14);
		contentPane.add(lblNewLabel_3);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(482, 327, 126, 26);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		textPrecio.addKeyListener(this);

		JLabel lblNewLabel_4 = new JLabel("Precio U. con descuento:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(482, 303, 174, 14);
		contentPane.add(lblNewLabel_4);
		
		chckbxActiva = new JCheckBox("Activar instantaneamente");
		chckbxActiva.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxActiva.setBounds(25, 362, 205, 23);
		contentPane.add(chckbxActiva);
		chckbxActiva.addMouseListener(this);
	}

	public void setActionListener(ActionListener actionListener){
		btnFinalizar.addActionListener(actionListener);
		btnVolver.addActionListener(actionListener);
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
		// TODO Auto-generated method stub
		this.nombre = this.txtNombre.getText();
		this.cantMin = this.txtCantMinima.getText();
		this.precio = this.textPrecio.getText();
		if (chckbx2x1.isSelected()) {
			this.btnFinalizar.setEnabled(
					this.comboBox.getSelectedItem() != null
							&&
							(this.chckbxLunes.isSelected() ||
									this.chckbxMartes.isSelected() ||
									this.chckbxMiercoles.isSelected() ||
									this.chckbxJueves.isSelected() ||
									this.chckbxViernes.isSelected() ||
									this.chckbxSabado.isSelected() ||
									this.chckbxDomingo.isSelected())
							&& this.nombre.length() > 0);
		} else if (chckbxDesc.isSelected()) {
				this.btnFinalizar.setEnabled(
						this.comboBox.getSelectedItem() != null
						&&
						(this.chckbxLunes.isSelected() ||
								this.chckbxMartes.isSelected() ||
								this.chckbxMiercoles.isSelected() ||
								this.chckbxJueves.isSelected() ||
								this.chckbxViernes.isSelected() ||
								this.chckbxSabado.isSelected() ||
								this.chckbxDomingo.isSelected())
						&& this.nombre.length() > 0
								&& this.cantMin.length() > 0
								&& this.precio.length()>0
								);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.nombre = this.txtNombre.getText();
		this.cantMin = this.txtCantMinima.getText();
		this.precio = this.textPrecio.getText();
			if (chckbx2x1.isSelected()) {
				this.btnFinalizar.setEnabled(
						this.comboBox.getSelectedItem() != null
								&&
								(this.chckbxLunes.isSelected() ||
										this.chckbxMartes.isSelected() ||
										this.chckbxMiercoles.isSelected() ||
										this.chckbxJueves.isSelected() ||
										this.chckbxViernes.isSelected() ||
										this.chckbxSabado.isSelected() ||
										this.chckbxDomingo.isSelected())
								&& this.nombre.length() > 0);
			} else if (chckbxDesc.isSelected()) {
				this.btnFinalizar.setEnabled(
						this.comboBox.getSelectedItem() != null
								&&
								(this.chckbxLunes.isSelected() ||
										this.chckbxMartes.isSelected() ||
										this.chckbxMiercoles.isSelected() ||
										this.chckbxJueves.isSelected() ||
										this.chckbxViernes.isSelected() ||
										this.chckbxSabado.isSelected() ||
										this.chckbxDomingo.isSelected())
								&& this.nombre.length() > 0
								&& this.cantMin.length() > 0
								&& this.precio.length()>0);

			}
		}
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void esconder() {
		this.setVisible(false);
		limpiaCampos();
	}
	
	public void limpiaCampos() {
		this.txtNombre.setText("");
		this.txtCantMinima.setText("");
		this.textPrecio.setText("");
		tipoDesc.clearSelection();
		chckbxDesc.setSelected(false);
		chckbxLunes.setSelected(false);
		chckbxMartes.setSelected(false);
		chckbxMiercoles.setSelected(false);
		chckbxJueves.setSelected(false);
		chckbxViernes.setSelected(false);
		chckbxSabado.setSelected(false);
		chckbxDomingo.setSelected(false);
		this.chckbxActiva.setSelected(false);
	}
	
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Resultado exitoso", JOptionPane.INFORMATION_MESSAGE);
	}

	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
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

	public JCheckBox getChckbx2x1() {
		return chckbx2x1;
	}

	public void setChckbx2x1(JCheckBox chckbx2x1) {
		this.chckbx2x1 = chckbx2x1;
	}

	public JCheckBox getChckbxDesc() {
		return chckbxDesc;
	}

	public void setChckbxDesc(JCheckBox chckbxDesc) {
		this.chckbxDesc = chckbxDesc;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public ButtonGroup getTipoDesc() {
		return tipoDesc;
	}

	public void setTipoDesc(ButtonGroup tipoDesc) {
		this.tipoDesc = tipoDesc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantMinima() {

		try{
			this.cantMinima = Integer.parseInt(this.txtCantMinima.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "No ingreso correctamente la cantidad minima de pedido", "Error", JOptionPane.ERROR_MESSAGE);
			this.txtCantMinima.setText("");
			return 0;
		}
		return cantMinima;
	}

	public void setCantMinima(int cantMinima) {
		this.cantMinima = cantMinima;
	}

	public float getPrecioPromo() {

		try{
			this.precioPromo = Float.parseFloat(this.textPrecio.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "No ingreso correctamente el precio unitario en Promocion", "Error", JOptionPane.ERROR_MESSAGE);
			this.textPrecio.setText("");
			return 0;
		}
		return precioPromo;
	}

	public void setPrecioPromo(float precioPromo) {
		this.precioPromo = precioPromo;
	}

	public JCheckBox getChckbxActiva() {
		return chckbxActiva;
	}

	public void setChckbxActiva(JCheckBox chckbxActiva) {
		this.chckbxActiva = chckbxActiva;
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtCantMinima() {
		return txtCantMinima;
	}

	public void setTxtCantMinima(JTextField txtCantMinima) {
		this.txtCantMinima = txtCantMinima;
	}

	public JTextField getTextPrecio() {
		return textPrecio;
	}

	public void setTextPrecio(JTextField textPrecio) {
		this.textPrecio = textPrecio;
	}


}
