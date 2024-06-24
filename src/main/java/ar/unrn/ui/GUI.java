package ar.unrn.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Clase utilitaria para crear componentes de la interfaz gráfica de usuario
 * (GUI).
 */
public class GUI {

    /**
     * Crea y configura una etiqueta (JLabel).
     *
     * @param text El texto que mostrará la etiqueta.
     * @return Una instancia de JLabel configurada.
     */
    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#F1F1F1"));
        label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.setPreferredSize(null);
        return label;
    }

    /**
     * Crea y configura un botón (JButton).
     *
     * @param text       El texto que mostrará el botón.
     * @param background El color de fondo del botón.
     * @return Una instancia de JButton configurada.
     */
    public static JButton button(String text, Color background) {
        JButton button = new JButton(text);
        button.setSize(40, 40);
        button.setBackground(background);
        button.setForeground(Color.white);
        button.setFont(new Font("Tahoma", Font.PLAIN, 17));
        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Crea y configura un campo de texto (JTextField).
     *
     * @param text El texto inicial del campo de texto.
     * @return Una instancia de JTextField configurada.
     */
    public static JTextField textField(String text) {
        JTextField textField = new JTextField();
        textField.setText(text);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        return textField;
    }

    /**
     * Crea y configura un área de texto (JTextArea) dentro de un JScrollPane.
     *
     * @param text El texto inicial del área de texto.
     * @return Una instancia de JScrollPane que contiene el JTextArea configurado.
     */
    public static JScrollPane textArea(String text) {
        JTextArea campo = new JTextArea(text);
        campo.setLineWrap(true);
        campo.setWrapStyleWord(true);
        campo.setEnabled(false);
        campo.setColumns(20);
        campo.setRows(1);

        campo.setFont(new Font("Tahoma", Font.PLAIN, 17));

        campo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        campo.setDisabledTextColor(Color.BLACK);
        campo.setBackground(new Color(238, 238, 238));

        JScrollPane scrollPane = new JScrollPane(campo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }
}
