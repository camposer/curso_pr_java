package es.indra.formacion.pr.java.model;

public class Camara extends Articulo {
	private static final long serialVersionUID = -1264847625549126704L;
	
	private TipoTarjeta tipoTarjeta;
	private Integer zoom = null;

	public Camara() {
		
	}

	public Camara(Integer id, String nombre, Float precio,
			TipoTarjeta tipoTarjeta, Integer zoom) {
		super(id, nombre, precio);
		this.tipoTarjeta = tipoTarjeta;
		this.zoom = zoom;
	}

	public Camara(String nombre, Float precio,
			TipoTarjeta tipoTarjeta, Integer zoom) {
		this(null, nombre, precio, tipoTarjeta, zoom);
	}

	public TipoTarjeta getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}
	
	public static enum TipoTarjeta {
		SD, MICRO_SD, MINI_SD, CF;
	}

	@Override
	public String toString() {
		return "Camara [tipoTarjeta=" + tipoTarjeta + ", zoom=" + zoom
				+ ", id=" + id + ", nombre=" + nombre + ", precio=" + precio
				+ "]";
	}
}
