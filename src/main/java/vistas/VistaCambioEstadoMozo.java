package vistas;

import controladores.ControladorCambioEstadoMozo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VistaCambioEstadoMozo extends JFrame implements MouseListener {

	private JPanel contentPane;
	private JButton btnAceptar,btnVolver;
	private ButtonGroup grupoEstado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCambioEstadoMozo frame = new VistaCambioEstadoMozo();
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
	public VistaCambioEstadoMozo() {
		setTitle("Cambio Estado de Mozo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxAusente = new JCheckBox("Ausente");
		chckbxAusente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxAusente.setBounds(195, 84, 126, 33);
		contentPane.add(chckbxAusente);
		chckbxAusente.addMouseListener(this);
		
		JCheckBox chckbxDeFranco = new JCheckBox("De Franco");
		chckbxDeFranco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxDeFranco.setBounds(195, 134, 99, 23);
		contentPane.add(chckbxDeFranco);
		chckbxDeFranco.addMouseListener(this);
		
		JCheckBox chckbxActivo = new JCheckBox("Activo");
		chckbxActivo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckbxActivo.setBounds(195, 182, 99, 23);
		contentPane.add(chckbxActivo);
		chckbxActivo.addMouseListener(this);
		
		this.grupoEstado = new ButtonGroup();
		this.grupoEstado.add(chckbxActivo);
		this.grupoEstado.add(chckbxAusente);
		this.grupoEstado.add(chckbxDeFranco);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVolver.setBounds(10, 243, 112, 40);
		contentPane.add(btnVolver);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAceptar.setBounds(392, 243, 112, 40);
		contentPane.add(btnAceptar);
		btnAceptar.setEnabled(false);
		
		JLabel lblNewLabel = new JLabel("Seleccione el nuevo estado del mozo seleccionado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(74, 30, 367, 37);
		contentPane.add(lblNewLabel);
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
		this.btnAceptar.setEnabled(grupoEstado.getSelection()!=null);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mostrar(){
		this.setVisible(true);
	}
	public void esconder(){
		this.setVisible(false);
		this.limpia();
	}
	public void limpia() {
		this.btnAceptar.setEnabled(false);
		this.grupoEstado.clearSelection();
	}
	
	public String getSelection() {
		
		AbstractButton button=null;
		for (Enumeration<AbstractButton> buttons = grupoEstado.getElements(); buttons.hasMoreElements();) {
			button = buttons.nextElement();
			if (button.isSelected()) {
				return button.getText();
			}
		}
		return button.getText();
		
	}
	
	public void success(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Informacion", JOptionPane.INFORMATION_MESSAGE);
	}


	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

    public void setActionListener(ActionListener actionListener) {
		this.btnAceptar.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
    }
}
