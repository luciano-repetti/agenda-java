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

/**
 * Clase que gestiona la ventana para agregar o editar contactos.
 */
public class OpenContact {

    private static Agenda agenda;
    private List<Validaciones> validacionesList = new ArrayList<>();
    private JFrame frame;
    private JPanel container;

    /**
     * Constructor para crear un nuevo contacto.
     *
     * @param dataBase  La interfaz de la base de datos.
     * @param container El contenedor donde se mostrarán los contactos.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
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

    /**
     * Constructor para ver o editar un contacto existente.
     *
     * @param operando  La operación a realizar ("view" o "edicion").
     * @param c         El contacto a ver o editar.
     * @param dataBase  La interfaz de la base de datos.
     * @param container El contenedor donde se mostrarán los contactos.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
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

    /**
     * Inicializa la agenda con los contactos de la base de datos.
     *
     * @param dataBase La interfaz de la base de datos.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    private void inicializarAgenda(DataBaseInterface dataBase) throws SQLException {
        try {
            agenda = Agenda.getAgenda(dataBase.getContactos(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configura el marco de la ventana.
     *
     * @param frame El marco de la ventana.
     */
    private void configurarFrame(JFrame frame) {
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
    }

    /**
     * Crea el formulario de contacto.
     *
     * @param contacto    El contacto a mostrar o editar.
     * @param desactivado Indica si los campos están desactivados.
     * @return Un panel con el formulario de contacto.
     */
    private JPanel crearFormularioContacto(Contacto contacto, boolean desactivado) {
        JPanel table = new JPanel(new GridLayout(11, 2, 15, 15));
        table.setBackground(Color.white);
        table.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        aniadirCampoValidacion(table, "Nombre: ", contacto.nombre, "^[a-zA-Z ]{1,20}$",
                "La entrada nombre debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Apellido: ", contacto.apellido, "^[a-zA-Z ]{1,20}$",
                "La entrada apellido debe contener solo letras y tener maximo 20 caracteres.", desactivado);

        aniadirCampoValidacion(table, "Numero de telefono: ", contacto.numeroTelefono, "^[0-9+]{1,15}$",
                "La entrada numero de telefono debe contener solo numeros y como maximo 15 caracteres, incluyendo el simbolo '+'.",
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

    /**
     * Añade un campo de validación al formulario.
     *
     * @param table        El panel donde se añade el campo.
     * @param etiqueta     La etiqueta del campo.
     * @param valor        El valor del campo.
     * @param regex        La expresión regular para validar el campo.
     * @param mensajeError El mensaje de error si la validación falla.
     * @param desactivo    Indica si el campo está desactivado.
     */
    private void aniadirCampoValidacion(JPanel table, String etiqueta, String valor, String regex,
            String mensajeError, boolean desactivo) {
        table.add(GUI.label(etiqueta)).setForeground(Color.black);
        if (desactivo) {
            JTextField campo = GUI.textField(valor);
            campo.setEditable(desactivo);
            table.add(campo);
            validacionesList.add(new Validaciones(regex, mensajeError, campo));
        } else {
            JScrollPane campo = GUI.textArea(valor);

            table.add(campo);
        }

    }

    /**
     * Configura la acción del botón cancelar.
     *
     * @param cancel El botón cancelar.
     */
    private void cancelarAccion(JButton cancel) {
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    /**
     * Configura la acción del botón guardar para un nuevo contacto.
     *
     * @param save     El botón guardar.
     * @param dataBase La interfaz de la base de datos.
     */
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

    /**
     * Configura la acción del botón guardar para la edición de un contacto
     * existente.
     *
     * @param save     El botón guardar.
     * @param c        El contacto a editar.
     * @param dataBase La interfaz de la base de datos.
     */
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

    /**
     * Valida los campos del formulario.
     *
     * @return true si todos los campos son válidos, false en caso contrario.
     */
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
