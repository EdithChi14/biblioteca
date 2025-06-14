package Clases;



public class Usuario{
	
	 private String idUsuario;  
	    private String nombre;    
	    private String correo;    
	    private String direccion; 
	    private String telefono; 
	    private String rol;
	    private String contra;
	    private String estado;
		public Usuario(String idUsuario, String nombre, String correo, String direccion, String telefono, String rol) {
			super();
			this.idUsuario = idUsuario;
			this.nombre = nombre;
			this.correo = correo;
			this.direccion = direccion;
			this.telefono = telefono;
			this.rol = rol;
		}
		
		@Override
	    public String toString() {
	        return nombre; // se mostrará en el combo
	    }

		

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getContra() {
			return contra;
		}



		public void setContra(String contra) {
			this.contra = contra;
		}



		public Usuario() {
			
		}

		public String getIdUsuario() {
			return idUsuario;
		}
		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getRol() {
			return rol;
		}
		public void setRol(String rol) {
			this.rol = rol;
		}
	    
	    
	

	




	
	
	
}
