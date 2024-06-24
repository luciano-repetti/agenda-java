package ar.unrn.ui;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#F1F1F1"));
        label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.setPreferredSize(null);
        return label;
    }

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

    public static JTextField textField(String text) {
        JTextField textField = new JTextField();
        textField.setText(text);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        return textField;
    }

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
