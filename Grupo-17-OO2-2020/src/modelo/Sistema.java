package modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class Sistema {
	
	private List<Sucursal> lstSucursales;
	private List<Producto> lstProductos;
	private List<Cliente> lstClientes;
	private List<Venta> lstVentas;
	
	
	public Sistema() {
		super();
		this.lstSucursales = new ArrayList<Sucursal>();
		this.lstProductos = new ArrayList<Producto>();
		this.lstClientes = new ArrayList<Cliente>();
		this.lstVentas = new ArrayList<Venta>();
	}
	
	public List<Sucursal> getLstSucursales() {
		return lstSucursales;
	}
	public List<Producto> getLstProductos() {
		return lstProductos;
	}
	public List<Cliente> getLstClientes() {
		return lstClientes;
	}
	public List<Venta> getLstVentas() {
		return lstVentas;
	}
	
	
	/*--------------------------------------------------*/
	
	/** 
	 *
	 * Se crea y agrega un producto a la Lista de Productos (lstProductos)
	 * 
	 */
	
	/*--------------------------------------------------*/
	
	public Producto traerProducto (int id) {
		Producto p = null;
		int i=0;
		while (i<lstProductos.size() && p==null)
		{
			if (lstProductos.get(i).getId() == id)
			{
				p = lstProductos.get(i);
			}
			i++;
		}
		return p;
	}
	
	/*--------------------------------------------------*/
	
	public Producto traerProducto (String descripcion) {
		Producto p = null;
		int i=0;
		while (i<lstProductos.size() && p==null)
		{
			if (lstProductos.get(i).getDescripcion().equals(descripcion))
			{
				p = lstProductos.get(i);
			}
			i++;
		}
		return p;
	}
	
	/*--------------------------------------------------*/
	
	public boolean agregarProducto(boolean status, String descripcion, float precioUnitario, LocalDate fechaAlta, String marca) throws Exception{
		if(this.traerProducto(descripcion)!=null) throw new Exception ("Error: ya existe un producto con esa descripcion");
		
		int id=1;
		if(!lstProductos.isEmpty()) {
			id= lstProductos.get(lstProductos.size()-1).getId() + 1;
		}
		return lstProductos.add(new Producto(id, status, descripcion, precioUnitario, fechaAlta, marca));
	}
	
	/*--------------------------------------------------*/
	
	public boolean eliminarProducto (String descripcion) throws Exception{
		if(this.traerProducto(descripcion)==null) throw new Exception ("Error: No se encontró el producto");
		int i=0;
		int j=0;
		
		while(i<lstVentas.size()) {
			while (j<lstVentas.get(i).getLstPedidos().size()) {
				if (lstVentas.get(i).getLstPedidos().get(j).getProducto().equals(this.traerProducto(descripcion))){
					throw new Exception("Error: El producto se encuentra está registrado en una venta");
				}
				j++;
			}
			i++;
		}
		
		i=0;
		j=0;
		
		while(i<lstSucursales.size()) {
			while (j<lstSucursales.get(i).getLstLotes().size()) {
				if (lstSucursales.get(i).getLstLotes().get(j).getProducto().equals(this.traerProducto(descripcion))){
					throw new Exception("Error: Existen lotes del Producto");
				}
				j++;
			}
			i++;
		}
		
		return lstProductos.remove(this.traerProducto(descripcion));
	}
	
	/*--------------------------------------------------*/
	
	public void desactivarProducto (Producto producto) {
		producto.desactivarProducto();
	}
	
	/*--------------------------------------------------*/
	
	public void activarProducto (Producto producto) {
		producto.activarProducto();
	}
	
	/*--------------------------------------------------*/

	public void modificarProducto (boolean status, String descripcion, 
	float precioUnitario, LocalDate fechaAlta, String marca) throws Exception {
		if(this.traerProducto(descripcion)==null) throw new Exception ("Error: No se encontró el producto");
		
		this.traerProducto(descripcion).setStatus(status);
		this.traerProducto(descripcion).setPrecioUnitario(precioUnitario);
		this.traerProducto(descripcion).setFechaAlta(fechaAlta);
		this.traerProducto(descripcion).setMarca(marca);
	}

/*--------------------------------------------------*/

	/** 
	 *
	 * Metodos con la lista de clientes. Agregar, traer, modificar y eliminar
	 * 
	 */
	public boolean agregarCliente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, String mail) throws Exception{
		if(this.traerCliente(dni) != null) throw new Exception("El cliente que se quiere agregar ya esta en el sistema.");
		
		return lstClientes.add(new Cliente(nombre, apellido, fechaNacimiento, dni, mail));
		
	
	}

/*--------------------------------------------------*/
	
	public Cliente traerCliente(long dni) {
		int indice=0;
		Cliente c = null;
		while(indice < this.getLstClientes().size() && c== null) {
			if(dni == lstClientes.get(indice).getDni()) {
				c = lstClientes.get(indice);
			}
			indice++;
		}
		return c;
	}
	
