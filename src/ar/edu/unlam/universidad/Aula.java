package ar.edu.unlam.universidad;

public class Aula {
	private Integer nro;
	private Integer capacidad;
	private Integer ocupados;
	
	public Aula(Integer nro, Integer capacidad) {
		this.nro = nro;
		this.capacidad = capacidad;
		this.ocupados = 0;
	}

	public Integer getNro() {
		return nro;
	}

	public void setNro(Integer nro) {
		this.nro = nro;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public Integer getOcupados() {
		return ocupados;
	}

	public void setOcupados(Integer ocupados) {
		this.ocupados += ocupados;
	}
	
	
	
	
}
