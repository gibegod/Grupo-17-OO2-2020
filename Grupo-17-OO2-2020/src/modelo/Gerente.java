package modelo;

import java.time.LocalDate;

public class Gerente extends Empleado{

	public Gerente(String nombre, String apellido, LocalDate fechaNacimiento, long dni, int horasPorJornada,
			float sueldoBasico) {
		super(nombre, apellido, fechaNacimiento, dni, horasPorJornada, sueldoBasico);
	}

	@Override
	public String toString() {
		return "Gerente [horasPorJornada=" + horasPorJornada + ", sueldoBasico=" + sueldoBasico + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + ", dni=" + dni + "]";
	}

	
}
