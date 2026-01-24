package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.Chat;
import meteorologia.PantallaMeoterologia;
import meteorologia.TerrazaInterfaz;
import modeloDto.EmpleadoDto;

public class Principal extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();



	public Principal(EmpleadoDto emp, String ipServer) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imagenes/LOGO.png")));
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Gestion Del Personal");


		setBounds(100, 100, 833, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lbTitulo = new JLabel("GESTION DEL PERSONAL");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(295, 24, 277, 49);
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
		btFichaje.setBounds(565, 161, 198, 80);
		contentPanel.add(btFichaje);

		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(490, 396, 150, 32);
		contentPanel.add(btSalir);


		JLabel lbImagen = new JLabel();
		lbImagen.setBounds(614, 10, 162, 125);
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
		btChat.setBounds(341, 173, 168, 32);
		contentPanel.add(btChat);

		JButton btMete = new JButton("M E T E O R O L O G I A");
		btMete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaMeoterologia vMeteo = new PantallaMeoterologia();
				vMeteo.setVisible(true);
				
			}
		});
		btMete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btMete.setBounds(341, 125, 168, 32);
		contentPanel.add(btMete);
		
		JButton btInformes = new JButton("I N F O R M E S");
		btInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaInformes vInformes = new VentanaInformes();
				vInformes.setVisible(true);
			}
		});
		btInformes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btInformes.setBounds(341, 237, 168, 32);
		contentPanel.add(btInformes);


				if (emp == null) {
					return;
				}

				if ( emp.getIdcategoria() == 1 ) { //camarero
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);
					//btInformes.setEnabled(false);

				}
				else if (emp.getIdcategoria() == 2) {//cocinero
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);
					//btInformes.setEnabled(false);


				}

				else if (emp.getIdcategoria() == 4){ //limpieza
					btAlta.setEnabled(false);
					btBaja.setEnabled(false);
					btConsultar.setEnabled(false);
					//btInformes.setEnabled(false);


				}


	}
}
