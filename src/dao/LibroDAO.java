/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Editorial;
import modelo.Libro;

public class LibroDAO {

	private ConexionBD conexion;
	
	//creo el constructor. este constructor llama al constructor de conexion
    public LibroDAO() {
        this.conexion = new ConexionBD();
    }

    //conecta con la base de datos, itera por cada fila de la tabla y me devuelve una lista con los resultados
    //se recogen todos los datos de la tabla
    public ArrayList<Libro> obtenerTodosLibros() {
    	// Obtenemos una conexion a la base de datos. 
    	//estas tres lineas siempre son iguales
		Connection con = conexion.getConexion(); //obtiene un objeto Connection para conectar a la DB
		Statement consulta = null; //me permite recoger la sentecia de select
		ResultSet resultado = null; //me permite recojer los resultados
		ArrayList<Libro> lista = new ArrayList<Libro>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from libros");
			
			// Bucle para recorrer todas las filas que devuelve la consulta. pasa por cada calumna de la final y recoge el resultado de la columna en una variable.
			while(resultado.next()) {
				String isbn = resultado.getString("isbn");
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codEditorial");
				int anio = resultado.getInt("anio");
				int num_pags = resultado.getInt("num_pags");
				float precio = resultado.getFloat("precio");
				int cantidad = resultado.getInt("cantidad");
				float precioCD = resultado.getFloat("preciocd");
				
				Libro libro = new Libro(isbn, titulo, codEditorial, anio, num_pags, precio, cantidad, precioCD);
				lista.add(libro);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			//cerrar los procedimientos que tenga abiertos
			try {
				resultado.close(); //cierra el resultado
				consulta.close(); //cierra la consulta
				conexion.desconectar(); //cierra la conexión
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lista;
    }


    public Libro obtenerUnLibro(String isbn) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Libro libro=null;
		
		try {
			consulta = con.prepareStatement("select * from libros "
					+ "where isbn = ?");
			consulta.setString(1, isbn);
			resultado = consulta.executeQuery(); //a diferencia del statement a secas, la consulta en el statement a secas se mete dentro de exte executeQuery; en las perpstatement la consulta la ponemos en su variable
			
			if (resultado.next()) {
				String titulo = resultado.getString("titulo");
				int codEditorial = resultado.getInt("codEditorial");
				int anio = resultado.getInt("anio");
				int num_pags = resultado.getInt("num_pags");
				float precio = resultado.getFloat("precio");
				int cantidad = resultado.getInt("cantidad");
				float precioCD = resultado.getFloat("preciocd");
				
				libro = new Libro(isbn, titulo, codEditorial, anio, num_pags, precio, cantidad, precioCD);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return libro;
    }

    public int insertarLibro(Libro libro) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO libros (isbn, titulo, codEditorial, anio, num_pags, precio, cantidad, precioCD)"
					+ " VALUES (?,?,?,?,?,?,?,?) ");
			
			consulta.setString(1, libro.getIsbn());
			consulta.setString(2, libro.getTitulo());
			consulta.setInt(3, libro.getCodEditorial());
			consulta.setInt(4, libro.getAnio());
			consulta.setInt(5, libro.getNum_pags());
			consulta.setFloat(6, libro.getPrecio());
			consulta.setInt(7, libro.getCantidad());
			consulta.setFloat(8, libro.getPrecioCD());
			
			resultado=consulta.executeUpdate(); //atenta a llamar a este método

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }
    
    public int actualizarLibro(Libro libro) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE biblioteca.libros\n"
					+ "SET titulo = ?\n, codEditorial = ?\n, anio = ?\n, "
					+ "num_pags = ?\n, precio = ?\n, cantidad = ?\n, precioCD = ?\n"
					+ "WHERE isbn = ?;");
			
			consulta.setString(1, libro.getTitulo());
			consulta.setInt(2, libro.getCodEditorial());
			consulta.setInt(3, libro.getAnio());
			consulta.setInt(4, libro.getNum_pags());
			consulta.setFloat(5, libro.getPrecio());
			consulta.setInt(6, libro.getCantidad());
			consulta.setFloat(7, libro.getPrecioCD());
			consulta.setString(8, libro.getIsbn());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }


    public int eliminarLibro(String isbn) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM biblioteca.libros \n"
					+ "WHERE isbn = ?");
			
			consulta.setString(1, isbn);
			resultado=consulta.executeUpdate(); //esto devuelve las filas actualizadas, borradas etc

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

}
