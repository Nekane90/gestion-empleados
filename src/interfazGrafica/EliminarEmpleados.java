package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.LoginChat;
import modeloDao.EmpleadoDao;
import modeloDto.CategoriaDto;
import modeloDto.EmpleadoDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

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
	EmpleadoDao emp = new EmpleadoDao();
	EmpleadoDto empleadodto = new EmpleadoDto();
	private JTextField txtID;

	
	
	/**
	 * Ventana gráfica que permite buscar y eliminar empleados de la base de datos.
	 * 
	 * El usuario puede:
	 * - Buscar un empleado por su ID
	 * - Visualizar sus datos
	 * - Confirmar y eliminar el empleado
	 * 
	 * @author Maialen y Nekane
	 * @version 1.0
	 */
	
	/**
	 * Evento del botón Eliminar:
	 * - Solicita confirmación al usuario
	 * - Elimina el empleado por ID
	 * - Limpia los campos de texto
	 */
	
	/**
	 * Evento del botón Buscar:
	 * - Valida el ID introducido
	 * - Busca el empleado en la base de datos
	 * - Muestra sus datos en los campos
	 */

	
		public EliminarEmpleados() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Baja Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginChat.class.getResource("/imagenes/LOGO.png")));
		setBounds(100, 100, 648, 539);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("BAJA EMPLEADOS");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(175, 22, 264, 60);
		contentPanel.add(lbTitulo);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(61, 146, 104, 25);
		contentPanel.add(lbNombre);
		
		JLabel lbApellidos = new JLabel("Apellido:");
		lbApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbApellidos.setBounds(61, 194, 104, 25);
		contentPanel.add(lbApellidos);
		
		JLabel lbDni = new JLabel("Dni:");
		lbDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDni.setBounds(61, 238, 104, 25);
		contentPanel.add(lbDni);
		
		JLabel lbSalario = new JLabel("Salario:");
		lbSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSalario.setBounds(61, 286, 104, 25);
		contentPanel.add(lbSalario);
		
		JLabel lbFechaAlta = new JLabel("Fecha de alta:");
		lbFechaAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFechaAlta.setBounds(61, 329, 104, 25);
		contentPanel.add(lbFechaAlta);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(175, 148, 154, 25);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(61, 364, 66, 25);
		contentPanel.add(lblDia);
		
		JLabel lbMes = new JLabel("Mes:");
		lbMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMes.setBounds(175, 364, 66, 25);
		contentPanel.add(lbMes);
		
		JLabel lbAño = new JLabel("Año:");
		lbAño.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAño.setBounds(300, 364, 66, 25);
		contentPanel.add(lbAño);
		
		JButton btEliminar = new JButton("E L I M I N A R");
		
		btEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog(null,
						 "¿Esta seguro de querer borrar a "+tfNombre.getText()+" "+ tfApellido.getText()+" ?",
						 "Confirmar mofidicación",
						 JOptionPane.YES_NO_OPTION);

				if (opcion == JOptionPane.YES_OPTION) {
				    // AQUÍ se borra
					int idEmpleado = Integer.parseInt(txtID.getText()); //coge id
					boolean borrar = emp.borrar(idEmpleado); //borra de la bd el empleado con ese id
					txtID.setText("");
					tfNombre.setText(" ");
		            tfApellido.setText(" ");
		            tfDni.setText(" ");
		            tfSalario.setText(" ");
		            tfDia.setText(" ");
		            tfMes.setText(" ");
		            tfAnio.setText(" ");
				    if (borrar) {
				    	JOptionPane.showMessageDialog(null,"Empleado eliminado");
				    } else {
				    	JOptionPane.showMessageDialog(null,"No se pudo eliminar al empleado");
				    	
				    }
				}

			}
		});
		btEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btEliminar.setBounds(300, 438, 169, 32);
		contentPanel.add(btEliminar);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(479, 438, 126, 32);
		contentPanel.add(btSalir);
		
		JLabel lbImagen = new JLabel("");
		lbImagen.setBounds(464, 2, 154, 108);
		contentPanel.add(lbImagen);
		
		// Cargar la imagen original
		try {
		    URL urlLogo = getClass().getResource("/imagenes/LOGO.png");
		    if (urlLogo != null) {
		        ImageIcon iconOriginal = new ImageIcon(urlLogo);

		        int anchoDeseado = 140; 
		        int altoDeseado = 120;
		        
		        Image img = iconOriginal.getImage();
		        Image imgEscalada = img.getScaledInstance(anchoDeseado, altoDeseado, Image.SCALE_SMOOTH);
		        lbImagen.setIcon(new ImageIcon(imgEscalada));
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
				
				tfApellido = new JTextField();
				tfApellido.setColumns(10);
				tfApellido.setBounds(175, 196, 154, 25);
				contentPanel.add(tfApellido);
				
				tfDni = new JTextField();
				tfDni.setColumns(10);
				tfDni.setBounds(175, 240, 154, 25);
				contentPanel.add(tfDni);
				
				tfSalario = new JTextField();
				tfSalario.setColumns(10);
				tfSalario.setBounds(175, 286, 154, 25);
				contentPanel.add(tfSalario);
				
				tfDia = new JTextField();
				tfDia.setColumns(10);
				tfDia.setBounds(93, 366, 53, 25);
				contentPanel.add(tfDia);
				
				tfMes = new JTextField();
				tfMes.setColumns(10);
				tfMes.setBounds(214, 366, 53, 25);
				contentPanel.add(tfMes);
				
				tfAnio = new JTextField();
				tfAnio.setColumns(10);
				tfAnio.setBounds(339, 366, 53, 25);
				contentPanel.add(tfAnio);
				
				JButton btBuscar = new JButton("B U S C A R");
				btBuscar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						empleadodto = null;
					    String textoCodigo = txtID.getText().trim();

					    // Verificamos si está vacío
					    if (textoCodigo.isEmpty()) {
					        JOptionPane.showMessageDialog(null, "No puede estar el código vacío");
					        return; // salimos del método para no seguir ejecutando
					    }	
						int codigo;
						try {
							codigo = Integer.parseInt(txtID.getText());
					    } catch (NumberFormatException ex) {
					        JOptionPane.showMessageDialog(null, "El código debe ser un número válido");
					        return;
					    }
					   
				  		empleadodto = emp.buscar(codigo);
				  		

				  		//Comprobamos que no sea null el Id
				  		if(empleadodto == null) {
				  			JOptionPane.showMessageDialog(null, "No existe ese Id de empleado");
				  		}else {
				  		
				  		tfNombre.setText(empleadodto.getNombreEmple());
				  		tfApellido.setText(empleadodto.getApellidoEmple());
				  		tfDni.setText(empleadodto.getDni());
				  		tfSalario.setText(Double.toString(empleadodto.getSalario())); // asi paso a string el double
				  		
				  		//Convierto la fecha a localDate
				  		Date fecha = empleadodto.getFechaAlta();

				  		LocalDate localDate = fecha.toLocalDate();

				  		tfDia.setText(String.valueOf(localDate.getDayOfMonth()));
				  		tfMes.setText(String.valueOf(localDate.getMonthValue()));
				  		tfAnio.setText(String.valueOf(localDate.getYear()));
				  		}
					}
				});
				btBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btBuscar.setBounds(164, 438, 126, 32);
				contentPanel.add(btBuscar);
				
				JLabel lblid = new JLabel("ID Empleado:");
				lblid.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblid.setBounds(61, 103, 104, 25);
				contentPanel.add(lblid);
				
				txtID = new JTextField();
				txtID.setColumns(10);
				txtID.setBounds(175, 105, 154, 25);
				contentPanel.add(txtID);
	}
}
