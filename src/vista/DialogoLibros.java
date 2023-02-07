package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Editorial;
import modelo.Libro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogoLibros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Controlador controlador;

	public DialogoLibros() {
		setBounds(100, 100, 602, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][grow][]"));
		{
			JLabel lblCabecera = new JLabel("Listado de libros:");
			lblCabecera.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblCabecera, "cell 0 0");
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, "cell 0 1,grow");
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null, null},
					},
					new String[] {
						"ISBN", "T\u00EDtulo", "Cod. Editorial", "A\u00F1o", "Num. Pags", "Precio", "Cantidad", "PrecioCD"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class, Integer.class, Integer.class, Float.class, Integer.class, Double.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setPreferredWidth(99);
				scrollPane.setViewportView(table);
			}
		}
		{
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel, "cell 0 2,grow");
				panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JButton btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							llamarActualizar();
						}
					});
					{
					JButton btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							llamarEliminar();
							}
						});
						panel.add(btnEliminar);
					}
					panel.add(btnModificar);
					btnModificar.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				JButton btnCerrar = new JButton("Cerrar");
				panel.add(btnCerrar);
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
			}
		}
	}
	
	protected void llamarEliminar() {
		int fila=table.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(this, "Debe de seleccionar una fila para borrarla.");
		} else {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String isbn = (String) modelo.getValueAt(fila, 0);
		
		controlador.eliminarLibro(isbn);
		}
		
	}

	protected void llamarActualizar() {
		int fila=table.getSelectedRow();
		if (fila == -1) {
			JOptionPane.showMessageDialog(this, "Debe de seleccionar una fila a modificar.");
		} else {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String isbn = (String) modelo.getValueAt(fila, 0);
		
		controlador.mostrarActualizarLibro(isbn);
		}
	}

	public void setListaLibros(ArrayList<Libro> lista) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		for (Libro libro : lista) {
			Object [] fila = {
				libro.getIsbn(), libro.getTitulo(), libro.getCodEditorial(), libro.getAnio(), 				libro.getNum_pags(), libro.getPrecio(), libro.getCantidad(), libro.getPrecioCD()
			};
			modelo.addRow(fila);
		}
	}

	//  controlador el controlador a establecer
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

}
