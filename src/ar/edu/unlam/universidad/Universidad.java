package ar.edu.unlam.universidad;

import java.time.LocalDate;
import java.util.ArrayList;

public class Universidad {
	private ArrayList<Alumno> alumnos;
	private ArrayList<Aula> aulas;
	private ArrayList<Materia> materias;
	private ArrayList<Curso> cursos;
	private ArrayList<CicloLectivo> ciclosLectivos;
	private ArrayList<CursoAlumno> asignacionesCursoAlumno;
	private ArrayList<RegistroMateriasAlumno> registrosAlumnoMateria;
	private ArrayList<Docente> docentes;
	private ArrayList<CursoDocente> asignacionesCursoDocente;
	private String nombre;
	private static Integer idCurso;
	private static Integer idCiclo;
	private LocalDate fechaActual;

	public Universidad(String nombre) {
		this.fechaActual = LocalDate.of(2023, 3, 10);
		this.alumnos = new ArrayList<>();
		this.docentes = new ArrayList<>();
		this.aulas = new ArrayList<>();
		this.materias = new ArrayList<>();
		this.cursos = new ArrayList<>();
		this.ciclosLectivos = new ArrayList<>();
		this.registrosAlumnoMateria = new ArrayList<>();
		this.asignacionesCursoAlumno = new ArrayList<>();
		this.asignacionesCursoDocente = new ArrayList<>();
		idCurso = 0;
		idCiclo = 0;
		this.nombre = nombre;
	}

	// - - - - - MATERIAS - CORRELATIVAS - - - - -

	// Asignar correlativa (codMateria_recibe, codMateria_asignar)
	public Boolean asignarMateriaCorrelativa(Integer codMateria, Materia materia) {
		Materia materiaQueRecibe = getMateriaPorId(codMateria);
		Boolean estaRegistradaLaCorrelativa = verificarSiExisteMateriaPorId(materia.getCodigo());
		Boolean pudoAgregarseLaCorrelativa = false;
		if (materiaQueRecibe != null && estaRegistradaLaCorrelativa) {
			pudoAgregarseLaCorrelativa = materiaQueRecibe.agregarCorrelativa(materia);
		}
		return pudoAgregarseLaCorrelativa;
	}

	// Eliminar correlativa (codMateria_quePierde, codMateria_laQueseElimina)
	public Boolean eliminarCorrelativaDeUnaMateria(Integer codMateria, Materia materiaParaEliminar) {
		Materia materiaQuePierde = getMateriaPorId(codMateria);
		Boolean pudoEliminarseLacorrelativa = false;
		if (materiaQuePierde != null) {
			pudoEliminarseLacorrelativa = materiaQuePierde.eliminarCorrelativa(materiaParaEliminar);
		}
		return pudoEliminarseLacorrelativa;
	}

	// Crear y agregar materia (composicion)
	public Boolean agregarMateria(Integer codigo, String nombre) {
		Boolean pudoAgregarse = false;
		if (!verificarSiExisteMateriaPorId(codigo)) {
			Materia nueva = new Materia(codigo, nombre);
			this.materias.add(nueva);
			pudoAgregarse = true;
			//System.out.println("\n" + nueva.getCodigo() + ") " + nueva.getNombre() + " agregada.");
			//System.out.println("Materias de la universidad: " + this.materias.size());
		}
		return pudoAgregarse;
	}

	// Crear y agregar materia (asignacion)
	public Boolean agregarMateria(Materia materia) {
		Boolean pudoAgregarse = false;
		if (!verificarSiExisteMateriaPorId(materia.getCodigo())) {
			this.materias.add(materia);
			pudoAgregarse = true;
			//System.out.println("\n-- " + materia.getNombre() + " agregada.");
		}
		return pudoAgregarse;
	}

	// - - - - - MATERIAS - ALUMNOS - - - - -

	// Registrar alumno
	public Boolean agregarAlumno(Integer dni, String nombre, String apellido, LocalDate fn) {
		Boolean pudoAgregarseALaUniversidad = false;
		Integer indice = 0;
		if (this.alumnos.size() == 0) {
			LocalDate fi = LocalDate.now();
			Alumno nuevo = new Alumno(dni, nombre, apellido, fn, fi);
			pudoAgregarseALaUniversidad = this.alumnos.add(nuevo);
		} else {
			while (indice < this.alumnos.size() && !pudoAgregarseALaUniversidad) {
				if (!this.alumnos.get(indice).getDni().equals(dni)) {
					LocalDate fi = LocalDate.now();
					Alumno nuevo = new Alumno(dni, nombre, apellido, fn, fi);
					pudoAgregarseALaUniversidad = this.alumnos.add(nuevo);
				}
				indice++;
			}
		}
		return pudoAgregarseALaUniversidad;
	}

