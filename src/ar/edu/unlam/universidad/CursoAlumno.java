package ar.edu.unlam.universidad;

public class CursoAlumno {
	private Alumno alumno;
	private Curso curso;
	private CondicionFinal condicion;
	
	public CursoAlumno(Alumno alumno, Curso curso) {
		this.alumno = alumno;
		this.curso = curso;
		this.condicion = CondicionFinal.CURSANDO;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public CondicionFinal getCondicion() {
		return condicion;
	}

	public void setCondicion(CondicionFinal condicion) {
		this.condicion = condicion;
	}
	
}
