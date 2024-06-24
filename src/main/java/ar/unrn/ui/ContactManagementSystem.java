package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

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

    // Colors
    private static final String topColor = "#356169";
    private static final String bgColor = "#5faab1";

    public static void main(String[] args) throws SQLException {
        databaseUrl = "test4.db";

        DataBaseInterface database = new DataBase(databaseUrl);
        agenda = Agenda.getAgenda(database.getContactos(true));

        agenda.registrarObserver((Observer) database);

        JFrame frame = new JFrame("Agenda");
        //frame.setBackground(Color.decode("#5faab1"));
        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        //frame.getContentPane().setBackground(Color.decode("#5faab1"));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        top.setBackground(Color.decode(topColor));
        //frame.setBackground(null);

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
                    new OpenContact(database, table);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        top.add(newContact);

        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(10, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(Color.decode(bgColor));

        Refresh.refreshContacts((DataBase) database, table);

        JScrollPane sp = new JScrollPane(table);
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
