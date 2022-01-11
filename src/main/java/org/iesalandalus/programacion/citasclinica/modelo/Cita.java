package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {
	public Paciente paciente;
	
	public static final String FORMATO_FECHA_HORA = ("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHora;
	
	public Cita(Paciente paciente,LocalDateTime fechaHora) {
		
		setPaciente(paciente);
		setFechaHora(fechaHora);
		
	}
	
	public Cita(Cita copiaCita) {
		if(copiaCita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		setPaciente(copiaCita.getPaciente());
		setFechaHora(copiaCita.getFechaHora());
	}
	
	
	private void setPaciente(Paciente paciente) {
		if (paciente==null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}else {
			this.paciente = new Paciente(paciente);
		}
	}
	
	public Paciente getPaciente() {
		return paciente;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		if(fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		
		this.fechaHora = fechaHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return paciente.toString() + ", "+ "fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA));
	}
	
	
	
	
	
}
