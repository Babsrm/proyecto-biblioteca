package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import net.miginfocom.swing.MigLayout;

public class VentanaPpal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;


	public VentanaPpal() {
		
		setTitle("Base de datos de Biblioteca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow][][grow][]", "[][][][][][][]"));
		
		JButton btnMostrarEd = new JButton("Mostar Editoriales");
		btnMostrarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarEditoriales();
			}
		});
		contentPane.add(btnMostrarEd, "cell 1 1,growx");
		
		JButton btnInsertarEditoral = new JButton("Insertar Editorial");
		btnInsertarEditoral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevaEditorial();
			}
		});
		contentPane.add(btnInsertarEditoral, "cell 1 2,growx");
		
		JButton btnMostrarLibro = new JButton("Mostrar Libros");
		btnMostrarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarLibros();
			}
		});
		contentPane.add(btnMostrarLibro, "cell 1 4,growx");
		
		JButton btnInsertarLibro = new JButton("Insertar Libro");
		btnInsertarLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.mostrarNuevoLibro();
			}
		});
		contentPane.add(btnInsertarLibro, "cell 1 5,growx");
	}

	//constructor de la clase controlador. es necesario porque es el acceso entre el controlador y las ventanas vista
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
