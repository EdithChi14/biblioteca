package Clases;

public class Editorial {
	private int id_edit;
	private String nombre;

	
	
	 public Editorial() {
		super();
	}



	

	public Editorial(int id_edit, String nombre) {
		super();
		this.id_edit = id_edit;
		this.nombre = nombre;
	}





	public int getId_edit() {
		return id_edit;
	}





	public void setId_edit(int id_edit) {
		this.id_edit = id_edit;
	}





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	@Override
	    public String toString() {
	        // Este método es importante para que el ComboBox muestre el nombre
	        return this.nombre;
	    }
	
}
