package ar.unrn.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactManagementSystem {
    private static GridLayout gridLayout;
    private static JPanel table;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Agenda");
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0, 38, 80));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        // top.setBorder(BorderFactory.createEmptyBorder(77, 50, 50, 50));
        frame.setBackground(null);

        JLabel title = new JLabel("Welcome to the Contact Management System");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        top.add(title);

        JButton newContact = new JButton("Add Contact");
        newContact.setFont(new Font("Tahoma", Font.BOLD, 18));
        newContact.setBackground(new Color(88, 179, 88));
        newContact.setForeground(Color.white);
        newContact.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        top.add(newContact);

        // Agregar el panel superior al marco
        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(10, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(Color.white);
        setData();

        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);

        // Hacer visible el frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void setData() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;

        for (int i = 0; i < 10; i++) {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.decode("#4A525A"));
            if (i % 2 == 0)
                panel.setBackground(Color.decode("#24272B"));
            panel.setPreferredSize(new Dimension(100, 55));

            GridBagConstraints panelGbc = new GridBagConstraints();
            panelGbc.fill = GridBagConstraints.BOTH;
            panelGbc.insets = new Insets(5, 10, 5, 10);
            panelGbc.weightx = 1.0;

            panelGbc.gridx = 0;
            panel.add(GUI.label("ID"), panelGbc);
            panelGbc.gridx = 1;
            panel.add(GUI.label("Nombre"), panelGbc);
            panelGbc.gridx = 2;
            panel.add(GUI.label("Apellido"), panelGbc);
            panelGbc.gridx = 3;
            panel.add(GUI.label("Telefono"), panelGbc);
            panelGbc.gridx = 4;
            panel.add(GUI.label("Email"), panelGbc);
            panelGbc.gridx = 5;
            panel.add(GUI.label("Notas"), panelGbc);
            panelGbc.gridx = 6;
            panel.add(GUI.label("Pais"), panelGbc);
            panelGbc.gridx = 7;
            panel.add(GUI.label("Provincia"), panelGbc);
            panelGbc.gridx = 8;
            panel.add(GUI.label("Calle"), panelGbc);
            panelGbc.gridx = 9;
            panel.add(GUI.label("Ciudad"), panelGbc);

            panelGbc.gridx = 10;
            JPanel containerButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

            containerButton.setBackground(panel.getBackground());
            containerButton.add(GUI.button("View", new Color(4, 57, 94)), panelGbc);
            containerButton.add(GUI.button("Edit", new Color(37, 162, 68)), panelGbc);
            containerButton.add(GUI.button("Delete", new Color(193, 18, 31)), panelGbc);

            panel.add(containerButton);

            gbc.gridy = i;
            table.add(panel, gbc);
        }
    }

}
