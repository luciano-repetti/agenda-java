package ar.unrn.validaciones;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validaciones {
    public String mensajeError;
    public String expresionRegular;

    public Validaciones(String expresionRegular, String mensajeError) {
        this.expresionRegular = expresionRegular;
        this.mensajeError = mensajeError;
    }

    public boolean validarEntrada(String entrada) {
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(entrada);
        boolean matches = matcher.matches();

        return matches;
    }
}
