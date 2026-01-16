package interfazGrafica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDao.EmpleadoDao;
import modeloDto.EmpleadoDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Toolkit;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIdEmpleado;
	private final JPanel contentPanel = new JPanel();
	private EmpleadoDao empDao = new EmpleadoDao();
	private JPasswordField tfPassword;

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
	 * Gestiona el evento de pulsación del botón de inicio de sesión.
	 * El flujo de ejecución es el siguiente
	 * Captura el ID del empleado y la contraseña (convirtiendo el {@code char[]} de la password en {@code String}).
	 * Valida que los campos no estén vacíos antes de proceder.
	 * Llama al método {@code validarLogin} del DAO para verificar las credenciales en la base de datos.
	 * Si el login es exitoso, muestra un mensaje de bienvenida y abre la ventana {@link Principal}, 
	 * pasando el objeto {@link EmpleadoDto} para mantener la sesión del usuario.
	 * Si las credenciales son incorrectas o hay un error de formato, muestra un mensaje de error mediante {@link JOptionPane}.
	 * Al finalizar, limpia los campos de texto por seguridad.
	 *
	 * @param e El evento de acción disparado por el componente (botón).
	 */
	
	
	public Login() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png"));
		setTitle("Login");
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
		
		JLabel lbIdEmpleado = new JLabel("ID:");
		lbIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbIdEmpleado.setBounds(261, 103, 181, 24);
		contentPane.add(lbIdEmpleado);
		
		tfIdEmpleado = new JTextField();
		tfIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIdEmpleado.setBounds(167, 140, 254, 29);
		contentPane.add(tfIdEmpleado);
		tfIdEmpleado.setColumns(10);
		
		JLabel lbContraseña = new JLabel("Contraseña:");
		lbContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContraseña.setBounds(240, 200, 181, 24);
		contentPane.add(lbContraseña);
				
		JButton btAcceder = new JButton("A C C E D E R");
		//aqui se pone el color al boton
		btAcceder.setBackground(new Color(105, 201, 183));
		btAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = new String(tfPassword.getPassword()).trim();
				
				if(tfIdEmpleado.getText().isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Para hacer el login tienes que rellenar los campos");
				}
				try {	
				int id = Integer.parseInt(tfIdEmpleado.getText());
			    
			    
				EmpleadoDto empleado = new EmpleadoDto();
				empleado = empDao.validarLogin(id,password);
				
				if (empleado != null) {
			        JOptionPane.showMessageDialog(null, "Bienvenid@ " + empleado.getNombreEmple());
			        Principal vPrincipal = new Principal(empleado);
					vPrincipal.setVisible(true);
				}else {
				    // Si los datos están mal, avisamos y NO abrimos la ventana
				    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de Login", JOptionPane.ERROR_MESSAGE);
				}
				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "Error de login ");
				}
				
				tfIdEmpleado.setText("");
				tfPassword.setText("");
				
			}
		});
		btAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btAcceder.setBounds(120, 329, 155, 35);
		contentPane.add(btAcceder);
		
		JButton btCancelar = new JButton("C A N C E L A R");
		btCancelar.setBackground(new Color(105, 201, 183));
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
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
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPassword.setBounds(167, 247, 254, 29);
		contentPane.add(tfPassword);
		
		JCheckBox cbVer = new JCheckBox("Ver");
		cbVer.addActionListener(new ActionListener() {
			
			///Aqui se ve la contraseña
			    public void actionPerformed(ActionEvent e) {
			        if (cbVer.isSelected()) {
			            // (char) 0 hace que el texto sea visible (quita los puntos)
			            tfPassword.setEchoChar((char) 0); 
			        } else {
			            tfPassword.setEchoChar('•'); 
			        }
			    }
			});
		
		
		cbVer.setBackground(new Color(225, 243, 225));
		cbVer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbVer.setBounds(456, 247, 86, 29);
		contentPane.add(cbVer);
		
		//this.dispose();
		
		
		

	}
}
