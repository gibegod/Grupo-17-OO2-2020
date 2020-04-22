package modelo;

import java.time.LocalDate;

public class Lote {
	
	private Producto producto;
	private char talle;
	private int cantidadActual;
	private int cantidadInicial;
	private LocalDate fechaIngreso;
	
	
	public Lote(Producto producto, char talle, int cantidadInicial, LocalDate fechaIngreso) {
		super();
		this.producto = producto;
		this.talle = talle;
		this.cantidadActual = cantidadInicial;
		this.cantidadInicial = cantidadInicial;
		this.fechaIngreso = fechaIngreso;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public char getTalle() {
		return talle;
	}


	public void setTalle(char talle) {
		this.talle = talle;
	}


	public int getCantidadActual() {
		return cantidadActual;
	}


	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}


	public int getCantidadInicial() {
		return cantidadInicial;
	}


	public void setCantidadInicial(int cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}


	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	@Override
	public String toString() {
		return "\nLote [producto=" + producto + ", talle=" + talle + ", cantidadActual=" + cantidadActual
				+ ", cantidadInicial=" + cantidadInicial + ", fechaIngreso=" + fechaIngreso + "]";
	}

}
