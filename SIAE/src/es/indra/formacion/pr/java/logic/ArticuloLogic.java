package es.indra.formacion.pr.java.logic;

import java.util.ArrayList;
import java.util.List;

import es.indra.formacion.pr.java.model.Articulo;

public class ArticuloLogic {
	private int contador;
	private List<Articulo> articulos;
	
	public ArticuloLogic() {
		this.articulos = new ArrayList<Articulo>();
		this.contador = 1;
	}
	
	public void agregar(Articulo art) {
		art.setId(contador++);
		articulos.add(art);
	}
	
	public List<Articulo> obtenerTodos() {
		return articulos;
	}
}
