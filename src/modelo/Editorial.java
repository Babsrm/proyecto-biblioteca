/**
 * 
 */
package modelo;

import java.util.Objects;

public class Editorial {

	//me traigo los campos de la tabla como variables
	private int codEditorial;
	private String nombre;
	private int anio;
	
	
	//creo los setters, getters, equals... lo que necesite
	
	public Editorial() {
		this.nombre="";
	}
	public Editorial(int codEditorial, String nombre, int anio) {
		super();
		this.codEditorial = codEditorial;
		this.nombre = nombre;
		this.anio = anio;
	}
	public int getCodEditorial() {
		return codEditorial;
	}
	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codEditorial);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return codEditorial == other.codEditorial;
	}
	@Override
	public String toString() {
		return this.codEditorial+" - "+this.nombre;
	}
	
	
}
