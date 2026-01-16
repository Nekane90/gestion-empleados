package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloDao.EmpleadoDao;
import modeloDao.FichajeDao;
import modeloDto.EmpleadoDto;
import modeloDto.FichajeDto;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * Se ejecuta al pulsar el botón FICHAR.
 * <p>
 * Registra un fichaje de entrada o salida dependiendo de la opción seleccionada,
 * junto con el tipo de trabajo (presencial o teletrabajo).
 * </p>
 *
 * @param e evento de acción
 */
public class Fichaje extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTexto;
	FichajeDao fichajeDAO = new FichajeDao();
	
	/**
	 * Create the dialog.
	 */
	public Fichaje() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fichaje.class.getResource("/imagenes/LOGO.png")));
		setTitle("Fichaje");
		setBounds(100, 100, 387, 752);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			txtTexto = new JTextField();
			txtTexto.setFont(new Font("Tahoma", Font.PLAIN, 32));
			txtTexto.setBounds(43, 72, 276, 125);
			contentPanel.add(txtTexto);
			txtTexto.setColumns(10);
		}
		
		JButton btnSiete = new JButton("7");
		btnSiete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("7");
			}
		});
		btnSiete.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSiete.setBounds(43, 319, 77, 56);
		contentPanel.add(btnSiete);
		
		JButton btnOcho = new JButton("8");
		btnOcho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("8");
			}
		});
		btnOcho.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnOcho.setBounds(146, 319, 77, 56);
		contentPanel.add(btnOcho);
		
		JButton btnNueve = new JButton("9");
		btnNueve.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("9");
			}
		});
		btnNueve.setBounds(242, 319, 82, 56);
		contentPanel.add(btnNueve);
		
		JButton btnSeis = new JButton("6");
		btnSeis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("6");
			}
		});
		btnSeis.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSeis.setBounds(242, 402, 82, 56);
		contentPanel.add(btnSeis);
		
		JButton btnTres = new JButton("3");
		btnTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("3");
			}
		});
		btnTres.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnTres.setBounds(242, 484, 82, 56);
		contentPanel.add(btnTres);
		
		JButton btnBorrar = new JButton("C");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTexto.setText("");
			}
		});
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnBorrar.setBounds(146, 237, 173, 56);
		contentPanel.add(btnBorrar);
		
		
		
		JButton btnCuatro = new JButton("4");
		btnCuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("4");
			}
		});
		btnCuatro.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnCuatro.setBounds(43, 402, 82, 56);
		contentPanel.add(btnCuatro);
		
		JButton btCinco = new JButton("5");
		btCinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("5");
			}
		});
		btCinco.setFont(new Font("Tahoma", Font.BOLD, 24));
		btCinco.setBounds(146, 402, 77, 56);
		contentPanel.add(btCinco);
		
		JButton btnCero = new JButton("0");
		btnCero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("0");
			}
		});
		btnCero.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnCero.setBounds(43, 240, 82, 56);
		contentPanel.add(btnCero);
		
		JButton btnUno = new JButton("1");
		btnUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("1");			}
		});
		btnUno.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnUno.setBounds(43, 484, 82, 56);
		contentPanel.add(btnUno);
		
		JButton btnDos = new JButton("2");
		btnDos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerNumero("2");
			}
		});
		btnDos.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnDos.setBounds(146, 484, 77, 56);
		contentPanel.add(btnDos);
		
		JButton btnSalir = new JButton("S A L I R");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalir.setBounds(253, 10, 110, 30);
		contentPanel.add(btnSalir);
		
		JRadioButton rbPresencial = new JRadioButton("P R E S E N C I A L");
		rbPresencial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rbPresencial.setBounds(43, 575, 131, 20);
		contentPanel.add(rbPresencial);
		
		JRadioButton rbSemiprecial = new JRadioButton("T E L E T R A B A J O");
		rbSemiprecial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rbSemiprecial.setBounds(187, 575, 143, 20);
		contentPanel.add(rbSemiprecial);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}

		
		
		JRadioButton rbEntrada = new JRadioButton("E N T R A D A");
		rbEntrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rbEntrada.setBounds(43, 620, 131, 20);
		contentPanel.add(rbEntrada);
		
		JRadioButton rbSalida = new JRadioButton("S A L I D A");
		rbSalida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rbSalida.setBounds(188, 620, 131, 20);
		contentPanel.add(rbSalida);
		
		ButtonGroup grupo= new ButtonGroup();
		grupo.add(rbPresencial);
		grupo.add(rbSemiprecial);
		
		
		
		ButtonGroup talde= new ButtonGroup();
		talde.add(rbEntrada);
		talde.add(rbSalida);
		
		JButton btnFichar = new JButton("F I C H A R");
		btnFichar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idEmpleado = Integer.parseInt(txtTexto.getText());

				String tipoTrabajo;
			    if (rbPresencial.isSelected()) {
			        tipoTrabajo = "PRESENCIAL";
			    } else if (rbSemiprecial.isSelected()) {
			        tipoTrabajo = "TELETRABAJO";
			    } else {
			        JOptionPane.showMessageDialog(null, "Selecciona presencial o teletrabajo");
			        return;
			    }
			    Date fecha = Date.valueOf(LocalDate.now()); //para que coja la fecha

			    Time horaSalida = Time.valueOf(LocalTime.now()); //para que coja el sysdate, no se puede poner a null porq lo pone a null en la bd

			    if (rbEntrada.isSelected()) {
			    	Time horaEntrada = Time.valueOf(LocalTime.now()); //SYSDATE
			        horaSalida = Time.valueOf("00:00:00");  // seria como null, pero no admite null porq en la bd esta en NOT NULL
			        FichajeDto fich = new FichajeDto(idEmpleado,fecha,horaEntrada,horaSalida,tipoTrabajo); //mete en la bd el sysdate en entrada y 00:00:00 en salida
			        if(fichajeDAO.insertar(fich)) {
						JOptionPane.showMessageDialog(null,"Fichaje registrado");
					}
					else {
						JOptionPane.showMessageDialog(null,"Fallo al registrar");
					}
			    } else if (rbSalida.isSelected()) {
			    	if (fichajeDAO.actualizarSalida(idEmpleado, fecha, horaSalida)) { // actualiza salida (lo cambia de 00:00:00 a sysdate, sin tocar entrada)
			            JOptionPane.showMessageDialog(null, "Salida registrada");
			        } else {
			            JOptionPane.showMessageDialog(null, "Salida no registrada");
			        }  
			    } else {
			        JOptionPane.showMessageDialog(null, "Selecciona Entrada o Salida");
			        return;
			    }
			    dispose();

			}
		});
		btnFichar.setFont(new Font("Tahoma", Font.BOLD, 23));
		btnFichar.setBounds(96, 664, 179, 38);
		contentPanel.add(btnFichar);
			
	}
	/**
	 * Añade un número al campo de texto del teclado numérico.
	 *
	 * @param num número a añadir
	 */
	private void ponerNumero(String num) {
		txtTexto.setText(txtTexto.getText() + num);
	}
}
