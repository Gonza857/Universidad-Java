package ar.edu.unlam.universidad;

import java.util.Objects;

public class Curso {

	private Integer idCurso;
	private Materia materiaAsignada;
	private Turno turno;
	private CicloLectivo ciclo;
	private Aula aula;

	public Curso(Integer idCurso, Materia materiaAsignada, Turno turno, CicloLectivo ciclo, Aula aula) {
		this.idCurso = idCurso;
		this.materiaAsignada = materiaAsignada;
		this.turno = turno;
		this.ciclo = ciclo;
		this.aula = aula;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Integer getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	public Materia getMateriaAsignada() {
		return materiaAsignada;
	}

	public void setMateriaAsignada(Materia materiaAsignada) {
		this.materiaAsignada = materiaAsignada;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public CicloLectivo getCiclo() {
		return ciclo;
	}

	public void setCiclo(CicloLectivo ciclo) {
		this.ciclo = ciclo;
	}


	

	

}
