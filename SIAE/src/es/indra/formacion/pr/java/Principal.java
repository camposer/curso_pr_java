package es.indra.formacion.pr.java;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import es.indra.formacion.pr.java.logic.ArticuloLogic;
import es.indra.formacion.pr.java.model.Articulo;
import es.indra.formacion.pr.java.model.Camara;
import es.indra.formacion.pr.java.model.Dvd;

public class Principal {
	private Scanner scanner;
	private ArticuloLogic articuloLogic;

	public static void main(String[] args) {
		new Principal().iniciar();
	}

	public Principal() {
		this.scanner = new Scanner(System.in);
		this.articuloLogic = new ArticuloLogic();
	}

	public void iniciar() {
		while (true) {
			System.out.println("\nArtículos Electrónicos");
			System.out.println("1. Agregar artículo");	
			System.out.println("2. Modificar artículo");
			System.out.println("3. Eliminar artículo");
			System.out.println("4. Listar artículos (ordenados asc por precio)");
			System.out.println("5. Salir");
			System.out.println("? ");
			
			String opcion = scanner.nextLine();
			if (opcion.equals("1")) {
				agregarArticulo();
			} else if (opcion.equals("2")) {
				
			} else if (opcion.equals("3")) {
				
			} else if (opcion.equals("4")) {
				listarArticulos();
			} else if (opcion.equals("5")) {
				break;
			}
		}
	}
	
	private void listarArticulos() {
		List<Articulo> articulos = articuloLogic.obtenerTodos();
		
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
		
		for (Articulo art : articulos) {
			System.out.println(art);
		}
	}
	
	private void agregarArticulo() {
		Articulo articulo = null;
		
		System.out.print("Tipo [CAMARA|DVD]: ");
		String tipo = scanner.nextLine();
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Precio: ");
		String sprecio = scanner.nextLine();
		Float precio = Float.parseFloat(sprecio); // TODO Agregar validación!
		
		if (tipo.toLowerCase().equals("camara")) {
			System.out.print("Zoom: ");
			String szoom = scanner.nextLine();
			System.out.print("Tipo de Tarjeta [SD|MICRO_SD|MINI_SD|CF]: ");
			String stipoTarjeta = scanner.nextLine();
			
			// TODO Agregar manejo de excepciones
			Integer zoom = Integer.parseInt(szoom); 
			Camara.TipoTarjeta tipoTarjeta = Camara.TipoTarjeta.valueOf(stipoTarjeta.toUpperCase());
			
			articulo = new Camara(nombre, precio, tipoTarjeta, zoom);
		} else if (tipo.toLowerCase().equals("dvd")) {
			System.out.print("Tiene grabadora [S|N]: ");
			String sgrabadora = scanner.nextLine();
			System.out.print("Tiene USB [S|N]: ");
			String sTieneUsb = scanner.nextLine();
			
			// TODO Agregar manejo de excepciones
			Boolean grabadora = (sgrabadora.toUpperCase().equals("S"))?true:false;
			Boolean tieneUsb = sTieneUsb.toUpperCase().equals("S");
			
			articulo = new Dvd(nombre, precio, grabadora, tieneUsb);			
		}
		
		articuloLogic.agregar(articulo);
	}
	
	public Scanner getScanner() {
		return scanner;
	}	
}