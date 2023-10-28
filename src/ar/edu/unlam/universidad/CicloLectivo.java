package ar.edu.unlam.universidad;

import java.time.LocalDate;
import java.util.Objects;

public class CicloLectivo {
	private Integer id = 0;
	private LocalDate fechaInicio;
	private LocalDate fechaFinalizacion;
	private LocalDate fechaInicioInscripcion;
	private LocalDate fechaFinalizacionnscripcion;
	
	public CicloLectivo(Integer id, LocalDate fechaInicio, LocalDate fechaFinalizacion,
			LocalDate fechaInicioInscripcion, LocalDate fechaFinalizacionnscripcion) {
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFinalizacion = fechaFinalizacion;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinalizacionnscripcion = fechaFinalizacionnscripcion;
	}
	
	public Integer getId () {
		return this.id;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}

	public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}

	public LocalDate getFechaFinalizacionnscripcion() {
		return fechaFinalizacionnscripcion;
	}

	public void setFechaFinalizacionnscripcion(LocalDate fechaFinalizacionnscripcion) {
		this.fechaFinalizacionnscripcion = fechaFinalizacionnscripcion;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaFinalizacion, fechaFinalizacionnscripcion, fechaInicio, fechaInicioInscripcion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CicloLectivo other = (CicloLectivo) obj;
		return Objects.equals(fechaFinalizacion, other.fechaFinalizacion)
				&& Objects.equals(fechaFinalizacionnscripcion, other.fechaFinalizacionnscripcion)
				&& Objects.equals(fechaInicio, other.fechaInicio)
				&& Objects.equals(fechaInicioInscripcion, other.fechaInicioInscripcion);
	}

	@Override
	public String toString() {
		return "CicloLectivo [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion
				+ ", fechaInicioInscripcion=" + fechaInicioInscripcion + ", fechaFinalizacionnscripcion="
				+ fechaFinalizacionnscripcion + "]";
	}
	
	
	
	
	
}
