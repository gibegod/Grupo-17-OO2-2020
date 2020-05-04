package com.controlstock.models;

import java.util.ArrayList;
import java.util.List;

import com.controlstock.entities.Direccion;
import com.controlstock.entities.Gerente;
import com.controlstock.entities.Lote;
import com.controlstock.entities.Store;
import com.controlstock.entities.Vendedor;

public class StoreModel {

	private int id;

	private Direccion ubicacion;

	private long telefono;

	private Gerente gerente;

	private List<Vendedor> lstVendedores;

	private List<Lote> lstLotes;

	public StoreModel() {}

	public StoreModel(Direccion ubicacion, int id, long telefono, Gerente gerente) {
		super();
		this.ubicacion = ubicacion;
		this.setId(id);
		this.telefono = telefono;
		this.gerente = gerente;
		this.lstVendedores = new ArrayList<Vendedor>();
		this.lstLotes = new ArrayList<Lote>();
	}

	public Direccion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Direccion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTelefono() {
		return telefono;
	}

	public void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	public Gerente getGerente() {
		return gerente;
	}

	public void setGerente(Gerente gerente) {
		this.gerente = gerente;
	}

	public List<Vendedor> getLstVendedores() {
		return lstVendedores;
	}

	public List<Lote> getLstLotes() {
		return lstLotes;
	}

	public boolean equals(Store sucursal) {
		return sucursal.getUbicacion().equals(ubicacion);
	}

}
