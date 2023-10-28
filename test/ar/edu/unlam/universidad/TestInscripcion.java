package ar.edu.unlam.universidad;

import static org.junit.Assert.*;

import java.security.DrbgParameters.Capability;
import java.time.LocalDate;

import org.junit.Test;

public class TestInscripcion {

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
	public Materia mg =  new Materia(idMainMateria++, "Matemática general");
	public Materia ig =  new Materia(idMainMateria++, "Informática general");
	public Materia pb1 =  new Materia(idMainMateria++, "Programación básica 1");
	public Materia it1 = new Materia(idMainMateria++, "Inglés técnico 1");
	public Materia it2 = new Materia(idMainMateria++, "Inglés técnico 2");
	public Materia bd1 = new Materia(idMainMateria++, "Base de Datos 1");
	
	LocalDate fic = LocalDate.of(2023, 3, 27);
	LocalDate ffc = LocalDate.of(2023, 12, 2);
	LocalDate fii = LocalDate.of(2023, 3, 6);
	LocalDate ffi = LocalDate.of(2023, 3, 14);
	public CicloLectivo ciclo = new CicloLectivo(0, fic, ffc, fii, ffi);
	
	LocalDate fic2 = LocalDate.of(2023, 3, 28);
	LocalDate ffc2 = LocalDate.of(2023, 12, 3);
	LocalDate fii2 = LocalDate.of(2023, 3, 7);
	LocalDate ffi2 = LocalDate.of(2023, 3, 15);
	public CicloLectivo ciclo2 = new CicloLectivo(1, fic2, ffc2, fii2, ffi2);
	
	public Aula aula = new Aula(0,30);
	public Aula aula2 = new Aula(1,25);
	public Aula aula3 = new Aula(2,20);
	public Aula aula4 = new Aula(3,15);
	
	
	@Test
	public void inscribirAlumnoAUnCurso() {
		// Valor esperado
		Integer cantidadDeAlumnosEsperadaEnElCurso = 1;
		
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		unlam.agregarMateria(pb1);
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		
		// inscripcion
		unlam.inscribirAlumnoAlCurso(dniAlumno, 0);
		
		// comprobación
		assertTrue(unlam.getCantidadInscriptosEnUnCursoPorid(0) == cantidadDeAlumnosEsperadaEnElCurso);
	}
	
	@Test
	public void queQueden28LugaresEnElAula() {
		Integer idCurso = 0;
		Integer capacidadOcupadaEsperada = 2;
		
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		unlam.agregarAlumno(dniAlumno2, nombreAlumno2, apellidoAlumno2, fn2);
		
		unlam.agregarMateria(pb1);
		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		
		unlam.inscribirAlumnoAlCurso(dniAlumno, idCurso);
		unlam.inscribirAlumnoAlCurso(dniAlumno2, idCurso);
		
		Curso curso = unlam.getCursoPorId(idCurso);
		assertTrue(curso.getAula().getOcupados() == capacidadOcupadaEsperada);
	}
	
	@Test
	public void queNoSePuedanInscribirSiNoHayLugar() {
		Integer idCurso = 0;
		Integer capacidadOcupadaEsperada = 1;
		
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		unlam.agregarAlumno(dniAlumno2, nombreAlumno2, apellidoAlumno2, fn2);
		
		unlam.agregarMateria(pb1);
		unlam.crearCurso(idCurso, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		
		unlam.inscribirAlumnoAlCurso(dniAlumno, idCurso);
		unlam.inscribirAlumnoAlCurso(dniAlumno2, idCurso);
		
		Curso curso = unlam.getCursoPorId(idCurso);
		assertFalse(curso.getAula().getOcupados() == capacidadOcupadaEsperada);
	}
	
	@Test
	public void queNoSePuedaInscribirSiYaEstaEnOtroCursoAlMismoTiempo() {
		Integer idCurso1 = 0;
		Integer idCurso2 = 1;
		
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
	
		unlam.agregarMateria(ig);
		unlam.agregarMateria(pb1);
		
		unlam.crearCurso(idCurso1, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		unlam.crearCurso(idCurso2, ig.getCodigo(), Turno.MANANA , ciclo, aula);
		
		boolean ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, idCurso1); 
		ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, idCurso2);
		
		assertFalse(ejecucion);
	}
	
