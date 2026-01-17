package chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDto.EmpleadoDto;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

public class LoginChat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIp;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginChat frame = new LoginChat(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 * @param ipServer 
	 */
	public LoginChat(EmpleadoDto emp, String ipServer) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginChat.class.getResource("/imagenes/LOGO.png")));
		setTitle("Bienvenido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 317);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(225, 243, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbImagen = new JLabel("");
		lbImagen.setBounds(302, 10, 135, 90);
		contentPane.add(lbImagen);
		
		
		// Cargar la imagen original
		
		try {
		    URL urlLogo = getClass().getResource("/imagenes/LOGO.png");
		    if (urlLogo != null) {
		        ImageIcon iconOriginal = new ImageIcon(urlLogo);

		        int anchoDeseado = 120; 
		        int altoDeseado = 100;
		        
		        Image img = iconOriginal.getImage();
		        Image imgEscalada = img.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		        lbImagen.setIcon(new ImageIcon(imgEscalada));
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		JButton btAcceso = new JButton("A C C E S O");
		btAcceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = emp.getNombreEmple();
				//String ip = tfIp.getText().trim();
				Chat vChat = new Chat(nombre,ipServer);
				vChat.setVisible(true);
				
			}
		});
		btAcceso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btAcceso.setBounds(153, 207, 146, 34);
		contentPane.add(btAcceso);
		
		JLabel lbIp = new JLabel("Introduce la IP:");
		lbIp.setHorizontalAlignment(SwingConstants.CENTER);
		lbIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbIp.setBounds(138, 72, 174, 28);
		contentPane.add(lbIp);
		
		tfIp = new JTextField();
		tfIp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfIp.setToolTipText("");
		tfIp.setColumns(10);
		tfIp.setBounds(138, 110, 163, 28);
		contentPane.add(tfIp);
		
		//Con esta linea dandole al enter se ejecuta el boton acceso
		this.getRootPane().setDefaultButton(btAcceso);

	}
}
