package ar.edu.unlam.universidad;

public class RegistroMateriasAlumno {
	private Alumno alumno;
	private Materia materia;
	private Nota nota;
	private TipoNota tipoNota;

	public RegistroMateriasAlumno(Alumno alumno, Materia materia, Nota nota, TipoNota tipoNota) {
		this.alumno = alumno;
		this.materia = materia;
		this.nota = nota;
		this.tipoNota = tipoNota;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public String getTipoNota() {
		return tipoNota.toString();
	}

	public void setTipoNota(TipoNota tipoNota) {
		this.tipoNota = tipoNota;
	}

}
