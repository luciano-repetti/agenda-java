package ar.unrn.database;

import java.io.File;
import java.sql.*;

import ar.unrn.contactos.Contacto;

import java.util.ArrayList;
import java.util.List;

public class DataBase implements DataBaseInterface {
    Connection conexion;

    public DataBase(String databaseUrl) {

        try {
            createDatabase(databaseUrl);
            createTable();
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la base de datos", e);
        }
    }

    public static void databaseExists(String databaseUrl) {
        File file = new File(databaseUrl);
        file.exists();
    }

    protected void createDatabase(String databaseUrl) throws SQLException {
        conexion = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
        //conexion.close();
        System.out.println("Base de datos creada."); // Para debuggear

    }

    @Override
    public void close() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage()); // Para debuggear
        }
    }

    @Override
    public void createTable() {
        try {
            Statement statement = conexion.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (uuid TEXT PRIMARY KEY, nombre TEXT, apellido TEXT, numeroTelefono TEXT, email TEXT, notas TEXT, pais TEXT, provincia TEXT, ciudad TEXT, calle TEXT)";
            statement.execute(sql);
            System.out.println("Tabla creada correctamente."); // Para debuggear
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage()); // Para debuggear
        }
    }

    @Override
    public void postContacto(Contacto contacto) {
        try {
            // Add isDeleted?
            String sql = "INSERT INTO usuarios (uuid, nombre, apellido, numeroTelefono, email, notas, pais, provincia,ciudad, calle) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                statement.executeUpdate();
            }
            System.out.println("Usuario agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }

    }

    @Override
    public void updateContacto(Contacto contacto) {

    }

    @Override
    public void deleteContacto(String uuid) {
        // Delete using UUID
        try {
            String sql = "DELETE FROM usuarios WHERE uuid = ?";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, uuid);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("El usuario con UUID " + uuid + " ha sido eliminado.");
                } else {
                    System.out.println("No se encontró ningún usuario con UUID " + uuid + ".");
                }
            }
            System.out.println("Todos los usuarios han sido eliminados.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuarios: " + e.getMessage());
        }
    }

    @Override
    public List<Contacto> getContactos() {
        String sql = "SELECT * FROM usuarios";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
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

                Contacto contacto = new Contacto(uuid, nombre, apellido, numeroTelefono, email, notas, pais, provincia, ciudad, calle);
                contactos.add(contacto);
            }
            return contactos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}