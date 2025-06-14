package Clases;

public class Reserva {
	private int nroreserva;
	private String idusu;
	private String usuario; 
    private long isbn; 
    private String fechaReserva;  
    private String nombreLibro;
    private String estado;
    
	public Reserva() { 
		
	}

	

	public Reserva(int nroreserva, String usuario, long isbn, String fechaReserva) {
		
		this.nroreserva = nroreserva;
		this.usuario = usuario;
		this.isbn = isbn;
		this.fechaReserva = fechaReserva;
	}



	public int getNroreserva() {
		return nroreserva;
	}

	public void setNroreserva(int nroreserva) {
		this.nroreserva = nroreserva;
	}

	public String getIdusu() {
		return idusu;
	}

	public void setIdusu(String idusu) {
		this.idusu = idusu;
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

	public String getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
