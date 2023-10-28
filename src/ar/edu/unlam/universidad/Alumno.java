package ar.edu.unlam.universidad;

import java.time.LocalDate;
import java.util.Date;

public class Alumno {
	private Integer dni;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private LocalDate fechaInscripcion;
	
	public Alumno(Integer dni, String nombre, String apellido, LocalDate fechaNacimiento, LocalDate fechaInscripcion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaInscripcion = fechaInscripcion;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	
	
}
