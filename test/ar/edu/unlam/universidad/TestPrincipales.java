package ar.edu.unlam.universidad;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

public class TestPrincipales {
	
	// ESTA CLASE ES PARA TESTEAR LOS REQUIRIMIENTOS PRINCIPALES DADOS EN LA CONSIGNA
	// Los tests pertenecientes a los metodos de cada clase estan en sus respecticas Test Cases

	public Universidad unlam = new Universidad("unlam");

	public Integer idMainMateria = 0;
	public Materia mg = new Materia(idMainMateria++, "Matemática general");
	public Materia ig = new Materia(idMainMateria++, "Informática general");
	public Materia pb1 = new Materia(idMainMateria++, "Programación básica 1");
	public Materia it1 = new Materia(idMainMateria++, "Inglés técnico 1");

	public Materia it2 = new Materia(idMainMateria++, "Inglés técnico 2");
	public Materia pw1 = new Materia(idMainMateria++, "Programación web 1");
	public Materia bd1 = new Materia(idMainMateria++, "Base de Datos 1");
	public Materia pb2 = new Materia(idMainMateria++, "Programación Básica 2");
	public Materia idgw = new Materia(idMainMateria++, "Introducción al diseño grafico en la web");

	public Materia pw2 = new Materia(idMainMateria++, "Programación web 2");
	public Materia daw = new Materia(idMainMateria++, "Diseño de aplicaciones web");
	public Materia vei = new Materia(idMainMateria++, "Visualización e interfaces");
	public Materia tw1 = new Materia(idMainMateria++, "Taller web 1");

	public Materia bd2 = new Materia(idMainMateria++, "Base de Datos 2");
	public Materia pw3 = new Materia(idMainMateria++, "Programación web 3");
	public Materia tr = new Materia(idMainMateria++, "Teconlogía de redes");
	public Materia tw2 = new Materia(idMainMateria++, "Taller web 2");
	public Materia scaw = new Materia(idMainMateria++, "Seguridad y Calidad en Aplicaciones Web");

	public Materia iap = new Materia(idMainMateria++, "Introducción a la administracion de Proyectos");
	public Materia tpi = new Materia(idMainMateria++, "Taller práctico integrador");

