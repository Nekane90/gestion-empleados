package chat;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Chat extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfEscribir;
	private JLabel lbNombre;
	private JTextArea taChat;
	
	private Socket socket;
	private PrintWriter salida;
	private BufferedReader entrada;
	private String nombreUsuario;
	private String ipUsuario;
	private DefaultListModel<String> modeloLista;
	private JList<String> listaUsuarios;
	private JLabel lbConectados;
	private JButton btDesconectar;
	private JButton btEnviar;
	
	

	/**
	 * Create the dialog.
	 */
	public Chat() {
		setTitle("Sala de chat");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/imagenes/LOGO.png")));
		setBounds(100, 100, 721, 539);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbImagen = new JLabel("New label");
		lbImagen.setBounds(563, 10, 122, 91);
		contentPanel.add(lbImagen);
		
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
		
		
		taChat = new JTextArea();
		taChat.setEditable(false); //para que no borren el historial
		taChat.setLineWrap(true);   // Para que las frases largas salten de línea
		taChat.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(taChat);
		scrollPane.setBounds(99, 69, 453, 349);
		contentPanel.add(scrollPane);
				
		tfEscribir = new JTextField();
		tfEscribir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfEscribir.setBounds(99, 443, 453, 25);
		contentPanel.add(tfEscribir);
		tfEscribir.setColumns(10);
		
		tfEscribir.addActionListener(e -> {
		    String texto = tfEscribir.getText().trim();
		    if (!texto.isEmpty()) {
		    	salida.println(texto);
		        tfEscribir.setText(""); 
		    }
		});
		
		lbNombre = new JLabel("");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(34, 21, 150, 25);
		contentPanel.add(lbNombre);
		
		//La lista con los nombres de los conectados
		modeloLista = new DefaultListModel<>();
		listaUsuarios = new JList<>(modeloLista);
		listaUsuarios.setFont(new Font("Tahoma", Font.BOLD, 12));
		JScrollPane scrollLista = new JScrollPane(listaUsuarios);
		scrollLista.setBounds(563, 142, 122, 276);
		contentPanel.add(scrollLista);
		
		lbConectados = new JLabel("Conectados");
		lbConectados.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbConectados.setBounds(573, 111, 109, 25);
		contentPanel.add(lbConectados);
		
		btDesconectar = new JButton("DESCONECTAR");
		btDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desconectar();
			}
		});
		btDesconectar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btDesconectar.setBounds(245, 21, 179, 38);
		contentPanel.add(btDesconectar);
		
		btEnviar = new JButton("ENVIAR");
		btEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String texto = tfEscribir.getText().trim();
			    if (!texto.isEmpty()) {
			        //salida.println(nombreUsuario + ": " + texto);
			    	salida.println(texto);
			        tfEscribir.setText(""); 
			    }
				
				
			}
		});
		btEnviar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btEnviar.setBounds(563, 442, 109, 25);
		contentPanel.add(btEnviar);

		

		//para que si cierran con la X tambien se desconecte
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        desconectar();
		    }
		});
		
		
		
		
		
	}
	
	public Chat(String nombre,String ip) {
		this();
		this.nombreUsuario = nombre;
		this.ipUsuario = ip;
		
		if (nombre != null && !nombre.isEmpty()) {
            lbNombre.setText("¡ Hola " +nombre+"!"); 
        } else {
            lbNombre.setText("");
        }
		conectarAlServidor(ip);
		
		
	}
	
	//Metodo para conectarse con el servidor
	
	private void conectarAlServidor(String ip) {
	    try {
	        socket = new Socket(ip, 5000);
	        salida = new PrintWriter(socket.getOutputStream(), true);
	        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	        salida.println(this.nombreUsuario); // Enviamos el nombre al conectar

	        new Thread(() -> { 
	            try {
	                String r;
	                while ((r = entrada.readLine()) != null) {
	                    if (r.startsWith("LISTA_USUARIOS:")) {
	                        modeloLista.clear();
	                        for (String n : r.substring(15).split(",")) modeloLista.addElement(n);
	                    } else {
	                        taChat.append(r + "\n");
	                    }
	                }
	            } catch (Exception e) {}
	        }).start();
	    } catch (Exception e) { 
	    	JOptionPane.showMessageDialog(null,"No se pudo conectar al servidor");
	    }
	}
	
	//Metodo para desconectarse del chat
	
	private void desconectar() {
	    try {
	        //Avisar al chat que te vas
	        if (salida != null) {
	            salida.println(nombreUsuario + " ha abandonado el chat.");
	        }

	        if (entrada != null) entrada.close();
	        if (salida != null) salida.close();
	        if (socket != null) socket.close();
	        
	    } catch (IOException e) {
	        System.out.println("Error al cerrar la conexión.");
	    } finally {
	        this.dispose(); 
	    }
	}
	
}
