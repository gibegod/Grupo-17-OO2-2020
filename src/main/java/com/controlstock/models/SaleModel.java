package com.controlstock.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class SaleModel {
	
	private int id;
	
	private Set<SaleRequestModel> setSaleRequests;

	private EmployeeModel employeeInCharge;

	private ClientModel client;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime date;
	
	private float totalPrice;
	
	private boolean status; //Esta en progreso o termino?
	
	private StoreModel storeModel;
	
	public SaleModel(){};
	
	public SaleModel(int id, Set<SaleRequestModel> setSaleRequests, EmployeeModel employeeInCharge,
					ClientModel client, LocalDateTime date, StoreModel storeModel) {
		super();
		setId(id);
		this.setSaleRequests = setSaleRequests;
		this.employeeInCharge = employeeInCharge;
		this.client = client;
		this.date = date;
		this.totalPrice = 0;
		this.status = false;
		this.storeModel = storeModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public StoreModel getStoreModel() {
		return storeModel;
	}
	
	public void setStoreModel(StoreModel store) {
		this.storeModel = store;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", setSaleRequests=" + setSaleRequests + ", employeeInCharge="
				+ employeeInCharge + ", client=" + client + ", date=" + date + ", totalPrice="
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
