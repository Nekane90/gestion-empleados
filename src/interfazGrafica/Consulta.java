package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;


import modeloDao.EmpleadoDao;
import modeloDto.EmpleadoDto;
import java.awt.Color;
import java.awt.Toolkit;




/**
 * Ventana que permite consultar el listado de empleados
 * almacenados en la base de datos.
 * <p>
 * Muestra los empleados en una tabla con información como ID, nombre,
 * apellido, DNI, salario, fecha de alta, categoría y contraseña.
 * </p>
 * 
 * @author Nekane y Maialen
 * @version 1.0
 */
public class Consulta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaEmp;
	private static EmpleadoDao empdao=new EmpleadoDao();
	private ArrayList<EmpleadoDto> empleados;
	
	
	public Consulta() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Consulta.class.getResource("/imagenes/LOGO.png")));
		setFont(new Font("Dialog", Font.BOLD, 14));
		setTitle("Consultar Empleados");
		setBounds(100, 100, 702, 449);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblConsultarEmpleados = new JLabel("CONSULTAR EMPLEADOS");
			lblConsultarEmpleados.setBounds(244, 31, 207, 22);
			lblConsultarEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 18));
			contentPanel.add(lblConsultarEmpleados);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(225, 243, 225));
		scrollPane.setBounds(57, 109, 578, 209);
		contentPanel.add(scrollPane);
		
		tablaEmp = new JTable();
		tablaEmp.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Apellido", "DNI", "Salario", "Fecha Alta", "ID Categoria", "Contrase\u00F1a"
			}
		));
		scrollPane.setViewportView(tablaEmp);
		try{
			cargarEmpleados();
		}catch(Exception e3){
			e3.printStackTrace();
		}
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btSalir.setBounds(550, 353, 85, 32);
		contentPanel.add(btSalir);
		
		JLabel lbImagen = new JLabel("");
		lbImagen.setBounds(527, 10, 119, 89);
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
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
	
	/**
	 * Carga la lista de empleados desde la base de datos y
	 * los muestra en la tabla.
	 * 
	 * @throws SQLException si ocurre un error al acceder a la base de datos
	 */
	private void cargarEmpleados() throws SQLException{
		empleados=empdao.listarTodos();
		
		DefaultTableModel modelo = (DefaultTableModel) tablaEmp.getModel();
	    while (modelo.getRowCount() > 0) modelo.removeRow(0); 
	    int numCols = modelo.getColumnCount();
	    Object[] fila = new Object[numCols]; 

	    for (EmpleadoDto emp : empleados) { 
	        fila[0] = emp.getIdEmpleado(); 
	        fila[1] = emp.getNombreEmple(); 
	        fila[2] = emp.getApellidoEmple(); 
	        fila[3] = emp.getDni(); 
	        fila[4] = emp.getSalario(); 
	        fila[5] = emp.getFechaAlta();
	        fila[6] = emp.getIdcategoria(); 
	        fila[7] = emp.getContrasenia();
	        

	        modelo.addRow(fila);
	    }
	}
}
