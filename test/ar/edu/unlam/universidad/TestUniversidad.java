package ar.edu.unlam.universidad;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Test;

public class TestUniversidad {
	
	public Universidad unlam = new Universidad("unlam");
	
	public Integer idMainMateria = 0;
	public Materia mg =  new Materia(idMainMateria++, "Matemática general");
	public Materia ig =  new Materia(idMainMateria++, "Informática general");
	public Materia pb1 =  new Materia(idMainMateria++, "Programación básica 1");
	public Materia it1 = new Materia(idMainMateria++, "Inglés técnico 1");
	
	public Materia db1 =  new Materia(idMainMateria++, "Base de datos 1");
	public Materia pb2 =  new Materia(idMainMateria++, "Programación básica 2");
	public Materia pw1 =  new Materia(idMainMateria++, "Programación web 1");
	public Materia idgw =  new Materia(idMainMateria++, "Introducción al diseño gráfico en la web 2");
	public Materia it2 =  new Materia(idMainMateria++, "Inglés técnico 2");

	// CICLIO, 27 DE MARZO AL 15 DE JULIO
	// INSCRIPCION, 6 DE MARZO AL 13 DE MARZO
	LocalDate fic = LocalDate.of(2023, 3, 27);
	LocalDate ffc = LocalDate.of(2023, 12, 2);
	LocalDate fii = LocalDate.of(2023, 3, 6);
	LocalDate ffi = LocalDate.of(2023, 3, 13);
	public final CicloLectivo c1_2023 = new CicloLectivo(0, fic, ffc, fii, ffi);

	// CICLIO, 14 DE AGOSTO AL 2 DE DICIEMBRE
	// INSCRIPCION, 31 DE JULIO A 3 DE AGOSTO
	LocalDate fic2 = LocalDate.of(2023, 8, 14);
	LocalDate ffc2 = LocalDate.of(2023, 12, 2);
	LocalDate fii2 = LocalDate.of(2023, 7, 31);
	LocalDate ffi2 = LocalDate.of(2023, 8, 3);
	public final CicloLectivo c2_2023 = new CicloLectivo(0, fic2, ffc2, fii2, ffi2);

	public Aula aula = new Aula(0, 60);
	public Aula aula2 = new Aula(1, 60);
	
	
	@Test
	public void queNoSePuedanAgregarDosConElMismoCodigo() {
		Universidad unlam = new Universidad("Unlam");
		Integer codigoMateria = 1000;
		String nombreMateria = "Programación Básica 1";
		boolean ejecicion = unlam.agregarMateria(codigoMateria, nombreMateria);
		ejecicion = unlam.agregarMateria(codigoMateria, nombreMateria);
		assertTrue(!ejecicion);
	}
	
