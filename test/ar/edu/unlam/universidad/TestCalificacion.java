package ar.edu.unlam.universidad;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class TestCalificacion {

	public Universidad unlam = new Universidad("unlam");

	public String nombreAlumno = "Gonzalo", apellidoAlumno = "Ramos";
	public LocalDate fi = LocalDate.now();
	public LocalDate fn = LocalDate.of(2003, 10, 28);
	public Integer dniAlumno = 45400606;

	public String nombreAlumno2 = "Leonel", apellidoAlumno2 = "Ramos";
	public LocalDate fi2 = LocalDate.now();
	public LocalDate fn2 = LocalDate.of(1990, 6, 28);
	public Integer dniAlumno2 = 30450450;

	public Integer idMainMateria = 0;
	public Materia mg = new Materia(idMainMateria++, "Matemática general");
	public Materia ig = new Materia(idMainMateria++, "Informática general");
	public Materia pb1 = new Materia(idMainMateria++, "Programación básica 1");
	public Materia it1 = new Materia(idMainMateria++, "Inglés técnico 1");

	LocalDate fic = LocalDate.of(2023, 3, 27);
	LocalDate ffc = LocalDate.of(2023, 12, 2);
	LocalDate fii = LocalDate.of(2023, 3, 6);
	LocalDate ffi = LocalDate.of(2023, 3, 14);
	public CicloLectivo ciclo = new CicloLectivo(0, fic, ffc, fii, ffi);

	public Aula aula = new Aula(0, 30);

	@Test
	public void calificarAlAlumno() {
		Universidad unlam = new Universidad("UNLaM");
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(it1);
		unlam.agregarMateria(ig);

		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);

		TipoNota tipo = TipoNota.PRIMER_PARCIAL;

		Boolean ejecucion = unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 2, tipo);
//		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, tipo);
//		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 6, tipo);
//		unlam.asignarNotaAlAlumno(it1.getCodigo(), dniAlumno, 7, tipo);
//		
//		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 9, tipo2);
//		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 3, tipo2);
//		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 5, tipo2);
//		unlam.asignarNotaAlAlumno(it1.getCodigo(), dniAlumno, 7, tipo2);

		assertTrue(ejecucion);

		// verExamentesAprobados(unlam.getRegistrosNotasAlumno(dniAlumno, tipo));
		// verNotasDelAlumno(unlam.getRegistrosNotasAlumno(dniAlumno, tipo2));
	}

	@Test
	public void queElAlumnoTengaUnaNotaPromocionada() {
		Universidad unlam = new Universidad("UNLaM");
		unlam.agregarMateria(pb1);
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		TipoNota tipo = TipoNota.PRIMER_PARCIAL;
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, tipo);
		Integer notasPromocionadasEsperadas = 1;
		assertTrue(notasPromocionadasEsperadas == unlam.getRegistrosNotasAlumno(dniAlumno, tipo).size());
	}

	@Test
	public void queElAlumnoTengaUnPrimerPacialRegistrado() {
		Universidad unlam = new Universidad("UNLaM");
		unlam.agregarMateria(pb1);
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		TipoNota tipo = TipoNota.PRIMER_PARCIAL;
		Integer nota = 2;
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, nota, tipo);
		Integer ve = 1;
		assertTrue(ve == unlam.getRegistrosNotasAlumno(dniAlumno, tipo).size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void verNotasDelAlumno(ArrayList<RegistroMateriasAlumno> registros) {
		Integer indice = 1;
		System.out.println("\n Visualizando registros del alumno");
		for (RegistroMateriasAlumno registro : registros) {
			System.out.println(
					indice++ + ") " + registro.getMateria().getNombre() + ": " + registro.getNota().getValor());
		}
	}

	public void verExamentesAprobados(ArrayList<RegistroMateriasAlumno> registros) {
		Integer indice = 1;
		System.out.println("\n Visualizando registros aprobados del alumno");
		for (RegistroMateriasAlumno registro : registros) {
			if (registro.getNota().getValor() >= 4) {
				System.out.println(
						indice++ + ") " + registro.getMateria().getNombre() + ": " + registro.getNota().getValor());
			}
		}
	}

	public void verExamentesDesaprobados(ArrayList<RegistroMateriasAlumno> registros) {
		Integer indice = 1;
		System.out.println("\n Visualizando registros desaprobados del alumno");
		for (RegistroMateriasAlumno registro : registros) {
			if (registro.getNota().getValor() < 4) {
				System.out.println(
						indice++ + ") " + registro.getMateria().getNombre() + ": " + registro.getNota().getValor());
			}
		}
	}

	public void verExamentesPromocionados(ArrayList<RegistroMateriasAlumno> registros) {
		Integer indice = 1;
		System.out.println("\n Visualizando registros promocionados del alumno");
		for (RegistroMateriasAlumno registro : registros) {
			if (registro.getNota().getValor() >= 7) {
				System.out.println(
						indice++ + ") " + registro.getMateria().getNombre() + ": " + registro.getNota().getValor());
			}
		}
	}

}
