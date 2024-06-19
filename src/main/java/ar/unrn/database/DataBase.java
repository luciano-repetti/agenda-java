package ar.unrn.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.File;

import ar.unrn.contactos.Contacto;

import java.util.List;

public class DataBase implements DataBaseInterface {
    Connection conexion;

    private void createDatabase(String databaseUrl) {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
            conexion.close();
            System.out.println("Base de datos creada."); // Para debuggear
        } catch (SQLException e) {
            System.err.println("Error al crear la base de datos: " + e.getMessage()); // Para debuggear
        }
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
            String sql = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad INTEGER)";
            statement.execute(sql);
            System.out.println("Tabla creada correctamente."); // Para debuggear
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage()); // Para debuggear
        }
    }

    @Override
    public void postContacto(Contacto contacto) {


    }

    @Override
    public void updateContacto(Contacto contacto) {

    }

    @Override
    public void deleteContacto(Contacto contacto) {

    }

    @Override
    public List<Contacto> getContactos() {
        return null;
    }

}