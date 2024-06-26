package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.archivos.ExportadorContactos;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Clase que representa el Sistema de Gestión de Contactos.
 * Configura la interfaz gráfica de usuario y gestiona la interacción con la
 * base de datos y la agenda de contactos.
 */
public class ContactManagementSystem {
    protected static GridLayout gridLayout;
    private static JPanel table;

    // Colors
    private static final String topColor = "#356169";
    private static final String bgColor = "#5faab1";

    /**
     * Constructor de la clase ContactManagementSystem.
     * Configura la interfaz gráfica de usuario y registra el observer para la
     * agenda.
     *
     * @param databaseUrl La URL de la base de datos.
     * @param database    La interfaz de la base de datos.
     * @param agenda      La agenda de contactos.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public ContactManagementSystem(String databaseUrl, DataBaseInterface database, Agenda agenda) throws SQLException {

        agenda.registrarObserver((Observer) database);

        JFrame frame = new JFrame("Agenda");

        frame.setLayout(new BorderLayout());
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        top.setBackground(Color.decode(topColor));

        JLabel title = new JLabel("Bienvenido a la Agenda de Contactos");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        top.add(title);

        JButton newContact = GUI.buttonAccion("Add contacto", new Color(88, 179, 88));

        newContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new OpenContact(database, table);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Ha ocurrido un error al agregar el contacto",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
        top.add(newContact);

        JButton exportContacts = GUI.buttonAccion("Exportar contactos", new Color(4, 57, 94));

        exportContacts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExportadorContactos exportador = new ExportadorContactos();
                try {
                    String ruta = exportador.exportarContactos(Agenda.getAgenda(database.getContactos(true)));
                    System.out.println(ruta);
                    JOptionPane.showMessageDialog(frame, "La ruta donde se guardo el archivo es: " + ruta,
                            "Archivo guardado con exito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException | IOException e1) {
                    JOptionPane.showMessageDialog(frame, "Ha ocurrido un error al exportar los contactos",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException("Error al editar el contacto" + e1);
                }


            }
        });
        top.add(exportContacts);

        frame.add(top, BorderLayout.NORTH);

        gridLayout = new GridLayout(10, 1, 0, 0);
        table = new JPanel(gridLayout);
        table.setBackground(Color.decode(bgColor));

        Refresh.refreshContacts((DataBase) database, table);

        JScrollPane sp = new JScrollPane(table);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
