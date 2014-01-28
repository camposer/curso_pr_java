package es.indra.formacion.pr.java.exception;

public class PrecioInvalidoException extends ArticuloException {
	private static final long serialVersionUID = 7014349567313107599L;
	private Float value;

	public PrecioInvalidoException() {
		super();
	}

	public PrecioInvalidoException(String msg, Float value) {
		super(msg);
		this.value = value;
	}

	public Float getValue() {
		return value;
	}
}
