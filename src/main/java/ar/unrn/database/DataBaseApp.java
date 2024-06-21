package ar.unrn.database;

import ar.unrn.contactos.Contacto;

import java.util.List;

public class DataBaseApp {
    public static void main(String[] args) {
        String databaseUrl = "test.db";
        DataBase db = new DataBase(databaseUrl);

        // Crear un nuevo contacto
        Contacto nuevoContacto = new Contacto("Juan", "Perez", "123456789", "juan.perez@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");
        Contacto nuevoContacto2 = new Contacto("Pedro", "Gonzalez", "123456789", "pedro.gonzalez@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");

        // Agregar el contacto a la base de datos
        db.postContacto(nuevoContacto);
        db.postContacto(nuevoContacto2);

        // Obtener y mostrar todos los contactos
        List<Contacto> contactos = db.getContactos();
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
        //db.deleteContacto(nuevoContacto.id.toString());

        // Cerrar la conexión a la base de datos
        db.close();
    }
}