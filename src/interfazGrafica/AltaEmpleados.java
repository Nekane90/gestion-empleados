package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modeloDao.CategoriaDao;
import modeloDao.EmpleadoDao;
import modeloDto.CategoriaDto;
import modeloDto.EmpleadoDto;


public class AltaEmpleados extends JDialog {

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
	EmpleadoDao empleadoDao = new EmpleadoDao();
	CategoriaDao catDao = new CategoriaDao();
	private JPasswordField tfPassword;

	/**
	 * El boton ALTA da de alta al empleado en la base de datos, comprobando que no esten vacios los TextField, la contraseña tiene que tener 6 caracteres,
	 * se comprueba que los campos nombre/apellido no se metan números, se valida el dni, y se encripta la contraseña, con estos datos se crea un empleado
	 * y se utiliza el metodo insert {@link EmpleadoDao}
	 */


	public AltaEmpleados() {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setForeground(new Color(0, 0, 0));
		setTitle("Alta Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AltaEmpleados.class.getResource("/imagenes/LOGO.png")));
		setBounds(100, 100, 673, 493);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lbTitulo = new JLabel("ALTA EMPLEADOS");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbTitulo.setBounds(239, 22, 264, 60);
		contentPanel.add(lbTitulo);

		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNombre.setBounds(61, 103, 104, 25);
		contentPanel.add(lbNombre);

		JLabel lbApellidos = new JLabel("Apellido:");
		lbApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbApellidos.setBounds(61, 153, 104, 25);
		contentPanel.add(lbApellidos);

		JLabel lbDni = new JLabel("Dni:");
		lbDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDni.setBounds(61, 193, 104, 25);
		contentPanel.add(lbDni);

		JLabel lbSalario = new JLabel("Salario:");
		lbSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSalario.setBounds(61, 239, 104, 25);
		contentPanel.add(lbSalario);

		JLabel lbFechaAlta = new JLabel("Fecha de alta:");
		lbFechaAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbFechaAlta.setBounds(61, 290, 104, 25);
		contentPanel.add(lbFechaAlta);

		tfNombre = new JTextField();
		tfNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNombre.setBounds(175, 108, 154, 25);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);

		JLabel lblDia = new JLabel("Dia:");
		lblDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDia.setBounds(61, 332, 66, 25);
		contentPanel.add(lblDia);

		JLabel lbMes = new JLabel("Mes:");
		lbMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMes.setBounds(183, 332, 66, 25);
		contentPanel.add(lbMes);

		JLabel lbAño = new JLabel("Año:");
		lbAño.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbAño.setBounds(305, 332, 66, 25);
		contentPanel.add(lbAño);




		JButton btAlta = new JButton("A L T A");
		btAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String password = new String(tfPassword.getPassword()).trim();

				if( tfNombre.getText().isEmpty() ||tfApellido.getText().isEmpty()|| tfSalario.getText().isEmpty()|| tfDia.getText().isEmpty()
						||tfAnio.getText().isEmpty()|| tfMes.getText().isEmpty() ||tfDni.getText().isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null,"Tienes que rellenar todas las casillas");
			        return; // Salimos del método si hay algún campo vacío
				}
				try {

					String nombre = tfNombre.getText().trim();
					String apellido = tfApellido.getText().trim();

					//Comprobamos que la contraseña sea de 6 caracteres
					if (password.length() < 6) {
		                JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 6 caracteres");
		                return;
		            }

					// Validación de letras en nombre y apellido

				    if (!nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
				        JOptionPane.showMessageDialog(null, "El nombre solo puede contener letras");
				        return;
				    }
				    if (!apellido.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
				        JOptionPane.showMessageDialog(null, "El apellido solo puede contener letras");
				        return;
				    }

					double salario = Double.parseDouble(tfSalario.getText().trim());// Convertimos a double
					if( salario < 0 ) {
						JOptionPane.showMessageDialog(null,"El salario no puede ser negativo");
						return;	}

					String dni = tfDni.getText().trim();

					//Comprobamos el dni que sea valido
					if (!dniValido(dni)) {
					    JOptionPane.showMessageDialog(null, "DNI inválido");
					    return;
					}

					//Cogemos la categoria del combo box
					CategoriaDto catSeleccionada = (CategoriaDto) cbCategoria.getSelectedItem();
					if (catSeleccionada == null) {
					    JOptionPane.showMessageDialog(null, "Debes seleccionar una categoría");
					    return;
					}

					int idCategoria = catSeleccionada.getIdCategoria();

					Date fecha = recogerFecha();
					if(fecha == null) {
					    return; // No seguimos con el insert
					}

					String passwordEncriptada = encriptarLocal(password);


					EmpleadoDto emp = new EmpleadoDto(nombre,apellido,dni,salario,fecha,idCategoria,passwordEncriptada);

					if(empleadoDao.insertar(emp)) {
						JOptionPane.showMessageDialog(null,"Usuario registrado");
					}
					else {
						JOptionPane.showMessageDialog(null,"Fallo al registrar");
					}

				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "Error, no estan los datos bien rellenados " + e2.getMessage());

				}

				tfNombre.setText("");
				tfApellido.setText("");
				tfSalario.setText("");
				tfDia.setText("");
				tfDni.setText("");
				tfMes.setText("");
				tfAnio.setText("");
				tfPassword.setText("");


			}
		});
		btAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btAlta.setBounds(333, 406, 126, 32);
		contentPanel.add(btAlta);

		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(486, 406, 126, 32);
		contentPanel.add(btSalir);

		JLabel lbImagen = new JLabel("");
		lbImagen.setBounds(499, 0, 135, 119);
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
				tfApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfApellido.setColumns(10);
				tfApellido.setBounds(175, 153, 154, 25);
				contentPanel.add(tfApellido);

				tfDni = new JTextField();
				tfDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfDni.setColumns(10);
				tfDni.setBounds(175, 193, 154, 25);
				contentPanel.add(tfDni);

				tfSalario = new JTextField();
				tfSalario.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfSalario.setColumns(10);
				tfSalario.setBounds(175, 244, 154, 25);
				contentPanel.add(tfSalario);

				tfDia = new JTextField();
				tfDia.setColumns(10);
				tfDia.setBounds(98, 334, 53, 25);
				contentPanel.add(tfDia);

				tfMes = new JTextField();
				tfMes.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfMes.setColumns(10);
				tfMes.setBounds(221, 334, 53, 25);
				contentPanel.add(tfMes);

				tfAnio = new JTextField();
				tfAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfAnio.setColumns(10);
				tfAnio.setBounds(344, 334, 53, 25);
				contentPanel.add(tfAnio);

				cbCategoria = new JComboBox();
				cbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbCategoria.setBounds(414, 174, 160, 25);
				contentPanel.add(cbCategoria);

				JLabel lbCategoria = new JLabel("Seleccionar categoria:");
				lbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbCategoria.setBounds(427, 135, 159, 33);
				contentPanel.add(lbCategoria);

				JLabel lbContraseña = new JLabel("Contraseña:");
				lbContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbContraseña.setBounds(424, 216, 159, 33);
				contentPanel.add(lbContraseña);

				tfPassword = new JPasswordField();
				tfPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
				tfPassword.setBounds(414, 259, 160, 25);
				contentPanel.add(tfPassword);

				JCheckBox cbVer = new JCheckBox("Ver");
				cbVer.addActionListener(new ActionListener() {

				///Aqui se ve la contraseña
				    @Override
					public void actionPerformed(ActionEvent e) {
				        if (cbVer.isSelected()) {
				            tfPassword.setEchoChar((char) 0);
				        } else {
				            tfPassword.setEchoChar('•');
				        }
				    }
				});
				cbVer.setBackground(new Color(225, 243, 225));
				cbVer.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbVer.setBounds(580, 261, 134, 25);
				contentPanel.add(cbVer);
				cargarComboConCategoria();
	}

	/**
	 * Recogemos la fecha de los TextField por separado dia/mes/año y los pasamos con LocalDate a una fecha para insertar en la base de datos
	 * @return LocalDate fecha
	 */

	public Date recogerFecha() {
	    try {
	        // Leemos y limpiamos los campos
	        String diaStr = tfDia.getText().trim();
	        String mesStr = tfMes.getText().trim();
	        String anioStr = tfAnio.getText().trim();

	        // Validamos que no estén vacíos
	        if(diaStr.isEmpty() || mesStr.isEmpty() || anioStr.isEmpty()) {
	            JOptionPane.showMessageDialog(null,"Debes introducir una fecha completa");
	            return null;
	        }

	        // Convertimos a enteros
	        int dia = Integer.parseInt(diaStr);
	        int mes = Integer.parseInt(mesStr);
	        int anio = Integer.parseInt(anioStr);

	        // Creamos LocalDate y convertimos a java.sql.Date
	        LocalDate localDate = LocalDate.of(anio, mes, dia);
	        return Date.valueOf(localDate);

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null,"La fecha debe ser numérica");
	        return null;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null,"Fecha no válida, ejemplo : 10/10/2025 " );
	        return null;
	    }
	}
	/**
	 * Cargamos en el combo box las categorias
	 */

	private void cargarComboConCategoria() {
	    ArrayList<CategoriaDto> listaCat = catDao.listarTodos();
	    for (CategoriaDto cat : listaCat) {
	        cbCategoria.addItem(cat); // guardamos el objeto categoria, para luego en el combo box se vea el nombre, pero metamos el id al insert del empleado
	    }
	}

	/**
	 * Comprobamos la validez del dni
	 * @param dni
	 * @return {@code true}  si el dni es valido
	 * {@code false} si el dni no es valido
	 *
	 */

	////Comprobar validez del dni

	public static boolean dniValido(String dni) {
	    if (dni == null || dni.length() != 9) {
			return false;
		}

	    String numeroStr = dni.substring(0, 8);
	    char letra = Character.toUpperCase(dni.charAt(8));

	    // Comprobar que los primeros 8 caracteres sean números
	    if (!numeroStr.matches("\\d{8}")) {
			return false;
		}

	    int numero = Integer.parseInt(numeroStr);
	    char[] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	    char letraCorrecta = letras[numero % 23];

	    return letra == letraCorrecta;
	}

	/**
	 * Encripta una cadena de texto utilizando el algoritmo hash SHA-256.
	 * El proceso convierte la contraseña en un array de bytes usando codificación UTF-8,
	 * genera el hash y finalmente lo devuelve representado en una cadena Base64
	 * para facilitar su almacenamiento o transporte.
	 *
	 * @param password La cadena de texto  que se desea encriptar.
	 * @return Una cadena de texto (String) que representa el hash en formato Base64.
	 */

	///Metodo para encriptar la contraseña
	public String encriptarLocal(String password) {
	    try {

	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(password.getBytes("UTF-8"));

	        return Base64.getEncoder().encodeToString(hash);
	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }
	}
}





