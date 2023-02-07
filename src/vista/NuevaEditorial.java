package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Editorial;
import net.miginfocom.swing.MigLayout;

public class NuevaEditorial extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtAnio;
	private Controlador controlador;
	private Editorial editorial;
	private JLabel lblTitulo;

	
	public NuevaEditorial() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		{
			lblTitulo = new JLabel("Insercion de editoriales");
			lblTitulo.setOpaque(true);
			lblTitulo.setForeground(Color.WHITE);
			lblTitulo.setBackground(Color.BLACK);
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
			contentPanel.add(lblTitulo, "cell 0 0 2 1,growx");
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNombre, "cell 0 2,alignx trailing");
		}
		{
			txtNombre = new JTextField();
			contentPanel.add(txtNombre, "cell 1 2,growx");
			txtNombre.setColumns(10);
		}
		{
			JLabel lblAnio = new JLabel("A\u00F1o:");
			lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblAnio, "cell 0 4,alignx right");
		}
		{
			txtAnio = new JTextField();
			contentPanel.add(txtAnio, "cell 1 4,growx");
			txtAnio.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertarEditorial();
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancel.setActionCommand("Cancelar");
				buttonPane.add(btnCancel);
			}
		}
	}

	protected void insertarEditorial() {
		try {
			String nombre = txtNombre.getText();
			int anio = Integer.parseInt(txtAnio.getText());
			
			//Editorial ed = new Editorial(0,nombre,anio); constructor con parámetros
			Editorial ed = new Editorial();
			
			ed.setNombre(nombre);
			ed.setAnio(anio);
			if (this.editorial == null) {
				lblTitulo.setText("Insertar editorial");
				//si la editorial no existe (es nueva), la añade
				controlador.insertarEditorial(ed);}
			else { //sino la actualiza
				lblTitulo.setText("Modificar editorial");
				ed.setCodEditorial(this.editorial.getCodEditorial());
				controlador.actualizarEditorial(ed);
				
			}
		} catch (NumberFormatException e ) { //controlo que en año introduzca un integer
			JOptionPane.showMessageDialog(null, "Introduzca un año correcto");
		}
		
		
	}

	//acceso entre el controlador y las vistas ventana
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setEditorial(Editorial e) {
		editorial = e;
		if (e!=null) {
			txtNombre.setText(editorial.getNombre());
			txtAnio.setText(""+editorial.getAnio());
		} else {
			txtNombre.setText("");
			txtAnio.setText("");
		}
		
	}
	
	

}
