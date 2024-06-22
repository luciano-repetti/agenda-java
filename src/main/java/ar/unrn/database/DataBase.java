package ar.unrn.database;

import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements DataBaseInterface, Observer {
    private Connection conexion;

    public DataBase(String databaseUrl) throws SQLException {
        createDatabase(databaseUrl);
        createTable();
    }

    protected void createDatabase(String databaseUrl) throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
    }

    @Override
    public void close() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = conexion.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (uuid TEXT PRIMARY KEY, nombre TEXT, apellido TEXT, numeroTelefono TEXT, email TEXT, notas TEXT, pais TEXT, provincia TEXT, ciudad TEXT, calle TEXT, active BOOLEAN)";
        statement.execute(sql);
    }

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

    @Override
    public void deletear(Contacto contacto) {
        try {
            logicalDeleteContacto(contacto.id.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aniadir(Contacto contacto) {
        try {
            postContacto(contacto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Contacto contacto) {
        try {
            updateContacto(contacto); // Cambié updateContacto a postContacto aquí
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void logicalDeleteContacto(String uuid) throws SQLException {
        String sql = "UPDATE usuarios SET active = false WHERE uuid = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, uuid);
            statement.executeUpdate();
        }
    }

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
