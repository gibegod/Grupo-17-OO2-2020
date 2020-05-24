package com.controlstock.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.sun.istack.Nullable;

@Entity
public class Sale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="sale")
	private Set<SaleRequest> setSaleRequests;
	
	//@NotNull
	@OneToOne
	private Employee employeeInCharge;
	
	//@NotNull
	@Nullable
	@OneToOne
	private Client client;
	
	//@NotNull
	@OneToOne
	private Store store;
	
	//@NotNull
	private LocalDateTime date;
	
	private float totalPrice;
	
	private boolean status;
	
	public Sale(){};
	
	public Sale(int id, Set<SaleRequest> setSaleRequests, Employee employeeInCharge, Client client, 
			LocalDateTime date, Store store) {
		super();
		this.id = id;
		this.setSaleRequests = setSaleRequests;
		this.employeeInCharge = employeeInCharge;
		this.client = client;
		this.date = date;
		this.totalPrice = 0;
		this.status = false;
		this.store = store;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<SaleRequest> getSetSaleRequests() {
		return setSaleRequests;
	}

	public void setSetSaleRequests(Set<SaleRequest> setSaleRequests) {
		this.setSaleRequests = setSaleRequests;
	}

	public Employee getEmployeeInCharge() {
		return employeeInCharge;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public Store getStore() {
		return store;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}

	public void setEmployeeInCharge(Employee employeeInCharge) {
		this.employeeInCharge = employeeInCharge;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
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
