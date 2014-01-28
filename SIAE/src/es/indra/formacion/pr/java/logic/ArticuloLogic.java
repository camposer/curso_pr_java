package es.indra.formacion.pr.java.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		Collections.sort(articulos, new Comparator<Articulo>() {
			@Override
			public int compare(Articulo a1, Articulo a2) {
				if (a1 != null && a1.getPrecio() != null &&
						a2 != null && a2.getPrecio() != null) 
					return (int)(a1.getPrecio() - a2.getPrecio());
				else
					return 0;
			}
		});
		
		return articulos;
	}

	private int buscarPos(Integer id) {
		int pos = -1;
		
		for (int i = 0; i < articulos.size(); i++) {
			Articulo a = articulos.get(i);
			
			if (a.getId().equals(id)) {
				pos = i;
				break;
			}
		}
		
		return pos;
	}
	
	public void modificar(Articulo art) {
		int pos = buscarPos(art.getId());
		if (pos >= 0) 
			articulos.set(pos, art);
	}
	
	public Articulo obtener(Integer id) {
		int pos = buscarPos(id);
		if (pos >= 0)
			return articulos.get(pos);
		else
			return null;
	}

	public void eliminar(Integer id) {
		int pos = buscarPos(id);
		if (pos >= 0)
			articulos.remove(pos);
	}

}
 