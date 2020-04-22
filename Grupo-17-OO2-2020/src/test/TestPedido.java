package test;

import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Sistema;

public class TestPedido {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Sistema sistema = new Sistema();
		LocalDate fecha = LocalDate.of(1999, 1, 24);
		LocalDate date = LocalDate.of(2019, 1, 24);
		LocalDate date2 = LocalDate.of(2019, 1, 22);
		LocalDate date3 = LocalDate.of(2019, 1, 28);
		LocalTime time = LocalTime.now();
		try {
			sistema.agregarCliente("Nicolas", "Golfieri", fecha, 95694659, "nicolas@golfieri");
			sistema.agregarCliente("Luana", "Montalibet", fecha, 11111111, "luana@montalibet");
			sistema.agregarProducto(true, "Runfalcon", 500, date, "Adidas");
			sistema.agregarProducto(true, "AirMAX", 600, date, "Nike");
			sistema.agregarProducto(true, "Disruptor", 450, date, "FILA");
			
			sistema.agregarSucursal("Lanús Oeste", "San Martín", 1234, 5, 6, 12345678, "Matías", "Veltri", fecha, 9999999, 24, 10);
			sistema.traerSucursal(1).agregarVendedor("Lautaro", "Veltri", fecha, 77777777, 1, 30000);
			sistema.traerSucursal(1).agregarVendedor("Nahuel", "Ovejero", fecha, 45645645, 1, 200);
			sistema.traerSucursal(1).agregarLote(sistema.traerProducto("Runfalcon"), 'x', 200, date);
			
			sistema.agregarSucursal("Banfield", "Yrigoyen", 5678, 10, 20, 98765432, "Ezequiel", "Traversa", fecha, 88888888, 24, 10);
			sistema.traerSucursal(2).agregarLote(sistema.traerProducto("AirMAX"), 'x', 200, date); 
			sistema.traerSucursal(2).agregarVendedor("Lautaro", "Bloisi", fecha, 66666666, 1, 30000);
			
			sistema.agregarVenta(sistema.traerSucursal(1).traerVendedor(77777777), sistema.traerCliente(11111111), date, time);
			
			/**
			 * 
			 */
			
	
			System.out.println("***/*ESCENARIO 1: COMPROBAR STOCK PROPIO Y GENERAR UN PEDIDO*/***");
			System.out.println(sistema.traerSucursal(1).validarStock(sistema.traerProducto("Runfalcon"), 10));
			sistema.traerVenta(1).generarPedidoConStockPropio(sistema.traerProducto("Runfalcon"), 10);
			
			System.out.println("\n***IMPRIMO VENTA***");
			System.out.println(sistema.traerVenta(1));
			
			
			
			System.out.println("\n***/*ESCENARIO 2: COMPROBAR STOCK PROPIO (INSUFICIENTE) Y SOLICITAR STOCK A OTRA SUCURSAL*/***");
			System.out.println(sistema.traerSucursal(1).validarStock(sistema.traerProducto("AirMAX"), 10));
			
			System.out.println("\n***COMPRUEBO SUCURSAL MAS CERCANA QUE CUBRA EL STOCK***");
			System.out.println(sistema.traerSucursalMasCercana(sistema.traerSucursal(1),sistema.traerProducto("AirMAX"), 30));
			sistema.generarPedidoConOtraSucursal(1, "AirMAX",30,
					sistema.traerSucursalMasCercana(sistema.traerSucursal(1),sistema.traerProducto("AirMAX"),30), 66666666);
			
			System.out.println("\n*** IMPRIMO LA VENTA ***");
			System.out.println(sistema.traerVenta(1));
			
			/**
			 * RANKING
			 */
			System.out.println("\n*** RANKING DE PRODUCTOS MAS VENDIDOS***");
			System.out.println(sistema.ranking());
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		/**
		
		 */
		
		System.out.println("\n***IMPRIMIR FACTURA DE LA VENTA 1***");
		System.out.println(sistema.traerVenta(1));
		
		/**
		 * 
		 */
		System.out.println("\n***CIERRE DEL MES***");
		System.out.println(sistema.traerSucursal(1).cierreDelMes());
		
		/**
		 * 
		 */
		System.out.println("\n***IMPRIMIR PRODUCTOS VENDIDOS ENTRE FECHAS***");
		System.out.println(sistema.productosVendidosPorFecha(1, date2, date3));
		
		System.out.println(sistema.traerVenta(1).getLstPedidos());
	}

}
