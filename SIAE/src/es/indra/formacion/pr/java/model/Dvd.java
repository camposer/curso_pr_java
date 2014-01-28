package es.indra.formacion.pr.java.model;

public class Dvd extends Articulo {
	private static final long serialVersionUID = -4361996451786268159L;

	private Boolean grabadora;
	private Boolean tieneUsb;
	
	public Dvd() {
		super();
	}

	public Dvd(Integer id, String nombre, 
			Float precio, Boolean grabadora, Boolean tieneUsb) {
		super(id, nombre, precio);
		this.grabadora = grabadora;
		this.tieneUsb = tieneUsb;
	}

	public Dvd(String nombre, 
			Float precio, Boolean grabadora, Boolean tieneUsb) {
		this(null, nombre, precio, grabadora, tieneUsb);
	}

	public Boolean isGrabadora() {
		return grabadora;
	}

	public void setGrabadora(Boolean grabadora) {
		this.grabadora = grabadora;
	}

	public Boolean isTieneUsb() {
		return tieneUsb;
	}

	public void setTieneUsb(Boolean tieneUsb) {
		this.tieneUsb = tieneUsb;
	}

	@Override
	public String toString() {
		return "Dvd [grabadora=" + grabadora + ", tieneUsb=" + tieneUsb
				+ ", id=" + id + ", nombre=" + nombre + ", precio=" + precio
				+ "]";
	}
}
