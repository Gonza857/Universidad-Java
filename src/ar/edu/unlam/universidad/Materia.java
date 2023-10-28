package ar.edu.unlam.universidad;

import java.util.ArrayList;
import java.util.Objects;

public class Materia {
	private Integer codigo;
	private ArrayList<Materia> correlativas;
	private String nombre;

	public Materia(Integer codigo, String nombre) {
		this.codigo = codigo;
		this.correlativas = new ArrayList<>();
		this.nombre = nombre;
	}
	
	public Boolean agregarCorrelativa (Materia materia) {
		Boolean pudoAgregarse = false;
		if (!saberSiYaExisteLaCorrelativa(materia.getCodigo())) {
			correlativas.add(materia);
			pudoAgregarse = true;
		}
		
		return pudoAgregarse;
	}
	
	public Boolean eliminarCorrelativa (Materia materia) {
		Boolean pudoEliminarse = false;
		if (saberSiYaExisteLaCorrelativa(materia.getCodigo())) {
			Materia paraEliminar = getMateriaCorrelativaPorCodigo(materia.getCodigo());
			if (paraEliminar != null) {
				pudoEliminarse = this.correlativas.remove(paraEliminar);
				System.out.println("\n" + paraEliminar.getCodigo() + ") " 
						+ paraEliminar.getNombre() +
						" eliminada como correlativa.");
			}
			
		}
		return pudoEliminarse;
	}
	
	public Materia getMateriaCorrelativaPorCodigo (Integer codigo) {
		Materia materia = null;
		Integer indice = 0;
		while (indice < this.correlativas.size() && materia == null) {
			if (this.correlativas.get(indice).getCodigo().equals(codigo)) {
				materia = this.correlativas.get(indice);
			}
			indice++;
		}
		return materia;
	}
	
	public Boolean saberSiYaExisteLaCorrelativa (Integer codigo) {
		Boolean existe = false;
		Integer indice = 0;
		while (indice < this.correlativas.size() && !existe) {
			if (this.correlativas.get(indice).getCodigo().equals(codigo)) {
				existe = true;
			}
			indice++;
		}
		return existe;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Materia> getCorrelativas() {
		return correlativas;
	}
	
	public Integer getCantidadCorrelativas() {
		return this.correlativas.size();
	}

	public void setCorrelativas(ArrayList<Materia> correlativas) {
		this.correlativas = correlativas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	

}
