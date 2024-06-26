package ar.unrn.validaciones;



import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase para realizar validaciones de entrada utilizando expresiones regulares.
 */
public class Validaciones {
    /**
     * Atributo que representa el mensaje de error asociado cuando la entrada no cumple con la expresión regular.
     */
    private String mensajeError;

    /**
     * Atributo que representa la expresión regular utilizada para validar la entrada.
     */
    private String expresionRegular;

    /**
     * Atributo que representa el campo de texto (JTextField) que se va a validar.
     */
    private JTextField campo;

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

    /**
     * Deshidrata el contacto en una lista de objetos.
     *
     * @return Lista de objetos que representan la validacion.
     */
    public List<Object> deshidratarValidaciones() {
        List<Object> list = new ArrayList<>();
        list.add(mensajeError);
        list.add(expresionRegular);
        list.add(campo.getText());
        return list;
    }

    /**
     * Crea un contacto a partir de una lista de objetos.
     *
     * @param list Lista de objetos que representan la validacion.
     * @return La validacion creada.
     */
    private Validaciones fromList(List<Object> list) {
        String mensajeError = (String) list.get(0);
        String expresionRegular = (String) list.get(1);
        JTextField campo = (JTextField) list.get(2);
        return new Validaciones(expresionRegular, mensajeError,campo);
    }

    /**
     * Hidrata la validación con una lista de objetos.
     *
     * @param list Lista de objetos que representan la validacion.
     */
    public void hidratarContacto(List<Object> list) {
        Validaciones validacion = fromList(list);
        this.mensajeError = validacion.mensajeError;
        this.expresionRegular = validacion.expresionRegular;
        this.campo = validacion.campo;
    }


}
