package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Editorial;

public class EditorialDAO {

	private ConexionBD conexion;
	
	//creo el constructor. este constructor llama al constructor de conexion
    public EditorialDAO() {
        this.conexion = new ConexionBD();
    }

    //conecta con la base de datos, itera por cada fila de la tabla y me devuelve una lista con los resultados
    //se recogen todos los datos de la tabla
    public ArrayList<Editorial> obtenerTodasEditoriales() {
    	// Obtenemos una conexion a la base de datos. 
    	//estas tres lineas siempre son iguales. podemos usar preparedStatement en vez de statement a secas. usaríamos entonces las ?
		Connection con = conexion.getConexion(); //obtiene un objeto Connection para conectar a la DB
		Statement consulta = null; //me permite recoger la sentecia de select
		ResultSet resultado = null; //me permite recojer los resultados
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from editoriales");
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial, nombre,anio);
				lista.add(ed);
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


    public Editorial obtenerUnaEditorial(int codEditorial) {
    	//solo obtiene una editorial en vez de todas las de la tabla
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Editorial ed=null;
		
		try {
			consulta = con.prepareStatement("select * from editoriales "
					+ "where codEditorial = ?");
			consulta.setInt(1, codEditorial);
			resultado = consulta.executeQuery(); //a diferencia del statement a secas, la consulta en el statement a secas se mete dentro de exte executeQuery; en las perpstatement la consulta la ponemos en su variable
			
			if (resultado.next()) {
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				ed = new Editorial(codEditorial, nombre,anio);
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
		return ed;
    }
    
  //recupera qué editoriales tienen una fecha de apertura posterior al año indicado
    public ArrayList<Editorial> obtenerEditorialesConParametros(int anioDesde, String letra) {
    	
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null; 
		ResultSet resultado = null; 
		ArrayList<Editorial> lista = new ArrayList<Editorial>();
		
		try {
			consulta = con.prepareStatement("select nombre, anio from editoriales "
					+"where anio>? and nombre '?%'");
			consulta.setInt(1, anioDesde);
			consulta.setString(2, letra);
			resultado = consulta.executeQuery();
			
			// Bucle para recorrer todas las filas que devuelve la consulta
			while(resultado.next()) {
				int codEditorial = resultado.getInt("codEditorial");
				String nombre = resultado.getString("nombre");
				int anio = resultado.getInt("anio");
				
				Editorial ed = new Editorial(codEditorial, nombre,anio);
				lista.add(ed);
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

    public int insertarEditorial(Editorial editorial) {
    	// Obtenemos una conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO editoriales (nombre,anio)"
					+ " VALUES (?,?) ");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAnio());
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
    
    public int actualizarEditorial(Editorial editorial) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE biblioteca.editoriales\n"
					+ "SET nombre = ?, anio = ?\n"
					+ "WHERE codEditorial = ?;");
			
			consulta.setString(1, editorial.getNombre());
			consulta.setInt(2, editorial.getAnio());
			consulta.setInt(3, editorial.getCodEditorial());
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


    public int eliminarEditorial(int codEditorial) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM biblioteca.editoriales \n"
					+ "WHERE codEditorial = ?");
			
			consulta.setInt(1, codEditorial);
			resultado=consulta.executeUpdate();

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