	public Boolean agregarMateriasDefault() {
		Integer totalMaterias = 20;
		this.unlam.agregarMateria(mg);
		this.unlam.agregarMateria(ig);
		this.unlam.agregarMateria(pb1);
		this.unlam.agregarMateria(it1);

		this.unlam.agregarMateria(it2);
		this.unlam.agregarMateria(pb2);
		this.unlam.agregarMateria(bd1);
		this.unlam.agregarMateria(idgw);
		this.unlam.agregarMateria(pw1);

		this.unlam.agregarMateria(pw2);
		this.unlam.agregarMateria(daw);
		this.unlam.agregarMateria(vei);
		this.unlam.agregarMateria(tw1);

		this.unlam.agregarMateria(bd2);
		this.unlam.agregarMateria(pw3);
		this.unlam.agregarMateria(tr);
		this.unlam.agregarMateria(tw2);
		this.unlam.agregarMateria(scaw);

		this.unlam.agregarMateria(iap);
		this.unlam.agregarMateria(tpi);

		if (unlam.getCantidadMateriasRegistradas().equals(totalMaterias)) {
			return true;
		} else
			return false;
	}

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
	public void queHayaUnaMateriaRegistrada() {
		unlam.agregarMateria(pb1);
		Integer obtenido = unlam.getCantidadMateriasRegistradas();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void queHayaUnAlumnoRegistrado() {
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		Integer obtenido = unlam.getCantidadDeAlumnosRegistrados();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void queNoSeRegistreAlumnoConMismoDNI() {
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		Integer obtenido = unlam.getCantidadDeAlumnosRegistrados();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void queSeCreeUnCicloLectivo() {
		LocalDate fechaInicio = LocalDate.of(2003, 10, 28), fechaFinalizacion = LocalDate.of(2023, 11, 30),
				fechaInicioInscripcion = LocalDate.of(2003, 10, 28),
				fechaFinalizacionInscripcion = LocalDate.of(2023, 11, 30);
		unlam.crearCiclo(fechaInicio, fechaFinalizacion, fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Integer obtenido = unlam.getCantidadCiclosCreados();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void queNoSeAgregeUnCicloConElMismoID() {
		LocalDate fechaInicio = LocalDate.of(2003, 10, 28), fechaFinalizacion = LocalDate.of(2023, 11, 30),
				fechaInicioInscripcion = LocalDate.of(2003, 10, 28),
				fechaFinalizacionInscripcion = LocalDate.of(2023, 11, 30);
		unlam.crearCiclo(fechaInicio, fechaFinalizacion, fechaInicioInscripcion, fechaFinalizacionInscripcion);
		unlam.crearCiclo(fechaInicio, fechaFinalizacion, fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Integer obtenido = unlam.getCantidadCiclosCreados();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void crearUnCurso() {
		unlam.agregarMateria(pb1);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Integer obtenido = unlam.getCantidadDeCursos();
		Integer ve = 1;
		assertTrue(ve.equals(obtenido));
	}

	@Test
	public void queNoSeCreeUnCursoConMismoIDyCiclo() {
		unlam.agregarMateria(pb1);
		Boolean ejecucion = unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		ejecucion = unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		assertFalse(ejecucion);
	}

	@Test
	public void queNoSeCreeUnCursoConMismoCiclo() {
		unlam.agregarMateria(pb1);
		Boolean ejecucion = unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		ejecucion = unlam.crearCurso(1, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		assertFalse(ejecucion);
	}

	@Test
	public void queNoSeCreeUnCursoConMismoID() {
		unlam.agregarMateria(pb1);
		Boolean ejecucion = unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		ejecucion = unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c2_2023, aula);
		assertFalse(ejecucion);
	}

	@Test
	public void queSeCreeUnDocente() {
		Integer ve = 1;
		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		unlam.agregarDocente(docente);
		assertTrue(ve == unlam.getCantidadDocentesRegistrados());
	}

	@Test
	public void queNoSeAgregeUnDocenteConElMismoDni() {
		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		Docente docente2 = unlam.crearDocente("Dario", "Ramos", 18078704);
		Boolean ejecucion = unlam.agregarDocente(docente);
		ejecucion = unlam.agregarDocente(docente2);
		assertFalse(ejecucion);
	}

	@Test
	public void asignarDocenteAlCurso() {
		unlam.agregarMateria(pb1);
		LocalDate fn = LocalDate.of(2003, 10, 28);
		Integer idCurso = 0;
		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Integer cantidadARegistrar = 20;
		for (int i = 0; i < cantidadARegistrar; i++) {
			unlam.agregarAlumno((45400600 + i), ("Nombre" + i), ("Apellido" + i), fn);
			unlam.inscribirAlumnoAlCurso((45400599 + i), idCurso);
		}

		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		unlam.agregarDocente(docente);

		Boolean ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		assertTrue(ejecucion);

	}

	@Test
	public void queNoSeAsigneDocentePorEstarEnElMismoCursoYa() {
		unlam.agregarMateria(pb1);
		LocalDate fn = LocalDate.of(2003, 10, 28);
		Integer idCurso = 0;
		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Integer cantidadARegistrar = 20;
		for (int i = 0; i < cantidadARegistrar; i++) {
			unlam.agregarAlumno((45400600 + i), ("Nombre" + i), ("Apellido" + i), fn);
			unlam.inscribirAlumnoAlCurso((45400599 + i), idCurso);
		}

		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		unlam.agregarDocente(docente);

		Boolean ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		assertFalse(ejecucion);

	}

	@Test
	public void queElDocenteEsteEnUnCursoPeroQueSeAsigneAOtroDistinto() {
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(mg);

		LocalDate fn = LocalDate.of(2003, 10, 28);
		Integer idCurso = 0;
		Integer idCurso2 = 1;

		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(idCurso2, mg.getCodigo(), Turno.NOCHE, c1_2023, aula);

		Integer cantidadARegistrar = 20;
		for (int i = 0; i < cantidadARegistrar; i++) {
			unlam.agregarAlumno((45400600 + i), ("Nombre" + i), ("Apellido" + i), fn);
			unlam.inscribirAlumnoAlCurso((45400599 + i), idCurso);
		}

		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		unlam.agregarDocente(docente);

		Boolean ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso2);

		assertTrue(ejecucion);
	}

	@Test
	public void asignarDosDocentesAlCurso() {
		unlam.agregarMateria(pb1);
		LocalDate fn = LocalDate.of(2003, 10, 28);
		Integer idCurso = 0;

		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Integer cantidadARegistrar = 35;
		for (int i = 0; i < cantidadARegistrar; i++) {
			unlam.agregarAlumno((45400600 + i), ("Nombre" + i), ("Apellido" + i), fn);
			unlam.inscribirAlumnoAlCurso((45400599 + i), idCurso);
		}

		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		Docente docente2 = unlam.crearDocente("Liliana", "Ramos", 18089228);
		unlam.agregarDocente(docente);
		unlam.agregarDocente(docente2);

		Boolean ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		ejecucion = unlam.asignarCursoDocente(docente2.getDni(), idCurso);
		assertTrue(ejecucion);
	}

	@Test
	public void asignarTresDocentesAlCurso() {
		unlam.agregarMateria(pb1);
		LocalDate fn = LocalDate.of(2003, 10, 28);
		Integer idCurso = 0;

		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Integer cantidadARegistrar = 50;
		for (int i = 0; i < cantidadARegistrar; i++) {
			unlam.agregarAlumno((45400600 + i), ("Nombre" + i), ("Apellido" + i), fn);
			unlam.inscribirAlumnoAlCurso((45400599 + i), idCurso);
		}

		Docente docente = unlam.crearDocente("Dario", "Ramos", 18078704);
		Docente docente2 = unlam.crearDocente("Liliana", "Ramos", 18089228);
		Docente docente3 = unlam.crearDocente("Leonel", "Ramos", 30123123);
		unlam.agregarDocente(docente);
		unlam.agregarDocente(docente2);
		unlam.agregarDocente(docente3);

		Boolean ejecucion = unlam.asignarCursoDocente(docente.getDni(), idCurso);
		ejecucion = unlam.asignarCursoDocente(docente2.getDni(), idCurso);
		ejecucion = unlam.asignarCursoDocente(docente3.getDni(), idCurso);
		assertTrue(ejecucion);
	}

	@Test
	public void agregarCorrelativa() {
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(bd1);
		Boolean ejecucion = unlam.asignarMateriaCorrelativa(bd1.getCodigo(), pb1);
		assertTrue(ejecucion);
	}

	@Test
	public void eliminarCorrelativa() {
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(bd1);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), pb1);
		unlam.eliminarCorrelativaDeUnaMateria(bd1.getCodigo(), pb1);
		Integer ve = 0;
		Integer correlativasBaseDeDatos = unlam
				.getCantidadDeCorrelativasDeUnaMateriaPorId(bd1.getCantidadCorrelativas());
		assertTrue(correlativasBaseDeDatos == ve);
	}
	
	@Test
	public void inscribirCorrectamenteUnAlumnoAunaMateria() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 0);
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoSeInscribaPorNoAprobarLaCorrelativaConMasDeCuatro() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.asignarMateriaCorrelativa(pb2.getCodigo(), pb1);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, pb2.getCodigo(), Turno.MANANA, c2_2023, aula);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 0);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		ejecucion = unlam.inscribirAlumnoAlCurso(dni, 1);
		assertFalse(ejecucion);
	}
	
