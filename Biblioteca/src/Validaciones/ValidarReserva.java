package Validaciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Clases.PrestamoDAO;
import Clases.ReservaDAO;

public class ValidarReserva {
	public static String validarFecha(String fecha) {
	    if (fecha == null || fecha.trim().isEmpty()) {
	        return "Error: Ingrese una fecha.";
	    }

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(false); // No acepta fechas inválidas como 2024-02-30

	    try {
	        Date fechaReserva = sdf.parse(fecha.trim());

	        // Normalizamos la fecha de hoy (sin hora)
	        String hoyStr = sdf.format(new Date());
	        Date hoy = sdf.parse(hoyStr);

	        if (fechaReserva.before(hoy)) {
	            return "Error: No se permite registrar reservas con fecha pasada.";
	        }

	    } catch (ParseException e) {
	        return "Error: Formato de fecha inválido. Use yyyy-MM-dd.";
	    }

	    return "OK";
	}


    
    

    public static String validarReservaDuplicada(String idUsuario, long isbn) {
        ReservaDAO dao = new ReservaDAO();
        if (dao.existeReservaActiva(idUsuario, isbn)) {
            return "Error: Ya existe una reserva activa para este usuario y libro.";
        }
        return "OK";
    }

    
}
