package ar.unrn.contactos;

import org.junit.jupiter.api.*;

import java.util.List;

@DisplayName("Test de Contactos")
class ContactosTest {

    private Contacto contacto1;
    private Contacto contacto2;
    private Contacto contacto3;

    @BeforeEach
    public void setUp() {
        contacto1 = new Contacto("John", "Doe", "123456789", "john.doe@example.com",
                "Nota1", "USA", "California", "Los Angeles", "Calle 1");
        List<Object> list = contacto1.deshidratarContacto();
        String uuid = list.get(0).toString();

        contacto2 = new Contacto(uuid, "Jane", "Smith",
                "987654321", "jane.smith@example.com", "Nota2", "USA", "California", "Los Angeles", " Calle 2");

        contacto3 = new Contacto("Alice", "Johnson", "111111111",
                "alice.johnson@example.com", "Nota3", "USA", "New York", "New York City",
                "Calle 3");
    }

    @DisplayName("Creacion de contacto")
    @Test
    public void testCreacionContacto() {
        List<Object> data = contacto1.deshidratarContacto();
        String nombre = data.get(1).toString();
        String apellido = data.get(2).toString();
        String telefono = data.get(3).toString();
        String email = data.get(4).toString();
        String notas = data.get(5).toString();
        String pais = data.get(6).toString();
        String provincia = data.get(7).toString();
        String ciudad = data.get(8).toString();
        String calle = data.get(9).toString();


        Assertions.assertNotNull(contacto1);
        Assertions.assertEquals("John", nombre);
        Assertions.assertEquals("Doe", apellido);
        Assertions.assertEquals("123456789", telefono);
        Assertions.assertEquals("john.doe@example.com", email);
        Assertions.assertEquals("Nota1", notas);
        Assertions.assertEquals("USA", pais);
        Assertions.assertEquals("California", provincia);
        Assertions.assertEquals("Los Angeles", ciudad);
        Assertions.assertEquals("Calle 1", calle);
    }

    @DisplayName("Comparacion de contactos")
    @Test
    public void testIgualdadContactos() {
        Assertions.assertTrue(contacto1.equals(contacto2));
        Assertions.assertFalse(contacto1.equals(contacto3));
    }

    @DisplayName("Modificacion de contacto")
    @Test
    public void testModificarContacto() {
        Contacto nuevoContacto = new Contacto("Bob", "Brown", "222222222",
                "bob.brown@example.com", "Nota4", "USA", "Nevada", "Las Vegas", "Calle 4");
        List<Object> dataTest = nuevoContacto.deshidratarContacto();
        contacto1.hidratarContacto(dataTest);


        List<Object> data = contacto1.deshidratarContacto();
        String nombre = data.get(1).toString();
        String apellido = data.get(2).toString();
        String telefono = data.get(3).toString();
        String email = data.get(4).toString();
        String notas = data.get(5).toString();
        String pais = data.get(6).toString();
        String provincia = data.get(7).toString();
        String ciudad = data.get(8).toString();
        String calle = data.get(9).toString();

        Assertions.assertEquals("Bob", nombre);
        Assertions.assertEquals("Brown", apellido);
        Assertions.assertEquals("222222222", telefono);
        Assertions.assertEquals("bob.brown@example.com", email);
        Assertions.assertEquals("Nota4", notas);
        Assertions.assertEquals("USA", pais);
        Assertions.assertEquals("Nevada", provincia);
        Assertions.assertEquals("Las Vegas", ciudad);
        Assertions.assertEquals("Calle 4", calle);
    }

}