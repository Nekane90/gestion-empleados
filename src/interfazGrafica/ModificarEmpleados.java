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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

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
	private JButton btBuscar;
	EmpleadoDao empDao = new EmpleadoDao();
	EmpleadoDto empDto = new EmpleadoDto();
	CategoriaDao catDao = new CategoriaDao();
	private JTextField tfIdEmpleado;
	
	
	public ModificarEmpleados() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Modificar Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mixha\\Mi unidad\\DAM3\\Desarrollo de Interfaces\\ProyectosEclipse\\CafeteriaGauPasa\\src\\imagenes\\LOGO.png"));
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
		
		/**
		 * Boton MODIFICAR pregunta primero si se quiere modificar, y si es true se hacen las comprobaciones de que no esten vacios los TextField
		 * se valida el dni, y se modifica con el metodo actualizar {@link EmpleadoDao}
		 */
		
		
		JButton btModificar = new JButton("M O D I F I C A R");
		btModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				int confirmacion =JOptionPane.showConfirmDialog(null,
						 "¿Esta seguro de querer modificar a "+tfNombre.getText()+" "+ tfApellido.getText()+" ?",
						 "Confirmar mofidicación",
						 JOptionPane.YES_NO_OPTION);
			
				 ///Aqui hago la comprobacion de si a pulsado si
				 if (confirmacion == JOptionPane.YES_OPTION) {
					 
					 //Comprobacion de apellido o este vacio y sin numeros		
					 
					 String nombre = tfNombre.getText().trim();

					 if (!nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+") || nombre.isEmpty()) {
					     JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras y no estar vacio");
					     return; 
					 }
					 empDto.setNombreEmple(nombre);
					 
					 //Comprobacion de apellido o este vacio y sin numeros
					 
					 String apellido = tfApellido.getText().trim();
					 if (!apellido.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+") || apellido.isEmpty()) {
					     JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras y no estar vacio");
					     return; 
					 }
					 empDto.setApellidoEmple(apellido);
									 
					 
					 //Verificamos que no metan letras en el salario y no sea negativo
					 try {
						 	
						    double salario = Double.parseDouble(tfSalario.getText().trim());
						    if( salario < 0 ) {
						    	JOptionPane.showMessageDialog(null,"No puede ser negativo");    	
						    	return;	
						    }
						    empDto.setSalario(salario);
						} catch (NumberFormatException e1) {
						    JOptionPane.showMessageDialog(null, "Introduce un número válido en el salario");
						    return; // Salimos o cancelamos la acción
						}
					 
					 String dni = tfDni.getText().trim();
						
						//Comprobamos el dni que sea valido
						if (!dniValido(dni)) {
						    JOptionPane.showMessageDialog(null, "DNI inválido");
						    return;
						}
					 
					 empDto.setDni(dni);
					
					 
					 //aqui cojo el id de la categoria del combo box
					 CategoriaDto categoria = (CategoriaDto) cbCategoria.getSelectedItem();
					 int idCategoria = categoria.getIdCategoria(); 
					 empDto.setIdcategoria(idCategoria);
				
					 Date fecha = null;

					 if (!tfDia.getText().isEmpty() &&
					     !tfMes.getText().isEmpty() &&
					     !tfAnio.getText().isEmpty()) {

					     try {
					         int dia = Integer.parseInt(tfDia.getText());
					         int mes = Integer.parseInt(tfMes.getText());
					         int anio = Integer.parseInt(tfAnio.getText());

					         fecha = validarFecha(dia, mes, anio);
					         empDto.setFechaAlta(fecha);
					         

					     } catch (NumberFormatException e1) {
					         JOptionPane.showMessageDialog(null,
					             "Día, mes y año deben ser números");
					         return;

					     } catch (IllegalArgumentException e1) {
					         JOptionPane.showMessageDialog(null, e1.getMessage());
					         return;
					     }
					 }

					 
					 
					 if(empDao.actualizar(empDto)) {
						 JOptionPane.showMessageDialog(null,"Empleado modificado correctamente");
					 }
					 else { JOptionPane.showMessageDialog(null,"No se ha podido modificar el empleado");	 
					 }
				 }else {
						// El usuario pulsó "No" (Cerrar/Cancelar) o cerró la ventana
				            JOptionPane.showMessageDialog(null, "Modificación cancelada.");
				            
				            tfNombre.setText("");
				            tfApellido.setText("");
				            tfDni.setText("");
				            tfSalario.setText("");
				            tfDia.setText("");
				            tfMes.setText("");
				            tfAnio.setText("");
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
				///Cargo el combo box con las categorias
				cargarComboConCategoria();
				
				JLabel lbIdEmpleado = new JLabel("Id empleado:");
				lbIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbIdEmpleado.setBounds(61, 84, 104, 25);
				contentPanel.add(lbIdEmpleado);
				
				tfIdEmpleado = new JTextField();
				tfIdEmpleado.setColumns(10);
				tfIdEmpleado.setBounds(175, 89, 154, 25);
				contentPanel.add(tfIdEmpleado);
				
				btBuscar = new JButton("B U S C A R");
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
	
	/**Segundo constructor que recibe el empleado y rellena los datos con el metodo cargarDatos, bloquea el boton BUSCAR
	 * 
	 * @param emp
	 */
	
	public ModificarEmpleados(EmpleadoDto emp) {
        this();// aqui llamamos al constructor de arriba
        this.empDto = emp;

        cargarDatos();
       
        if(empDto.getIdcategoria() != 3) {
        	btBuscar.setEnabled(false);
        }
        
    }
	
	
	
	///cargar en el combo las categorias

	private void cargarComboConCategoria() {
	    ArrayList<CategoriaDto> listaCat = catDao.listarTodos();
	    for (CategoriaDto cat : listaCat) {
	        cbCategoria.addItem(cat); // guardamos el objeto categoria, para luego en el combo box se vea el nombre, pero metamos el id al insert del empleado
	    }
	}
	    
	    
	/**
	 * Busca un empleado en la base de datos mediante el ID introducido en la interfaz.
	 * El método realiza las siguientes acciones:
	 * Valida que el campo de texto del ID no esté vacío y contenga un formato numérico válido.
	 * Consulta el objeto {@code empDto} a través del DAO correspondiente.
	 * Si el empleado existe, actualiza los campos de texto (Nombre, Apellido, DNI, Salario) 
	 * y desglosa la fecha de alta en día, mes y año.
	 * Sincroniza el ComboBox de categorías con la categoría del empleado encontrado.
	 */
	
	    
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
	  			return;
	  		}
	  		
	  		//aqui hacemos que en comboBox aparezca la categoria del empleado que buscamos
	  		int idCatEmpleado = empDto.getIdcategoria(); 

	  		for (int i = 0; i < cbCategoria.getItemCount(); i++) {
	  		    CategoriaDto c = (CategoriaDto) cbCategoria.getItemAt(i);
	  		    if (c.getIdCategoria() == idCatEmpleado) {
	  		        cbCategoria.setSelectedItem(c); 
	  		        break;
	  		    }
	  		}
	
	  		tfNombre.setText(empDto.getNombreEmple());
	  		tfApellido.setText(empDto.getApellidoEmple());
	  		tfDni.setText(empDto.getDni());
	  		tfSalario.setText(Double.toString(empDto.getSalario())); // asi paso a string el double
	  		
	  		//Convierto la fecha a localDate
	  		Date fecha = empDto.getFechaAlta();

	  		LocalDate localDate = fecha.toLocalDate();

	  		tfDia.setText(String.valueOf(localDate.getDayOfMonth()));
	  		tfMes.setText(String.valueOf(localDate.getMonthValue()));
	  		tfAnio.setText(String.valueOf(localDate.getYear()));

	  		
	 
	  }
	  
	  /**
	   * Valida si un Documento Nacional de Identidad (DNI) es sintáctica y algorítmicamente correcto.
	   * El método realiza las siguientes comprobaciones:
	   * Verifica que la cadena no sea nula y tenga una longitud exacta de 9 caracteres.
	   * Valida que los primeros 8 caracteres sean dígitos numéricos.
	   * Calcula la letra de control correspondiente al número mediante el algoritmo de módulo 23.
	   * Compara la letra calculada con la letra proporcionada (ignorando mayúsculas/minúsculas).
	   *
	   * @param dni La cadena de texto que contiene el DNI
	   * @return {@code true} si el DNI sigue el formato oficial y la letra de control es válida; 
	   */
	  
	  
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
	  /**
	   * Valida una fecha basándose en el día, mes y año proporcionados y la convierte en un objeto SQL Date.
	   * El método aplica las siguientes reglas de validación:
	   * El año debe estar comprendido entre 1950 y el año actual (inclusive).
	   * La combinación de día, mes y año debe formar una fecha real en el calendario 
	   * @param dia  El día del mes (1-31).
	   * @param mes  El número del mes (1-12).
	   * @param anio El año de la fecha (mínimo 1950).
	   * @return Un objeto {@link java.sql.Date} que representa la fecha validada.
	   */
	  
	  
	  ///Metodo para validar la fecha que sea entre 1950 y año actual
	  public static Date validarFecha(int dia, int mes, int anio) {
		    int anioActual = Year.now().getValue();

		    if (anio < 1950 || anio > anioActual) {
		        throw new IllegalArgumentException(
		            "El año debe estar entre 1950 y " + anioActual
		        );
		    }

		    try {
		        LocalDate localDate = LocalDate.of(anio, mes, dia);
		        return Date.valueOf(localDate); 
		    } catch (DateTimeException e) {
		        throw new IllegalArgumentException("La fecha no es válida");
		    }
		}
	  
	  
	  /**
	   * Carga la información del objeto {@code empDto} en los componentes de la interfaz gráfica.
	   * El método realiza las siguientes operaciones:
	   * Vuelca los datos personales (nombre, apellido, DNI y salario) en sus respectivos campos de texto.
	   * Descompone la fecha de alta en día, mes y año para rellenar los campos de fecha.
	   * Sincroniza el selector de categorías (ComboBox) buscando el ID de categoría coincidente.
	   * Control de acceso:Si el empleado no es un gerente (ID de categoría distinto de 3), 
	   * bloquea la edición de campos sensibles. Si es gerente, limpia los campos para permitir una nueva entrada 
	   * y habilita la edición del ID.
	   */
	  
	  
	  
	  //metodo para cargar datos cuando se le da a modificar y no es gerente
	  
	  private void cargarDatos() {
	        if (empDto != null ) {
	            tfNombre.setText(empDto.getNombreEmple());
	            tfApellido.setText(empDto.getApellidoEmple());
	            tfDni.setText(empDto.getDni());
	            tfSalario.setText(String.valueOf(empDto.getSalario())); // convierto en string

	            if (empDto.getFechaAlta() != null) {
	                LocalDate localDate = empDto.getFechaAlta().toLocalDate();
	                tfDia.setText(String.valueOf(localDate.getDayOfMonth()));
	                tfMes.setText(String.valueOf(localDate.getMonthValue()));
	                tfAnio.setText(String.valueOf(localDate.getYear()));
	            }

	            // Seleccionar categoría en el ComboBox
	            for (int i = 0; i < cbCategoria.getItemCount(); i++) {
	                CategoriaDto c = (CategoriaDto) cbCategoria.getItemAt(i);
	                if (c.getIdCategoria() == empDto.getIdcategoria()) {
	                    cbCategoria.setSelectedItem(c);
	                    break;
	                }
	            }
	          
	            // Si el ID de categoría NO es 3 ( gerente)
	            if (empDto.getIdcategoria() != 3) {
	                tfIdEmpleado.setEditable(false);
	                tfSalario.setEditable(false); 
	                cbCategoria.setEnabled(false);
	                tfDia.setEnabled(false);
	                tfMes.setEnabled(false);
	                tfAnio.setEnabled(false);
	                             
	            } else {
	            	//Cuando accede el gerente estan los tf vacios
	            	tfNombre.setText("");
	            	tfApellido.setText("");
	            	tfDni.setText("");
	            	tfDia.setText("");
	            	tfMes.setText("");
	            	tfSalario.setText("");
	            	tfAnio.setText("");
	            	
	            	

	                tfIdEmpleado.setEditable(true);
	                
	            }
	        }
	    }
	  
	  
	  
	  
}

	
	
	
	
	
	
	
	
	

