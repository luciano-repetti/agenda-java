package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.database.DataBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Refresh {

    private static Agenda agenda;
    // Colors
    private static final String row1 = "#1a2c32";
    private static final String row2 = "#2d464c";

    public static void refreshContacts(DataBase dataBase, JPanel container) {
        container.removeAll();
        container.repaint();
        container.revalidate();

        try {
            agenda = Agenda.getAgenda(dataBase.getContactos(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;
        int i = 0;

        for (Contacto c : agenda) {
            i++;
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.decode(row1));
            if (i % 2 == 0)
                panel.setBackground(Color.decode(row2));
            panel.setPreferredSize(new Dimension(1500, 55));

            GridBagConstraints panelGbc = new GridBagConstraints();
            panelGbc.fill = GridBagConstraints.BOTH;
            panelGbc.insets = new Insets(5, 10, 5, 10);
            panelGbc.weightx = 1.0;

            panelGbc.gridx = 0;
            panel.add(GUI.label(c.nombre), panelGbc);
            panelGbc.gridx = 1;
            panel.add(GUI.label(c.apellido), panelGbc);
            panelGbc.gridx = 2;
            panel.add(GUI.label(c.numeroTelefono), panelGbc);
            panelGbc.gridx = 3;
            panel.add(GUI.label(c.email), panelGbc);
            panelGbc.gridx = 4;
            panel.add(GUI.label(c.notas), panelGbc);
            panelGbc.gridx = 5;
            panel.add(GUI.label(c.direccion.pais), panelGbc);
            panelGbc.gridx = 6;
            panel.add(GUI.label(c.direccion.provincia), panelGbc);
            panelGbc.gridx = 7;
            panel.add(GUI.label(c.direccion.ciudad), panelGbc);
            panelGbc.gridx = 8;
            panel.add(GUI.label(c.direccion.calle), panelGbc);

            panelGbc.gridx = 9;
            JPanel containerButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

            containerButton.setBackground(panel.getBackground());

            JButton view = GUI.button("View", new Color(4, 57, 94));
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact("view", c, dataBase, container);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            containerButton.add(view);

            JButton edit = GUI.button("Edit", new Color(37, 162, 68));
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact("edicion", c, dataBase, container);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            });
            containerButton.add(edit);

            JButton delete = GUI.button("Delete", new Color(193, 18, 31));
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agenda.eliminarContacto(c.id);
                    refreshContacts(dataBase, container);
                }
            });
            containerButton.add(delete);

            panel.add(containerButton, panelGbc);

            gbc.gridy = i;
            container.add(panel, gbc);
        }
    }
}
