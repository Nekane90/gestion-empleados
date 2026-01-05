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

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDni;
	private JTextField tfSalario;
	private JTextField tfDia;
	private JTextField tfMes;
	private JTextField tfAnio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarEmpleados dialog = new EliminarEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarEmpleados() {
		setBounds(100, 100, 648, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("ELIMINAR EMPLEADOS");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(175, 22, 264, 60);
		contentPanel.add(lbTitulo);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(61, 103, 104, 25);
		contentPanel.add(lbNombre);
		
		JLabel lbApellidos = new JLabel("Apellido:");
		lbApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbApellidos.setBounds(61, 153, 104, 25);
		contentPanel.add(lbApellidos);
		
		JLabel lbDni = new JLabel("Dni:");
		lbDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDni.setBounds(61, 193, 104, 25);
		contentPanel.add(lbDni);
		
		JLabel lbSalario = new JLabel("Salario:");
		lbSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSalario.setBounds(61, 239, 104, 25);
		contentPanel.add(lbSalario);
		
		JLabel lbFechaAlta = new JLabel("Fecha de alta:");
		lbFechaAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFechaAlta.setBounds(61, 290, 104, 25);
		contentPanel.add(lbFechaAlta);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(175, 108, 154, 25);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(61, 332, 66, 25);
		contentPanel.add(lblDia);
		
		JLabel lbMes = new JLabel("Mes:");
		lbMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMes.setBounds(183, 332, 66, 25);
		contentPanel.add(lbMes);
		
		JLabel lbAño = new JLabel("Año:");
		lbAño.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAño.setBounds(305, 332, 66, 25);
		contentPanel.add(lbAño);
		
		JButton btEliminar = new JButton("E L I M I N A R");
		btEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btEliminar.setBounds(290, 406, 169, 32);
		contentPanel.add(btEliminar);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(486, 406, 126, 32);
		contentPanel.add(btSalir);
		
		JLabel lbImagen = new JLabel("New label");
		lbImagen.setBounds(414, 2, 204, 107);
		contentPanel.add(lbImagen);
		
		// Cargar la imagen original
				ImageIcon iconOriginal = new ImageIcon("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png");
				// Escalar la imagen al tamaño del JLabel
				Image img = iconOriginal.getImage();
				Image imgEscalada = img.getScaledInstance(lbImagen.getWidth(), lbImagen.getHeight(), Image.SCALE_SMOOTH);
				lbImagen.setIcon(new ImageIcon(imgEscalada));
				
				tfApellido = new JTextField();
				tfApellido.setColumns(10);
				tfApellido.setBounds(175, 153, 154, 25);
				contentPanel.add(tfApellido);
				
				tfDni = new JTextField();
				tfDni.setColumns(10);
				tfDni.setBounds(175, 193, 154, 25);
				contentPanel.add(tfDni);
				
				tfSalario = new JTextField();
				tfSalario.setColumns(10);
				tfSalario.setBounds(175, 244, 154, 25);
				contentPanel.add(tfSalario);
				
				tfDia = new JTextField();
				tfDia.setColumns(10);
				tfDia.setBounds(98, 334, 53, 25);
				contentPanel.add(tfDia);
				
				tfMes = new JTextField();
				tfMes.setColumns(10);
				tfMes.setBounds(221, 334, 53, 25);
				contentPanel.add(tfMes);
				
				tfAnio = new JTextField();
				tfAnio.setColumns(10);
				tfAnio.setBounds(344, 334, 53, 25);
				contentPanel.add(tfAnio);
	}
}
