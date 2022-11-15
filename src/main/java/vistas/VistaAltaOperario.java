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

public class VistaAltaOperario extends JFrame implements IAltaOperario, KeyListener, MouseListener{

	private JFrame frmAltaDeOperario;
	private JTextField txtNombre;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JTextField txtNombreUsuario;
	private JRadioButton rdbtnOperarioNoActivo;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private ButtonGroup grupoEstado;
	
	private String nombre;
	private String username;
	private String password;
	private String estado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAltaOperario window = new VistaAltaOperario();
					window.frmAltaDeOperario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaAltaOperario() {
		initialize();
	}

	private void initialize() {
		frmAltaDeOperario = new JFrame();
		frmAltaDeOperario.setTitle("Nuevo Operario");
		frmAltaDeOperario.setBounds(100, 100, 450, 300);
		frmAltaDeOperario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaDeOperario.getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre completo");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(39, 24, 110, 21);
		frmAltaDeOperario.getContentPane().add(lblNombre);
		
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreUsuario.setBounds(39, 68, 126, 20);
		frmAltaDeOperario.getContentPane().add(lblNombreUsuario);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstado.setBounds(39, 149, 126, 32);
		frmAltaDeOperario.getContentPane().add(lblEstado);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(200, 20, 184, 32);
		frmAltaDeOperario.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAceptar.setBounds(274, 205, 110, 32);
		frmAltaDeOperario.getContentPane().add(btnAceptar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(39, 205, 110, 32);
		frmAltaDeOperario.getContentPane().add(btnVolver);
		
		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setColumns(10);
		txtNombreUsuario.setBounds(200, 64, 184, 32);
		txtNombreUsuario.addKeyListener(this);
		frmAltaDeOperario.getContentPane().add(txtNombreUsuario);
		
		this.grupoEstado = new ButtonGroup();
		
		JRadioButton rdbtnOperarioActivo = new JRadioButton("Activo");
		rdbtnOperarioActivo.setSelected(true);
		rdbtnOperarioActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOperarioActivo.setBounds(201, 154, 72, 23);
		grupoEstado.add(rdbtnOperarioActivo);
		frmAltaDeOperario.getContentPane().add(rdbtnOperarioActivo);
		rdbtnOperarioActivo.addMouseListener(this);
		
		rdbtnOperarioNoActivo = new JRadioButton("No Activo");
		rdbtnOperarioNoActivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOperarioNoActivo.setBounds(299, 154, 85, 23);
		grupoEstado.add(rdbtnOperarioNoActivo);
		frmAltaDeOperario.getContentPane().add(rdbtnOperarioNoActivo);
		rdbtnOperarioNoActivo.addMouseListener(this);
		
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(39, 111, 126, 20);
		frmAltaDeOperario.getContentPane().add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(200, 107, 184, 32);
		txtPassword.addKeyListener(this);
		frmAltaDeOperario.getContentPane().add(txtPassword);
		
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
		// TODO Auto-generated method stub
		if( nombre.length() > 0 &&
				username.length() > 0 &&
				password.length() > 0
				&& this.grupoEstado.getSelection()!=null){
			this.btnAceptar.setEnabled(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.nombre = this.txtNombre.getText();
		this.username = this.txtNombreUsuario.getText();
		this.password = new String(this.txtPassword.getPassword());
		
		if( nombre.length() > 0 && 
			username.length() > 0 && 
			password.length() > 0) {
			
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
		this.frmAltaDeOperario.setVisible(true);
	}

	@Override
	public void esconder() {
		this.frmAltaDeOperario.setVisible(false);
		limpia();
	}

	@Override
	public void limpia() {

		this.btnAceptar.setEnabled(false);
		this.txtNombre.setText("");
		this.txtNombreUsuario.setText("");
		this.txtPassword.setText("");
		this.grupoEstado.clearSelection();

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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEstado() {
		AbstractButton button=null;
		for (Enumeration<AbstractButton> buttons = grupoEstado.getElements(); buttons.hasMoreElements();) {
			button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return button.getText();
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(JTextField txtNombre) {
		this.txtNombre = txtNombre;
	}

	public JTextField getTxtNombreUsuario() {
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(JTextField txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}
}
