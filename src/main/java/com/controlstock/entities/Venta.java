package com.controlstock.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	private int numero;
	
	@NotNull
	@OneToMany
	private List<Pedido>lstPedidos;
	
	@NotNull
	@OneToOne
	private Vendedor vendedorEncargado;
	
	@NotNull
	@OneToOne
	private Cliente cliente;
	
	@NotNull
	private LocalDate fecha;
	
	@NotNull
	private LocalTime hora;
	
	@NotNull
	private float precioTotal;
	
	public Venta(int numero, Vendedor vendedorEncargado, Cliente cliente, LocalDate fecha,
			LocalTime hora) {
		super();
		this.numero = numero;
		this.lstPedidos = new ArrayList<Pedido>();
		this.vendedorEncargado = vendedorEncargado;
		this.cliente = cliente;
		this.fecha = fecha;
		this.hora = hora;
		this.precioTotal = 0;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Pedido> getLstPedidos() {
		return lstPedidos;
	}

	public Vendedor getVendedorEncargado() {
		return vendedorEncargado;
	}

	public void setVendedorEncargado(Vendedor vendedorEncargado) {
		this.vendedorEncargado = vendedorEncargado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	public float precioTotal() {
		return precioTotal;
	}
	
	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}
	
	@Override
	public String toString() {
		return "Venta [numero=" + numero + ", \n lstPedidos=" + lstPedidos + ", \n precio total=" + precioTotal +", \n vendedorEncargado=" + vendedorEncargado
				+ ", \n cliente=" + cliente + ", \n fecha=" + fecha + ", \n hora=" + hora + "]";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean generarPedidoConStockPropio(Producto producto, int cantidad) {
		this.vendedorEncargado.getSucursal().restarLotes(producto, cantidad);
		
		float plus=((producto.getPrecioUnitario()*5)/100)*cantidad;
		
		this.vendedorEncargado.setPlus(this.vendedorEncargado.getPlus()+plus);
		return lstPedidos.add(new Pedido(producto, cantidad,null));
	}
	
	/*************************************************************/
	
	public void calcularTotal() {
		for(int i=0; i<lstPedidos.size(); i++) {
			this.setPrecioTotal(precioTotal+(lstPedidos.get(i).getCantidad() * lstPedidos.get(i).getProducto().getPrecioUnitario()));
		}
	}

}
