package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;
import modeloDao.EmpleadoDao;
import modeloDto.EmpleadoDto;
import modeloDto.FichajeDto;
import modeloDao.FichajeDao;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class VentanaInformes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private EmpleadoDto empDto = new EmpleadoDto();
	private EmpleadoDao empDao = new EmpleadoDao();
	private JComboBox cbNombreEmp;
	private FichajeDto fichajeDto = new FichajeDto();
	private FichajeDao fichajeDao = new FichajeDao();
	private JComboBox cbAño;

	public VentanaInformes() {
		setTitle("Informes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInformes.class.getResource("/imagenes/LOGO.png")));
		setBounds(100, 100, 580, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbInformes = new JLabel("IMPRIMIR INFORMES");
		lbInformes.setHorizontalAlignment(SwingConstants.CENTER);
		lbInformes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbInformes.setBounds(131, 35, 277, 39);
		contentPanel.add(lbInformes);
		
		JButton btSalir = new JButton("S A L I R");
		btSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btSalir.setBounds(418, 303, 127, 35);
		contentPanel.add(btSalir);
		
		JButton btEmpleados = new JButton("Todos los empleados");
		btEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        // 1. Silenciador de avisos
			        java.util.logging.Logger.getLogger("net.sf.jasperreports").setLevel(java.util.logging.Level.SEVERE);
			        
			        // 2. Conexión
			        Connection con = Conexion.getInstancia().getCon();

			        // 3. Cargar el diseño (.jrxml)
			        JasperDesign jd = JRXmlLoader.load("informes/InformeEmpleados.jrxml");

			        /// 4. Configurar Parámetros
			        Map<String, Object> parametros = new HashMap<>();

			        try {
			            // Abrimos la imagen como un flujo de datos (InputStream)
			            InputStream logoStream = getClass().getResourceAsStream("/imagenes/LOGO.png");
			            
			            if (logoStream != null) {
			                parametros.put("p_rutaLogo", logoStream);
			            } else {
			                System.out.println("ERROR: No se encontró el archivo LOGO.png en /imagenes/");
			            }
			        } catch (Exception exLogo) {
			            System.out.println("Error al cargar el stream del logo: " + exLogo.getMessage());
			        }

			        // 5. Compilar el reporte
			        JasperReport jr = JasperCompileManager.compileReport(jd);

			        // 6. Llenar el reporte (Pasamos 'jr', los 'parametros' y la conexión 'con')
			        // Nota: Si este informe no usa filtros todavía, 'parametros' solo llevará el logo.
			        JasperPrint jp = JasperFillManager.fillReport(jr, parametros, con);

			        // 7. Mostrar el visor
			        JasperViewer.viewReport(jp, false);

			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al generar informe: " + ex.getMessage());
			    }
			}
		});
		btEmpleados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btEmpleados.setBounds(145, 118, 206, 39);
		contentPanel.add(btEmpleados);
		
		JButton btFichajes = new JButton("Fichaje de empleados");
		btFichajes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btFichajes.setBounds(145, 278, 206, 39);
		contentPanel.add(btFichajes);
		
		cbNombreEmp = new JComboBox();
		cbNombreEmp.setBounds(71, 222, 165, 28);
		contentPanel.add(cbNombreEmp);
		
		JLabel lbElegir = new JLabel("Elige nombre de empleado:");
		lbElegir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbElegir.setBounds(71, 174, 173, 34);
		contentPanel.add(lbElegir);
		
		JLabel lbElegirAnio = new JLabel("Elige el año:");
		lbElegirAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbElegirAnio.setBounds(297, 174, 109, 34);
		contentPanel.add(lbElegirAnio);
		
		cbAño = new JComboBox();
		cbAño.setBounds(292, 222, 94, 28);
		contentPanel.add(cbAño);
		
		cargarComboConEmple();
		cargarComboConAños();
	}
	
	//Metodo cargar comboBox con nombres
	private void cargarComboConEmple() {
	    ArrayList<EmpleadoDto> listEmple = empDao.listarTodos();
	    for (EmpleadoDto emp : listEmple) {
	        cbNombreEmp.addItem(emp); // guardamos el objeto empleado para luego en el combo box se vea el nombre
	    }
	}
	
	//cargar combo con años
	private void cargarComboConAños() {
		cbAño.removeAllItems(); // Limpiamos el combo por si acaso
	    ArrayList<Integer> anios = fichajeDao.listarAniosDisponibles();  
	    for (Integer a : anios) {
	        cbAño.addItem(a);
	    }
	}
}
