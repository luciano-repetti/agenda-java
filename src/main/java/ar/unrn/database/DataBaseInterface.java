package ar.unrn.database;

import ar.unrn.contactos.Contacto;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define los métodos necesarios para interactuar con una base de datos que gestiona contactos.
 */
public interface DataBaseInterface {
    /**
     * Método para cerrar la conexión con la base de datos.
     *
     * @throws SQLException Si ocurre algún error durante el cierre de la conexión.
     */
    public void close() throws SQLException;
    /**
     * Método para crear la tabla de contactos en la base de datos si no existe.
     *
     * @throws SQLException Si ocurre algún error durante la creación de la tabla.
     */
    public void createTable() throws SQLException;

    /**
     * Método para insertar un nuevo contacto en la base de datos.
     *
     * @param contacto El contacto a insertar.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    public void postContacto(Contacto contacto) throws SQLException;

    /**
     * Método para actualizar los datos de un contacto existente en la base de datos.
     *
     * @param contacto El contacto con los datos actualizados.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    public void updateContacto(Contacto contacto) throws SQLException;
    /**
     * Método para realizar una eliminación lógica de un contacto en la base de datos.
     *
     * @param uuid El UUID del contacto a eliminar.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    public void logicalDeleteContacto(String uuid) throws SQLException;

    /**
     * Método para obtener una lista de contactos activos o inactivos de la base de datos.
     *
     * @param active true si se desean obtener los contactos activos, false si se desean obtener los inactivos.
     * @return Una lista de contactos que cumplen con el estado especificado.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    public List<Contacto> getContactos(Boolean active) throws SQLException;

}
