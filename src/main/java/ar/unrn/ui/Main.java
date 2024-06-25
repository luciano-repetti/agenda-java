package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import java.sql.SQLException;

/**
 * Clase principal para inicializar y ejecutar la aplicación del Sistema de
 * Gestión de Contactos.
 * Configura la conexión a la base de datos, recupera los datos de contacto y
 * arranca el sistema de interfaz de usuario.
 */
public class Main {
    private static String databaseUrl;

    private static DataBaseInterface database;
    private static Agenda agenda;

    /**
     * Punto de entrada principal de la aplicación.
     * Este método inicializa la conexión a la base de datos, recupera los datos de
     * contacto y arranca el Sistema de Gestión de Contactos.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta
     *             aplicación).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    public static void main(String[] args) throws SQLException {
        databaseUrl = "test.db";

        DataBaseInterface database = new DataBase(databaseUrl);
        agenda = Agenda.getAgenda(database.getContactos(true));

        new ContactManagementSystem(databaseUrl, database, agenda);
    }
}
