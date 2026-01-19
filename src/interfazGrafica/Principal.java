package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.Chat;
import chat.LoginChat;
import modeloDto.EmpleadoDto;

public class Principal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();



	public Principal(EmpleadoDto emp, String ipServer) {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Gestion Del Personal");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginChat.class.getResource("/imagenes/LOGO.png")));

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
			@Override
			public void actionPerformed(ActionEvent e) {
				AltaEmpleados vAlta = new AltaEmpleados();
				vAlta.setVisible(true);
			}
		});
		btAlta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btAlta.setBounds(58, 125, 168, 32);
		contentPanel.add(btAlta);

		JButton btBaja = new JButton("B A J A");
		btBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EliminarEmpleados baja = new EliminarEmpleados();
				baja.setVisible(true);
			}
		});
		btBaja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btBaja.setBounds(58, 186, 168, 32);
		contentPanel.add(btBaja);

		JButton btModificar = new JButton("M O D I F I C A R ");
		btModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModificarEmpleados vModificar = new ModificarEmpleados(emp);
				vModificar.setVisible(true);
			}
		});
		btModificar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btModificar.setBounds(58, 250, 168, 32);
		contentPanel.add(btModificar);

		JButton btConsultar = new JButton("C O N S U L T A R");
		btConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Consulta consul = new Consulta();
				consul.setVisible(true);
			}
		});
		btConsultar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btConsultar.setBounds(58, 316, 168, 32);
		contentPanel.add(btConsultar);

		JButton btFichaje = new JButton("F I C H A J E");
		btFichaje.setBackground(new Color(59,164,147));
		btFichaje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

					Fichaje fichaje = new Fichaje();
					fichaje.setVisible(true);

			}
		});
		btFichaje.setBackground(new Color(59, 164, 147));
		btFichaje.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btFichaje.setBounds(455, 225, 198, 80);
		contentPanel.add(btFichaje);

		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(521, 379, 150, 32);
		contentPanel.add(btSalir);


		JLabel lbImagen = new JLabel();
		lbImagen.setBounds(529, 0, 162, 125);
		contentPanel.add(lbImagen);

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

		JButton btChat = new JButton("C H A T ");
		btChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String nombre = emp.getNombreEmple();
				Chat vChat = new Chat(nombre,ipServer);
				vChat.setVisible(true);
				//LoginChat vChat = new LoginChat( emp,ipServer);
				//vChat.setVisible(true);
			}
		});
		btChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btChat.setBounds(461, 158, 168, 32);
		contentPanel.add(btChat);


				if (emp == null) {
					return;
				}

				if ( emp.getIdcategoria() == 1 ) { //camarero
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);

				}
				else if (emp.getIdcategoria() == 2) {//cocinero
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);


				}

				else if (emp.getIdcategoria() == 4){ //limpieza
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);


				}


	}
}
