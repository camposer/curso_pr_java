package es.indra.formacion.pr.java.exception;

public class DecimalInvalidoException extends ArticuloException {
	private static final long serialVersionUID = -9193370722946836591L;
	private Object value;
	
	public DecimalInvalidoException() {
		super();
	}

	public DecimalInvalidoException(String msg, Object value) {
		super(msg);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}
}
