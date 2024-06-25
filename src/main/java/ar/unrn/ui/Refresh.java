package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.database.DataBase;

import java.util.UUID;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Clase que gestiona la actualización de la lista de contactos en el
 * contenedor.
 */
public class Refresh {

    private static Agenda agenda;
    // Colors
    private static final String row1 = "#1a2c32";
    private static final String row2 = "#2d464c";

    /**
     * Actualiza la lista de contactos en el contenedor.
     *
     * @param dataBase  La base de datos de contactos.
     * @param container El contenedor donde se mostrarán los contactos.
     */
    public static void refreshContacts(DataBase dataBase, JPanel container) throws SQLException {
        container.removeAll();
        container.repaint();
        container.revalidate();

        agenda = Agenda.getAgenda(dataBase.getContactos(true));

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
            panelGbc.insets = new Insets(5, 10, 5, 0);
            panelGbc.weightx = 1.0;
            // ------------------------------------------------------------------------------
            panelGbc.gridx = 0;
            List<Object> data = c.deshidratarContacto();
            for (int j = 1; j < data.size(); j++) {

                panel.add(GUI.label(data.get(j).toString()), panelGbc);
                if (j != 9) {
                    panelGbc.gridx = j;
                }

            }
            panelGbc.gridx = 9;

            panelGbc.weightx = 0.0;
            panelGbc.anchor = GridBagConstraints.EAST;

            JPanel containerButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            containerButton.setBackground(panel.getBackground());

            JButton view = GUI.button("View", new Color(4, 57, 94));
            view.setCursor(new Cursor(Cursor.HAND_CURSOR));
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact("view", c, dataBase, container);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(container, "Ha ocurrido un error al ver el contacto",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException("Error al editar el contacto");
                    }
                }
            });
            containerButton.add(view);

            JButton edit = GUI.button("Edit", new Color(37, 162, 68));
            edit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        new OpenContact("edicion", c, dataBase, container);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(container, "Ha ocurrido un error al editar el contacto",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException("Error al editar el contacto");
                    }
                }
            });
            containerButton.add(edit);

            JButton delete = GUI.button("Delete", new Color(193, 18, 31));
            delete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // agenda.eliminarContacto(c.id);
                        agenda.eliminarContacto(UUID.fromString(data.get(0).toString()));
                        refreshContacts(dataBase, container);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(container, "Ha ocurrido un error al borrar el contacto",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(ex); // Por las dudas
                    }
                }
            });
            containerButton.add(delete);

            panel.add(containerButton, panelGbc);

            gbc.gridy = i;
            container.add(panel, gbc);
        }
    }
}
