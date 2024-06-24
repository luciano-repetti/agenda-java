package ar.unrn.validaciones;

import org.junit.jupiter.api.*;

import javax.swing.*;


@DisplayName("Test de Validaciones")
class ValidacionesTest {


    private Validaciones validacionEmail;
    private Validaciones validacionTelefono;
    private Validaciones validacionNombre;

    @BeforeEach
    public void setUp() {
        validacionEmail = new Validaciones("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", "Email inválido", new JTextField());
        validacionTelefono = new Validaciones("^\\d{10}$", "Número de teléfono inválido", new JTextField());
        validacionNombre = new Validaciones("^[A-Za-z ]+$", "Nombre inválido", new JTextField());
    }

    @DisplayName("Entrada de Email Valido")
    @Test
    public void testValidarEntradaEmailValido() {
        Assertions.assertTrue(validacionEmail.validarEntrada("correo@example.com"));
    }

    @DisplayName("Entrada de Email Invalido")
    @Test
    public void testValidarEntradaEmailInvalido() {
        Assertions.assertFalse(validacionEmail.validarEntrada("correo@.com"));
    }

    @DisplayName("Entrada de Telefono Valido")
    @Test
    public void testValidarEntradaTelefonoValido() {
        Assertions.assertTrue(validacionTelefono.validarEntrada("1234567890"));
    }

    @DisplayName("Entrada de Telefono Invalido")
    @Test
    public void testValidarEntradaTelefonoInvalido() {
        Assertions.assertFalse(validacionTelefono.validarEntrada("12345"));
    }

    @DisplayName("Entrada de Nombre Valido")
    @Test
    public void testValidarEntradaNombreValido() {
        Assertions.assertTrue(validacionNombre.validarEntrada("John Doe"));
    }

    @DisplayName("Entrada de Nombre Invalido")
    @Test
    public void testValidarEntradaNombreInvalido() {
        Assertions.assertFalse(validacionNombre.validarEntrada("John123"));
    }
}
