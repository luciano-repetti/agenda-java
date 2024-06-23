package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class OpenContact {

    public OpenContact(String dataBaseUrl) throws SQLException {
        DataBaseInterface database = new DataBase(dataBaseUrl);
        Agenda agenda = Agenda.getAgenda(database.getContactos(true));

        agenda.registrarObserver((Observer) database);

        JFrame frame = new JFrame("Contacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);

        JPanel table = new JPanel(new GridLayout(11,2,15,15));
        table.setBackground(Color.white);
        table.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        table.add(GUI.label("UUID")).setForeground(Color.black);
        JLabel id = GUI.label(String.valueOf(""));
        id.setForeground(Color.black);
        table.add(id);

        table.add(GUI.label("Nombre: ")).setForeground(Color.black);
        JTextField nombre = GUI.textField("");
        table.add(nombre);

        table.add(GUI.label("Apellido: ")).setForeground(Color.black);
        JTextField apellido = GUI.textField("");
        table.add(apellido);

        table.add(GUI.label("Numero de telefono: ")).setForeground(Color.black);
        JTextField numeroTelefono = GUI.textField("");
        table.add(numeroTelefono);

        table.add(GUI.label("Email: ")).setForeground(Color.black);
        JTextField email = GUI.textField("");
        table.add(email);

        table.add(GUI.label("Notas: ")).setForeground(Color.black);
        JTextField notas = GUI.textField("");
        table.add(notas);

        table.add(GUI.label("Pais: ")).setForeground(Color.black);
        JTextField pais = GUI.textField("");
        table.add(pais);

        table.add(GUI.label("Provincia: ")).setForeground(Color.black);
        JTextField provincia = GUI.textField("");
        table.add(provincia);

        table.add(GUI.label("Ciudad: ")).setForeground(Color.black);
        JTextField ciudad = GUI.textField("");
        table.add(ciudad);

        table.add(GUI.label("Calle: ")).setForeground(Color.black);
        JTextField calle = GUI.textField("");
        table.add(calle);

        JButton cancel = GUI.button("Cancel", new Color(208, 11, 3));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        table.add(cancel);

        JButton save = GUI.button("Save", new Color(88, 179, 88));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Contacto nuevoContacto = new Contacto(nombre.getText(), apellido.getText(), numeroTelefono.getText(),
                        email.getText(), notas.getText(), pais.getText(),
                        provincia.getText(), ciudad.getText(), calle.getText());
                agenda.agregarContacto(nuevoContacto);
                frame.dispose();
            }
        });
        table.add(save);

        frame.add(table, BorderLayout.CENTER);
        frame.setVisible(true);
    }



    public OpenContact(Contacto c) {
        JFrame frame = new JFrame("Contacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);

        JPanel table = new JPanel(new GridLayout(11,2,15,15));
        table.setBackground(Color.white);
        table.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        table.add(GUI.label("UUID")).setForeground(Color.black);
        JLabel id = GUI.label(String.valueOf(c.id));
        id.setForeground(Color.black);
        table.add(id);

        table.add(GUI.label("Nombre: ")).setForeground(Color.black);
        JTextField nombre = GUI.textField(c.nombre);
        table.add(nombre);

        table.add(GUI.label("Apellido: ")).setForeground(Color.black);
        JTextField apellido = GUI.textField(c.apellido);
        table.add(apellido);

        table.add(GUI.label("Numero de telefono: ")).setForeground(Color.black);
        JTextField numero = GUI.textField(c.numeroTelefono);
        table.add(numero);

        table.add(GUI.label("Email: ")).setForeground(Color.black);
        JTextField email = GUI.textField(c.email);
        table.add(email);

        table.add(GUI.label("Notas: ")).setForeground(Color.black);
        JTextField notas = GUI.textField(c.notas);
        table.add(notas);

        table.add(GUI.label("Pais: ")).setForeground(Color.black);
        JTextField pais = GUI.textField(c.direccion.pais);
        table.add(pais);

        table.add(GUI.label("Provincia: ")).setForeground(Color.black);
        JTextField provincia = GUI.textField(c.direccion.provincia);
        table.add(provincia);

        table.add(GUI.label("Ciudad: ")).setForeground(Color.black);
        JTextField ciudad = GUI.textField(c.direccion.ciudad);
        table.add(ciudad);

        table.add(GUI.label("Calle: ")).setForeground(Color.black);
        JTextField calle = GUI.textField(c.direccion.calle);
        table.add(calle);

        JButton cancel = GUI.button("Cancel", new Color(208, 11, 3));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        table.add(cancel);

        JButton save = GUI.button("Save", new Color(88, 179, 88));
        table.add(save);

        frame.add(table, BorderLayout.CENTER);
        frame.setVisible(true);


    }
}