/*--------------------------------------------------*/
	
	public void modificarCliente(String nombre, String apellido, LocalDate fechaNacimiento, String mail, long dni) throws Exception{
		if(this.traerCliente(dni) == null) throw new Exception("Error: El cliente con este dni nunca fue cargado");
		
		this.traerCliente(dni).setNombre(nombre);
		this.traerCliente(dni).setApellido(apellido);
		this.traerCliente(dni).setFechaNacimiento(fechaNacimiento);
		this.traerCliente(dni).setMail(mail);
		
	}
	
/*--------------------------------------------------*/
	
	public boolean eliminarCliente(long dni) throws Exception{
		if(this.traerCliente(dni) == null) throw new Exception("Error: El cliente que se quiere eliminar no esta en el sistema.");
	
		return lstClientes.remove(this.traerCliente(dni));
		
	}


/*--------------------------------------------------*/

	/** 
	 *
	 * Metodos con la lista de sucursales. Agregar, traer, modificar y eliminar
	 * 
	 */
	public boolean agregarSucursal(String ciudad, String calle, int numero, float latitud, float longitud, long telefono, String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) throws Exception{
		Direccion d = new Direccion(ciudad,calle,numero,latitud,longitud);
		if(this.traerSucursal(d) != null) throw new Exception("La Sucursal que se quiere agregar ya esta en el sistema.");
		
		Gerente g = new Gerente(nombre,apellido,fechaNacimiento,dni,horasPorJornada,sueldoBasico);
		int idSucursal=1;
		if(!this.getLstSucursales().isEmpty()) {
			idSucursal=lstSucursales.get(lstSucursales.size()-1).getId()+1;
		}
		return lstSucursales.add(new Sucursal(d,idSucursal,telefono,g)); //si la sucursal es la primera, va con el id 1, caso contrario va con el ultimo id+1
		
	}
	
	/*--------------------------------------------------*/
	
	public Sucursal traerSucursal(Direccion ubicacion) {
		int indice=0;
		Sucursal s = null;
		while(indice < this.getLstSucursales().size() && s == null) {
			if(ubicacion.equals(lstSucursales.get(indice).getUbicacion())) {
				s = lstSucursales.get(indice);
			}
			indice++;
		}
		return s;
	}
	
	/*--------------------------------------------------*/
	
	public Sucursal traerSucursal(int id) {
		int indice=0;
		Sucursal s = null;
		while(indice < this.getLstSucursales().size() && s == null) {
			if(id == lstSucursales.get(indice).getId()){
				s = lstSucursales.get(indice);
			}
			indice++;
		}
		return s;
	}
	
	/*--------------------------------------------------*/
		
		public void modificarSucursal(String ciudad, String calle, int numero, float latitud, float longitud, long telefono, String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) throws Exception{
		Direccion d = new Direccion(ciudad,calle,numero,latitud,longitud);
		if(this.traerSucursal(d) == null) throw new Exception("La sucursal con esta ubicación nunca fue cargada");
		Gerente g = new Gerente(nombre,apellido,fechaNacimiento,dni,horasPorJornada,sueldoBasico);
		this.traerSucursal(d).setTelefono(telefono);
		this.traerSucursal(d).setGerente(g);
		}
	
		/*--------------------------------------------------*/
	
		public boolean eliminarSucursal(String ciudad, String calle, int numero, float latitud, float longitud) throws Exception{
			Direccion d = new Direccion(ciudad,calle,numero,latitud,longitud);
			if(this.traerSucursal(d) == null) throw new Exception("El cliente que se quiere eliminar no esta en el sistema.");
			
			return lstSucursales.remove(this.traerSucursal(d));
			
		}
		
		/*--------------------------------------------------*/
		
		public Gerente traerGerente(long dni) {
			int indice=0;
			Gerente g = null;
			while(indice < this.getLstSucursales().size() && g == null) {
				if(dni == lstSucursales.get(indice).getGerente().getDni()){
					g = lstSucursales.get(indice).getGerente();
				}
				indice++;
			}
			return g;
		}
		
		/*--------------------------------------------------*/
		
		public Sucursal traerSucursalMasCercana(Sucursal sucursal, Producto producto, int cantidad) {
			Sucursal sucMasCercana=this.traerSucursalesConStock(producto, cantidad).get(0);;
			float distancia=sucursal.calcularDistancia(this.traerSucursalesConStock(producto, cantidad).get(0));
			
			if(sucursal.equals(this.traerSucursalesConStock(producto, cantidad).get(0))) {
				distancia=sucursal.calcularDistancia(this.traerSucursalesConStock(producto, cantidad).get(1));
				sucMasCercana=this.traerSucursalesConStock(producto, cantidad).get(1);
			}
			
			for(int indice=0; indice<this.traerSucursalesConStock(producto, cantidad).size(); indice++) {
				if(sucursal.calcularDistancia(this.traerSucursalesConStock(producto, cantidad).get(indice)) < distancia && sucursal!=this.traerSucursalesConStock(producto, cantidad).get(indice))
				{
					distancia=sucursal.calcularDistancia(this.traerSucursalesConStock(producto, cantidad).get(indice));
					sucMasCercana=this.traerSucursalesConStock(producto, cantidad).get(indice);
				}
			}
			return sucMasCercana;
		}
		
		/*--------------------------------------------------*/
		
		public List<Sucursal> traerSucursalesConStock(Producto producto, int cantidad) {
			List<Sucursal> s = new ArrayList<Sucursal>();

			for(int i=0; i<lstSucursales.size(); i++) {
				if(lstSucursales.get(i).validarStock(producto, cantidad)) s.add(lstSucursales.get(i));
			}
			return s;
		}
		/*--------------------------------------------------*/
		
		public boolean agregarVenta(Vendedor vendedorEncargado, Cliente cliente, LocalDate fecha, LocalTime hora) {
			int numeroVenta=1;
			if(!lstVentas.isEmpty()) {
				numeroVenta=lstVentas.get(lstVentas.size()-1).getNumero()+1;
			}
			return lstVentas.add(new Venta(numeroVenta, vendedorEncargado, cliente, fecha, hora));
		}
		
		/*--------------------------------------------------*/
		
		public Venta traerVenta (int numero) {
			Venta v = null;
			int i=0;
			while (i<lstVentas.size() && v==null)
			{
				if (lstVentas.get(i).getNumero() == numero)
				{
					v = lstVentas.get(i);
				}
				i++;
			}
			v.calcularTotal();
			return v;
		}
		
		/*--------------------------------------------------*/
		
		public void generarPedidoConStockPropio (int numVenta, Producto producto, int cantidad) throws Exception
		{
			if (!producto.isStatus()) throw new Exception ("Error: El producto no está activo");
			this.traerVenta(numVenta).generarPedidoConStockPropio(producto, cantidad);
		}
		
		/*--------------------------------------------------*/
		
		public boolean generarPedidoConOtraSucursal(int numVenta, String descripcion, int cantidad, Sucursal sucursal, long dni) throws Exception {
			if (!this.traerProducto(descripcion).isStatus()) throw new Exception ("Error: El producto no está activo");
			
			sucursal.restarLotes(this.traerProducto(descripcion), cantidad);
			float plusVendedor=((this.traerProducto(descripcion).getPrecioUnitario()*3)/100)*cantidad;
			float plusColaborador=((this.traerProducto(descripcion).getPrecioUnitario()*2)/100)*cantidad;
			
			this.traerVenta(numVenta).getVendedorEncargado().setPlus(this.traerVenta(numVenta).getVendedorEncargado().getPlus()+plusVendedor);
			
			sucursal.traerVendedor(dni).setPlus(sucursal.traerVendedor(dni).getPlus()+plusColaborador);
			
			return this.traerVenta(numVenta).getLstPedidos().add(new Pedido(this.traerProducto(descripcion),cantidad,sucursal.traerVendedor(dni)));
		}
		
		/*--------------------------------------------------*/
		
		public int calcularCantidadProducto(Producto producto) {
			
			int cantidad=0;
			for(int indice=0;indice<lstVentas.size();indice++) {
				for (int indice2=0; indice2 < lstVentas.get(indice).getLstPedidos().size(); indice2++) {
					if (lstVentas.get(indice).getLstPedidos().get(indice2).getProducto().equals(producto))
						{
							cantidad += lstVentas.get(indice).getLstPedidos().get(indice2).getCantidad();
						}
				}
			}
			return cantidad;
		}
		
		/*--------------------------------------------------*/
		
		public List<Producto> rankingDeProductos (){
			//una clase que retorne el prod y la cantidad 
			
			List <Producto> rankingProductos = this.lstProductos;
			
			for (int i=0; i<rankingProductos.size()-1; i++) {
				for (int j=rankingProductos.size()-1; j>i; j--) {
					if (calcularCantidadProducto(rankingProductos.get(j-1)) < calcularCantidadProducto(rankingProductos.get(j))) {
						Producto t;
					    t = rankingProductos.get(i);
					    rankingProductos.set(i,rankingProductos.get(j));
					    rankingProductos.set(j,t);
					}
				}    
			}
			return rankingProductos;
		}
		
		/*--------------------------------------------------*/
		
		public List<RankingProductos> ranking() {
			List<RankingProductos> ranking = new ArrayList <RankingProductos>();
			
			for (int i = 0; i < this.rankingDeProductos().size() ; i++) {
				ranking.add(new RankingProductos(this.rankingDeProductos().get(i),this.calcularCantidadProducto(this.rankingDeProductos().get(i))));
			}
			return ranking;
		}
		
		/*--------------------------------------------------*/
		
		public List<Producto> productosVendidosPorFecha(int idSucursal, LocalDate fecha1, LocalDate fecha2){
			List<Producto> vendidos = new ArrayList<Producto>();
			for(int i=0; i<lstVentas.size(); i++) {
				if((lstVentas.get(i).getVendedorEncargado().getSucursal().equals(this.traerSucursal(idSucursal))) && 
						lstVentas.get(i).getFecha().isAfter(fecha1) && lstVentas.get(i).getFecha().isBefore(fecha2)) {
					for(int j=0; j<lstVentas.get(i).getLstPedidos().size(); j++) {
						vendidos.add(lstVentas.get(i).getLstPedidos().get(i).getProducto());
					}
				}
			}
			return vendidos;
		}
		
}

