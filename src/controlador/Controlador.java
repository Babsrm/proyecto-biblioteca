/**
 * 
 */
package controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.EditorialDAO;
import dao.LibroDAO;
import modelo.Editorial;
import modelo.Libro;
import vista.DialogoEditoriales;
import vista.DialogoLibros;
import vista.NuevaEditorial;
import vista.NuevoLibro;
import vista.VentanaPpal;

public class Controlador {

	// Ventanas del sistema. ventanas de la aplicación
	private VentanaPpal ventanaPpal;
	private DialogoEditoriales dialogoEditoriales;
	private NuevaEditorial NuevaEditorial;
	private NuevoLibro nuevoLibro;
	private DialogoLibros dialogoLibros;
	
	// Objetos DAO para el acceso a la base de datos
	private EditorialDAO editorialDAO;
	private LibroDAO libroDAO;
	
	
	public Controlador() {
		// Creo las ventanas de la aplicación, las instancio
		this.ventanaPpal = new VentanaPpal();
		this.dialogoEditoriales = new DialogoEditoriales();
		this.NuevaEditorial = new NuevaEditorial();
		this.nuevoLibro = new NuevoLibro();
		this.dialogoLibros = new DialogoLibros();
		
		// Dando acceso al controlador desde las vistas
		this.ventanaPpal.setControlador(this); 
		//que a la ventana principal le va a controlar un controlador que sea este mismo que estoy creando
		this.dialogoEditoriales.setControlador(this);
		this.NuevaEditorial.setControlador(this);
		this.dialogoLibros.setControlador(this);
		this.nuevoLibro.setControlador(this);
		
		// Creamos los objetos DAO
		editorialDAO = new EditorialDAO();
		libroDAO = new LibroDAO();
	}
	
	public void inciarPrograma() {
		//muestra la ventana principal para que se empiece a interactuar con ella
		this.ventanaPpal.setVisible(true);
	}
	
	public void mostrarEditoriales() {
		ArrayList<Editorial> lista = editorialDAO.obtenerTodasEditoriales();
		dialogoEditoriales.setListaEditoriales(lista); //esta lista la creamos para borrar la antigua y que vaya pintando la nueva lista. siempre la misma ventana, solo cambia la lista que queremos que nos pinte
		dialogoEditoriales.setVisible(true);
	}
	public void mostrarLibros() {
		ArrayList<Libro> lista = libroDAO.obtenerTodosLibros();
		dialogoLibros.setListaLibros(lista); //esta lista la creamos para borrar la antigua y que vaya pintando la nueva lista. siempre la misma ventana, solo cambia la lista que queremos que nos pinte
		dialogoLibros.setVisible(true);
	}
	
	public void mostrarNuevaEditorial() {
		NuevaEditorial.setEditorial(null);
		NuevaEditorial.setVisible(true);
	}
	
	public void mostrarNuevoLibro() {
		nuevoLibro.setLibro(null);
		nuevoLibro.setVisible(true);
	}
	
	public void insertarEditorial(Editorial ed) {
		int res=editorialDAO.insertarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error: no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial añadida correctamente.");
			NuevaEditorial.setVisible(false);
		}
	}
	
	public void insertarLibro(Libro li) {
		int res=libroDAO.insertarLibro(li);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevoLibro, "Error: no se ha podido insertar el libro.");
		} else {
			JOptionPane.showMessageDialog(nuevoLibro, "Libro añadido correctamente.");
			nuevoLibro.setVisible(false);
		}
	}

	public void mostrarActualizarEditorial(int codEditorial) {
		Editorial e = editorialDAO.obtenerUnaEditorial(codEditorial);
		NuevaEditorial.setEditorial(e);
		NuevaEditorial.setVisible(true);
	}
	
	public void mostrarActualizarLibro(String isbn) {
		Libro li = libroDAO.obtenerUnLibro(isbn);
		nuevoLibro.setLibro(li);
		nuevoLibro.setVisible(true);
	}

	public void actualizarEditorial(Editorial ed) {
		int res=editorialDAO.actualizarEditorial(ed);
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error: no se ha podido actualizar.");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial actualizada correctamente.");
			NuevaEditorial.setVisible(false);
		}
		mostrarEditoriales();
	}

	public void actualizarLibro(Libro li) {
		int res=libroDAO.actualizarLibro(li);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevoLibro, "Error: no se ha podido actualizar.");
		} else {
			JOptionPane.showMessageDialog(nuevoLibro, "Libro actualizado correctamente.");
			nuevoLibro.setVisible(false);
		}
		mostrarLibros();
	}

	public void eliminarEditorial(int codEditorial) {
		int res = editorialDAO.eliminarEditorial(codEditorial); 
		if (res==0) {
			JOptionPane.showMessageDialog(NuevaEditorial, "Error: no se ha podido eliminar.");
		} else {
			JOptionPane.showMessageDialog(NuevaEditorial, "Editorial eliminada correctamente.");
		this.NuevaEditorial.setVisible(false);
			}
		mostrarEditoriales();	
		}

	public void eliminarLibro(String isbn) {
		int res = libroDAO.eliminarLibro(isbn);
		if (res==0) {
			JOptionPane.showMessageDialog(nuevoLibro, "Error: no se ha podido eliminar el libro.");
		} else {
			JOptionPane.showMessageDialog(nuevoLibro, "Libro eliminado correctamente.");
		this.nuevoLibro.setVisible(false);
			}
		mostrarLibros();	
		}
		
	}
		
