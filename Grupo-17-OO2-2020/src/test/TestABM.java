package test;

import java.time.LocalDate;

import modelo.Sistema;

public class TestABM {

	public static void main(String[] args) {

		/**
		 * Creamos el sistema de ejemplo y las variables necesarias para el testeo de
		 * ABM's
		 */

		/**
		 * DAR DE ALTA CLIENTE
		 */
		Sistema sistema = new Sistema();
		LocalDate fecha = LocalDate.of(1999, 1, 25);
		LocalDate date = LocalDate.now();
		LocalDate nacimiento = LocalDate.of(1999, 1, 25);

		try {
			System.out.println("\n*******AGREGO PRODUCTOS Y MUESTRO LA LISTA*******");
			sistema.agregarProducto(true, "Runfalcon", 500, date, "Adidas");
			sistema.agregarProducto(true, "AirMAX", 600, date, "Nike");
			sistema.agregarProducto(true, "Disruptor", 450, date, "FILA");
			System.out.println(sistema.getLstProductos());

			
			
			System.out.println("\n*******AGREGO SUCURSALES Y  MUESTRO LA LISTA *******");
			sistema.agregarSucursal("Banfield", "PARIS", 952, 67.6f, 89.0f, 1122334455, "Alberto", "Fernandez",
					LocalDate.of(2000, 12, 12), 12331234, 9, 1200.0f);
			sistema.agregarSucursal("Temperley", "CALLE FALSA", 123, 80.5f, 200.5f, 456789012, "Mauricio", "Macri",
					LocalDate.of(1900, 1, 1), 98000000, 10, 200.0f);
			sistema.agregarSucursal("Lomas de Zamora", "Boedo", 2000, 36.9f, 48.4f, 120998437, "Martin", "Insaurralde",
					LocalDate.of(1940, 8, 8), 243523453, 12, 2200.0f);
			System.out.println(sistema.getLstSucursales());

			
			
			System.out.println("\n*******AGREGO CLIENTES Y MUESTRO LA LISTA *******");
			sistema.agregarCliente("Nicolas", "Golfieri", fecha, 95694659, "nicolas@golfieri");
			sistema.agregarCliente("Luana", "Montalibet", fecha, 11111111, "luana@montalibet");
			System.out.println(sistema.getLstClientes());

			
			System.out.println("\n****** AGREGO LOTES Y MUESTRO LA LISTA ********");
			
					sistema.traerSucursal(1).agregarLote(sistema.traerProducto(1), 'M', 20, LocalDate.of(2012, 9, 21));
		
					sistema.traerSucursal(1).agregarLote(sistema.traerProducto(1), 'M', 20, LocalDate.of(2012, 9, 21));
			sistema.traerSucursal(2).agregarLote(sistema.traerProducto(1), 'M', 100,
					LocalDate.of(2012, 9, 22));
			sistema.traerSucursal(3).agregarLote(sistema.traerProducto(1), 'M', 200,
					LocalDate.of(2012, 1, 23));
			System.out.println(sistema.traerSucursal(1).getLstLotes());

			
			System.out.println("\n*******AGREGO UN VENDEDOR Y MUESTRO LA LISTA *******");
			System.out.println(
					sistema.traerSucursal(1).agregarVendedor("Nahuel", "Ovejero", nacimiento, 41414141, 12, 80000.00f));
			System.out.println(sistema.traerSucursal(1).getLstVendedores());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		

		try {
			System.out.println("\n*******/*ESCENARIO 2: CARGAR UN CLIENTE QUE YA FUE CARGADO*/*******");
			sistema.agregarCliente("Nicolas", "Golfieri", fecha, 95694659, "nicolas@golfieri");

		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
	
		try {
			System.out.println("\n********ESCENARIO 1: EL CLIENTE QUE SE QUIERE MODIFICAR EXISTE*/*******");
			sistema.modificarCliente("Luana", "Selena", fecha, "Luana@Selena", 95694659);
			System.out.println(sistema.getLstClientes());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("\n*******/*ESCENARIO 2: EL CLIENTE QUE SE QUIERE MODIFICAR NO EXISTE*******");
			sistema.modificarCliente("Luana", "Selena", fecha, "Luana@Selena", 22222222);
			System.out.println("El cliente fue modificado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		

		try {
			System.out.println("\n*******/*ESCENARIO 1: EL CLIENTE QUE SE QUIERE DAR DE BAJA EXISTE*/*******");
			sistema.eliminarCliente(95694659);
			System.out.println(sistema.getLstClientes());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		
		try {
			System.out.println("\n*******/*ESCENARIO 2: EL CLIENTE QUE SE QUIERE DAR DE BAJA NO EXISTE*/*******");
			sistema.eliminarCliente(11111);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		/**
		 * DAR DE ALTA PRODUCTO
		 */

		try {
			System.out.println("\n*******TRAIGO LA CANTIDAD DE UNIDADES DEL PRODUCTO UNO EN LA SUCURSAL UNO *******");
			System.out.println(sistema.traerSucursal(1).traerCantidad(sistema.traerProducto(1)));
			// un lote tiene talle??????? no seria mejor que un producto tenga el
			// talle???????

		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
		System.out.println();

		try {
			System.out.println("\n********ESCENARIO 2: CARGAR UN PRODUCTO QUE YA ESTA CARGADO********");
			sistema.agregarProducto(true, "Runfalcon", 500, date, "Adidas");
		} catch (Exception e) {
			System.out.println("Excepcion: " + e.getMessage());
		}
		System.out.println();

		/**
		 * MODIFICAR PRODUCTO
		 */

		try {
			System.out.println("\n*******ESCENARIO 1: MODIFICAR UN PRODUCTO QUE EXISTE*******");
			sistema.modificarProducto(false, "Runfalcon", 345, date, "Adidas");
			System.out.println(sistema.getLstProductos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Escenario 1: Modificar un producto que no existe
		try {
			System.out.println("\n*******ESCENARIO 2: MODIFICAR UN PRODUCTO QUE NO EXISTE*******");
			sistema.modificarProducto(false, "Runfalcon2", 345, date, "Adidas");
			System.out.println(sistema.getLstProductos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		/**
		 * ELIMINAR PRODUCTO
		 */

		try {
			System.out.println("\n*******ESCENARIO 1: ELIMINAR UN PRODUCTO QUE  EXISTE*******");
			sistema.eliminarProducto("Runfalcon");
			System.out.println(sistema.getLstProductos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("\n*******ESCENARIO 2: ELIMINAR UN PRODUCTO QUE NO EXISTE*******");
			sistema.eliminarProducto("Runfalcon2");
			System.out.println(sistema.getLstProductos());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