	// - - - - - INSCRIPCION de ALUMNOS a un CURSO - - - - -

	// Inscribir el alumno al curso
	// Verificar existencia de alumno y curso
	// Verificar si el aula tiene espacio para alojar
	// Verificar que no este inscripto en el mismo horario
	// Verificar que haya aprobado las correlativas
	// Si se cumple, se crea una asignacion CursoAlumno
	// Se resta espacio en el aula del curso

	// Verificar que se inscriba dentro de las fechas asignadas
	public Boolean inscribirAlumnoAlCurso(Integer dniAlumno, Integer idCurso) {
		Boolean pudoInscribirse = false;
		Alumno alumno = getAlumnoPorDni(dniAlumno);
		Curso curso = getCursoPorId(idCurso);
		Boolean existeAlumnoAndCurso = alumno != null && curso != null;
		Materia materiaDelCurso = curso.getMateriaAsignada();
		ArrayList<Materia> correlativas = materiaDelCurso.getCorrelativas();
		
		//System.out.println("\n-- Verificando: " + curso.getMateriaAsignada().getNombre());
		if (existeAlumnoAndCurso) {
			Boolean hayEspacio = saberSiElAulaTieneEspacio(curso.getAula());
			Boolean estaInscriptoEnElMismoHorario = saberSiElAlumnoEstaInscriptoEnElMismoHorario(alumno, curso);
			Boolean aproboLasCorrelativas = saberSiElAlumnoAproboLasCorrelativas(correlativas, alumno.getDni());
			// IF si hay espacio en el aula
			if (hayEspacio) {
				// IF si el alumno oesta inscripto en el mismo horario en otra materia
				if (!estaInscriptoEnElMismoHorario) {
					// IF si aprobo las correlativas de la materia
					if (aproboLasCorrelativas) {
						// IF si le fecha actual es anterior a que se cierre la inscripcion
						Boolean saberSiEstaAntesDeLaFecha = fechaActual.isBefore(curso.getCiclo().getFechaFinalizacionnscripcion());
						Boolean saberSiEstaDespuesDeLaFecha = fechaActual.isAfter(curso.getCiclo().getFechaInicioInscripcion());
						if (saberSiEstaAntesDeLaFecha && saberSiEstaDespuesDeLaFecha) {
							// IF si la fecha actual esta despues a la fecha de inicio de inscripcion
							pudoInscribirse = crearAsignacionCursoAlumno(alumno, curso);
							curso.getAula().setOcupados(1);
							//System.out.println("-- " + alumno.getDni() + " se inscribió correctamente al curso.");
						} else {
							//System.out.println("Fuera de fechas de inscripcion.");
						}
					} else {
						//System.out.println("-- No aprobó las correlativas");
					}
				}
			}
		}
		Integer totalAsientos = curso.getAula().getCapacidad();
		Integer lugaresLibres = curso.getAula().getCapacidad() - curso.getAula().getOcupados();
		//System.out.println("-- Lugares en el aula: " + lugaresLibres + "/" + totalAsientos);
		return pudoInscribirse;
	}

	// Verificar aprobacion de correlativas (asignacion CursoAlumno)
	public Boolean saberSiElAlumnoAproboLasCorrelativas(ArrayList<Materia> correlativas, Integer dni) {
		Boolean aprobo = false;
		Integer contadorAprobadas = 0;
		Integer nroCorrelativas = correlativas.size();
		if (nroCorrelativas == 0) {
			aprobo = true;
		} else {
			for (Materia correlativa : correlativas) {
				if (verificarSiAproboEstaMateria(correlativa, dni)) {
					contadorAprobadas++;
				}
			}
			if (nroCorrelativas == contadorAprobadas) {
				aprobo = true;
				//System.out.println("-- Aprobó las correlativas. Puede inscribirse.");
			}
		}

		return aprobo;
	}

