package Validaciones;

public class ValidarCatEdi {
	public static String validarNomC(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: Ingrese un nombre de categoría.";
        }

        String valor = nombre.trim();

        if (valor.length() < 4) return "Error: El nombre debe tener al menos 4 caracteres.";
        if (valor.length() > 100) return "Error: El nombre no puede superar los 100 caracteres.";
        if (!valor.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) {
            return "Error: Solo se permiten letras y espacios (sin números ni caracteres especiales).";
        }

        return "OK";
    }
	
	public static String validarNomE(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: Ingrese un nombre de editorial.";
        }

        String valor = nombre.trim();

        if (valor.length() < 4) return "Error: El nombre debe tener al menos 4 caracteres.";
        if (valor.length() > 100) return "Error: El nombre no puede superar los 100 caracteres.";
        if (!valor.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 ]+")) {
            return "Error: Solo se permiten letras y espacios (sin números ni caracteres especiales).";
        }

        return "OK";
    }
}
