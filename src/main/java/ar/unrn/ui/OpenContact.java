package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;
import ar.unrn.validaciones.Validaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OpenContact {

    private static Agenda agenda;
    private List<Validaciones> validacionesList = new ArrayList<>();
    private JFrame frame;
    private JPanel container;

    public OpenContact(DataBaseInterface dataBase, JPanel container) throws SQLException {
        this.container = container;
        inicializarAgenda(dataBase);

        frame = new JFrame("Contacts Management System");
        configurarFrame(frame);

        JPanel table = crearFormularioContacto(new Contacto("", "", "", "", "", "", "", "", ""), true);

        JButton cancel = GUI.button("Cancel", new Color(208, 11, 3));
        cancelarAccion(cancel);

        JButton save = GUI.button("Save", new Color(88, 179, 88));
        guardarAccion(save, dataBase);

        table.add(cancel);
        table.add(save);

        frame.add(table, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public OpenContact(String operando, Contacto c, DataBaseInterface dataBase, JPanel container) throws SQLException {
        this.container = container;
        inicializarAgenda(dataBase);

        frame = new JFrame("Contacts Management System");
        configurarFrame(frame);

        boolean desactivado = true;
        if (operando.equals("view")) {
            desactivado = false;
        }

        JPanel table = crearFormularioContacto(c, desactivado);

        if (operando.equals("edicion")) {
            JButton cancel = GUI.button("Cancel", new Color(208, 11, 3));
            cancelarAccion(cancel);

            JButton save = GUI.button("Save", new Color(88, 179, 88));
            guardarEdicionAccion(save, c, dataBase);

            table.add(cancel);
            table.add(save);
        }

        frame.add(table, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void inicializarAgenda(DataBaseInterface dataBase) throws SQLException {
        try {
            agenda = Agenda.getAgenda(dataBase.getContactos(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void configurarFrame(JFrame frame) {
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
    }

    private JPanel crearFormularioContacto(Contacto contacto, boolean desactivado) {
        JPanel table = new JPanel(new GridLayout(11, 2, 15, 15));
        table.setBackground(Color.white);
        table.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        aniadirCampoValidacion(table, "Nombre: ", contacto.nombre, "^[a-zA-Z ]{1,20}$",
                "La entrada nombre debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Apellido: ", contacto.apellido, "^[a-zA-Z ]{1,20}$",
                "La entrada apellido debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Numero de telefono: ", contacto.numeroTelefono, "^[0-9+]{1,15}$",
                "La entrada numero debe contener solo numeros y como maximo 15 caracteres, incluyendo el simbolo '+'.",
                desactivado);

        aniadirCampoValidacion(table, "Email: ", contacto.email,
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                "La entrada email debe ser una direccion de correo electronico valida.", desactivado);

        aniadirCampoValidacion(table, "Notas: ", contacto.notas, "^[\\s\\S]{0,50}$",
                "La entrada notas debe tener un maximo de 50 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Pais: ", contacto.direccion.pais, "^[a-zA-Z ]{0,20}$",
                "La entrada pais debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Provincia: ", contacto.direccion.provincia, "^[a-zA-Z ]{0,20}$",
                "La entrada provincia debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Ciudad: ", contacto.direccion.ciudad, "^[a-zA-Z ]{0,20}$",
                "La entrada ciudad debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Calle: ", contacto.direccion.calle, "^[a-zA-Z0-9 ]{0,20}$",
                "La entrada calle debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        return table;
    }

    private void aniadirCampoValidacion(JPanel table, String etiqueta, String valor, String regex,
            String mensajeError, boolean desactivo) {
        table.add(GUI.label(etiqueta)).setForeground(Color.black);
        JTextField campo = GUI.textField(valor);
        campo.setEditable(desactivo);
        table.add(campo);
        validacionesList.add(new Validaciones(regex, mensajeError, campo));
    }

    private void cancelarAccion(JButton cancel) {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void guardarAccion(JButton save, DataBaseInterface dataBase) {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Contacto nuevoContacto = new Contacto(
                            validacionesList.get(0).campo.getText(),
                            validacionesList.get(1).campo.getText(),
                            validacionesList.get(2).campo.getText(),
                            validacionesList.get(3).campo.getText(),
                            validacionesList.get(4).campo.getText(),
                            validacionesList.get(5).campo.getText(),
                            validacionesList.get(6).campo.getText(),
                            validacionesList.get(7).campo.getText(),
                            validacionesList.get(8).campo.getText());
                    agenda.agregarContacto(nuevoContacto);
                    Refresh.refreshContacts((DataBase) dataBase, container);
                    frame.dispose();
                }
            }
        });
    }

    private void guardarEdicionAccion(JButton save, Contacto c, DataBaseInterface dataBase) {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Contacto contactoEditado = new Contacto(
                            validacionesList.get(0).campo.getText(),
                            validacionesList.get(1).campo.getText(),
                            validacionesList.get(2).campo.getText(),
                            validacionesList.get(3).campo.getText(),
                            validacionesList.get(4).campo.getText(),
                            validacionesList.get(5).campo.getText(),
                            validacionesList.get(6).campo.getText(),
                            validacionesList.get(7).campo.getText(),
                            validacionesList.get(8).campo.getText());
                    agenda.editarContacto(c.id, contactoEditado);
                    Refresh.refreshContacts((DataBase) dataBase, container);
                    frame.dispose();
                }
            }
        });
    }

    private boolean validarCampos() {
        for (Validaciones validacion : validacionesList) {
            if (!validacion.validarEntrada(validacion.campo.getText())) {
                JOptionPane.showMessageDialog(frame, validacion.mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
