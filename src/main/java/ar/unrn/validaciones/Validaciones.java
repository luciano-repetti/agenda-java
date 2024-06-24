package ar.unrn.validaciones;

import javax.swing.JTextField;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Validaciones {
    public String mensajeError;
    public String expresionRegular;
    public JTextField campo;

    public Validaciones(String expresionRegular, String mensajeError, JTextField campo) {
        this.expresionRegular = expresionRegular;
        this.mensajeError = mensajeError;
        this.campo = campo;
    }

    public boolean validarEntrada(String entrada) {
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(entrada);
        boolean matches = matcher.matches();
        return matches;
    }
}
