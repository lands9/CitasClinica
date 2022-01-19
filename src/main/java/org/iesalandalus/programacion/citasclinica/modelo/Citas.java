package org.iesalandalus.programacion.citasclinica.modelo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Citas {

	

	int capacidad, tamano;
	private Cita[] coleccionCitas;


	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}


	private boolean tamanoSuperado(int tamanoActual) {
		return tamanoActual >= getTamano();
	}

	private boolean capacidadSuperada(int numCitas) {
		return numCitas >= getCapacidad();
	}


	public Citas(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionCitas = new Cita[capacidad];
		this.capacidad = capacidad;
		tamano = 0;
	}


	private int buscarIndice(Cita cita) {

		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}

		int indice = 0;
		boolean citaEncontrada = false;

		while (!tamanoSuperado(indice) && !citaEncontrada) {
			if (coleccionCitas[indice].equals(cita)) {
				citaEncontrada = true;
			} else {
				indice++;
			}
		}
		return indice;

	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}

		if (buscar(cita) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}

		coleccionCitas[getTamano()] = new Cita(cita);
		tamano++;
	}

	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}

		Cita citaEncontrada = null;

		for (int i = 0; i < coleccionCitas.length && coleccionCitas[i] != null && citaEncontrada == null; i++) {
			if (coleccionCitas[i].getFechaHora().isEqual(cita.getFechaHora())) {
				citaEncontrada = coleccionCitas[i];
			}
		}

		return citaEncontrada;
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) throws IllegalArgumentException {
		for (int i = indice; i < coleccionCitas.length - 1; i++) 
		{
			coleccionCitas[i] = coleccionCitas[i + 1];
		}
		
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}

		int indice = buscarIndice(cita);

		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
		}

		tamano--;

	}

	public Cita[] getCitas() {
		return coleccionCitas;
	}

	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}

		Cita[] citasEncontradas = new Cita[getCapacidad()];
		int posCita = 0;

		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				citasEncontradas[posCita++] = coleccionCitas[i];
			}
		}

		return citasEncontradas;
	}
}