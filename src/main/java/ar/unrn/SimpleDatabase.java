package ar.unrn;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleDatabase {
    private Connection connection;

    public SimpleDatabase(String databaseUrl) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
            System.out.println("Conexion establecida a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver de SQLite: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER)";
            statement.execute(sql);
            System.out.println("Tabla creada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    public void insertUsuario(String nombre, int edad) {
        try {
            String sql = "INSERT INTO usuarios (nombre, edad) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setInt(2, edad);
                statement.executeUpdate();
            }
            System.out.println("Usuario agregado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    public void deleteUsuarios() {
        try {
            String sql = "DELETE FROM usuarios";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
            System.out.println("Todos los usuarios han sido eliminados.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuarios: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String databaseUrl = "test2.db";
        if (!databaseExists(databaseUrl)) {
            createDatabase(databaseUrl);
        }

        SimpleDatabase db = new SimpleDatabase(databaseUrl);
        db.createTable();
        db.deleteUsuarios();
        db.insertUsuario("Jesus", 19);
        db.insertUsuario("Pedro", 19);
        db.insertUsuario("Martin", 30);
        db.insertUsuario("Lucho", 20);
        db.close();
    }

    private static boolean databaseExists(String databaseUrl) {
        File file = new File(databaseUrl);
        return file.exists();
    }

    private static void createDatabase(String databaseUrl) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
            connection.close();
            System.out.println("Base de datos creada.");
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
}
