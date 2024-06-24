package ar.unrn.validaciones;

import javax.swing.JTextField;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase para realizar validaciones de entrada utilizando expresiones regulares.
 */
public class Validaciones {
    /**
     * Atributo que representa el mensaje de error asociado cuando la entrada no cumple con la expresión regular.
     */
    public String mensajeError;

    /**
     * Atributo que representa la expresión regular utilizada para validar la entrada.
     */
    public String expresionRegular;

    /**
     * Atributo que representa el campo de texto (JTextField) que se va a validar.
     */
    public JTextField campo;

    /**
     * Constructor de la clase Validaciones.
     *
     * @param expresionRegular La expresión regular utilizada para validar la entrada.
     * @param mensajeError     El mensaje de error asociado cuando la entrada no cumple con la expresión regular.
     * @param campo            El campo de texto (JTextField) que se va a validar.
     */
    public Validaciones(String expresionRegular, String mensajeError, JTextField campo) {
        this.expresionRegular = expresionRegular;
        this.mensajeError = mensajeError;
        this.campo = campo;
    }

    /**
     * Método para validar la entrada contra la expresión regular.
     *
     * @param entrada La cadena de entrada a validar.
     * @return true si la entrada cumple con la expresión regular, false en caso contrario.
     */
    public boolean validarEntrada(String entrada) {
        Pattern pattern = Pattern.compile(expresionRegular);
        Matcher matcher = pattern.matcher(entrada);
        boolean matches = matcher.matches();
        return matches;
    }
}
