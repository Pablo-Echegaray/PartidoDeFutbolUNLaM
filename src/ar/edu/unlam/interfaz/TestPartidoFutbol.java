package ar.edu.unlam.interfaz;

import java.util.Scanner;

import ar.edu.unlam.dominio.Jugador;
import ar.edu.unlam.dominio.Partido;
import ar.edu.unlam.dominio.Puesto;
import ar.edu.unlam.dominio.TipoEquipo;

public class TestPartidoFutbol {
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		/*
		 * TODO: Generar un menu que permita:
		 * 
		 * 1) Crear partido: Se debera solicitar el ingreso de datos por pantalla. 2)
		 * Agregar jugador equipo local: Se debera° solicitar el ingreso de datos por
		 * pantalla. 3) Agregar jugador equipo visitante: Se deber√° solicitar el
		 * ingreso de datos por pantalla. 4) Iniciar el partido: Cambia el estado de la
		 * variable iniciado de la clase Partido No se puede inciar el partido si ambos
		 * equipos no tienen almenos 11 jugadores cada uno.
		 * 
		 * 5) Registar gol: Para registrar un gol, primero debe buscarse el jugador por
		 * su DNI, indicando su equipo (Local o Visitante). Debe existir el partido y
		 * estar iniciado. 6) Finalizar partido: Cambia el estado de la variable
		 * iniciado de la clase Partido.
		 * 
		 */

		/*************************************************************
		 * 
		 * Notas: - El men√∫ finaliza, cuando el partido est√© finalizado. - Una vez
		 * finalizado el partido, informar equipo ganador indicando: Local, Visitante o
		 * Empate y jugadores que marcaron gol ordenados por DNI (en caso de existir). -
		 * y tambien informar el jugador mas joven que hizo un gol
		 * 
		 * Puede crear todos los metodos y atributos que necesite
		 *************************************************************/

		System.out.println("Bienvenido al partido del dÌa de la fecha \n Ingrese el partido que se ha de disputar: ");
		int numeroDePartido = teclado.nextInt();
		Partido picadito = new Partido(numeroDePartido);

