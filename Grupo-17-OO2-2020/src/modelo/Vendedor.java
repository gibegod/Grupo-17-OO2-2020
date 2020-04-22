package modelo;

import java.time.LocalDate;

public class Vendedor extends Empleado{

	private float plus;
	private Sucursal sucursal;

	public Vendedor(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico, Sucursal sucursal) {
		super(nombre, apellido, fechaNacimiento, dni, horasPorJornada, sueldoBasico);
		this.plus = 0;
		this.sucursal = sucursal;
	}

	public float getPlus() {
		return plus;
	}
	public void setPlus(float plus) {
		this.plus = plus;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public String toString() {
		return "Vendedor [nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", dni="
				+ dni + ", plus=" + plus + ", horasPorJornada=" + horasPorJornada + ", sueldoBasico=" + sueldoBasico + "]";
	}
}
