package es.indra.formacion.pr.java.exception;

public class TipoTarjetaInvalidoException extends ArticuloException {
	private static final long serialVersionUID = -8151421783184718317L;
	private Object value;

	public TipoTarjetaInvalidoException() {
		super();
	}

	public TipoTarjetaInvalidoException(String msg, Object value) {
		super(msg);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}