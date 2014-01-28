package es.indra.formacion.pr.java.model;

public abstract class Articulo implements java.io.Serializable {
	private static final long serialVersionUID = 4518802249991730832L;

	protected Integer id;
	protected String nombre;
	protected Float precio;
	
	public Articulo() {
		// No inicializa, ya todo referencia a null!!
	}
	
	public Articulo(Integer id, String nombre, Float precio) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	
}
