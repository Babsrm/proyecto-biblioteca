package modelo;

import java.util.Objects;

public class Departamento {
	//me traigo los campos de la tabla como variables
	private String isbn;
	private String titulo;
	private int codEditorial;
	private int anio;
	private int num_pags;
	private float precio;
	private int cantidad;
	private float precioCD;
	
	public Departamento() {
		this.isbn = "";
		this.titulo = "";
		this.codEditorial = 0;
		this.anio = 0;
		this.num_pags=0;
		this.precio=0;
		this.cantidad=0;
		this.precioCD = 0;
	}

	public Departamento(String isbn, String titulo, int codEditorial, int anio, int num_pags, float precio, int cantidad, float precioCD) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.codEditorial = codEditorial;
		this.anio = anio;
		this.num_pags = num_pags;
		this.precio = precio;
		this.cantidad = cantidad;
		this.precioCD = precioCD;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCodEditorial() {
		return codEditorial;
	}

	public void setCodEditorial(int codEditorial) {
		this.codEditorial = codEditorial;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getNum_pags() {
		return num_pags;
	}

	public void setNum_pags(int num_pags) {
		this.num_pags = num_pags;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecioCD() {
		return precioCD;
	}

	public void setPrecioCD(float precioCD) {
		this.precioCD = precioCD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(isbn, other.isbn);
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + "\nT??tulo: " + titulo + "\nCodEditorial: " + codEditorial +"\nA??o: " + anio+ "\nNum Pags: " + num_pags + "\nPrecio: " + precio + "\nCantidad: " + cantidad + "\nrecioCD: " + precioCD;
	}
	
	
}
