package ar.edu.unlam.dominio;

public class Partido {

	private int numeroPartido;
	private boolean iniciado;
	private Jugador[] equipoLocal;
	private Jugador[] equipoVisitante;
	private Jugador[] goles;
	public static final int CANTIDAD_MAXIMA_JUGADORES = 11;
	private final int CANTIDAD_APROX_GOLES = 10;

	public Partido(int numeroPartido) {
		this.numeroPartido = numeroPartido;
		this.equipoLocal = new Jugador[CANTIDAD_MAXIMA_JUGADORES];
		this.equipoVisitante = new Jugador[CANTIDAD_MAXIMA_JUGADORES];
		this.goles = new Jugador[CANTIDAD_APROX_GOLES];
	}

	public Jugador[] getEquipoLocal() {
		return equipoLocal;
	}

	public Jugador[] getEquipoVisitante() {
		return equipoVisitante;
	}

	public boolean getIniciado() {
		return this.iniciado;
	}

	/**
	 * Agregar un jugador al array del equipo correspondiente según el tipo de
	 * equipo
	 * 
	 * @param jugador Jugador Jugador que se agregará al equipo correspondiente
	 * @return boolean Verdadero en caso de éxito
	 */
	public boolean agregarJugadorAEquipo(Jugador jugador, TipoEquipo tipoEquipo) {
		boolean agregar = false;
		for (int i = 0; i < CANTIDAD_MAXIMA_JUGADORES; i++) {
			if (tipoEquipo == TipoEquipo.Local) {
				if (equipoLocal[i] == null) {
					equipoLocal[i] = jugador;
					agregar = true;
					break;

				}
			}
			if (tipoEquipo == TipoEquipo.Visitante) {
				if (equipoVisitante[i] == null) {
					equipoVisitante[i] = jugador;
					agregar = true;
					break;
				}
			}
		}
		return agregar;
	}

	/**
	 * Agrega un jugador al array de goles
	 * 
	 * @param jugador Jugador Jugador que se agregará al array de goles
	 * @return boolean Verdadero en caso de éxito
	 */
	public boolean registrarGolDeJugador(Jugador jugador) {
		for (int i = 0; i < goles.length; i++) {
			if (goles[i] == null) {
				goles[i] = jugador;
				return true;
			}
		}
		return false;
	}

	/**
	 * Busca un jugador por su DNI, debiendo indicar el equipo al que pertenece
	 * 
	 * @param dni        int DNI del jugador
	 * @param tipoEquipo TipoEquipo Tipo de equipo en el que se buscará el jugador
	 *                   (local o visitante)
	 * @return Jugador En caso de encontrarlo o null.
	 */
	public Jugador buscarJugadorPorDNIEnEquipo(int dni, TipoEquipo tipoEquipo) {
		for (int i = 0; i < CANTIDAD_MAXIMA_JUGADORES; i++) {
			if (tipoEquipo == TipoEquipo.Local) {
				if (equipoLocal[i] != null) {
					if (equipoLocal[i].getDni() == dni) {
						return equipoLocal[i];
					}
				}
			}
			if (tipoEquipo == TipoEquipo.Visitante) {
				if (equipoVisitante[i] != null) {
					if (equipoVisitante[i].getDni() == dni) {
						return equipoVisitante[i];
					}
				}
			}
		}
		return null;
	}

	/**
	 * Cambia el estado de la variable iniciado a verdadero
	 */
	public void iniciarPartido() {
		this.iniciado = true;

	}

	/**
	 * Cambia el estado de la variable iniciado a falso
	 */
	public void finalizarPartido() {
		this.iniciado = false;
	}

	/**
	 * Obtiene un array de jugadores que marcaron un gol, debiendo ser ordenados por
	 * DNI
	 * 
	 * @return Jugador[] En caso de existir goles o null.
	 */
	public Jugador[] obtenerGolesDeJugadoresOrdenadosPorDni() {
		int i, j;
		Jugador auxiliar;
		for (i = 1; i <= goles.length; i++)

		{
			for (j = 0; j < goles.length - 1; j++) {
				if (goles[j] == null && goles[j + 1] != null) {
					auxiliar = goles[j + 1];
					goles[j + 1] = goles[j];
					goles[j] = auxiliar;
				}
				if (goles[j] != null && goles[j + 1] == null) {
					break;
				} else if (goles[j] != null && goles[j + 1] != null) {
					if (goles[j].getDni() > goles[j + 1].getDni()) {
						auxiliar = goles[j + 1];
						goles[j + 1] = goles[j];
						goles[j] = auxiliar;
					}
				}
			}
		}
		return goles;
	}

	/**
	 * Devuelve un String con la descripción del equipo ganador: Local, Visitante o
	 * Empate
	 */
	public String obtenerEquipoGanador() {
		int golLocal = 0, golVisitante = 0;
		String resultado = " ";
		for (int i = 0; i < goles.length; i++) {
			if (goles[i] != null) {
				if (goles[i].getTipoEquipo() == TipoEquipo.Local) {
					golLocal++;
				}
				if (goles[i].getTipoEquipo() == TipoEquipo.Visitante) {
					golVisitante++;
				}
			}
		}
		if (golLocal < golVisitante) {
			resultado = "El ganador es el equipo visitante, por " + golVisitante + " a " + golLocal;
		} else if (golLocal > golVisitante) {
			resultado = "El ganador es el equipo local, por " + golLocal + " a " + golVisitante;
		} else {
			resultado = "El resultado es una empate " + golLocal + " a " + golVisitante;
		}
		return resultado;
	}

	/*
	 * Devuelve el jugador mas joven querealizo un gol
	 */
	public Jugador obtenerJugadorMasJovenQueHizoUnGol() {
		Jugador masJoven = null;
		int min = 100;
		for (int i = 0; i < goles.length; i++) {
			if (goles[i] != null) {
				if (goles[i].getEdad() < min) {
					masJoven = goles[i];
					min = goles[i].getEdad();
				}
			}
		}

		return masJoven;
	}
}
