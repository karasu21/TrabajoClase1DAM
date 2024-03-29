package Ej1;

public class Trabajador {
	private String nombre;
	private int edad;
	private int categoria;
	private int antigŁedad;
	public static final int CAT_EMPLEADO = 0;
	public static final int CAT_ENCARGADO = 1;
	public static final int CAT_DIRECTIVO = 2;
	public static final int ANT_NOVATO = 0;
	public static final int ANT_MADURO = 1;
	public static final int ANT_EXPERTO = 2;

	public Trabajador(String nombre, int edad, int categoria, int antigŁedad) {
		this.nombre = nombre;
		this.edad = edad;
		if (categoria == 0 || categoria == 1 || categoria == 2) {
			this.categoria = categoria;
		} else {
			throw new IllegalArgumentException("Categoria no concuerda con las admitidas");
		}
		if (antigŁedad == 0 || antigŁedad == 1 || antigŁedad == 2) {
			this.antigŁedad = antigŁedad;
		} else {
			throw new IllegalArgumentException("AntigŁedad no concuerda con las admitidas");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		if (categoria == 0 || categoria == 1 || categoria == 2) {
			this.categoria = categoria;
		} else {
			throw new IllegalArgumentException("Categoria no concuerda con las admitidas");
		}
	}

	public int getAntigŁedad() {
		return antigŁedad;
	}

	public void setAntigŁedad(int antigŁedad) {
		if (antigŁedad == 0 || antigŁedad == 1 || antigŁedad == 2) {
			this.antigŁedad = antigŁedad;
		} else {
			throw new IllegalArgumentException("AntigŁedad no concuerda con las admitidas");
		}
	}

	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", edad=" + edad + ", categoria=" + categoria + ", antigŁedad="
				+ antigŁedad + "]";
	}

	public double calcularSueldo() {
		double base = 607;
		double sueldo = 0;
		if (this.categoria == 0) {
			sueldo = base + (0.15 * base);
		} else {
			if (this.categoria == 1) {
				sueldo = base + (0.35 * base);
			} else {
				if (this.categoria == 2) {
					sueldo = base + (0.60 * base);
				}
			}
		}
		if (this.antigŁedad == 0) {
			sueldo = sueldo + 150;
		} else {
			if (this.antigŁedad == 1) {
				sueldo = sueldo + 300;
			} else {
				if (this.antigŁedad == 2) {
					sueldo = sueldo + 600;
				}
			}
		}
		return sueldo;
	}
}