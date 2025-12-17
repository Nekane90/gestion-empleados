package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfContraseña;

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

	/**
	 * Create the frame.
	 */
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
		lbLogin.setBounds(243, 31, 241, 46);
		contentPane.add(lbLogin);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(272, 103, 181, 24);
		contentPane.add(lbNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(199, 141, 254, 29);
		contentPane.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lbContraseña = new JLabel("Contraseña:");
		lbContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContraseña.setBounds(272, 201, 181, 24);
		contentPane.add(lbContraseña);
		
		tfContraseña = new JTextField();
		tfContraseña.setColumns(10);
		tfContraseña.setBounds(199, 245, 254, 29);
		contentPane.add(tfContraseña);
		
		JButton btAcceder = new JButton("A C C E D E R");
		btAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btAcceder.setBounds(167, 329, 155, 35);
		contentPane.add(btAcceder);
		
		JButton btCancelar = new JButton("C A N C E L A R");
		btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCancelar.setBounds(377, 329, 168, 35);
		contentPane.add(btCancelar);

	}
}
