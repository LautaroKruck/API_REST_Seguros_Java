package com.es.segurosinseguros.utils;

public class NIFValidator {

    // Tabla de letras NIF según el resto de dividir por 23
    private static final char[] LETRAS_NIF = {
            'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D',
            'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L',
            'C', 'K', 'E'
    };

    /**
     * Valida el formato y la letra del NIF.
     *
     * @param nif El NIF a validar.
     * @return true si el NIF es válido, false en caso contrario.
     */
    public static boolean esNIFValido(String nif) {
        if (nif == null || nif.length() != 9) {
            return false; // Formato incorrecto
        }

        String numerosParte = nif.substring(0, 8);
        char letraParte = nif.charAt(8);

        // Verifica que los primeros 8 caracteres sean números
        if (!numerosParte.matches("\\d{8}")) {
            return false;
        }

        // Convierte los números a entero
        int numeros = Integer.parseInt(numerosParte);

        // Calcula la letra correcta
        char letraCalculada = LETRAS_NIF[numeros % 23];

        // Compara la letra proporcionada con la calculada
        return Character.toUpperCase(letraParte) == letraCalculada;
    }

}

