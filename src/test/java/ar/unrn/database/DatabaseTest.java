package ar.unrn.database;

import ar.unrn.contactos.Contacto;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;


@DisplayName("Test de Database")
class DatabaseTest {

    private DataBase database;

    @DisplayName("Se crea una base de datos en memoria")
    @BeforeEach
    public void setUp() throws SQLException {
        // Usar una base de datos en memoria para pruebas
        // https://www.sqlite.org/inmemorydb.html
        database = new DataBase(":memory:");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Cerrar la conexión de la base de datos después de cada prueba
        database.close();
    }

    @DisplayName("Envio de contacto")
    @Test
    public void testPostContacto() throws SQLException {
        Contacto contacto = new Contacto("John", "Doe", "1234567890",
                "john.doe@example.com", "Notas", "Argentina", "Buenos Aires", "CABA",
                "Calle Falsa 123");
        database.postContacto(contacto);

        List<Contacto> contactos = database.getContactos(true);
        Assertions.assertEquals(1, contactos.size());
        Assertions.assertEquals(contacto, contactos.get(0));
    }

    @DisplayName("Eliminacion logica de contacto")
    @Test
    public void testDeletearContacto() throws SQLException {
        Contacto contacto = new Contacto("John", "Doe", "1234567890",
                "john.doe@example.com", "Notas", "Argentina", "Buenos Aires", "CABA",
                "Calle Falsa 123");
        database.postContacto(contacto);

        database.deletear(contacto);

        List<Contacto> contactos = database.getContactos(true);
        Assertions.assertEquals(0, contactos.size());
    }

    @DisplayName("Actualizacion contacto")
    @Test
    public void testActualizarContacto() throws SQLException {
        Contacto contacto = new Contacto("John", "Doe", "1234567890",
                "john.doe@example.com", "Notas", "Argentina", "Buenos Aires", "CABA",
                "Calle Falsa 123");
        database.postContacto(contacto);

        List<Object> data = contacto.deshidratarContacto();
        String uuid = data.get(0).toString();
        System.out.println("UUID: " + uuid);

        Contacto contactoActualizado = new Contacto(uuid, "John",
                "Doe", "0987654321", "john.doe@newexample.com", "Nuevas Notas", "Argentina",
                "Buenos Aires", "CABA", "Calle Verdadera 456");

        database.actualizar(contactoActualizado);

        List<Contacto> contactos = database.getContactos(true);

        List<Object> data1 = contactos.get(0).deshidratarContacto();
        String email = data1.get(4).toString();
        Assertions.assertEquals(1, contactos.size());
        Assertions.assertEquals(email, "john.doe@newexample.com");

        String calle = data1.get(9).toString();
        Assertions.assertEquals(calle, "Calle Verdadera 456");

        String telefono = data1.get(3).toString();
        Assertions.assertEquals(telefono, "0987654321");

        String notas = data1.get(5).toString();
        Assertions.assertEquals(notas, "Nuevas Notas");

        String street = data1.get(9).toString();
        Assertions.assertEquals(street, "Calle Verdadera 456");

    }

    @DisplayName("Eliminacion logica de contacto")
    @Test
    public void testLogicalDeleteContacto() throws SQLException {
        Contacto contacto = new Contacto("John", "Doe", "1234567890",
                "john.doe@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle " +
                "Falsa 123");
        database.postContacto(contacto);
        List<Object> data = contacto.deshidratarContacto();
        String uuid = data.get(0).toString();


        database.logicalDeleteContacto(uuid);

        List<Contacto> contactos = database.getContactos(true);
        Assertions.assertEquals(0, contactos.size());

        List<Contacto> contactosInactivos = database.getContactos(false);
        Assertions.assertEquals(1, contactosInactivos.size());
        Assertions.assertEquals(contacto, contactosInactivos.get(0));
    }

    @DisplayName("Obtener contactos")
    @Test
    public void testGetContactos() throws SQLException {
        Contacto contacto1 = new Contacto("John", "Doe", "1234567890",
                "john.doe@example.com", "Notas", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");

        Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321",
                "jane.smith@example.com", "Notas2", "Argentina", "Buenos Aires", "CABA",
                "Calle Falsa 456");
        database.postContacto(contacto1);
        database.postContacto(contacto2);

        List<Contacto> contactos = database.getContactos(true);
        Assertions.assertEquals(2, contactos.size());
        Assertions.assertTrue(contactos.contains(contacto1));
        Assertions.assertTrue(contactos.contains(contacto2));
    }
}