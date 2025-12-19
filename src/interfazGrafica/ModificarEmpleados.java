package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDao.CategoriaDao;
import modeloDao.EmpleadoDao;
import modeloDto.CategoriaDto;
import modeloDto.EmpleadoDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarEmpleados extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDni;
	private JTextField tfSalario;
	private JTextField tfDia;
	private JTextField tfMes;
	private JTextField tfAnio;
	private JComboBox cbCategoria;
	EmpleadoDao empDao = new EmpleadoDao();
	EmpleadoDto empDto = new EmpleadoDto();
	CategoriaDao catDao = new CategoriaDao();
	private JTextField tfIdEmpleado;

	public ModificarEmpleados() {
		setBounds(100, 100, 671, 522);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("MODIFICAR EMPLEADOS");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(156, 22, 264, 60);
		contentPanel.add(lbTitulo);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(61, 132, 104, 25);
		contentPanel.add(lbNombre);
		
		JLabel lbApellidos = new JLabel("Apellido:");
		lbApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbApellidos.setBounds(61, 182, 104, 25);
		contentPanel.add(lbApellidos);
		
		JLabel lbDni = new JLabel("Dni:");
		lbDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDni.setBounds(61, 222, 104, 25);
		contentPanel.add(lbDni);
		
		JLabel lbSalario = new JLabel("Salario:");
		lbSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSalario.setBounds(61, 268, 104, 25);
		contentPanel.add(lbSalario);
		
		JLabel lbFechaAlta = new JLabel("Fecha de alta:");
		lbFechaAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFechaAlta.setBounds(61, 319, 104, 25);
		contentPanel.add(lbFechaAlta);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(175, 137, 154, 25);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(61, 361, 66, 25);
		contentPanel.add(lblDia);
		
		JLabel lbMes = new JLabel("Mes:");
		lbMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMes.setBounds(183, 361, 66, 25);
		contentPanel.add(lbMes);
		
		JLabel lbAño = new JLabel("Año:");
		lbAño.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAño.setBounds(305, 361, 66, 25);
		contentPanel.add(lbAño);
		
		JButton btModificar = new JButton("M O D I F I C A R");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirmacion =JOptionPane.showConfirmDialog(null,
						 "¿Esta seguro de querer modificar a "+tfNombre.getText()+" "+ tfApellido.getText()+" ?",
						 "Confirmar mofidicación",
						 JOptionPane.YES_NO_OPTION);
			
				 ///Aqui hago la comprobacion de si a pulsado si
				 if (confirmacion == JOptionPane.YES_OPTION) {
					 empDto.setNombreEmple(tfNombre.getText().trim());
					 empDto.setApellidoEmple(tfApellido.getText().trim());
					 
					 
					 
					 //Verificamos que no metan letras en el salario
					 try {
						    double salario = Double.parseDouble(tfSalario.getText().trim());
						    empDto.setSalario(salario);
						} catch (NumberFormatException e1) {
						    JOptionPane.showMessageDialog(null, "Introduce un número válido en el salario");
						    return; // Salimos o cancelamos la acción
						}
					 empDto.setDni(tfDni.getText().trim());

				
					 Date fecha = null;

					 if (!tfDia.getText().isEmpty() &&
					     !tfMes.getText().isEmpty() &&
					     !tfAnio.getText().isEmpty()) {

					     int año = Integer.parseInt(tfAnio.getText());
					     int mes = Integer.parseInt(tfMes.getText());
					     int dia = Integer.parseInt(tfDia.getText());

					     //Con esto formateo la fecha para que tenga 2 digitos mes/dia y 4 el año
					     String fechaStr = String.format("%04d-%02d-%02d", año, mes, dia);
					     fecha = Date.valueOf(fechaStr);
					 }
					 
					 if(empDao.actualizar(empDto)) {
						 JOptionPane.showMessageDialog(null,"Empleado modificado correctamente");
					 }
					 else { JOptionPane.showMessageDialog(null,"No se ha podido modificar el empleado");	 
					 }
				 }else {
						// El usuario pulsó "No" (Cerrar/Cancelar) o cerró la ventana
				            JOptionPane.showMessageDialog(null, "Modificación cancelada.");
				            
				            tfNombre.setText(" ");
				            tfApellido.setText(" ");
				            tfDni.setText(" ");
				            tfSalario.setText(" ");
				            tfDia.setText(" ");
				            tfMes.setText(" ");
				            tfAnio.setText(" ");
					 }
				
				
				
			}
		});
		btModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btModificar.setBounds(296, 431, 169, 32);
		contentPanel.add(btModificar);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(492, 431, 126, 32);
		contentPanel.add(btSalir);
		
		JLabel lbImagen = new JLabel("New label");
		lbImagen.setBounds(414, 2, 204, 107);
		contentPanel.add(lbImagen);
		
		// Cargar la imagen original
				ImageIcon iconOriginal = new ImageIcon("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png");
				// Escalar la imagen al tamaño del JLabel
				Image img = iconOriginal.getImage();
				Image imgEscalada = img.getScaledInstance(lbImagen.getWidth(), lbImagen.getHeight(), Image.SCALE_SMOOTH);
				lbImagen.setIcon(new ImageIcon(imgEscalada));
				
				tfApellido = new JTextField();
				tfApellido.setColumns(10);
				tfApellido.setBounds(175, 182, 154, 25);
				contentPanel.add(tfApellido);
				
				tfDni = new JTextField();
				tfDni.setColumns(10);
				tfDni.setBounds(175, 222, 154, 25);
				contentPanel.add(tfDni);
				
				tfSalario = new JTextField();
				tfSalario.setColumns(10);
				tfSalario.setBounds(175, 273, 154, 25);
				contentPanel.add(tfSalario);
				
				tfDia = new JTextField();
				tfDia.setColumns(10);
				tfDia.setBounds(98, 363, 53, 25);
				contentPanel.add(tfDia);
				
				tfMes = new JTextField();
				tfMes.setColumns(10);
				tfMes.setBounds(221, 363, 53, 25);
				contentPanel.add(tfMes);
				
				tfAnio = new JTextField();
				tfAnio.setColumns(10);
				tfAnio.setBounds(344, 363, 53, 25);
				contentPanel.add(tfAnio);
				
				JLabel lbCategoria = new JLabel("Cambiar categoria:");
				lbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbCategoria.setBounds(440, 168, 132, 25);
				contentPanel.add(lbCategoria);
				
				cbCategoria = new JComboBox();
				cbCategoria.setBounds(414, 203, 158, 25);
				contentPanel.add(cbCategoria);
				
				JLabel lbIdEmpleado = new JLabel("Id empleado:");
				lbIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbIdEmpleado.setBounds(61, 84, 104, 25);
				contentPanel.add(lbIdEmpleado);
				
				tfIdEmpleado = new JTextField();
				tfIdEmpleado.setColumns(10);
				tfIdEmpleado.setBounds(175, 89, 154, 25);
				contentPanel.add(tfIdEmpleado);
				
				JButton btBuscar = new JButton("B U S C A R");
				btBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buscar();
					}
				});
				btBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btBuscar.setBounds(80, 431, 169, 32);
				contentPanel.add(btBuscar);
				
				
				
				
	}
	////////////////METODOS///////////////////
	
	
	///cargar en el combo las categorias

	private void cargarComboConCategoria() {
	    ArrayList<CategoriaDto> listaCat = catDao.listarTodos();
	    cbCategoria.addItem(null); 
	    for (CategoriaDto cat : listaCat) {
	        cbCategoria.addItem(cat); // guardamos el objeto categoria, para luego en el combo box se vea el nombre, pero metamos el id al insert del empleado
	    }
	}
	    
	    
	    
	  ////metodo buscar
	 
	  private void buscar() {
		  	empDto = null;
		    String textoCodigo = tfIdEmpleado.getText().trim();

		    // Verificamos si está vacío
		    if (textoCodigo.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "No puede estar el código vacío");
		        return; // salimos del método para no seguir ejecutando
		    }	
			int codigo;
			try {
				codigo = Integer.parseInt(tfIdEmpleado.getText());
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(null, "El código debe ser un número válido");
		        return;
		    }
		   
	  		empDto = empDao.buscar(codigo);
	  		//Comprobamos que no sea null el Id
	  		if(empDto == null) {
	  			JOptionPane.showMessageDialog(null, "No existe ese Id de empleado");
	  		}else {
	  		
	  		tfNombre.setText(empDto.getNombreEmple());
	  		tfApellido.setText(empDto.getApellidoEmple());
	  		tfDni.setText(empDto.getDni());
	  		tfSalario.setText(Double.toString(empDto.getSalario())); // asi paso a string el double
	  		
	  		//sacamos la fecha para poder partirla en dia/mes/año
	  		
	  		Date fecha = empDto.getFechaAlta();
	  		tfDia.setText(String.valueOf(fecha.getDay()));
	  		tfMes.setText(String.valueOf(fecha.getMonth()));
	  		tfAnio.setText(String.valueOf(fecha.getYear()));
	  		}
	 
	  }
	  
	  ///Metodo para comprobar el dni
	  public static boolean dniValido(String dni) {
		    if (dni == null || dni.length() != 9) return false;

		    String numeroStr = dni.substring(0, 8);
		    char letra = Character.toUpperCase(dni.charAt(8));

		    // Comprobar que los primeros 8 caracteres sean números
		    if (!numeroStr.matches("\\d{8}")) return false;

		    int numero = Integer.parseInt(numeroStr);
		    char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		    char letraCorrecta = letras[numero % 23];

		    return letra == letraCorrecta;
		}

}

	
	
	
	
	
	
	
	
	

