package Clases;

public class Libro {
	private long isbn;
	private int stock;
   private String  titulo, autor,categoria, editorial;
   
   private int id_categoria;
   private int id_editorial;

public Libro() {
	super();
}



public Libro(long isbn, int stock, String titulo, String autor, int id_categoria, int id_editorial) {
	super();
	this.isbn = isbn;
	this.stock = stock;
	this.titulo = titulo;
	this.autor = autor;
	this.id_categoria = id_categoria;
	this.id_editorial = id_editorial;
}



public Libro(long isbn, int stock, String titulo, String autor, String categoria, String editorial) {
	super();
	this.isbn = isbn;
	this.stock = stock;
	this.titulo = titulo;
	this.autor = autor;
	this.categoria = categoria;
	this.editorial = editorial;
}

@Override
public String toString() {
    return titulo; // se mostrará en el combo
}


public int getId_categoria() {
	return id_categoria;
}



public void setId_categoria(int id_categoria) {
	this.id_categoria = id_categoria;
}



public int getId_editorial() {
	return id_editorial;
}



public void setId_editorial(int id_editorial) {
	this.id_editorial = id_editorial;
}



public long getIsbn() {
	return isbn;
}

public void setIsbn(long isbn) {
	this.isbn = isbn;
}

public int getStock() {
	return stock;
}

public void setStock(int stock) {
	this.stock = stock;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getAutor() {
	return autor;
}

public void setAutor(String autor) {
	this.autor = autor;
}

public String getCategoria() {
	return categoria;
}

public void setCategoria(String categoria) {
	this.categoria = categoria;
}

public String getEditorial() {
	return editorial;
}

public void setEditorial(String editorial) {
	this.editorial = editorial;
} 

}
