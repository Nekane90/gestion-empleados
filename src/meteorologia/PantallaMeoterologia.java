package meteorologia;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaMeoterologia extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PantallaMeoterologia dialog = new PantallaMeoterologia();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PantallaMeoterologia() {
		setBounds(100, 100, 737, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(225, 243, 225));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMeteorologia = new JLabel("METEOROLOGIA");
			lblMeteorologia.setFont(new Font("Tahoma", Font.BOLD, 31));
			lblMeteorologia.setHorizontalAlignment(SwingConstants.CENTER);
			lblMeteorologia.setBounds(194, 27, 338, 55);
			contentPanel.add(lblMeteorologia);
		}
		
		JTextArea taMeteo = new JTextArea();
		taMeteo.setBounds(103, 125, 510, 250);
		contentPanel.add(taMeteo);
		
		JButton btnSalir = new JButton("S A L I R");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(567, 403, 130, 31);
		contentPanel.add(btnSalir);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}
}
