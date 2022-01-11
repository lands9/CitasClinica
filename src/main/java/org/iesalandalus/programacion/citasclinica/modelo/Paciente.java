package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	// ATRIBUTOS
	private static final String ER_DNI="([0-9]{8})([a-zA-Z])";
	private static final String ER_TELEFONO="[0-9]{9}";
	private String nombre, dni, telefono;
	
	public Paciente(String nombre, String dni, String telefono) {
		
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);

	}
	public Paciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		}
		setNombre(paciente.nombre);
		setDni(paciente.dni);
		setTelefono(paciente.telefono);
		
	}
	// Metodo formateanombre
	private String formateaNombre(String nombre) {
		String cambiar = this.nombre;
		cambiar = cambiar.trim().replaceAll(" +", " ").replaceAll("[^A-Za-z-Á-á-É-é-Í-í-Ó-ó-Ú-ú] ", "").toLowerCase();
		char[] caracteres = cambiar.toCharArray();
		for(int i = 0; i < cambiar.length(); i++ ) {
			caracteres[0] = Character.toUpperCase(caracteres[0]);
			if (caracteres[i] == ' ') {
				caracteres[i+1] = Character.toUpperCase(caracteres[i + 1]);
			}
			
			
		}
		cambiar = String.valueOf(caracteres);
		return cambiar;
	}
	
	// Metodo comprobar dni
	private Boolean comprobarLetraDni(String dni) {
		boolean comprueba = false;
		String letras="TRWAGMYFPDXBNJZSQVHLCKE";
		String dnis = dni;
		Pattern p = Pattern.compile(ER_DNI);
		Matcher m;
		m = p.matcher(dnis);
		if (m.matches()) {
			int pasarResto = Integer.parseInt(m.group(1));
			pasarResto = pasarResto%23;
			if(m.group(2).charAt(0) == letras.charAt(pasarResto)) {
				
				return comprueba = true;
				 
			}else {
				throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
			}
		}else {
		return comprueba;
		}
		
	}
	
	private String getIniciales() {
		
		String []palabra=nombre.split(" ");
		String inicial="";
		
		for(int i=0;i<palabra.length;i++) {
			
			if(palabra[i].length()!=0) {
				
				inicial=inicial+palabra[i].charAt(0);
			}
			
			
		}
		
		return inicial.toUpperCase();
	}
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}else if (telefono.equals("")  || telefono.isEmpty() || telefono.equals("   ")) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		}else {
		Pattern p = Pattern.compile(ER_TELEFONO);
		Matcher m;
		m = p.matcher(telefono);
			if (m.matches() && (telefono.charAt(0) == '6' || telefono.charAt(0) == '9')) {
				this.telefono = telefono;
			
			}else {
				throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
			}
		}
	}
	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}else if (nombre.isEmpty() || nombre.equals("") || nombre.equals("  ")) {
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		}else {
		
		this.nombre = nombre;
		}
	}

	private void setDni(String dni) {
		if(dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		}else if (dni.isEmpty() || dni.equals("") || dni.equals("   ")) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
			}else {
				if (comprobarLetraDni(dni).equals(true)) {
					this.dni = dni;
				}else if(dni == null || dni.equals("")) {
					throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
				}else {
					throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
				}
			}
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		
		return formateaNombre(nombre);
	}



	public String getDni() {
		// TODO Auto-generated method stub
		return dni;
		}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Paciente other = (Paciente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), 
				getDni(), getTelefono());  
	}
	
	
	}
	



