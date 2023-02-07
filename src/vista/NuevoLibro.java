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
import modelo.Libro;
import net.miginfocom.swing.MigLayout;

public class NuevoLibro extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private Controlador controlador;
	private Libro libro;
	private JLabel lblCabecera;
	private JTextField txtIsbn;
	private JTextField txtCodEd;
	private JTextField txtNumPags;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTextField txtPrecioCD;

	
	public NuevoLibro() {
		setBounds(100, 100, 478, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][]"));
		{
			lblCabecera = new JLabel("Insercion de libros");
			lblCabecera.setOpaque(true);
			lblCabecera.setForeground(Color.WHITE);
			lblCabecera.setBackground(Color.BLACK);
			lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 24));
			contentPanel.add(lblCabecera, "cell 0 0 2 1,growx");
		}
		{
			JLabel lblIsbn = new JLabel("ISBN:");
			lblIsbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblIsbn, "cell 0 1,alignx trailing");
		}
		{
			txtIsbn = new JTextField();
			txtIsbn.setColumns(10);
			contentPanel.add(txtIsbn, "cell 1 1,growx");
		}
		{
			JLabel lblTitulo = new JLabel("Título: ");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblTitulo, "cell 0 2,alignx trailing");
		}
		{
			txtTitulo = new JTextField();
			contentPanel.add(txtTitulo, "cell 1 2,growx");
			txtTitulo.setColumns(10);
		}
		{
			JLabel lblCodEd = new JLabel("Cód. Editorial: ");
			lblCodEd.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblCodEd, "cell 0 3,alignx trailing");
		}
		{
			txtCodEd = new JTextField();
			txtCodEd.setColumns(10);
			contentPanel.add(txtCodEd, "cell 1 3,growx");
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
			JLabel lblNumPags = new JLabel("Num. Pags:");
			lblNumPags.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblNumPags, "cell 0 5,alignx trailing");
		}
		{
			txtNumPags = new JTextField();
			txtNumPags.setColumns(10);
			contentPanel.add(txtNumPags, "cell 1 5,growx");
		}
		{
			JLabel lblPrecio = new JLabel("Precio: ");
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblPrecio, "cell 0 6,alignx trailing");
		}
		{
			txtPrecio = new JTextField();
			txtPrecio.setColumns(10);
			contentPanel.add(txtPrecio, "cell 1 6,growx");
		}
		{
			JLabel lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblCantidad, "cell 0 7,alignx trailing");
		}
		{
			txtCantidad = new JTextField();
			txtCantidad.setColumns(10);
			contentPanel.add(txtCantidad, "cell 1 7,growx");
		}
		{
			JLabel lblPrecioCD = new JLabel("PrecioCD:");
			lblPrecioCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(lblPrecioCD, "cell 0 8,alignx trailing");
		}
		{
			txtPrecioCD = new JTextField();
			txtPrecioCD.setColumns(10);
			contentPanel.add(txtPrecioCD, "cell 1 8,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						insertarLibro();
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

	protected void insertarLibro() {
		try {
			String isbn = txtIsbn.getText();
			String titulo = txtTitulo.getText();
			int codEditorial = Integer.parseInt(txtCodEd.getText());
			int anio = Integer.parseInt(txtAnio.getText());
			int num_pags = Integer.parseInt(txtNumPags.getText());
			float precio = Float.parseFloat(txtPrecio.getText());
			int cantidad = Integer.parseInt(txtCantidad.getText());
			float precioCD=0;
			if (txtPrecioCD.getText() != null && !txtPrecioCD.getText().isBlank()) {
				precioCD = Float.parseFloat(txtPrecioCD.getText());
			}
						
			Libro li = new Libro();
			
			li.setIsbn(isbn);
			li.setTitulo(titulo);
			li.setCodEditorial(codEditorial);
			li.setAnio(anio);
			li.setNum_pags(num_pags);
			li.setPrecio(precio);
			li.setCantidad(cantidad);
			li.setPrecioCD(precioCD);
			
			if (this.libro == null) {
				lblCabecera.setText("Insertar libro nuevo");
				controlador.insertarLibro(li);}
			else { 
				lblCabecera.setText("Modificar libro");
				li.setIsbn(this.libro.getIsbn());
				controlador.actualizarLibro(li);
				
			}
		} catch (NumberFormatException e ) { //controlo que en año introduzca un integer
			JOptionPane.showMessageDialog(null, "Introduzca un año correcto");
		}
	}

	//acceso entre el controlador y las vistas ventana
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void setLibro(Libro li) {
		libro = li;
		if (li!=null) {
		//	isbn, titulo, codEditorial, anio, num_pags, precio, cantidad, precioCD
			txtIsbn.setText(libro.getIsbn());
			txtTitulo.setText(libro.getTitulo());
			txtCodEd.setText(""+libro.getCodEditorial());
			txtAnio.setText(""+libro.getAnio());
			txtNumPags.setText(""+ libro.getNum_pags());
			txtPrecio.setText(""+ libro.getPrecio());
			txtCantidad.setText("" + libro.getCantidad());
			txtPrecioCD.setText("" + libro.getPrecioCD());
			
		} else {
			txtIsbn.setText("");
			txtTitulo.setText("");
			txtCodEd.setText("");
			txtAnio.setText("");
			txtNumPags.setText("");
			txtPrecio.setText("");
			txtCantidad.setText("");
			txtPrecioCD.setText("");
		}
		
	}
	
	

}