	@Test
	public void inscribirseAInglesDos() {		
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		unlam.agregarMateria(it1);
		unlam.agregarMateria(it2);
		unlam.asignarMateriaCorrelativa(it2.getCodigo(), it1);
		
		unlam.crearCurso(0, it1.getCodigo(), Turno.MANANA , ciclo, aula);
		unlam.crearCurso(1, it2.getCodigo(), Turno.TARDE , ciclo, aula);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, 0);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dniAlumno, 10, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(it1.getCodigo(), dniAlumno, 8, TipoNota.SEGUNDO_PARCIAL);
		
		ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, 1);
		
		assertTrue(ejecucion);
	}
	
	@Test
	public void inscribirseBaseDeDatosUno() {
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		
		unlam.agregarMateria(ig);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(bd1);
		
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), ig);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), mg);
		
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		unlam.crearCurso(1, mg.getCodigo(), Turno.TARDE , ciclo, aula2);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE , ciclo2, aula3);
		unlam.crearCurso(3, bd1.getCodigo(), Turno.TARDE , ciclo2, aula4);
		
		unlam.inscribirAlumnoAlCurso(dniAlumno, 0);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 1);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 2);
		
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 4, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 4, TipoNota.SEGUNDO_PARCIAL);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, 3);
		
		assertTrue(ejecucion);
	}
	
	@Test
	public void queNoPuedaInscribirseABaseDeDatosUno() {
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		
		unlam.agregarMateria(ig);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(bd1);
		
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), ig);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), mg);
		
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		unlam.crearCurso(1, mg.getCodigo(), Turno.TARDE , ciclo, aula2);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE , ciclo2, aula3);
		unlam.crearCurso(3, bd1.getCodigo(), Turno.TARDE , ciclo2, aula4);
		
		unlam.inscribirAlumnoAlCurso(dniAlumno, 0);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 1);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 2);
		
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 3, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 3, TipoNota.SEGUNDO_PARCIAL);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, 3);
		
		assertFalse(ejecucion);
	}
	
	@Test
	public void queSePuedaInscribirUsandoRecuperatorio() {
		unlam.agregarAlumno(dniAlumno, nombreAlumno, apellidoAlumno, fn);
		
		unlam.agregarMateria(ig);
		unlam.agregarMateria(mg);
		unlam.agregarMateria(pb1);
		unlam.agregarMateria(bd1);
		
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), pb1);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), ig);
		unlam.asignarMateriaCorrelativa(bd1.getCodigo(), mg);
		
		unlam.crearCurso(0, pb1.getCodigo(), Turno.MANANA , ciclo, aula);
		unlam.crearCurso(1, mg.getCodigo(), Turno.TARDE , ciclo, aula2);
		unlam.crearCurso(2, ig.getCodigo(), Turno.NOCHE , ciclo2, aula3);
		unlam.crearCurso(3, bd1.getCodigo(), Turno.TARDE , ciclo2, aula4);
		
		unlam.inscribirAlumnoAlCurso(dniAlumno, 0);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 1);
		unlam.inscribirAlumnoAlCurso(dniAlumno, 2);
		
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(pb1.getCodigo(), dniAlumno, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(mg.getCodigo(), dniAlumno, 4, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 3, TipoNota.PRIMER_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 7, TipoNota.SEGUNDO_PARCIAL);
		unlam.asignarNotaAlAlumno(ig.getCodigo(), dniAlumno, 7, TipoNota.RECUP_PRIMER_PARCIAL);
		
		Boolean ejecucion = unlam.inscribirAlumnoAlCurso(dniAlumno, 3);
		
		assertTrue(ejecucion);
	}

}
