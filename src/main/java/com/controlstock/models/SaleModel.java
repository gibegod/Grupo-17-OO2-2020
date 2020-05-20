package com.controlstock.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;



public class SaleModel {
	
	private int id;
	
	private Set<SaleRequestModel> setSaleRequests;

	private EmployeeModel employeeInCharge;

	private ClientModel client;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private LocalTime time;
	
	private float totalPrice;
	
	public SaleModel(){};
	
	public SaleModel(int id, EmployeeModel employeeInCharge, ClientModel client, LocalDate date,
			LocalTime time) {
		super();
		this.setSaleRequests = new HashSet<SaleRequestModel>();
		this.employeeInCharge = employeeInCharge;
		this.client = client;
		this.date = date;
		this.time = time;
		this.totalPrice = 0;
	}


	public Set<SaleRequestModel> getSetSaleRequests() {
		return setSaleRequests;
	}



	public void setSetSaleRequests(Set<SaleRequestModel> setSaleRequests) {
		this.setSaleRequests = setSaleRequests;
	}



	public EmployeeModel getEmployeeInCharge() {
		return employeeInCharge;
	}



	public void setEmployeeInCharge(EmployeeModel employeeInCharge) {
		this.employeeInCharge = employeeInCharge;
	}



	public ClientModel getClient() {
		return client;
	}



	public void setClient(ClientModel client) {
		this.client = client;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public float getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", setSaleRequests=" + setSaleRequests + ", employeeInCharge="
				+ employeeInCharge + ", client=" + client + ", date=" + date + ", time=" + time + ", totalPrice="
				+ totalPrice + "]";
	}

	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
	public boolean generarPedidoConStockPropio(Product producto, int cantidad) {
		this.vendedorEncargado.getSucursal().restarLotes(producto, cantidad);
		
		float plus=((producto.getUnitPrice()*5)/100)*cantidad;
		
		this.vendedorEncargado.setPlus(this.vendedorEncargado.getPlus()+plus);
		return lstPedidos.add(new Pedido(producto, cantidad,null));
	}
	
	/*************************************************************/
	/*
	public void calcularTotal() {
		for(int i=0; i<lstPedidos.size(); i++) {
			this.setPrecioTotal(precioTotal+(lstPedidos.get(i).getCantidad() * lstPedidos.get(i).getProducto().getUnitPrice()));
		}
	}
	*/
}