	@Test
	public void queNoSeInscribaPorEstarFueraDeLaFecha() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		
		// Adelantamos fecha para que las inscripciones ya esten vencidas, ademas es fecha
		// de iniciar cursadas
		unlam.setFechaActual(LocalDate.of(2023, 4, 1));
		
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 0);

		assertFalse(ejecucion);
	}
	
	@Test
	public void queNoSeInscribaSiElAulaEstaLlena() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		
		// Establecemos la capacidad del aula en 0 para que no se pueda inscribir
		aula.setCapacidad(0);
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 0);
		assertFalse(ejecucion);
	}
	
	@Test
	public void queNoSeInscribaSiYaCursaOtraMateriaEnElMismoHorario() {
		if (agregarMateriasDefault()) {
			System.out.println("\n# -- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, mg.getCodigo(), Turno.MANANA, c1_2023, aula2);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dni, 0);
		ejecucion = unlam.inscribirAlumnoAlCurso(dni, 1);
		
		assertFalse(ejecucion);
	}
	
	

	@Test
	public void verMateriasAprobadasParaUnAlumno() {
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(ig);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(it1);

		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, it1.getCodigo(), Turno.TARDE, c1_2023, aula);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE, c1_2023, aula);
		unlam.crearCurso(3, mg.getCodigo(), Turno.MANANA, c2_2023, aula);

		unlam.inscribirAlumnoAlCurso(dni, 0);
		unlam.inscribirAlumnoAlCurso(dni, 1);
		unlam.inscribirAlumnoAlCurso(dni, 2);
		unlam.inscribirAlumnoAlCurso(dni, 3);

		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);

		verMateriasAprobadas(unlam.obtenerListadoDeMateriasAprobadasParaUnAlumno(dni), unlam.getAlumnoPorDni(dni));
	}

	@Test
	public void verMateriasQueLeFaltanAlAlumnoPorAprobar() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(ig);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(it1);

		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);
		unlam.crearCurso(1, it1.getCodigo(), Turno.TARDE, c1_2023, aula);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE, c1_2023, aula);
		unlam.crearCurso(3, mg.getCodigo(), Turno.MANANA, c2_2023, aula);

		unlam.inscribirAlumnoAlCurso(dni, 0);
		unlam.inscribirAlumnoAlCurso(dni, 1);
		unlam.inscribirAlumnoAlCurso(dni, 2);
		unlam.inscribirAlumnoAlCurso(dni, 3);

		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dni, 7, TipoNota.SEGUNDO_PARCIAL);
		verMateriasRestantes(unlam.obtenerMateriasQueFaltanCursasParaUnAlumno(dni));
	}
	
	@Test
	public void verReporteDeNotasDeUnCurso() {
		if (agregarMateriasDefault()) {
			System.out.println("\n#-- Materias agregadas");
		} else
			System.out.println("#-- Materias no agregadas");
		
		// alumno
		Integer dni = 45400606;
		LocalDate fn = LocalDate.of(2003, 10, 28);
		String nombre = "Gonzalo", apellido = "Ramos";
		// alumno
		Integer dni2 = 45400607;
		LocalDate fn2 = LocalDate.of(2003, 10, 29);
		String nombre2 = "Favio", apellido2 = "Gomez";
		// alumno
		Integer dni3 = 45400608;
		LocalDate fn3 = LocalDate.of(2003, 10, 30);
		String nombre3 = "Enzo", apellido3 = "Perez";
		
		unlam.agregarAlumno(dni, nombre, apellido, fn);
		unlam.agregarAlumno(dni2, nombre2, apellido2, fn2);
		unlam.agregarAlumno(dni3, nombre3, apellido3, fn3);

		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA, c1_2023, aula);

		unlam.inscribirAlumnoAlCurso(dni, 0);
		unlam.inscribirAlumnoAlCurso(dni2, 0);
		unlam.inscribirAlumnoAlCurso(dni3, 0);

		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 10, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni, 8, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni2, 3, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni2, 9, TipoNota.SEGUNDO_PARCIAL);
		
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni3, 10, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dni3, 4, TipoNota.SEGUNDO_PARCIAL);

		ArrayList <RegistroMateriasAlumno> reportes = unlam.obetenerReporteDeNotasDeAlumnosDeUnCurso(0);
		Integer ve = unlam.getCantidadDeNotasRegistradasDeUnCurso(0);
		verReporteDeNotas(reportes, 0);
		assertTrue(ve == reportes.size());
	}
	
	// METODOS PARA VER EN CONSOLA RESULTADOS
	public static void verReporteDeNotas(ArrayList<RegistroMateriasAlumno> registros, Integer idCurso) {
		System.out.println("\n#-- Visualizando las materias aprobadas del alumno.");
		System.out.println("\n DNI | Nombre | Apellido | Cod_Mat | Nota | Tipo | Materia");
		for (RegistroMateriasAlumno registro : registros) {
			System.out.println(
					idCurso + " | " 
					+ registro.getAlumno().getNombre() + " | "
					+ registro.getAlumno().getApellido() + " | " 
					+ registro.getMateria().getCodigo() + " | "
					+ registro.getNota().getValor() + " | " + registro.getTipoNota() + " | "
					+ registro.getMateria().getNombre());
		}
	}
	
	public static void verMateriasRestantes(ArrayList<Materia> materias) {
		System.out.println("\n#-- Visualizando las materias que faltan aprobar.");
		for (Materia materia : materias) {
			System.out.println(materia.getNombre());
		}
	}

	public static void verMateriasAprobadas(ArrayList<RegistroMateriasAlumno> registros, Alumno alumno) {
		System.out.println("\n#-- Visualizando las materias aprobadas del alumno.");
		System.out.println("\n DNI | Nombre | Apellido | Cod_Mat | Nota | Tipo | Materia");
		for (RegistroMateriasAlumno registro : registros) {
			System.out.println(registro.getAlumno().getDni() + " | " + registro.getAlumno().getNombre() + " | "
					+ registro.getAlumno().getApellido() + " | " + registro.getMateria().getCodigo() + " | "
					+ registro.getNota().getValor() + " | " + registro.getTipoNota() + " | "
					+ registro.getMateria().getNombre());
		}
	}

	public static void verCursoDocente(ArrayList<CursoDocente> asignaciones) {
		System.out.println("\n#-- Visualizando asignaciones CursoDocente.");
		for (CursoDocente asignacion : asignaciones) {
			System.out.println(asignacion.getDocente().getDni() + " | " + asignacion.getCurso().getIdCurso());
		}
	}

}