		int opcion = 0;
		do {
			opcion = seleccionarOpcion();
			switch (opcion) {
			case 1:
				agregarJugadorEquipoLocal(picadito);
				break;
			case 2:
				agregarJugadorEquipoVisitante(picadito);
				break;
			case 3:
				iniciarPartido(picadito);
				break;
			case 4:
				registrarGol(picadito);
				break;
			case 5:
				finalizarPartido(picadito);
				break;
			case 6:
				verPlantillaEquipoLocal(picadito);
				break;
			case 7:
				verPlantillaEquipoVisitante(picadito);
				break;
			default:
				System.out.println("Opcion incorrecta!");
				break;
			}
		} while (opcion != 9);

	}

	private static void finalizarPartido(Partido picadito) {
		String resultado = picadito.obtenerEquipoGanador();
		Jugador[] goleadores = picadito.obtenerGolesDeJugadoresOrdenadosPorDni();
		Jugador goleadorMasJoven = picadito.obtenerJugadorMasJovenQueHizoUnGol();

		if (picadito.getIniciado()) {
			picadito.finalizarPartido();
			System.out.println("Final del partido!!");
			System.out.println(resultado);

			if (goleadorMasJoven != null) {
				System.out.println("El jugador mas joven en realizar un gol fue: " + goleadorMasJoven.getNombre() + " "
						+ goleadorMasJoven.getApellido());

				System.out.println("\n Los goleadores del partido, ordenados por dni, son: ");
				for (int i = 0; i < goleadores.length; i++) {
					if (goleadores[i] != null) {
						System.out.println(goleadores[i].toString());
					}
				}
			}
		}
	}

	private static void registrarGol(Partido picadito) {
		int dni;
		TipoEquipo equipo = TipoEquipo.Local;
		Jugador goleador = null;
		char team = ' ';
		System.out.println("Usted va a registrar un gol, a continuacion se le piden los datos del autor del gol: ");
		System.out.println("Ingrese el DNI del autor del gol: ");
		dni = teclado.nextInt();
		System.out.println("Ingrese el equipo al cual pertenece: L para local, V para visitante: ");
		team = teclado.next().charAt(0);

		if (team == 'L') {
			equipo = TipoEquipo.Local;
		}
		if (team == 'V') {
			equipo = TipoEquipo.Visitante;
		}

		if (picadito.getIniciado()) {
			goleador = picadito.buscarJugadorPorDNIEnEquipo(dni, equipo);
			if (goleador != null) {
				boolean gol = picadito.registrarGolDeJugador(goleador);
				if (gol) {
					System.out.println("Gaaaaaaaaaaaaaal del " + equipo + ", lo hizo " + goleador.getNombre() + " "
							+ goleador.getApellido());
				} else {
					System.out.println("3 tiros 10 pesos!!");
				}
			} else {
				System.out.println("El jugador indicado no ha sido encontrado!!");
			}
		} else {
			System.out.println("Para poder registrar un gol el partido debe estar iniciado!");
		}
	}

	private static void iniciarPartido(Partido picadito) {
		Jugador[] equipoLocal = picadito.getEquipoLocal();
		Jugador[] equipoVisitante = picadito.getEquipoVisitante();
		int locales = 0;
		int visitantes = 0;

		for (int i = 0; i < equipoLocal.length; i++) {
			if (equipoLocal[i] != null) {
				locales++;
			}
		}
		for (int i = 0; i < equipoVisitante.length; i++) {
			if (equipoVisitante[i] != null) {
				visitantes++;
			}
		}

		if (locales >= Partido.CANTIDAD_MAXIMA_JUGADORES && visitantes >= Partido.CANTIDAD_MAXIMA_JUGADORES) {
			picadito.iniciarPartido();
			System.out.println("La orden del juez, y el partido en marcha!");
		} else {
			System.out.println("Los equipos deben estar completos para que el partido pueda iniciarse!!");
		}
	}

	private static void verPlantillaEquipoLocal(Partido picadito) {
		Jugador[] equipoLocal = picadito.getEquipoLocal();
		for (int i = 0; i < equipoLocal.length; i++) {
			if (equipoLocal[i] != null) {
				System.out.println(i + 1 + " - " + equipoLocal[i].toString());
			}
		}

	}

	private static void verPlantillaEquipoVisitante(Partido picadito) {
		Jugador[] equipoVisitante = picadito.getEquipoVisitante();
		for (int i = 0; i < equipoVisitante.length; i++) {
			if (equipoVisitante[i] != null) {
				System.out.println(i + 1 + " - " + equipoVisitante[i].toString());
			}
		}

	}

	private static void agregarJugadorEquipoVisitante(Partido picadito) {
		String nombre, apellido;
		int dni, numeroCamiseta, edad;
		Puesto puesto = Puesto.Arquero;
		String posicion;

		System.out.println("Usted va a agregar un jugador al equipo Visitante, ingrese los siguientes datos: \n");
		System.out.println("Nombre del jugador: ");
		nombre = teclado.next();
		System.out.println("Apellido del jugador: ");
		apellido = teclado.next();
		System.out.println("DNI del jugador: ");
		dni = teclado.nextInt();
		System.out.println("Numero de camiseta: ");
		numeroCamiseta = teclado.nextInt();
		System.out.println("Edad: ");
		edad = teclado.nextInt();
		System.out.println("Puesto del jugador: Arquero - Defensor - Mediocampista - Delantero");
		posicion = teclado.next();
		switch (posicion) {
		case "Arquero":
			puesto = Puesto.Arquero;
			break;
		case "Defensor":
			puesto = Puesto.Defensor;
			break;
		case "Mediocampista":
			puesto = Puesto.Mediocampista;
			break;
		case "Delantero":
			puesto = Puesto.Delantero;
			break;
		default:
			System.out.println("El puesto indicado no es correcto!");
			break;
		}

		Jugador futbolista = new Jugador(dni, nombre, apellido, numeroCamiseta, edad, TipoEquipo.Visitante, puesto);
		boolean agregarJugador = picadito.agregarJugadorAEquipo(futbolista, futbolista.getTipoEquipo());

		if (agregarJugador) {
			System.out.println("El futbolista " + futbolista.getNombre() + " " + futbolista.getApellido()
					+ ", fue agregado con exito al equipo " + futbolista.getTipoEquipo() + "\n en la posicion de "
					+ futbolista.getPuesto());
		} else {
			System.out.println("El futbolista no ha podido agregarse al equipo!");
		}

	}

	private static void agregarJugadorEquipoLocal(Partido picadito) {
		String nombre, apellido;
		int dni, numeroCamiseta, edad;
		Puesto puesto = Puesto.Arquero;
		String posicion;

		System.out.println("Usted va a agregar un jugador al equipo local, ingrese los siguientes datos: \n");
		System.out.println("Nombre del jugador: ");
		nombre = teclado.next();
		System.out.println("Apellido del jugador: ");
		apellido = teclado.next();
		System.out.println("DNI del jugador: ");
		dni = teclado.nextInt();
		System.out.println("Numero de camiseta: ");
		numeroCamiseta = teclado.nextInt();
		System.out.println("Edad: ");
		edad = teclado.nextInt();
		System.out.println("Puesto del jugador: Arquero - Defensor - Mediocampista - Delantero");
		posicion = teclado.next();
		switch (posicion) {
		case "Arquero":
			puesto = Puesto.Arquero;
			break;
		case "Defensor":
			puesto = Puesto.Defensor;
			break;
		case "Mediocampista":
			puesto = Puesto.Mediocampista;
			break;
		case "Delantero":
			puesto = Puesto.Delantero;
			break;
		default:
			System.out.println("El puesto indicado no es correcto!");
			break;
		}

		Jugador futbolista = new Jugador(dni, nombre, apellido, numeroCamiseta, edad, TipoEquipo.Local, puesto);
		boolean agregarJugador = picadito.agregarJugadorAEquipo(futbolista, futbolista.getTipoEquipo());

		if (agregarJugador) {
			System.out.println("El futbolista " + futbolista.getNombre() + " " + futbolista.getApellido()
					+ ", fue agregado con exito al equipo " + futbolista.getTipoEquipo() + "\n en la posicion de "
					+ futbolista.getPuesto());
		} else {
			System.out.println("El futbolista no ha podido agregarse al equipo!");
		}

	}

	private static int seleccionarOpcion() {

		int opcionSeleccionada = 0;
		System.out.println("************************");
		System.out.println("Menu de opciones\n");
		// System.out.println("1 - Crear partido \n");
		System.out.println("1 - Agregar jugador al equipo local");
		System.out.println("2 - Agregar jugador al equipo visitante");
		System.out.println("3 - Iniciar el partido");
		System.out.println("4 - Registrar gol");
		System.out.println("5 - Finalizar partido");
		System.out.println("6 - Ver plantilla equipo local");
		System.out.println("7 - Ver plantilla equipo visitante ");
		System.out.println("9 - Para salir del menu");
		System.out.println("************************");
		System.out.println("Ingrese una opcion");
		opcionSeleccionada = teclado.nextInt();
		return opcionSeleccionada;

	}

}
