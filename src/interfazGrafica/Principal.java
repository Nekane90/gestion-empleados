package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	

	public Principal() {
		setBounds(100, 100, 715, 471);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("GESTION DEL PERSONAL");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(208, 34, 277, 49);
		contentPanel.add(lbTitulo);
		
		JButton btAlta = new JButton("A L T A");
		btAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEmpleados vAlta = new AltaEmpleados();
				vAlta.setVisible(true);
			}
		});
		btAlta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btAlta.setBounds(58, 125, 168, 32);
		contentPanel.add(btAlta);
		
		JButton btBaja = new JButton("B A J A");
		btBaja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btBaja.setBounds(58, 186, 168, 32);
		contentPanel.add(btBaja);
		
		JButton btModificar = new JButton("M O D I F I C A R ");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarEmpleados vModificar = new ModificarEmpleados();
				vModificar.setVisible(true);
			}
		});
		btModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btModificar.setBounds(58, 250, 168, 32);
		contentPanel.add(btModificar);
		
		JButton btConsultar = new JButton("C O N S U L T A R");
		btConsultar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btConsultar.setBounds(58, 316, 168, 32);
		contentPanel.add(btConsultar);
		
		JButton btFichaje = new JButton("F I C H A J E");
		btFichaje.setBackground(new Color(59,164,147));
		btFichaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btFichaje.setBackground(new Color(59, 164, 147));
		btFichaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btFichaje.setBounds(425, 189, 198, 80);
		contentPanel.add(btFichaje);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(521, 379, 150, 32);
		contentPanel.add(btSalir);
		
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(475, 0, 216, 148);
		contentPanel.add(lblNewLabel);

		// Cargar la imagen original
		ImageIcon iconOriginal = new ImageIcon("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png");
		// Escalar la imagen al tamaño del JLabel
		Image img = iconOriginal.getImage();
		Image imgEscalada = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(imgEscalada));
		
		
	}
}
