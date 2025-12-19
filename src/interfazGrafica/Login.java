package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfContraseña;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(225, 243, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbLogin = new JLabel("L O G I N");
		lbLogin.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lbLogin.setBounds(218, 29, 241, 46);
		contentPane.add(lbLogin);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(240, 102, 181, 24);
		contentPane.add(lbNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(167, 140, 254, 29);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lbContraseña = new JLabel("Contraseña:");
		lbContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContraseña.setBounds(240, 200, 181, 24);
		contentPane.add(lbContraseña);
		
		tfContraseña = new JTextField();
		tfContraseña.setColumns(10);
		tfContraseña.setBounds(167, 244, 254, 29);
		contentPane.add(tfContraseña);
		
		JButton btAcceder = new JButton("A C C E D E R");
		//aqui se pone el color al boton
		btAcceder.setBackground(new Color(105, 201, 183));
		btAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal vPrincipal = new Principal();
				vPrincipal.setVisible(true);
			}
		});
		btAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btAcceder.setBounds(120, 329, 155, 35);
		contentPane.add(btAcceder);
		
		JButton btCancelar = new JButton("C A N C E L A R");
		btCancelar.setBackground(new Color(105, 201, 183));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCancelar.setBounds(359, 329, 168, 35);
		contentPane.add(btCancelar);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		lblNewLabel.setBounds(437, 2, 176, 114);
		contentPane.add(lblNewLabel);
		// Cargar la imagen original
		ImageIcon iconOriginal = new ImageIcon("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png");
		// Escalar la imagen al tamaño del JLabel
		Image img = iconOriginal.getImage();
		Image imgEscalada = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(imgEscalada));
		
		
		
		
		

	}
}
