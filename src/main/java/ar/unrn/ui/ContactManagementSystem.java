package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class ContactManagementSystem {
    private static String databaseUrl;
    private static GridLayout gridLayout;
    private static JPanel table;
    private static DataBaseInterface database;
    private static Agenda agenda;


    public static void main(String[] args) throws SQLException {
        // String dataBaseUrl = "test4.db";
        databaseUrl = "test4.db";
        database = new DataBase(databaseUrl);

        // ---------------------------------------------------------------------
        agenda = Agenda.getAgenda(database.getContactos(true));
        //agenda.registrarObserver((Observer) database);
        // ---------------------------------------------------------------------


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
                try {
                    new OpenContact(databaseUrl);
                    setData(database.getContactos(true));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        top.add(newContact);

        // Agregar el panel superior al marco
        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(10, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(Color.white);


        setData(database.getContactos(true));

        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);

        // Hacer visible el frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void setData(List<Contacto> contactos) {
        table.removeAll();
        table.repaint();
        table.revalidate();


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.weightx = 1.0;
        int i = 0;

        for (Contacto c : contactos) {
            i++;
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBackground(Color.decode("#4A525A"));
            if (i % 2 == 0) panel.setBackground(Color.decode("#24272B"));
            panel.setPreferredSize(new Dimension(100, 55));

            GridBagConstraints panelGbc = new GridBagConstraints();
            panelGbc.fill = GridBagConstraints.BOTH;
            panelGbc.insets = new Insets(5, 10, 5, 10);
            panelGbc.weightx = 1.0;

            panelGbc.gridx = 0;
            panel.add(GUI.label(c.id.toString()), panelGbc);
            panelGbc.gridx = 1;
            panel.add(GUI.label(c.nombre), panelGbc);
            panelGbc.gridx = 2;
            panel.add(GUI.label(c.apellido), panelGbc);
            panelGbc.gridx = 3;
            panel.add(GUI.label(c.numeroTelefono), panelGbc);
            panelGbc.gridx = 4;
            panel.add(GUI.label(c.email), panelGbc);
            panelGbc.gridx = 5;
            panel.add(GUI.label(c.notas), panelGbc);
            panelGbc.gridx = 6;
            panel.add(GUI.label(c.direccion.pais), panelGbc);
            panelGbc.gridx = 7;
            panel.add(GUI.label(c.direccion.provincia), panelGbc);
            panelGbc.gridx = 8;
            panel.add(GUI.label(c.direccion.ciudad), panelGbc);
            panelGbc.gridx = 9;
            panel.add(GUI.label(c.direccion.calle), panelGbc);

            panelGbc.gridx = 10;
            JPanel containerButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

            containerButton.setBackground(panel.getBackground());

            JButton view = GUI.button("View", new Color(4, 57, 94));
            view.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new OpenContact(c);
                }
            });
            containerButton.add(view, panelGbc);

            JButton edit = GUI.button("Edit", new Color(37, 162, 68));
            edit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new OpenContact(c);

                }
            });
            containerButton.add((edit), panelGbc);

            JButton delete = GUI.button("Delete", new Color(193, 18, 31));
            delete.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("A tu casita");
                    try {
                        agenda.eliminarContacto(c.id);
                        setData(database.getContactos(true));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            containerButton.add((delete), panelGbc);

            panel.add(containerButton);

            gbc.gridy = i;
            table.add(panel, gbc);
        }
    }

}
