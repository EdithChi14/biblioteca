package Validaciones;

import java.util.List;

public class ValidarLibro {
	public static String validarISBN(String isbnIngresado, List<String> listaExistente) {
        if (isbnIngresado == null || isbnIngresado.trim().isEmpty()) {
            return "Error: Ingrese un ISBN";
        }

        String isbn = isbnIngresado.replaceAll("[\\s-]", ""); // Elimina espacios y guiones

        if (!isbn.matches("\\d+")) {
            return isbnIngresado.matches(".*[a-zA-Z]+.*") ? 
                "Error ISBN: Solo se permiten números" : 
                "Error ISBN: No se permiten caracteres especiales";
        }
        if (isbn.length() < 10) return "Error: ISBN demasiado corto";
        if (isbn.length() > 13) return "Error: ISBN demasiado largo";
        if (listaExistente.contains(isbn)) return "Error: No repetir el mismo ISBN";

        return "OK";
    }

    public static String validarTitulo(String tituloIngresado) {
        if (tituloIngresado == null || tituloIngresado.trim().isEmpty()) {
            return "Error: Ingrese un título";
        }

        String titulo = tituloIngresado.trim();

        if (!titulo.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ0-9 :]+")) {
            return "Error Título: Solo se permiten letras y números";
        }
        if (titulo.length() < 4) return "Error Título: nombre demasiado corto";
        if (titulo.length() > 200) return "Error Título: longitud máxima excedida";

        return "OK";
    }

    public static String validarAutor(String autorIngresado) {
        if (autorIngresado == null || autorIngresado.trim().isEmpty()) {
            return "Error: campo obligatorio";
        }

        String autor = autorIngresado.trim();

        if (autor.length() < 3) return "Error Autor: nombre demasiado corto";
        if (autor.length() > 200) return "Error Autor: longitud máxima excedida";

        if (!autor.matches("[a-zA-ZÁÉÍÓÚáéíóúÑñ., ]+")) {
            return autor.matches("\\s+") ? "Error: autor inválido" : "Error Autor: solo se permiten letras";
        }

        return "OK";
    }

    public static String validarStock(String stockIngresado) {
        if (stockIngresado == null || stockIngresado.trim().isEmpty()) {
            return "Error: Ingrese stock";
        }

        if (stockIngresado.contains(" ")) {
            return "Error Stock: No dejar espacios en blanco";
        }

        if (!stockIngresado.matches("\\d+")) {
            if (stockIngresado.matches(".*[a-zA-Z].*")) return "Error Stock: Solo se permiten números";
            if (stockIngresado.matches(".*[.,].*")) return "Error Stock: Solo números enteros, no decimales";
            return "Error Stock: No se permiten caracteres especiales";
        }

        int valor = Integer.parseInt(stockIngresado);

        if (valor < 0) return "Error Stock: Solo números positivos";
        if (valor > 99) return "Error Stock: Solo se permiten 2 dígitos como máximo";

        return "OK";
    }
	
}
