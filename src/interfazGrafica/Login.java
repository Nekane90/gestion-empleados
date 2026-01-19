package interfazGrafica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


import conexion.Conexion;
import modeloDao.EmpleadoDao;
import modeloDto.EmpleadoDto;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIdEmpleado;
	private final JPanel contentPanel = new JPanel();
	private EmpleadoDao empDao = new EmpleadoDao();
	private JPasswordField tfPassword;
	private JTextField tfIp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagenes/LOGO.png")));
		setFont(new Font("Dialog", Font.BOLD, 14));
		
		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 655, 478);
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
		lbContraseña.setBounds(240, 179, 181, 24);
		contentPane.add(lbContraseña);

		JButton btAcceder = new JButton("A C C E D E R");
		//aqui se pone el color al boton
		btAcceder.setBackground(new Color(105, 201, 183));
		btAcceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				    String password = new String(tfPassword.getPassword()).trim();
				    String ipServer = tfIp.getText().trim();
				    String idTexto = tfIdEmpleado.getText().trim(); // Guardamos el texto primero


				    if(idTexto.isEmpty() || password.isEmpty() || ipServer.isEmpty()) {
				        JOptionPane.showMessageDialog(null, "Para hacer el login tienes que rellenar los campos");
				        return;// Esto detiene el método y no sigue ejecutando lo de abajo
				    }

				    try {
				        int id = Integer.parseInt(idTexto);

				        //ASIGNAR LA IP A LA CLASE CONEXIÓN ANTES DE VALIDAR
				        Conexion.ipServer = ipServer;
				        Conexion miCon = Conexion.getInstancia(ipServer);
				        EmpleadoDto empleado = empDao.validarLogin(id, password);

				        if (empleado != null) {
				            JOptionPane.showMessageDialog(null, "Bienvenid@ " + empleado.getNombreEmple());

				            // Abrimos la ventana principal
				            Principal vPrincipal = new Principal(empleado, ipServer);
				            vPrincipal.setVisible(true);

				            // Cerramos la ventana de login actual (opcional pero recomendado)
				             Login.this.dispose();

				        } else {
				            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de Login", JOptionPane.ERROR_MESSAGE);
				        }

				    } catch (NumberFormatException nfe) {
				        JOptionPane.showMessageDialog(null, "El ID de empleado debe ser un número válido");
				    } catch (Exception e2) {
				        JOptionPane.showMessageDialog(null, "Error de conexión: " + e2.getMessage());
				    } finally {
				        // Limpiamos los campos siempre al final
				        tfIdEmpleado.setText("");
				        tfPassword.setText("");
				    }
				}
		});
		btAcceder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btAcceder.setBounds(114, 380, 155, 35);
		contentPane.add(btAcceder);

		JButton btCancelar = new JButton("C A N C E L A R");
		btCancelar.setBackground(new Color(105, 201, 183));
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btCancelar.setBounds(353, 380, 168, 35);
		contentPane.add(btCancelar);

		JLabel lbImagen = new JLabel("New label");

		lbImagen.setBounds(465, 8, 148, 103);
		contentPane.add(lbImagen);
		// Cargar la imagen original

		try {
		    URL urlLogo = getClass().getResource("/imagenes/LOGO.png");
		    if (urlLogo != null) {
		        ImageIcon iconOriginal = new ImageIcon(urlLogo);

		        int anchoDeseado = 150;
		        int altoDeseado = 120;

		        Image img = iconOriginal.getImage();
		        Image imgEscalada = img.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		        lbImagen.setIcon(new ImageIcon(imgEscalada));
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPassword.setBounds(167, 226, 254, 29);
		contentPane.add(tfPassword);

		JCheckBox cbVer = new JCheckBox("Ver");
		cbVer.addActionListener(new ActionListener() {

			///Aqui se ve la contraseña
			    @Override
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

		JLabel lbIp = new JLabel("IP del servidor:");
		lbIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbIp.setBounds(218, 277, 181, 24);
		contentPane.add(lbIp);

		tfIp = new JTextField();
		tfIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIp.setColumns(10);
		tfIp.setBounds(167, 312, 254, 29);
		contentPane.add(tfIp);

		//this.dispose();




	}
}
