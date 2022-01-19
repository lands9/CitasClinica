package org.iesalandalus.programacion.citasclinica;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.*;
import org.iesalandalus.programacion.citasclinica.vista.*;
public class MainApp {
	
private static final int NUM_MAX_CITAS=10;
private static Citas listaCitas = new Citas(NUM_MAX_CITAS);
	public static void main(String[] args) throws OperationNotSupportedException {
		Opciones opciones;
		do {
			
		
		System.out.println("Programa para gestionar las citas de la Clínica.");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		Consola.mostrarMenu();
		opciones = Consola.elegirOpcion();
		ejecutarOpcion(opciones);
		}while(opciones !=Opciones.SALIR);
		
		
	}
	
	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException 
	{
		switch (opcion)
		{
		case SALIR:
			System.out.println("");
			System.out.print("¡Sesión terminada!");
			break;
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITA_DIA:
			mostrarCitasDia();
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
			
		}
	}
	
	private static void insertarCita() throws OperationNotSupportedException {
		try {
			Cita cita = Consola.leerCita();
			listaCitas.insertar(cita);
			System.out.println("");
			System.out.println("Cita insertada correctamente.");
			
		}catch(NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void buscarCita() throws OperationNotSupportedException {
		LocalDateTime fechaHora = Consola.leerFechaHora();
		
		// setPaciente throw !null
		Cita buscada = new Cita(new Paciente("adaw","11223344B","630772354"), fechaHora);
		buscada = listaCitas.buscar(buscada); 
		if (buscada == null) {
			System.out.println("La cita buscada no existe");
		} else {
			System.out.println(buscada.toString());
		}
		
	}
	
	private static void borrarCita() throws OperationNotSupportedException {
		LocalDateTime fechaHora=Consola.leerFechaHora();
		Paciente paciente = new Paciente("adaw","11223344B","630772354");
		Cita cita = new Cita(paciente ,fechaHora);
		try {
			listaCitas.borrar(cita);
			
		}catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
			
		}
		
		
	}
	
	private static void mostrarCitasDia() {
		try {
			LocalDate fechaCitasDia=Consola.leerFecha();
			boolean citaEncontrada = false;
			Cita[] citas = listaCitas.getCitas(fechaCitasDia);
			for(int i=0; i<citas.length; i++) {
				if(listaCitas.getCitas(fechaCitasDia)[i] != null) {
					citaEncontrada = true;
					System.out.println(listaCitas.getCitas(fechaCitasDia)[i]);
				}
			}
			if(listaCitas.getTamano()==0) {
				System.out.println("El sistema no tiene citas, inserte una primero");
			}else if(!citaEncontrada) {
				System.out.println("No hay citas registradas con esta fecha");
			}
		}catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
		private static void mostrarCitas() {
			
			if(listaCitas.getTamano()==0) {
				System.out.println("El sistema no tiene citas, tienes que introducirlas primero.");
			}else {
				for(int i=0;i < listaCitas.getTamano(); i++) {
					System.out.println(listaCitas.getCitas()[i]);
				}
				
			}
		}
	}
	


