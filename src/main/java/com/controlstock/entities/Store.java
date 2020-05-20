package com.controlstock.entities;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Store {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@OneToOne(cascade = CascadeType.MERGE)
	//Direccion
	private Address address;
	
	@NotNull
	//Telefono
	private long phoneNumber;
	
	//@Null
	//@OneToOne
	//Gerente
	//private Employee manager;
	
	@Null
	@OneToMany(fetch = FetchType.LAZY, mappedBy="store")
	//Vendedores
	private Set<Employee> setEmployees = new HashSet<Employee>();
	
	//@NotNull
	//@OneToMany
	//private Set<Batch> setBatchs;
	
	public Store() {}
	
	public Store (int id, Address address, long phoneNumber, Set<Employee> setEmployees) {
		super();
		this.id = id;
		this.address = address;
		this.phoneNumber = phoneNumber;
		//this.manager = manager;
		this.setEmployees = new HashSet<Employee>();
		//this.setBatchs = new HashSet<Batch>();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

/*	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
*/
	
	public Set<Employee> getSetEmployees() {
		return setEmployees;
	}

	public void setSetEmployees(Set<Employee> setEmployees) {
		this.setEmployees = setEmployees;
	}
	/*
	public Set<Batch> getSetBatchs() {
		return setBatchs;
	}

	public void setSetBatchs(Set<Batch> setBatchs) {
		this.setBatchs = setBatchs;
	}
*/
	public boolean equals(Store store)
	{
		return store.getAddress().equals(address);
	}
	/*--------------------------------------------------*/

	/** 
	 *
	 * Metodos con la lista de vendedores. Agregar, traer, modificar y eliminar
	 * 
	 */
	/*public boolean addEmployee(String name, String surname, LocalDate birthdate, long dni, int workingHours,
			float minimunWage) throws Exception{
		if(this.traerVendedor(dni) != null) throw new Exception("El vendedor que se quiere agregar ya esta en el sistema.");
		return setEmployees.add(new Employee(name,surname, birthdate, dni, workingHours,
			 minimunWage,false, 0f, this));  
	}
	
	/*--------------------------------------------------*/
	/*
	public Vendedor traerVendedor(long dni) {
		int indice=0;
		Vendedor v = null;
		while(indice < this.getLstVendedores().size() && v == null) {
			if(dni == lstVendedores.get(indice).getDni()) {
				v = lstVendedores.get(indice);
			}
			indice++;
		}
		return v;
	}
	/*--------------------------------------------------*/
	/*
	public void modificarVendedor(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico, float plus) throws Exception{
	if(this.traerVendedor(dni) == null) throw new Exception("Error: El vendedor no existe");
	
	this.traerVendedor(dni).setApellido(apellido);
	this.traerVendedor(dni).setFechaNacimiento(fechaNacimiento);
	this.traerVendedor(dni).setHorasPorJornada(horasPorJornada);
	this.traerVendedor(dni).setNombre(nombre);
	this.traerVendedor(dni).setDni(dni);
	this.traerVendedor(dni).setPlus(plus);
	this.traerVendedor(dni).setSueldoBasico(sueldoBasico);
	}

	/*--------------------------------------------------*/
/*
	public boolean eliminarVendedor(long dni) throws Exception{
		
		if(this.traerVendedor(dni) != null) throw new Exception("El vendedor que se quiere eliminar no esta en el sistema.");
		return lstVendedores.remove(this.traerVendedor(dni));
		
	}
	
	/*--------------------------------------------------*/
	/*
	public int traerCantidad(Product producto) {
		int cantidad=0;
		for(int indice=0; indice<this.traerLotesActivos(producto).size(); indice++){
			if(this.traerLotesActivos(producto).get(indice).getProducto().equals(producto)){
				cantidad += this.traerLotesActivos(producto).get(indice).getCantidadActual();
			}
		}
		return cantidad;
	}
	
	/*--------------------------------------------------*/
	/*
	public boolean agregarLote(Product producto, char talle, int cantidadInicial, LocalDate fechaIngreso) {
		return lstLotes.add(new Lote(producto, talle, cantidadInicial, fechaIngreso));
	}
	
	/*--------------------------------------------------*/
	/*
	public List<Lote> traerLotesActivos(Product producto){
		List<Lote> lotesActivos = new ArrayList<Lote>();
	
		for(int i=0; i<lstLotes.size();i++) {
			if(lstLotes.get(i).getProducto().equals(producto) && lstLotes.get(i).getCantidadActual()>0) {
				lotesActivos.add(lstLotes.get(i));
			}
		}
		return lotesActivos;
	}
	
	/*--------------------------------------------------*/
	/*
	//Devuelve True si la cantidad que hay en stock es suficiente para la cantidad pedida
	public boolean validarStock (Product producto, int cantidad) {
		return this.traerCantidad(producto)>=cantidad;
	}
	
	/*--------------------------------------------------*/
	/*
	public void restarLotes(Product producto, int cantidad) {
		int indice=0;
		
		List<Lote> activos = this.traerLotesActivos(producto);
		
		while(cantidad > 0) {
				if(activos.get(indice).getCantidadActual() > cantidad) {
					activos.get(indice).setCantidadActual(activos.get(indice).getCantidadActual()-cantidad);
					cantidad=0;
				}
				else {
					cantidad -= activos.get(indice).getCantidadActual();
					activos.get(indice).setCantidadActual(0);
				}
			}
			indice++;
	
	}
	
	/*--------------------------------------------------*/
	/*
	public float calcularDistancia(Store sucursal) {
		return (float) Math.sqrt((Math.pow((sucursal.getUbicacion().getLatitud() - this.getUbicacion().getLatitud()), 2) +
					((Math.pow((sucursal.getUbicacion().getLongitud() - this.getUbicacion().getLongitud()), 2)))));
	}
	
	/*--------------------------------------------------*/
	/*
	@Override
	public String toString() {
		return "Sucursal [ubicacion=" + ubicacion + ", id=" + id + ", telefono=" + telefono + ", gerente=" + gerente
				+ ", lstVendedores=" + lstVendedores + ", lstLotes=" + lstLotes + "]";
	}
	
	/*--------------------------------------------------*/
	/*
	public List<String> cierreDelMes(){
		List<String> cierre = new ArrayList<String>();
		
		for(int i=0; i<lstVendedores.size(); i++) {
			cierre.add("Sueldo total del vendedor " + lstVendedores.get(i).getNombre()+ " "+lstVendedores.get(i).getApellido() + ": "+
					(String.valueOf(lstVendedores.get(i).getSueldoBasico() + lstVendedores.get(i).getPlus()))
					+ "\n");
		}
		
		return cierre;
	}*/
	
}