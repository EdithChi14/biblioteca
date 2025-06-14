package Clases;

import java.util.Date;

public class Prestamo {
	private long isbnPre;
	private String idusu;
	private String estado;

	private int nroprestamo;
    private String usuario; 
    private long isbn; 
    private String fechaPrestamo;  
    private String fechaDevolucion;
    private String nombreLibro;

public Prestamo() {
	super(); 
}




public Prestamo(int nroprestamo, String usuario, long isbn, String fechaPrestamo,
		String fechaDevolucion) {
	super();
	this.nroprestamo = nroprestamo;
	this.usuario = usuario;
	this.isbn = isbn;
	this.fechaPrestamo = fechaPrestamo;
	this.fechaDevolucion = fechaDevolucion;
	
}





public String getEstado() {
    return estado;
}

public void setEstado(String estado) {
    this.estado = estado;
}


public long getIsbnPre() {
	return isbnPre;
}







public void setIsbnPre(long isbnPre) {
	this.isbnPre = isbnPre;
}







public String getIdusu() {
	return idusu;
}




public void setIdusu(String idusu) {
	this.idusu = idusu;
}


public int getNroprestamo() {
	return nroprestamo;
}



public void setNroprestamo(int nroprestamo) {
	this.nroprestamo = nroprestamo;
}

public String getNombreLibro() {
	return nombreLibro;
}

public void setNombreLibro(String nombreLibro) {
	this.nombreLibro = nombreLibro;
}

public String getUsuario() {
	return usuario;
}

public void setUsuario(String usuario) {
	this.usuario = usuario;
}

public long getIsbn() {
	return isbn;
}

public void setIsbn(long isbn) {
	this.isbn = isbn;
}

public String getFechaPrestamo() {
	return fechaPrestamo;
}

public void setFechaPrestamo(String fechaPrestamo) {
	this.fechaPrestamo = fechaPrestamo;
}

public String getFechaDevolucion() {
	return fechaDevolucion;
}

public void setFechaDevolucion(String fechaDevolucion) {
	this.fechaDevolucion = fechaDevolucion;
}



}
