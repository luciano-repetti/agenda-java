package ar.unrn.ui;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.decode("#F1F1F1"));
        label.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.setPreferredSize(new Dimension(100, 55));
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
}
