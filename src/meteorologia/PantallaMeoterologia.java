package meteorologia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PantallaMeoterologia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea taMeteo;
	private String ipServer;




	/**
	 * Create the dialog.
	 */
	public PantallaMeoterologia(String ipServer) {
		setBounds(100, 100, 737, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMeteorologia = new JLabel("METEOROLOGÍA");
			lblMeteorologia.setFont(new Font("Tahoma", Font.BOLD, 31));
			lblMeteorologia.setHorizontalAlignment(SwingConstants.CENTER);
			lblMeteorologia.setBounds(194, 27, 338, 55);
			contentPanel.add(lblMeteorologia);
		}

		taMeteo = new JTextArea();
		taMeteo.setBounds(99, 172, 514, 203);
		taMeteo.setEditable(false);
		contentPanel.add(taMeteo);

		JButton btnSalir = new JButton("S A L I R");
		btnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(567, 403, 130, 31);
		contentPanel.add(btnSalir);
		
		JLabel lblBienvenida = new JLabel("BIENVENIDO A LA METEOROLOGÍA DE CAFETERÍA GAUPASA");
		lblBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setBounds(143, 79, 421, 42);
		contentPanel.add(lblBienvenida);
		
		JLabel lblTareas = new JLabel("TAREAS DEL CAMARERO:");
		lblTareas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTareas.setBounds(101, 130, 150, 32);
		contentPanel.add(lblTareas);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		cargarInstrucciones();
	}
	
	private void cargarInstrucciones() {// hace de cliente
	    try {// Conexión RMI
	        Registry registry = LocateRegistry.getRegistry(ipServer, 1099);

	        TerrazaInterfaz service =
	            (TerrazaInterfaz) registry.lookup("TerrazaInterfaz");
	     // URL del archivo 
	        String rutaArchivo =
	            "https://drive.google.com/uc?export=download&id=1R-XDcmZhXN1SNKUOtjR3Wc2Df9f-FsMZ";

	        String instrucciones = service.obtenerInstrucciones(rutaArchivo);//manda url
	        taMeteo.setText(instrucciones);// coge instrucciones del servidor

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        taMeteo.setText(
	            "Error al conectar con el servidor:\n\n" + ex.getMessage()
	        );
	    }
	}

}

