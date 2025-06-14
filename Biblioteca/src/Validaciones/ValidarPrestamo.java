package Validaciones;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Clases.PrestamoDAO;

public class ValidarPrestamo {
	public static String validarFechas(String fechaPrestamo, String fechaDevolucion) {
		if (fechaPrestamo == null || fechaPrestamo.trim().isEmpty() ||
	            fechaDevolucion == null || fechaDevolucion.trim().isEmpty()) {
	            return "Error: Ambas fechas son obligatorias.";
	        }

	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        sdf.setLenient(false); 
	        try {
	            Date fechaPres = sdf.parse(fechaPrestamo.trim());
	            Date fechaDevo = sdf.parse(fechaDevolucion.trim());

	            if (fechaDevo.before(fechaPres)) {
	                return "Error: La fecha de devolución no puede ser anterior a la de préstamo.";
	            }

	        } catch (ParseException e) {
	            return "Error: Formato de fecha inválido. Use yyyy-MM-dd.";
	        }

	        return "OK";
	    }
    
	public static String validarFechasNoAnteriores(String fechaPrestamo, String fechaDevolucion) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(false);

	    try {
	        // Parseamos las fechas ingresadas
	        Date fechaPres = sdf.parse(fechaPrestamo.trim());
	        Date fechaDevo = sdf.parse(fechaDevolucion.trim());

	        // Obtenemos solo la parte de la fecha de hoy (sin hora)
	        Date hoy = sdf.parse(sdf.format(new Date()));

	        if (fechaPres.before(hoy) || fechaDevo.before(hoy)) {
	            return "Las fechas no pueden ser anteriores al día de hoy.";
	        }
	    } catch (ParseException e) {
	        return "Formato de fecha inválido. Use yyyy-MM-dd.";
	    }

	    return "OK";
	}

	
	public static String validarRangoMaximo5Dias(String fechaPrestamo, String fechaDevolucion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date fechaPres = sdf.parse(fechaPrestamo.trim());
            Date fechaDevo = sdf.parse(fechaDevolucion.trim());

            if (fechaDevo.before(fechaPres)) {
                return "La fecha de devolución no puede ser anterior a la de préstamo.";
            }

            long diff = fechaDevo.getTime() - fechaPres.getTime();
            long dias = diff / (1000 * 60 * 60 * 24);
            if (dias > 3) {
                return "El préstamo no puede exceder los 3 días.";
            }
        } catch (ParseException e) {
            return "Formato de fecha inválido. Use yyyy-MM-dd.";
        }

        return "OK";
    }

	public static String validarPrestamoDuplicado(long isbn, String idUsuario) {
        PrestamoDAO dao = new PrestamoDAO();
        if (dao.PrestamoActivo(isbn, idUsuario)) {
            return "Ya existe un préstamo activo para este libro y usuario.";
        }
        return "OK";
    }
    
}
