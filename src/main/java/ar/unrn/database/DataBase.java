package ar.unrn.database;

import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz DataBaseInterface que gestiona la base de datos SQLite para almacenar contactos.
 * Actúa como un observador para los cambios en la lista de contactos.
 */
public class DataBase implements DataBaseInterface, Observer {
    /**
     * Atributo que representa la conexion a la DB
     */
    private Connection conexion;

    /**
     * Constructor que establece la conexión con la base de datos SQLite y crea la tabla de usuarios si no existe.
     *
     * @param databaseUrl La URL de la base de datos SQLite.
     * @throws SQLException Si ocurre algún error durante la creación de la base de datos.
     */
    public DataBase(String databaseUrl) throws SQLException {
        createDatabase(databaseUrl);
        createTable();
    }

    /**
     * Método protegido para crear la conexión con la base de datos SQLite.
     *
     * @param databaseUrl La URL de la base de datos SQLite.
     * @throws SQLException Si ocurre algún error durante la conexión.
     */
    protected void createDatabase(String databaseUrl) throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     *
     * @throws SQLException Si ocurre algún error durante el cierre de la conexión.
     */
    @Override
    public void close() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    /**
     * Método para crear la tabla 'usuarios' en la base de datos si no existe.
     *
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    @Override
    public void createTable() throws SQLException {
        Statement statement = conexion.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (uuid TEXT PRIMARY KEY, nombre TEXT, apellido TEXT, numeroTelefono TEXT, email TEXT, notas TEXT, pais TEXT, provincia TEXT, ciudad TEXT, calle TEXT, active BOOLEAN)";
        statement.execute(sql);
    }

    /**
     * Método para insertar un nuevo contacto en la base de datos.
     *
     * @param contacto El contacto a insertar.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    @Override
    public void postContacto(Contacto contacto) throws SQLException {
        String sql = "INSERT INTO usuarios (uuid, nombre, apellido, numeroTelefono, email, notas, pais, provincia, ciudad, calle, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, contacto.id.toString());
            statement.setString(2, contacto.nombre);
            statement.setString(3, contacto.apellido);
            statement.setString(4, contacto.numeroTelefono);
            statement.setString(5, contacto.email);
            statement.setString(6, contacto.notas);
            statement.setString(7, contacto.direccion.pais);
            statement.setString(8, contacto.direccion.provincia);
            statement.setString(9, contacto.direccion.ciudad);
            statement.setString(10, contacto.direccion.calle);
            statement.setBoolean(11, true);
            statement.executeUpdate();
        }
    }

    /**
     * Método para eliminar lógicamente un contacto de la base de datos.
     *
     * @param contacto El contacto a eliminar.
     */
    @Override
    public void deletear(Contacto contacto) {
        try {
            logicalDeleteContacto(contacto.id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para añadir un nuevo contacto a la base de datos.
     *
     * @param contacto El contacto a añadir.
     */
    @Override
    public void aniadir(Contacto contacto) {
        try {
            postContacto(contacto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para actualizar un contacto existente en la base de datos.
     *
     * @param contacto El contacto actualizado.
     */
    @Override
    public void actualizar(Contacto contacto) {
        try {
            updateContacto(contacto); // Cambié updateContacto a postContacto aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para actualizar los datos de un contacto en la base de datos.
     *
     * @param contacto El contacto con los datos actualizados.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    @Override
    public void updateContacto(Contacto contacto) throws SQLException {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, numeroTelefono = ?, email = ?, notas = ?, pais = ?, provincia = ?, ciudad = ?, calle = ? WHERE uuid = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, contacto.nombre);
            statement.setString(2, contacto.apellido);
            statement.setString(3, contacto.numeroTelefono);
            statement.setString(4, contacto.email);
            statement.setString(5, contacto.notas);
            statement.setString(6, contacto.direccion.pais);
            statement.setString(7, contacto.direccion.provincia);
            statement.setString(8, contacto.direccion.ciudad);
            statement.setString(9, contacto.direccion.calle);
            statement.setString(10, contacto.id.toString());
            statement.executeUpdate();
        }
    }

    /**
     * Método para realizar una eliminación lógica de un contacto en la base de datos.
     *
     * @param uuid El UUID del contacto a eliminar.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    @Override
    public void logicalDeleteContacto(String uuid) throws SQLException {
        String sql = "UPDATE usuarios SET active = false WHERE uuid = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.executeUpdate();
        }
    }

    /**
     * Método para obtener una lista de contactos activos o inactivos de la base de datos.
     *
     * @param active true si se desean obtener los contactos activos, false si se desean obtener los inactivos.
     * @return Una lista de contactos que cumplen con el estado especificado.
     * @throws SQLException Si ocurre algún error durante la ejecución de la consulta SQL.
     */
    @Override
    public List<Contacto> getContactos(Boolean active) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE active = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setBoolean(1, active);
            ResultSet resultSet = statement.executeQuery();
            List<Contacto> contactos = new ArrayList<>();
            while (resultSet.next()) {
                String uuid = resultSet.getString("uuid");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String numeroTelefono = resultSet.getString("numeroTelefono");
                String email = resultSet.getString("email");
                String notas = resultSet.getString("notas");
                String pais = resultSet.getString("pais");
                String provincia = resultSet.getString("provincia");
                String ciudad = resultSet.getString("ciudad");
                String calle = resultSet.getString("calle");

                Contacto contacto = new Contacto(uuid, nombre, apellido, numeroTelefono, email, notas, pais, provincia,
                        ciudad, calle);
                contactos.add(contacto);
            }
            return contactos;
        }
    }

    /**
     * Método que implementa la función traerContactos de la interfaz Observer para obtener una lista de contactos activos o inactivos.
     *
     * @param active true si se desean obtener los contactos activos, false si se desean obtener los inactivos.
     * @return Una lista de contactos que cumplen con el estado especificado.
     */
    @Override
    public List<Contacto> traerContactos(Boolean active) {
        try {
            return getContactos(active);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
