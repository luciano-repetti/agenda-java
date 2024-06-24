package ar.unrn.contactos;

import org.junit.jupiter.api.*;


@DisplayName("Test de Contactos")
class ContactosTest {

    private Contacto contacto1;
    private Contacto contacto2;
    private Contacto contacto3;

    @BeforeEach
    public void setUp() {
        contacto1 = new Contacto("John", "Doe", "123456789", "john.doe@example.com", "Nota1", "USA", "California", "Los Angeles", "Calle 1");
        contacto2 = new Contacto(contacto1.id.toString(), "Jane", "Smith", "987654321", "jane.smith@example.com", "Nota2", "USA", "California", "Los Angeles", "Calle 2");
        contacto3 = new Contacto("Alice", "Johnson", "111111111", "alice.johnson@example.com", "Nota3", "USA", "New York", "New York City", "Calle 3");
    }

    @DisplayName("Creacion de contacto")
    @Test
    public void testCreacionContacto() {
        Assertions.assertNotNull(contacto1);
        Assertions.assertEquals("Doe", contacto1.apellido);
        Assertions.assertEquals("John", contacto1.nombre);
        Assertions.assertEquals("123456789", contacto1.numeroTelefono);
        Assertions.assertEquals("john.doe@example.com", contacto1.email);
        Assertions.assertEquals("Nota1", contacto1.notas);
        Assertions.assertEquals("USA", contacto1.direccion.pais);
        Assertions.assertEquals("California", contacto1.direccion.provincia);
        Assertions.assertEquals("Los Angeles", contacto1.direccion.ciudad);
        Assertions.assertEquals("Calle 1", contacto1.direccion.calle);
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
        Contacto nuevoContacto = new Contacto("Bob", "Brown", "222222222", "bob.brown@example.com", "Nota4", "USA", "Nevada", "Las Vegas", "Calle 4");
        contacto1.modificarContacto(nuevoContacto);

        Assertions.assertEquals("Bob", contacto1.nombre);
        Assertions.assertEquals("Brown", contacto1.apellido);
        Assertions.assertEquals("222222222", contacto1.numeroTelefono);
        Assertions.assertEquals("bob.brown@example.com", contacto1.email);
        Assertions.assertEquals("Nota4", contacto1.notas);
        Assertions.assertEquals("USA", contacto1.direccion.pais);
        Assertions.assertEquals("Nevada", contacto1.direccion.provincia);
        Assertions.assertEquals("Las Vegas", contacto1.direccion.ciudad);
        Assertions.assertEquals("Calle 4", contacto1.direccion.calle);
    }

    @DisplayName("Actualizacion de atributos")
    @Test
    public void testActualizarAtributo() {
        contacto1.actualizarAtributo("nombre", "Michael");
        contacto1.actualizarAtributo("apellido", "Scott");
        contacto1.actualizarAtributo("numerotelefono", "333333333");
        contacto1.actualizarAtributo("email", "michael.scott@example.com");
        contacto1.actualizarAtributo("notas", "Nota5");
        contacto1.actualizarAtributo("pais", "Canada");
        contacto1.actualizarAtributo("provincia", "Ontario");
        contacto1.actualizarAtributo("ciudad", "Toronto");
        contacto1.actualizarAtributo("calle", "Calle 5");

        Assertions.assertEquals("Michael", contacto1.nombre);
        Assertions.assertEquals("Scott", contacto1.apellido);
        Assertions.assertEquals("333333333", contacto1.numeroTelefono);
        Assertions.assertEquals("michael.scott@example.com", contacto1.email);
        Assertions.assertEquals("Nota5", contacto1.notas);
        Assertions.assertEquals("Canada", contacto1.direccion.pais);
        Assertions.assertEquals("Ontario", contacto1.direccion.provincia);
        Assertions.assertEquals("Toronto", contacto1.direccion.ciudad);
        Assertions.assertEquals("Calle 5", contacto1.direccion.calle);
    }

    @DisplayName("Actualizacion de atributos desconocidos")
    @Test
    public void testActualizarAtributoDesconocido() {
        try {
            contacto1.actualizarAtributo("atributoDesconocido", "valor");
            Assertions.fail("No se debería llegar a este punto");
        } catch (IllegalArgumentException e) {
            System.out.println("Tedría que haber llegado aca");
        }
    }
}