	@Test
	public void agregarMateriaCorrelativa() {
		Universidad unlam = new Universidad("Unlam");		
		boolean ejecucion = unlam.agregarMateria(pb2);
		ejecucion = unlam.agregarMateria(pb1);
		ejecucion = unlam.asignarMateriaCorrelativa(pb2.getCodigo(), pb1);
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSePuedaAgregarCorrelativaSiNoEstaRegistrada() {
		Universidad unlam = new Universidad("Unlam");
		Integer codMateria = 1000;
		String nombreMateria = "Programación Básica 1";
		
		Integer codMateria2 = 1001;
		String nombreMateria2 = "Programación Básica 2";
		Materia materia = new Materia(codMateria, nombreMateria);
		
		boolean ejecucion = unlam.agregarMateria(codMateria2, nombreMateria2);
		ejecucion = unlam.asignarMateriaCorrelativa(codMateria2, materia);
		assertFalse(ejecucion);
	}
	
	@Test
	public void eliminarUnaMateriaCorrelativa() {
		Universidad unlam = new Universidad("Unlam");
		Integer codMateria = 1000;
		String nombreMateria = "Programación Básica 1";
		
		Integer codMateria2 = 1001;
		String nombreMateria2 = "Programación Básica 2";
		Materia materia = new Materia(codMateria, nombreMateria);
		
		boolean ejecucion = unlam.agregarMateria(codMateria2, nombreMateria2);
		ejecucion = unlam.agregarMateria(codMateria, nombreMateria);
		
		ejecucion = unlam.asignarMateriaCorrelativa(codMateria2, materia);
		ejecucion = unlam.eliminarCorrelativaDeUnaMateria(codMateria2, materia);
		
		assertTrue(ejecucion);
	}
	
	@Test
	public void registrarAlumnoALaUniversidad() {
		Universidad unlam = new Universidad("Unlam");
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		boolean ejecucion = unlam.agregarAlumno(dni, nombre, apellido, fn);
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSePuedranAgregarDosAlumnosConElMismoDNI() {
		Universidad unlam = new Universidad("Unlam");
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		boolean ejecucion = unlam.agregarAlumno(dni, nombre, apellido, fn);
		ejecucion = unlam.agregarAlumno(dni, "Leonel", "Ramos", fn);
		assertFalse(ejecucion);
	}
	
	@Test
	public void crearCicloLectivo() {
		Universidad unlam = new Universidad("Unlam");
		LocalDate fic = LocalDate.of(2023, 3, 27);
		LocalDate ffc = LocalDate.of(2023, 12, 2);
		LocalDate fii = LocalDate.of(2023, 3, 6);
		LocalDate ffi = LocalDate.of(2023, 3, 14);
		
		boolean ejecucion = unlam.crearCiclo(fic, ffc, fii, ffi);
	
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSePuedanCrearDosCiclosIguales() {
		Universidad unlam = new Universidad("Unlam");
		LocalDate fic = LocalDate.of(2023, 3, 27);
		LocalDate ffc = LocalDate.of(2023, 12, 2);
		LocalDate fii = LocalDate.of(2023, 3, 6);
		LocalDate ffi = LocalDate.of(2023, 3, 14);
		
		boolean ejecucion = unlam.crearCiclo(fic, ffc, fii, ffi);
		ejecucion = unlam.crearCiclo(fic, ffc, fii, ffi);
	
		assertFalse(ejecucion);
	}
	
	@Test
	public void crearCurso() {
		Universidad unlam = new Universidad("Unlam");
		LocalDate fic = LocalDate.of(2023, 3, 27);
		LocalDate ffc = LocalDate.of(2023, 12, 2);
		LocalDate fii = LocalDate.of(2023, 3, 6);
		LocalDate ffi = LocalDate.of(2023, 3, 14);
		
		Integer codigoComision = 2666;
		CicloLectivo ciclo = new CicloLectivo(0, fic, ffc, fii, ffi);
		Aula aula = new Aula(1,30);
		Turno turno = Turno.MANANA;
		
		boolean ejecucion = unlam.crearCurso(codigoComision, pb1.getCodigo(), turno, ciclo, aula);
	
		assertNotNull(ejecucion);
	}
	
	@Test
	public void queNoSePuedanCrearDosCursosIguales () {
		Universidad unlam = new Universidad("Unlam");
		LocalDate fic = LocalDate.of(2023, 3, 27);
		LocalDate ffc = LocalDate.of(2023, 12, 2);
		LocalDate fii = LocalDate.of(2023, 3, 6);
		LocalDate ffi = LocalDate.of(2023, 3, 14);
		
		Integer codigoComision = 2666;
		CicloLectivo ciclo = new CicloLectivo(0, fic, ffc, fii, ffi);
		Aula aula = new Aula(1,30);
		Turno turno = Turno.MANANA;
		
		boolean ejecucion = unlam.crearCurso(codigoComision, pb1.getCodigo(), turno, ciclo, aula);
		ejecucion = unlam.crearCurso(codigoComision, pb1.getCodigo(), turno, ciclo, aula);
		System.out.println("Cursos actuales creados: " + unlam.getCantidadDeCursos());
		assertFalse(ejecucion);
	}
	
	@Test
	public void registrarNotaCorrectamenteAlAlumno () {
		Integer nota = 9;
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.agregarMateria(pb1);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Boolean ejecucion = unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, nota, TipoNota.PRIMER_PARCIAL);
		assertTrue(ejecucion);
	}
	
	@Test
	public void inscribirAlumnoAlCursoEnUnCursoQueRequiereCorrelativaAprobada () {
		Integer nota = 9;
		// inscripción alumno
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		
		// inscripcion materia
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(pb2);
		
		// asignacion correlativa
		unlam.asignarMateriaCorrelativa(pb2.getCodigo(), pb1);
	

		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, pb2.getCodigo(), Turno.MANANA, c2_2023, aula);
		
		// calificacion 
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, nota, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, nota, TipoNota.SEGUNDO_PARCIAL);
		
		// cambio de fecha para inscribirlo al segundo cuatri
		unlam.setFechaActual(LocalDate.of(2023, 8, 1));
		
		// inscripcion del alumno al curso
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 1);

		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSePuedaInscribirSiNoApruebaLasCorrelativas () {
		// inscripción alumno
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		
		// inscripcion materia
		unlam.agregarMateria(mg);
		unlam.agregarMateria(ig);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(db1);
		
		// asignacion correlativa
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), ig);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), mg);
		
		// crear curso
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, mg.getCodigo(), Turno.TARDE, c1_2023, aula);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE, c1_2023, aula);
		unlam.crearCurso(3, db1.getCodigo(), Turno.MANANA, c2_2023, aula);
		
		// inscripciones para aprobar
		unlam.inscribirAlumnoAlCurso(dni, 0);
		unlam.inscribirAlumnoAlCurso(dni, 1);
		unlam.inscribirAlumnoAlCurso(dni, 2);
		
		// calificacion 
		Integer notaAprobar = 7;
		Integer notaDesaprobar = 3;
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaDesaprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		// inscripcion del alumno al curso
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 3);

		assertFalse(ejecucion);
	}
	
	@Test
	public void inscribirAlumnoAlCursoHabiendoAprobadoTresCorrelativas () {		
		// inscripción alumno
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		
		// inscripcion materia
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(ig);
		unlam.agregarMateria(db1);
		
		// asignacion correlativa
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), mg);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), ig);

		unlam.crearCurso(0, db1.getCodigo(), Turno.MANANA, c2_2023, aula);
		unlam.crearCurso(1, pb1.getCodigo(), Turno.TARDE, c1_2023, aula);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE, c1_2023, aula);
		unlam.crearCurso(3, mg.getCodigo(), Turno.MANANA, c1_2023, aula);
		
		// calificacion 
		Integer notaAprobar = 7;
		Integer notaDesaprobar = 3;
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaDesaprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		// inscripcion del alumno al curso
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 3);

		assertTrue(ejecucion);
	}
	
	@Test
	public void verMateriasAprobadasParaUnAlumno () {
		// inscripción alumno
		Integer dni = 45400606;
		String nombre = "Gonzalo", apellido = "Ramos";
		LocalDate fn = LocalDate.of(2003, 10, 28);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		
		// inscripcion materia
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(ig);
		unlam.agregarMateria(db1);
		
		// asignacion correlativa
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), mg);
		unlam.asignarMateriaCorrelativa(db1.getCodigo(), ig);

		unlam.crearCurso(0, db1.getCodigo(), Turno.MANANA, c2_2023, aula);
		unlam.crearCurso(1, pb1.getCodigo(), Turno.TARDE, c1_2023, aula);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE, c1_2023, aula);
		unlam.crearCurso(3, mg.getCodigo(), Turno.MANANA, c1_2023, aula);
		
		// calificacion 
		Integer notaAprobar = 7;
		Integer notaDesaprobar = 3;
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, notaDesaprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, notaAprobar, TipoNota.SEGUNDO_PARCIAL);
		
		// inscripcion del alumno al curso
		ArrayList<Materia> materiasAprobadasDeUnAlumno = unlam.getListaDeMateriasAprobadasDeUnAlumno(dni);
		Integer vo = materiasAprobadasDeUnAlumno.size();
		Integer ve = 2;
		verMaterias("Aprobadas", materiasAprobadasDeUnAlumno);

		assertTrue(ve.equals(vo));
	}
	
	@Test
	public void crearAula () {
		Universidad unlam = new Universidad("Unlam");
		Integer capacidadAula = 30, nro = 1;
		Boolean ejecucion = unlam.crearAula(nro, capacidadAula);
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSePuedaCrearAulaConElMismoNro () {
		Universidad unlam = new Universidad("Unlam");
		Integer capacidadAula = 30, nro = 1;
		Boolean ejecucion = unlam.crearAula(nro, capacidadAula);
		ejecucion = unlam.crearAula(nro, capacidadAula);
		assertFalse(ejecucion);
	}
	
	@Test
	public void queNoSePuedaAgregarMasAlumnosDeLaCapacidadDelAula () {
		Integer contadorAgregados = 0;
		Boolean ejecucion = false;
		
		Integer capacidadAula = 30, nro = 1;
		unlam.crearAula(nro, capacidadAula);
		Aula estaAula = unlam.getAulaPorNro(nro);
		
		unlam.agregarMateria(pb1);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, estaAula);
		unlam.crearCurso(1, pb1.getCodigo(), Turno.TARDE, c1_2023, estaAula);
		
		
		for (int i = 1; i < 35; i++) {
			unlam.agregarAlumno(i, "Memo", "Memi", LocalDate.now());
		}
		for (int i = 1; i < 35; i++) {
			ejecucion = unlam.inscribirAlumnoAlCurso(unlam.getAlumnoPorDni(i).getDni(), 0);
			if (!ejecucion) {
				i = 29;
				System.out.println("Alumno con DNI '" + unlam.getAlumnoPorDni(i).getDni()
								+ "' no agregado por falta de espacio en el aula");
				ejecucion = false;
				break;
			}
			contadorAgregados++;
			System.out.println(unlam.getAulaPorNro(nro).getOcupados());
		}		
		
		assertFalse(ejecucion);
		
	}
	
	private void verMaterias (String texto, ArrayList<Materia> materias) {
		System.out.println("\n" + texto);
		int indice = 1;
		for (Materia materia: materias) {
			System.out.println(indice + ") " + materia.getNombre());
			indice++;
		}
	}
	
}
