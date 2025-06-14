package Validaciones;

public class ValidarUsuario {
	public static String validarID(String idUsuario) {
        if (idUsuario == null || !idUsuario.matches("\\d{8}")) {
            return "Error: El ID debe contener exactamente 8 dígitos numéricos.";
        }
        return "OK";
    }

    public static String validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return "Error: Ingrese un nombre.";
        if (!nombre.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ ]+")) return "Error: Solo se permiten letras.";
        if (nombre.trim().length() < 3) return "Error: Nombre demasiado corto.";
        return "OK";
    }

    public static String validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) return "Error: Ingrese un correo.";
        if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) return "Error: Formato de correo inválido.";
        return "OK";
    }

    public static String validarTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\d{9}")) {
            return "Error: El teléfono debe tener 9 dígitos numéricos.";
        }
        return "OK";
    }

    public static String validarPin(String pin) {
        if (pin == null || !pin.matches("\\d{6}")) return "Error: El PIN debe tener 6 dígitos numéricos.";
        return "OK";
    }
}
