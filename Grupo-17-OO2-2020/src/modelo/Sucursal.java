package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sucursal {
	
	private Direccion ubicacion;
	private int id;
	private long telefono;
	private Gerente gerente;
	private List<Vendedor> lstVendedores;
	private List<Lote> lstLotes;
	
	public Sucursal(Direccion ubicacion, int id, long telefono, Gerente gerente) {
		super();
		this.ubicacion = ubicacion;
		this.id = id;
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
	
	public boolean equals(Sucursal sucursal)
	{
		return sucursal.getUbicacion().equals(ubicacion);
	}
	/*--------------------------------------------------*/

	/** 
	 *
	 * Metodos con la lista de vendedores. Agregar, traer, modificar y eliminar
	 * 
	 */
	public boolean agregarVendedor(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) throws Exception{
		if(this.traerVendedor(dni) != null) throw new Exception("El vendedor que se quiere agregar ya esta en el sistema.");
		return lstVendedores.add(new Vendedor(nombre,apellido, fechaNacimiento, dni, horasPorJornada,
			 sueldoBasico, this));  
	}
	
	/*--------------------------------------------------*/
	
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

	public boolean eliminarVendedor(long dni) throws Exception{
		
		if(this.traerVendedor(dni) != null) throw new Exception("El vendedor que se quiere eliminar no esta en el sistema.");
		return lstVendedores.remove(this.traerVendedor(dni));
		
	}
	
	/*--------------------------------------------------*/
	
	public int traerCantidad(Producto producto) {
		int cantidad=0;
		for(int indice=0; indice<this.traerLotesActivos(producto).size(); indice++){
			if(this.traerLotesActivos(producto).get(indice).getProducto().equals(producto)){
				cantidad += this.traerLotesActivos(producto).get(indice).getCantidadActual();
			}
		}
		return cantidad;
	}
	
	/*--------------------------------------------------*/
	
	public boolean agregarLote(Producto producto, char talle, int cantidadInicial, LocalDate fechaIngreso) {
		return lstLotes.add(new Lote(producto, talle, cantidadInicial, fechaIngreso));
	}
	
	/*--------------------------------------------------*/
	
	public List<Lote> traerLotesActivos(Producto producto){
		List<Lote> lotesActivos = new ArrayList<Lote>();
	
		for(int i=0; i<lstLotes.size();i++) {
			if(lstLotes.get(i).getProducto().equals(producto) && lstLotes.get(i).getCantidadActual()>0) {
				lotesActivos.add(lstLotes.get(i));
			}
		}
		return lotesActivos;
	}
	
	/*--------------------------------------------------*/
	
	//Devuelve True si la cantidad que hay en stock es suficiente para la cantidad pedida
	public boolean validarStock (Producto producto, int cantidad) {
		return this.traerCantidad(producto)>=cantidad;
	}
	
	/*--------------------------------------------------*/
	
	public void restarLotes(Producto producto, int cantidad) {
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
	
	public float calcularDistancia(Sucursal sucursal) {
		return (float) Math.sqrt((Math.pow((sucursal.getUbicacion().getLatitud() - this.getUbicacion().getLatitud()), 2) +
					((Math.pow((sucursal.getUbicacion().getLongitud() - this.getUbicacion().getLongitud()), 2)))));
	}
	
	/*--------------------------------------------------*/

	@Override
	public String toString() {
		return "Sucursal [ubicacion=" + ubicacion + ", id=" + id + ", telefono=" + telefono + ", gerente=" + gerente
				+ ", lstVendedores=" + lstVendedores + ", lstLotes=" + lstLotes + "]";
	}
	
	/*--------------------------------------------------*/
	
	public List<String> cierreDelMes(){
		List<String> cierre = new ArrayList<String>();
		
		for(int i=0; i<lstVendedores.size(); i++) {
			cierre.add("Sueldo total del vendedor " + lstVendedores.get(i).getNombre()+ " "+lstVendedores.get(i).getApellido() + ": "+
					(String.valueOf(lstVendedores.get(i).getSueldoBasico() + lstVendedores.get(i).getPlus()))
					+ "\n");
		}
		
		return cierre;
	}
	

}
