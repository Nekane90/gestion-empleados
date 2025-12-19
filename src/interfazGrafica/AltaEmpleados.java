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

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

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


	public AltaEmpleados() {
		setBounds(100, 100, 648, 497);
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
			public void actionPerformed(ActionEvent e) {
				
				if( tfNombre.getText().isEmpty() ||tfApellido.getText().isEmpty()|| tfSalario.getText().isEmpty()|| tfDia.getText().isEmpty()
						||tfAnio.getText().isEmpty()|| tfMes.getText().isEmpty() ||tfDni.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Tienes que rellenar todas las casillas");
			        return; // Salimos del método si hay algún campo vacío
				}
				try {
					
					String nombre = tfNombre.getText().trim();
					String apellido = tfApellido.getText().trim();
					double salario = Double.parseDouble(tfSalario.getText().trim());// Convertimos a double
					String dni = tfDni.getText().trim();
					
					//Cogemos la categoria del combo box
					CategoriaDto catSeleccionada = (CategoriaDto) cbCategoria.getSelectedItem();
					if (catSeleccionada == null) {
					    JOptionPane.showMessageDialog(null, "Debes seleccionar una categoría");
					    return;
					}

					int idCategoria = catSeleccionada.getIdCategoria();
					
					Date fecha = recogerFecha();
					if(fecha == null) {
					    JOptionPane.showMessageDialog(null,"La fecha no es válida");
					    return; // No seguimos con el insert
					}

					
					EmpleadoDto emp = new EmpleadoDto(nombre,apellido,dni,salario,fecha,idCategoria);
			
					if(empleadoDao.insertar(emp)) {
						JOptionPane.showMessageDialog(null,"Usuario registrado");
					}
					else {
						JOptionPane.showMessageDialog(null,"Fallo al registrar");
					}
					
				} catch (Exception e2) {
					 JOptionPane.showMessageDialog(null, "Error: " + e2.getMessage());
					
				}
				
			}
		});
		btAlta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btAlta.setBounds(333, 406, 126, 32);
		contentPanel.add(btAlta);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(486, 406, 126, 32);
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
				tfApellido.setBounds(175, 153, 154, 25);
				contentPanel.add(tfApellido);
				
				tfDni = new JTextField();
				tfDni.setColumns(10);
				tfDni.setBounds(175, 193, 154, 25);
				contentPanel.add(tfDni);
				
				tfSalario = new JTextField();
				tfSalario.setColumns(10);
				tfSalario.setBounds(175, 244, 154, 25);
				contentPanel.add(tfSalario);
				
				tfDia = new JTextField();
				tfDia.setColumns(10);
				tfDia.setBounds(98, 334, 53, 25);
				contentPanel.add(tfDia);
				
				tfMes = new JTextField();
				tfMes.setColumns(10);
				tfMes.setBounds(221, 334, 53, 25);
				contentPanel.add(tfMes);
				
				tfAnio = new JTextField();
				tfAnio.setColumns(10);
				tfAnio.setBounds(344, 334, 53, 25);
				contentPanel.add(tfAnio);
				
				cbCategoria = new JComboBox();
				cbCategoria.setBounds(414, 174, 160, 25);
				contentPanel.add(cbCategoria);
				
				JLabel lbCategoria = new JLabel("Seleccionar categoria:");
				lbCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lbCategoria.setBounds(427, 135, 159, 33);
				contentPanel.add(lbCategoria);
				cargarComboConCategoria();
	}
	
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
	        JOptionPane.showMessageDialog(null,"Fecha no válida: " + e.getMessage());
	        return null;
	    }
	}
	
	

	private void cargarComboConCategoria() {
	    ArrayList<CategoriaDto> listaCat = catDao.listarTodos();
	    cbCategoria.addItem(null); 
	    for (CategoriaDto cat : listaCat) {
	        cbCategoria.addItem(cat); // guardamos el objeto categoria, para luego en el combo box se vea el nombre, pero metamos el id al insert del empleado
	    }
	}


}
	
	
	
	

