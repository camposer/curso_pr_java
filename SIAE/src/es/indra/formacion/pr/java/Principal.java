package es.indra.formacion.pr.java;

import java.util.List;
import java.util.Scanner;

import es.indra.formacion.pr.java.exception.DecimalInvalidoException;
import es.indra.formacion.pr.java.exception.EnteroInvalidoException;
import es.indra.formacion.pr.java.exception.PrecioInvalidoException;
import es.indra.formacion.pr.java.exception.TipoTarjetaInvalidoException;
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
				try {
					agregarArticulo();
				} catch (DecimalInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (PrecioInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (EnteroInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (TipoTarjetaInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				}
			} else if (opcion.equals("2")) {
				try {
					modificarArticulo();
				} catch (EnteroInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (DecimalInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (PrecioInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				} catch (TipoTarjetaInvalidoException e) {
					System.err.println(e.getMessage() + ":" + e.getValue());
				}
			} else if (opcion.equals("3")) {
				try {
					eliminarArticulo();
				} catch (EnteroInvalidoException e) {
					System.err.println(e.getMessage() + ": " + e.getValue());
				}
			} else if (opcion.equals("4")) {
				listarArticulos();
			} else if (opcion.equals("5")) {
				break;
			}
		}
	}
	
	private void eliminarArticulo() throws EnteroInvalidoException {
		System.out.print("Id: ");
		String sid = scanner.nextLine();
		
		try {
			Integer id = Integer.parseInt(sid);
			articuloLogic.eliminar(id);
		} catch (Exception e) {
			throw new EnteroInvalidoException("Id inválido", sid);
		}		
	}

	private void modificarArticulo() throws EnteroInvalidoException, DecimalInvalidoException, PrecioInvalidoException, TipoTarjetaInvalidoException {
		System.out.print("Id: ");
		String sid = scanner.nextLine();
		Integer id = null;
		try {
			id = Integer.parseInt(sid); 
		} catch (Exception e) {
			throw new EnteroInvalidoException("Id inválido", sid);
		}
		
		Articulo articulo = articuloLogic.obtener(id);
		
		if (articulo == null) // Si no existe el artícuylo en ArticuloLogic que aborte
			return;

		// Pidiendo datos comunes de artículo
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Precio: ");
		String sprecio = scanner.nextLine();
		Float precio = null;
		try {
			precio = Float.parseFloat(sprecio);
			
			if (precio < 0)
				throw new PrecioInvalidoException("Precio inválido", precio);
		} catch (NumberFormatException e) {
			throw new DecimalInvalidoException("Precio inválido", sprecio);
		}
		
		if (articulo instanceof Camara) {
			System.out.print("Zoom: ");
			String szoom = scanner.nextLine();
			System.out.print("Tipo de Tarjeta [SD|MICRO_SD|MINI_SD|CF]: ");
			String stipoTarjeta = scanner.nextLine();
			
			// Validaciones
			Integer zoom = null;
			try {
				zoom = Integer.parseInt(szoom);
			} catch(Exception e) {
				throw new EnteroInvalidoException("Zoom inválido", szoom);
			}
			
			Camara.TipoTarjeta tipoTarjeta = null;
			try {
				tipoTarjeta = Camara.TipoTarjeta.valueOf(stipoTarjeta.toUpperCase());
			} catch(Exception e) {
				throw new TipoTarjetaInvalidoException("Tipo de tarjeta inválido", stipoTarjeta);
			}
			
			articulo = new Camara(id, nombre, precio, tipoTarjeta, zoom);
		} else if (articulo instanceof Dvd) {
			System.out.print("Tiene grabadora [S|N]: ");
			String sgrabadora = scanner.nextLine();
			System.out.print("Tiene USB [S|N]: ");
			String sTieneUsb = scanner.nextLine();
			
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
	
	private void agregarArticulo() throws DecimalInvalidoException, PrecioInvalidoException, EnteroInvalidoException, TipoTarjetaInvalidoException {
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
		Float precio = null;
		try {
			precio = Float.parseFloat(sprecio);
			
			if (precio < 0)
				throw new PrecioInvalidoException("Precio inválido", precio);
		} catch (NumberFormatException e) {
			throw new DecimalInvalidoException("Precio inválido", sprecio);
		}
		
		if (tipo.toLowerCase().equals("camara")) {
			System.out.print("Zoom: ");
			String szoom = scanner.nextLine();
			System.out.print("Tipo de Tarjeta [SD|MICRO_SD|MINI_SD|CF]: ");
			String stipoTarjeta = scanner.nextLine();
			
			// Validaciones
			Integer zoom = null;
			try {
				zoom = Integer.parseInt(szoom);
			} catch(Exception e) {
				throw new EnteroInvalidoException("Zoom inválido", szoom);
			}
			
			Camara.TipoTarjeta tipoTarjeta = null;
			try {
				tipoTarjeta = Camara.TipoTarjeta.valueOf(stipoTarjeta.toUpperCase());
			} catch(Exception e) {
				throw new TipoTarjetaInvalidoException("Tipo de tarjeta inválido", stipoTarjeta);
			}
			
			articulo = new Camara(nombre, precio, tipoTarjeta, zoom);
		} else if (tipo.toLowerCase().equals("dvd")) {
			System.out.print("Tiene grabadora [S|N]: ");
			String sgrabadora = scanner.nextLine();
			System.out.print("Tiene USB [S|N]: ");
			String sTieneUsb = scanner.nextLine();
			
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