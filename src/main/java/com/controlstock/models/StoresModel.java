package com.controlstock.models;

public class StoresModel {

	private StoreModel store1;
	private StoreModel store2;

	public StoresModel() {}

	public StoresModel(StoreModel store1, StoreModel store2) {
		super();
		this.store1 = store1;
		this.store2 = store2;
	}

	public StoreModel getStore1() {
		return store1;
	}

	public void setStore1(StoreModel store1) {
		this.store1 = store1;
	}

	public StoreModel getStore2() {
		return store2;
	}

	public void setStore2(StoreModel store2) {
		this.store2 = store2;
	}

}
