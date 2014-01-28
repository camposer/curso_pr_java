package es.indra.formacion.pr.java;

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
				modificarArticulo();
			} else if (opcion.equals("3")) {
				eliminarArticulo();
			} else if (opcion.equals("4")) {
				listarArticulos();
			} else if (opcion.equals("5")) {
				break;
			}
		}
	}
	
	private void eliminarArticulo() {
		System.out.print("Id: ");
		String sid = scanner.nextLine();
		Integer id = Integer.parseInt(sid); // TODO Agregar validación
		
		articuloLogic.eliminar(id);
	}

	private void modificarArticulo() {
		System.out.print("Id: ");
		String sid = scanner.nextLine();
		Integer id = Integer.parseInt(sid); // TODO Agregar validación
		
		Articulo articulo = articuloLogic.obtener(id);
		
		if (articulo == null) // Si no existe el artícuylo en ArticuloLogic que aborte
			return;

		// Pidiendo datos comunes de artículo
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Precio: ");
		String sprecio = scanner.nextLine();
		Float precio = Float.parseFloat(sprecio); // TODO Agregar validación!
		
		if (articulo instanceof Camara) {
			System.out.print("Zoom: ");
			String szoom = scanner.nextLine();
			System.out.print("Tipo de Tarjeta [SD|MICRO_SD|MINI_SD|CF]: ");
			String stipoTarjeta = scanner.nextLine();
			
			// TODO Agregar manejo de excepciones
			Integer zoom = Integer.parseInt(szoom); 
			Camara.TipoTarjeta tipoTarjeta = Camara.TipoTarjeta.valueOf(stipoTarjeta.toUpperCase());
			
			articulo = new Camara(id, nombre, precio, tipoTarjeta, zoom);
		} else if (articulo instanceof Dvd) {
			System.out.print("Tiene grabadora [S|N]: ");
			String sgrabadora = scanner.nextLine();
			System.out.print("Tiene USB [S|N]: ");
			String sTieneUsb = scanner.nextLine();
			
			// TODO Agregar manejo de excepciones
			Boolean grabadora = (sgrabadora.toUpperCase().equals("S"))?true:false;
			Boolean tieneUsb = sTieneUsb.toUpperCase().equals("S");
			
			articulo = new Dvd(id, nombre, precio, grabadora, tieneUsb);						
		}
		
		articuloLogic.modificar(articulo);
	}

	private void listarArticulos() {
		List<Articulo> articulos = articuloLogic.obtenerTodos();
		
		for (Articulo art : articulos) {
			System.out.println(art);
		}
	}
	
	private void agregarArticulo() {
		Articulo articulo = null;
		
		System.out.print("Tipo [CAMARA|DVD]: ");
		String tipo = scanner.nextLine();

		// Verificación Ruth!
		if (!tipo.toLowerCase().equals("camara") && 
						!tipo.toLowerCase().equals("dvd"))
			return;
		
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