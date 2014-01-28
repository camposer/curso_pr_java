package es.indra.formacion.pr.java.exception;

public class EnteroInvalidoException extends ArticuloException {
	private static final long serialVersionUID = 1719363864430162961L;
	private Object value;

	public EnteroInvalidoException() {
		super();
	}

	public EnteroInvalidoException(String msg, Object value) {
		super(msg);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}