	// Verificar aprobacion de materia en los registros (asignacion CursoAlumno)
	private Boolean verificarSiAproboEstaMateria(Materia correlativa, Integer dni) {
		Boolean aprobada = false;
		Integer notasContadas = 0;
		for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
			if (registro.getMateria().getCodigo().equals(correlativa.getCodigo())) {
				// se encontró la correlativa en el registro de notas
				if (registro.getAlumno().getDni().equals(dni)) {
					// se verifica que sea del alumno pedido
					if (registro.getNota().getValor() >= 4) {
						notasContadas++;
					}

				}
			}
		}
		if (notasContadas >= 2) {
			aprobada = true;
			//System.out.println("-- Aprobó " + correlativa.getNombre());
		} else {
			//System.out.println("-- No probó " + correlativa.getNombre());
		}
		return aprobada;
	}

	// Contar cuantas calificaciones tiene de ese curso el alumno en los registros
	// (asignacion CursoAlumno)
	public Integer contarMaterias(Integer dni, Integer idMateria) {
		Integer contador = 0;
		for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
			if (registro.getAlumno().getDni().equals(dni)) {
				if (registro.getMateria().getCodigo().equals(idMateria)) {
					contador++;
				}
			}
		}
		//System.out.println("Encontre " + contador + " notas del alumno en la misma materia");
		return contador;
	}

	// Verificar si el alumno cursa en el mismo horario (asignacion CursoAlumno)
	public Boolean saberSiElAlumnoEstaInscriptoEnElMismoHorario(Alumno alumno, Curso cursoAlQueSeInscribe) {
		Boolean estaYaInscriptoEnOtroCurso = false;
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			// IF si el dni coincide en un registro de las asignaciones
			if (asignacion.getAlumno().getDni().equals(alumno.getDni())) {
				// IF si el turno del curso al que se inscribe coincide con otro curso
				// dentro de las asignaciones del alumno
				if (asignacion.getCurso().getTurno().equals(cursoAlQueSeInscribe.getTurno())) {
					// IF si el ciclo del curso al que se inscribe coincide con otro curso
					// dentro de las asignaciones del alumno
					if (asignacion.getCurso().getCiclo().equals(cursoAlQueSeInscribe.getCiclo())) {
						estaYaInscriptoEnOtroCurso = true;
					}
				}

			}
		}
		return estaYaInscriptoEnOtroCurso;
	}

	// Verificar si el aula tiene espacio disponible (asignacion CursoAlumno)
	// VERIFICAR SI HAY ESPACIO EN EL AULA
	private Boolean saberSiElAulaTieneEspacio(Aula aula) {
		Boolean hayLugar = false;
		if ((aula.getCapacidad() - aula.getOcupados()) != 0) {
			hayLugar = true;
		}
		return hayLugar;
	}

	// Crear asignacion CursoAlumno
	private Boolean crearAsignacionCursoAlumno(Alumno alumno, Curso curso) {
		return asignacionesCursoAlumno.add(new CursoAlumno(alumno, curso));
	}

	// - - - - - ASIGNACION NOTA ALUMNO - - - - -

	// Asignar nota al alumno
	// Verificar existencia del alumno
	// Verificar existencia de materia
	// Crecion de registro
	// Agregar registro a la tabla de registros
	public Boolean asignarNotaAlAlumno(Integer codMateria, Integer dni, Integer nota, TipoNota tipoNota) {
		Boolean pudoCalificar = false;
		Nota notaAlum = new Nota(nota);
		Alumno alumnoParaCalificar = getAlumnoPorDni(dni);
		Materia materiaParaCalificar = getMateriaPorId(codMateria);
		Boolean existen = alumnoParaCalificar != null && materiaParaCalificar != null;

		if (existen) {
			RegistroMateriasAlumno calificacion = new RegistroMateriasAlumno(alumnoParaCalificar, materiaParaCalificar,
					notaAlum, tipoNota);

			pudoCalificar = registrosAlumnoMateria.add(calificacion);
//			System.out.println("\n-- Alumno calificado: " + calificacion.getAlumno().getDni());
//			System.out.println("-- Materia: " + calificacion.getMateria().getNombre());
//			System.out.println("-- Nota: " + calificacion.getNota().getValor());
//			System.out.println("-- Tipo: " + calificacion.getTipoNota().toString());
		}

		return pudoCalificar;
	}

	// Crear curso
	// Verificar que exista la materia
	// Verificar que no hall otro curso con el midmo id
	// Que no se ocupe otro curso al mismo tiempo
	// Si no hay cursos, se agrega el primero sin problema
	// Si hay cursos, se verifica que no comparta el aula al mismo timepo
	public Boolean crearCurso(Integer idCurso, Integer codMateria, Turno turno, CicloLectivo ciclo, Aula aula) {
		Boolean pudoCrearseCurso = false;
		Materia materia = this.getMateriaPorId(codMateria);
		Boolean yaExisteCursoConEseId = saberSiYaExisteCurso(idCurso);
		Boolean existeUnCursoConMismoCicloAulaTurno = false;
		for (Curso curso : cursos) {
			// IF si el aula se encuentra usada en otro cursos
			if (curso.getAula().getNro().equals(aula.getNro())) {
				// IF si el ciclo es el mismo en otro curso
				if (curso.getCiclo().equals(ciclo)) {
					// IF si el turno es el mismo en otro curso
					if (curso.getTurno().equals(turno)) {
						// Existe un curso con la misma aula, turno y materia
						// No se puede ocupar el lugar de otro curso al mismo tiempo
						existeUnCursoConMismoCicloAulaTurno = true;
					}
				}
			}
		}

		if (!yaExisteCursoConEseId && materia != null) {
			if (this.cursos.size() == 0) {
				// System.out.println("\n-- Primer curso agregado.");
				Curso nuevoCurso = new Curso(idCurso, materia, turno, ciclo, aula);
				pudoCrearseCurso = this.cursos.add(nuevoCurso);

			} else if (this.cursos.size() > 0) {
				if (!existeUnCursoConMismoCicloAulaTurno) {
					Curso nuevoCurso = new Curso(idCurso, materia, turno, ciclo, aula);
					pudoCrearseCurso = this.cursos.add(nuevoCurso);
				} else {
					//System.out.println("-- No se agregó.");
				}
			}
		}
		return pudoCrearseCurso;
	}

	// - - - - - CICLO - - - - -

	// Crear ciclo (id automatico)
	public Boolean crearCiclo(LocalDate fechaInicio, LocalDate fechaFinalizacion, LocalDate fechaInicioInscripcion,
			LocalDate fechaFinalizacionnscripcion) {

		CicloLectivo nuevoCiclo = new CicloLectivo(0, fechaInicio, fechaFinalizacion, fechaInicioInscripcion,
				fechaFinalizacionnscripcion);
		Boolean pudoCrearse = false;
		Boolean yaExisteElCiclo = saberSiExisteElCiclo(nuevoCiclo);
		if (this.ciclosLectivos.size() == 0) {
			nuevoCiclo.setId(idCiclo++);
			pudoCrearse = ciclosLectivos.add(nuevoCiclo);
		} else {
			if (!yaExisteElCiclo) {
				pudoCrearse = ciclosLectivos.add(nuevoCiclo);
				//System.out.println("-- Ciclo agregado.");
			} else {
				//System.out.println("-- El ciclo ya existe.");
			}
		}
		return pudoCrearse;
	}

	// - - - - - AULA - - - - -

	// Crear aula (composision)
	public Boolean crearAula(Integer numero, Integer capacidadAula) {
		Boolean existeNroDeAula = saberSiExisteElAulaPorNro(numero);
		Boolean pudoAgregarse = false;
		if (this.aulas.size() == 0) {
			Aula aula = new Aula(numero, capacidadAula);
			pudoAgregarse = this.aulas.add(aula);
		} else {
			if (!existeNroDeAula) {
				Aula aula = new Aula(numero, capacidadAula);
				pudoAgregarse = this.aulas.add(aula);
			}
		}
		return pudoAgregarse;
	}

	// - - - - - DOCENTES - - - - -

	// Crear docente (composicion)
	public Docente crearDocente(String nombre, String apellido, Integer dni) {
		Boolean saberSiYaExiste = saberSiExisteDocentePorDni(dni);
		Docente docente = null;
		if (!saberSiYaExiste) {
			docente = new Docente(nombre, apellido, dni);
		}
		return docente;
	}

	// Registrar docente (asignacion)
	public Boolean agregarDocente(Docente docente) {
		Boolean agregado = false;
		if (!this.docentes.contains(docente)) {
			agregado = this.docentes.add(docente);
			//System.out.println("\n-- Docente agregado.");
		}
		return agregado;
	}

	// Asignar docente al curso
	// Verificar existencia de docente y curso
	// Saber docentes requeridos segun cantidad de alumnos inscriptos
	// Saber cantidad de docentes ya asignados a un curso
	// Saber si el docente esta en otro curso asignado
	// Saber si el turno del curso asignado es el mismo al que se asignará
	// Saber si el ciclo del curso asignado es el mismo al que se asignará
	// ACLARACION, se creo el algoritmo con el fin de que el mismo docente no este
	// asignado a un mismo curso en un mismo horario
	// Si los docentes que se necesitan ya estan asignados, no se asigna docente
	// Si los docentes necesarios no estan conseguidos, se asigna el docente
	// Se crea asignacion CursoDocente
	public Boolean asignarCursoDocente(Integer dni, Integer idCurso) {
		//System.out.println("\n-- Docente a asignar: " + dni);
		Boolean asignado = false;
		Curso curso = getCursoPorId(idCurso);
		Docente docente = getDocentePorDni(dni);
		// Docentes necesarios para el curso (1,2,3)
		Integer docentesRequeridos = saberCantidadDeDocentesQueNecesitaElCurso(curso);
		// Cantidad de docentes que ya estan asignados a este curso
		Integer cantidadDeDocentesYaAsignadosDelCurso = saberDocentesAsignadosEnUnCurso(curso);
		// Requisitos para ser asignado
		Boolean verificadoParaSerAsignado = false;
		// Saber si el docente esta en otro curso
		Boolean estaEnOtroCurso = saberSiDocenteEstaEnOtroCurso(docente);
		// Saber si hay otro curso en el mismo turno
		Boolean coincideTurno = saberSiOtroCursoTieneElMismoTurno(curso);
		// Saber si hay otro curso con el mismo ciclo lectivo
		Boolean coincideCiclo = saberSiOtroCursoTieneElMismoCiclo(curso);

		if (estaEnOtroCurso) {
			if (coincideCiclo && coincideTurno) {
				//System.out.println("# -- Esta en otro curso en el mismo ciclo y turno");
			} else {
				verificadoParaSerAsignado = true;
				//System.out.println("No esta en otro turno ni ciclo iguales");
			}
		} else {
			//System.out.println("# -- No esta en otro curso");
			verificadoParaSerAsignado = true;
		}

		if (verificadoParaSerAsignado) {
			if (docentesRequeridos == cantidadDeDocentesYaAsignadosDelCurso) {
				//System.out.println("\n-- No se agregó docente, ya estan asignados los suficientes");
			} else if (cantidadDeDocentesYaAsignadosDelCurso < docentesRequeridos) {
				CursoDocente asignacionNueva = new CursoDocente(docente, curso);
				asignado = asignacionesCursoDocente.add(asignacionNueva);
				cantidadDeDocentesYaAsignadosDelCurso = saberDocentesAsignadosEnUnCurso(curso);
				//System.out.println("\n-- Docente asignado a curso " + curso.getIdCurso());
			} else {
				//System.out.println("\n -- No deberias estar aca, corre!!");
			}
		}

		//System.out.println("-- Docentes del curso " + cantidadDeDocentesYaAsignadosDelCurso + "/" + docentesRequeridos);
		return asignado;
	}

	// VERIFICACION si esta en otro curso ya el docente
	public Boolean saberSiDocenteEstaEnOtroCurso(Docente docente) {
		//System.out.println("# -- Buscando al docente " + docente.getDni() + " en otro curso.");
		Boolean estaEnOtroCurso = false;
		for (CursoDocente asignacion : asignacionesCursoDocente) {
			if (asignacion.getDocente().getDni().equals(docente.getDni())) {
				estaEnOtroCurso = true;
				//System.out.println("# -- Esta en otro curso");
				break;
			} else {
				//System.out.println("# -- No esta en otro curso");
			}
		}
		return estaEnOtroCurso;
	}

	// VERIFICACION si hay otro curso con el mismo turno
	public Boolean saberSiOtroCursoTieneElMismoTurno(Curso curso) {
		Boolean coincideTurno = false;
		for (CursoDocente asignacion : asignacionesCursoDocente) {
			if (asignacion.getCurso().getTurno().equals(curso.getTurno())) {
				coincideTurno = true;
				break;
			}
		}
		return coincideTurno;
	}

	// VERIFICACION si hay otro curso con el mismo ciclo
	public Boolean saberSiOtroCursoTieneElMismoCiclo(Curso curso) {
		Boolean coincideCiclo = false;
		for (CursoDocente asignacion : asignacionesCursoDocente) {
			if (asignacion.getCurso().getCiclo().getId().equals(curso.getCiclo().getId())) {
				coincideCiclo = true;
				break;
			}
		}
		return coincideCiclo;
	}

	// SABER cuantos docentes necesita el curso segun la cantidad de alumnos
	public Integer saberCantidadDeDocentesQueNecesitaElCurso(Curso curso) {
		Integer cantidadAlumnosCurso = saberCantidadDeAlumnosDeUnCurso(curso);
		Integer requeridos = 0;
		if (cantidadAlumnosCurso <= 20) {
			requeridos = 1;
		} else if (cantidadAlumnosCurso >= 21 && cantidadAlumnosCurso <= 40) {
			requeridos = 2;
		} else if (cantidadAlumnosCurso >= 41 && cantidadAlumnosCurso <= 60) {
			requeridos = 3;
		}
		return requeridos;
	}

	// SABER cuantos alumnos hay inscriptos en un curso
	public Integer saberCantidadDeAlumnosDeUnCurso(Curso curso) {
		Integer cantidadAlumnosCurso = 0;
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
				cantidadAlumnosCurso++;
			}
		}
		return cantidadAlumnosCurso;
	}

	// SABER cuantos docentes hay asignados en un curso
	public Integer saberDocentesAsignadosEnUnCurso(Curso curso) {
		Integer cantidadDeProfesQueTieneElCurso = 0;
		for (CursoDocente asignacion : asignacionesCursoDocente) {
			if (asignacion.getCurso().getIdCurso().equals(curso.getIdCurso())) {
				cantidadDeProfesQueTieneElCurso++;
			}
		}
		return cantidadDeProfesQueTieneElCurso;
	}

	// SABER si existe el docente por dni (si esta registrado)
	public Boolean saberSiExisteDocentePorDni(Integer dni) {
		Boolean existe = false;
		for (Docente docente : docentes) {
			if (docente.getDni().equals(dni)) {
				existe = true;
				break;
			}
		}
		return existe;
	}

	// - - - - - FIN DOCENTES- - - - -

	// - - - - - SABER EXISTENCIA - OBJETOS - - - - -

	// Saber si existe materia por id
	private Boolean verificarSiExisteMateriaPorId(Integer id) {
		Integer indice = 0;
		Boolean existe = false;
		while (indice < this.materias.size() && !existe) {
			if (this.materias.get(indice).getCodigo().equals(id)) {
				existe = true;
			}
			indice++;
		}
		return existe;
	}

	// Saber si existe el aula por numero
	private Boolean saberSiExisteElAulaPorNro(Integer numero) {
		Boolean existe = false;
		Integer indice = 0;
		while (indice < this.aulas.size() && !existe) {
			if (this.aulas.get(indice).getNro().equals(numero)) {
				existe = true;
			}
			indice++;
		}
		return existe;
	}

	// Saber si el ciclo ya existe
	private Boolean saberSiExisteElCiclo(CicloLectivo nuevoCiclo) {
		return this.ciclosLectivos.contains(nuevoCiclo);
	}

	// Saber si ya existe un curso con el mismo id
	private Boolean saberSiYaExisteCurso(Integer idCurso) {
		Integer indice = 0;
		Boolean existe = false;
		while (indice < this.cursos.size() && !existe) {
			if (this.cursos.get(indice).getIdCurso().equals(idCurso)) {
				//System.out.println("Ya existe ese curso.");
				existe = true;
			}
			indice++;
		}
		return existe;
	}

	// Saber si la condicion de un alumno por dni en un curso
	private Boolean saberSiSuCondicionEsAprobada(Integer dni, Integer idCurso) {
		Boolean aprobo = false;
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			if (asignacion.getAlumno().getDni().equals(dni)) {
				if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
					if (asignacion.getCondicion().equals(CondicionFinal.APROBADO)) {
						aprobo = true;
					}
				}
			}
		}
		return aprobo;
	}

	// - - - - - GETTERS OBJETOS - - - - -
	
	public LocalDate getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(LocalDate fechaActual) {
		this.fechaActual = fechaActual;
	}

	// Get docente por DNI
	public Docente getDocentePorDni(Integer dni) {
		Docente docenteBuscado = null;
		for (Docente docente : docentes) {
			if (docente.getDni().equals(dni)) {
				docenteBuscado = docente;
				break;
			}
		}
		return docenteBuscado;
	}

	// Get materia por id
	public Materia getMateriaPorId(Integer id) {
		Materia materiaBuscada = null;
		Integer indice = 0;
		while (indice < this.materias.size() && materiaBuscada == null) {
			if (this.materias.get(indice).getCodigo().equals(id)) {
				materiaBuscada = this.materias.get(indice);
			}
			indice++;
		}
		return materiaBuscada;
	}

	// Get alumno por dni
	public Alumno getAlumnoPorDni(Integer dni) {
		Alumno buscado = null;
		Integer indice = 0;
		while (indice < this.alumnos.size() && buscado == null) {
			if (this.alumnos.get(indice).getDni().equals(dni)) {
				buscado = this.alumnos.get(indice);
			}
			indice++;
		}
		return buscado;
	}

	// Get curso por id
	public Curso getCursoPorId(Integer idCurso) {
		Integer indice = 0;
		Curso cursoBuscado = null;
		while (indice < this.cursos.size() && cursoBuscado == null) {
			if (this.cursos.get(indice).getIdCurso().equals(idCurso)) {
				cursoBuscado = this.cursos.get(indice);
			}
			indice++;
		}
		return cursoBuscado;
	}

	// - - - - - GETTERS ARRAYLIST - - - - -

	// Get asignaciones CursoAlumno
	public ArrayList<CursoAlumno> getAsignacionesCursoAlumno() {
		return this.asignacionesCursoAlumno;
	}

	// Get registros de calificaciones de un alumno
	public ArrayList<RegistroMateriasAlumno> getRegistrosNotasAlumno(Integer dni) {
		ArrayList<RegistroMateriasAlumno> encontrados = new ArrayList<>();
		for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
			if (registro.getAlumno().getDni().equals(dni)) {
				encontrados.add(registro);
			}
		}
		return encontrados;
	}

	// Get registros de calificaciones de un alumno, segun su tipo de nota
	public ArrayList<RegistroMateriasAlumno> getRegistrosNotasAlumno(Integer dni, TipoNota tipoNota) {
		ArrayList<RegistroMateriasAlumno> encontrados = new ArrayList<>();
		for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
			if (registro.getAlumno().getDni().equals(dni)) {
				if (registro.getTipoNota().equals(tipoNota.toString())) {
					encontrados.add(registro);
				}
			}
		}
		return encontrados;
	}

	// - - - - - GETTERS CANTIDAD - - - - -

	// Ciclos registrados
	public Integer getCantidadCiclosCreados() {
		return this.ciclosLectivos.size();
	}

	// Materias registradas
	public Integer getCantidadMateriasRegistradas() {
		return this.materias.size();
	}

	// Cursos registrados
	public Integer getCantidadDeCursos() {
		return this.cursos.size();
	}

	// Docentes registrados
	public Integer getCantidadDocentesRegistrados() {
		return this.docentes.size();
	}

	// Alumnos registrados
	public Integer getCantidadDeAlumnosRegistrados() {
		return this.alumnos.size();
	}

	// Alumnos inscriptos en un curso por id
	public Integer getCantidadInscriptosEnUnCursoPorid(Integer idCurso) {
		Integer inscriptos = 0;
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
				inscriptos++;
			}
		}
		//System.out.println("\n-- Se inscribieron " + inscriptos + " en el curso " + idCurso);
		return inscriptos;
	}

	// Correlativas de una materia
	public Integer getCantidadDeCorrelativasDeUnaMateriaPorId(Integer id) {
		Integer cantidad = 0;
		for (Materia materia : this.materias) {
			if (materia.getCodigo().equals(id)) {
				cantidad = materia.getCantidadCorrelativas();
			}
		}
		return cantidad;
	}

	// - - - - - GETTERS ARRAYLIST - - - - -

	// Get registros de asignacion CursoDocente
	public ArrayList<CursoDocente> getAsignacionesCursoDocente() {
		return this.asignacionesCursoDocente;
	}

	// Get alumnos inscriptos en un curso por id
	public ArrayList<Alumno> getAlumnosInscriptosEnUnCursoPorid(Integer idCurso) {
		ArrayList<Alumno> inscriptos = new ArrayList<>();
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
				inscriptos.add(asignacion.getAlumno());
			}
		}
		//System.out.println("Se inscribieron " + inscriptos.size() + " en el curso " + idCurso);
		return inscriptos;
	}

	// Get correlativas de una materia
	public ArrayList<Materia> getCorrelativasDeUnaMateriaPorId(Integer id) {
		ArrayList<Materia> correlativas = new ArrayList<>();
		for (Materia materia : this.materias) {
			if (materia.getCodigo().equals(id)) {
				correlativas = materia.getCorrelativas();
				break;
			}
		}
		return correlativas;
	}

	// Get listado de materias que aprobó el alumno (nota >= 4)
	public ArrayList<RegistroMateriasAlumno> obtenerListadoDeMateriasAprobadasParaUnAlumno(Integer dni) {
		ArrayList<RegistroMateriasAlumno> registrosAprobadas = new ArrayList<>();
		Alumno alumno = this.getAlumnoPorDni(dni);
		if (alumno != null) {
			for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
				if (registro.getAlumno().getDni().equals(alumno.getDni())) {
					if (registro.getNota().getValor() >= 4) {
						registrosAprobadas.add(registro);
					}
				}
			}
		}
		return registrosAprobadas;
	}
	
	// Get listado de materias que aprobó el alumno (nota >= 4)
		public ArrayList<Materia> getListaDeMateriasAprobadasDeUnAlumno(Integer dni) {
			ArrayList<Materia> aprobadas = new ArrayList<>();
			Alumno alumno = this.getAlumnoPorDni(dni);
			Integer cantidadAprobadas = 0;
			if (alumno != null) {
				for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
					if (registro.getAlumno().getDni().equals(alumno.getDni())) {
						if (registro.getNota().getValor() >= 4) {
							cantidadAprobadas++;
							if (cantidadAprobadas == 2) {
								aprobadas.add(registro.getMateria());
								cantidadAprobadas = 0;
							}
						}

					}

				}
			}
			return aprobadas;
		}
	
	// Get listado de materias que faltan aprobar
	public ArrayList<Materia> obtenerMateriasQueFaltanCursasParaUnAlumno(Integer dni) {
		ArrayList<Materia> yaAprobadas = getListaDeMateriasAprobadasDeUnAlumno(dni);
		ArrayList<Materia> copiaDeTodasLasMaterias = this.materias;
		Alumno alumno = this.getAlumnoPorDni(dni);
		if (alumno != null) {
	        for (Materia aprobada : yaAprobadas) {
	            copiaDeTodasLasMaterias.removeIf(materia -> materia.getCodigo().equals(aprobada.getCodigo()));
	        }
	    }
		return copiaDeTodasLasMaterias;
	}

	public ArrayList<RegistroMateriasAlumno> obetenerReporteDeNotasDeAlumnosDeUnCurso(Integer idCurso) {
		ArrayList<RegistroMateriasAlumno> reportes = new ArrayList<>();
		//System.out.println("\ncurso | materia | dni | n | a | nota");
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			// IF si coincide un registro en las asignaciones con el id de curso
			if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
				// IF el alumno de ese registro coincide con los registros de las notas
				for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
					// IF si coincide el alumno de la asignacion con un registro
					// de de las notas
					if (asignacion.getAlumno().getDni().equals(registro.getAlumno().getDni())) {
						// IF Si coincide la materia del curso de la asignacion
						// con la materia del registro de notas
						if (asignacion.getCurso().getMateriaAsignada().getCodigo()
								.equals(registro.getMateria().getCodigo())) {
							reportes.add(registro);
						}
					}
				}
			}
		}
		return reportes;
		
	}

	public Integer getCantidadDeNotasRegistradasDeUnCurso(Integer idCurso) {
		Integer notasRegistradas = 0;
		for (CursoAlumno asignacion : asignacionesCursoAlumno) {
			// IF si coincide con el curso que buscamos en las asignaciones
			if (asignacion.getCurso().getIdCurso().equals(idCurso)) {
				for (RegistroMateriasAlumno registro : registrosAlumnoMateria) {
					// IF si el alumno de la asignacion coincide con algun registro de nota
					if (asignacion.getAlumno().getDni().equals(registro.getAlumno().getDni())) {
						// si coincide 
						notasRegistradas++;
					}	
				}
			}
		}
		return notasRegistradas;
	}

	public Aula getAulaPorNro(Integer nro) {
		Aula buscada = null;
		for (Aula aula : aulas) {
			if (aula.getNro().equals(nro)) {
				buscada = aula;
				break;
			}
		}
		return buscada;
	}
}
