package ar.unrn.database;

import ar.unrn.contactos.Contacto;

import java.sql.SQLException;
import java.util.List;

public class DataBaseApp {
    public static void main(String[] args) {
        try {
        String databaseUrl = "test3.db";
        DataBase db = new DataBase(databaseUrl);

        // Crear un nuevo contacto
        Contacto nuevoContacto = new Contacto("Juan", "Perez", "123456789", "juan.perez@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");
        Contacto nuevoContacto2 = new Contacto("Pedro", "Gonzalez", "123456789", "pedro.gonzalez@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");
        //Contacto nuevoContacto3 = new Contacto("251bf0ab-eff7-49c7-af0b-099b12e3c22b","NAASDASD", "asdasd", "123456789", "maria.lopez@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");
        // Agregar el contacto a la base de datos
        db.postContacto(nuevoContacto);
        db.postContacto(nuevoContacto2);
        //db.postContacto(nuevoContacto3);

        // Obtener y mostrar todos los contactos
        List<Contacto> contactos = db.getContactos(false);
        for (Contacto contacto : contactos) {
            System.out.println("ID: " + contacto.id);
            System.out.println("Nombre: " + contacto.nombre);
            System.out.println("Apellido: " + contacto.apellido);
            System.out.println("Número de Teléfono: " + contacto.numeroTelefono);
            System.out.println("Email: " + contacto.email);
            System.out.println("Notas: " + contacto.notas);
            System.out.println("Dirección: " + contacto.direccion.calle + ", " + contacto.direccion.ciudad + ", " + contacto.direccion.provincia + ", " + contacto.direccion.pais);
            System.out.println("-----");
        }


        // Eliminar el contacto recién agregado
        db.logicalDeleteContacto("b1d17f50-dcd7-4915-887f-d4ec73d32e08");

        // Cerrar la conexión a la base de datos
        db.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}