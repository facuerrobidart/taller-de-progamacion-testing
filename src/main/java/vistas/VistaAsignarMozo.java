package vistas;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.Font;

public class VistaAsignarMozo extends JFrame implements IGenerica, ItemListener {

	private JPanel contentPane;
	JComboBox comboBox;
	JButton btnAsignar;
	private JButton btnVolver;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAsignarMozo frame = new VistaAsignarMozo();
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
	public VistaAsignarMozo() {
		setTitle("Asignar Mozo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(10, 41, 171, 40);
		contentPane.add(comboBox);
		comboBox.addItemListener(this);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAsignar.setBounds(256, 40, 171, 40);
		contentPane.add(btnAsignar);
		btnAsignar.setEnabled(false);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnVolver.setBounds(10, 121, 112, 40);
		contentPane.add(btnVolver);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnAsignar.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
	}

	@Override
	public void mostrar() {
		this.setVisible(true);
	}

	@Override
	public void esconder() {
		this.setVisible(false);
	}

	@Override
	public void limpia() {
		this.btnAsignar.setEnabled(false);
		this.comboBox.setSelectedItem(null);
	}

	@Override
	public void success(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Resultado exitoso", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void failure(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			this.btnAsignar.setEnabled( true );
		}
	}
}
