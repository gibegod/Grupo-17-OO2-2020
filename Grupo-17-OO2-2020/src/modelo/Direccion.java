package modelo;

public class Direccion {
	
	private String ciudad;
	private String calle;
	private int numero;
	private float latitud;
	private float longitud;
	
	
	public Direccion(String ciudad, String calle, int numero, float latitud, float longitud) {
		super();
		this.ciudad = ciudad;
		this.calle = calle;
		this.numero = numero;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public boolean equals(Direccion direccion)
	{
		return direccion.getLatitud() == latitud && direccion.getLongitud() == longitud;
	}

	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public float getLatitud() {
		return latitud;
	}


	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}


	public float getLongitud() {
		return longitud;
	}


	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}


	@Override
	public String toString() {
		return "Direccion [ciudad=" + ciudad + ", calle=" + calle + ", numero=" + numero + ", latitud=" + latitud
				+ ", longitud=" + longitud + "]";
	}
	
	
	
	
	

}
