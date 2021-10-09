package ar.edu.unlam.dominio;

public class Jugador {

	private int dni;
	private String nombre;
	private String apellido;
	private int numeroCamiseta;
	private int edad;
	private TipoEquipo tipoEquipo;
	private Puesto puesto;

	public Jugador(int dni, String nombre, String apellido, int numeroCamiseta, int edad, TipoEquipo tipoEquipo,
			Puesto puesto) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numeroCamiseta = numeroCamiseta;
		this.edad = edad;
		this.tipoEquipo = tipoEquipo;
		this.puesto = puesto;

	}

	public int getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public int getNumeroCamiseta() {
		return numeroCamiseta;
	}

	public int getEdad() {
		return edad;
	}

	public TipoEquipo getTipoEquipo() {
		return tipoEquipo;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	@Override
	public String toString() {
		return "Jugador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", numeroCamiseta="
				+ numeroCamiseta + ", edad=" + edad + ", tipoEquipo=" + tipoEquipo + ", puesto=" + puesto + "]";
	}

}
