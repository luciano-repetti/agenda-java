// package ar.unrn.agenda;

// import ar.unrn.agenda.strategyBusqueda.EstrategiaBusqueda;
// import ar.unrn.contactos.Contacto;
// import ar.unrn.contactos.Observer;
// import org.junit.jupiter.api.*;

// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// @DisplayName("Test de Agenda")
// class AgendaTest {

// /**
// * Este test es una plantilla.
// */
// @Test
// @DisplayName("Completar que se esta probando")
// void testPlantilla() {
// ;//
// }

// private Agenda agenda;
// private ObserverMock observer;
// private int contador = 0;

// @BeforeEach
// public void setUp() {
// if (contador >= 0) {
// agenda = Agenda.getAgenda(new ArrayList<>());
// observer = new ObserverMock();
// agenda.registrarObserver(observer);
// }
// contador++;
// }

// @AfterEach
// public void tearDown() {
// if (contador >= 0) {
// agenda.limpiarAgenda();
// }
// contador++;
// }

// @Test
// public void testAgregarContacto() {
// Contacto contacto = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// try {
// agenda.agregarContacto(contacto);
// } catch (Exception e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// Assertions.assertEquals(1, agenda.cantidadContactos());
// Assertions.assertTrue(observer.addCalled);
// }

// @Test
// public void testEliminarContacto() {
// Contacto contacto = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// try {
// agenda.agregarContacto(contacto);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }
// try {
// agenda.eliminarContacto(contacto.id);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// Assertions.assertEquals(0, agenda.cantidadContactos());
// Assertions.assertTrue(observer.deleteCalled);
// }

// @Test
// public void testModificarAtributoContacto() {
// Contacto contacto = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// try {
// agenda.agregarContacto(contacto);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }
// try {
// agenda.modificarAtributoContacto(contacto.id, "numeroTelefono",
// "0987654321");
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// Assertions.assertEquals("0987654321", contacto.numeroTelefono);
// Assertions.assertTrue(observer.updateCalled);
// }

// @Test
// public void testEditarContacto() {
// Contacto contacto = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// try {
// agenda.agregarContacto(contacto);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }
// Contacto contactoEditado = new Contacto(contacto.id.toString(), "John",
// "Doe", "0987654321",
// "john.doe@newexample.com", "Nuevas Notas", "Argentina", "Buenos Aires",
// "CABA", "Calle Verdadera 456");
// try {
// agenda.editarContacto(contacto.id, contactoEditado);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// Assertions.assertEquals("0987654321", contacto.numeroTelefono);
// Assertions.assertEquals("john.doe@newexample.com", contacto.email);
// Assertions.assertEquals("Nuevas Notas", contacto.notas);
// Assertions.assertEquals("Calle Verdadera 456", contacto.direccion.calle);
// Assertions.assertTrue(observer.updateCalled);
// }

// @Test
// public void testBuscarContacto() {
// Contacto contacto1 = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321",
// "jane.smith@example.com", "Notas2",
// "Argentina", "Buenos Aires", "CABA", "Calle Falsa 456");
// try {
// agenda.agregarContacto(contacto1);
// agenda.agregarContacto(contacto2);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// EstrategiaBusquedaNombre estrategiaBusqueda = new EstrategiaBusquedaNombre();
// List<Contacto> resultados = agenda.buscarContacto(estrategiaBusqueda,
// "John");

// resultados.toString();
// Assertions.assertEquals(1, resultados.size());
// Assertions.assertEquals(contacto1, resultados.get(0));
// }

// @Test
// public void testVerContactos() {
// Contacto contacto1 = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321",
// "jane.smith@example.com", "Notas2",
// "Argentina", "Buenos Aires", "CABA", "Calle Falsa 456");
// try {
// agenda.agregarContacto(contacto1);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }
// try {
// agenda.agregarContacto(contacto2);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// // Este test solo verifica que el método no cause errores.
// // En una situación real, deberías capturar la salida del sistema y verificar
// su
// // contenido.
// agenda.verContactos();
// }

// @Test
// public void testLimpiarAgenda() {
// Contacto contacto1 = new Contacto("John", "Doe", "1234567890",
// "john.doe@example.com", "Notas", "Argentina",
// "Buenos Aires", "CABA", "Calle Falsa 123");
// Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321",
// "jane.smith@example.com", "Notas2",
// "Argentina", "Buenos Aires", "CABA", "Calle Falsa 456");
// try {
// agenda.agregarContacto(contacto1);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }
// try {
// agenda.agregarContacto(contacto2);
// } catch (SQLException e) {
// Assertions.fail("No deberías haber llegado hasta aca");
// e.printStackTrace();
// }

// agenda.limpiarAgenda();

// Assertions.assertEquals(0, agenda.cantidadContactos());
// }

// private static class ObserverMock implements Observer {
// public boolean addCalled = false;
// public boolean deleteCalled = false;
// public boolean updateCalled = false;

// @Override
// public void aniadir(Contacto contacto) {
// addCalled = true;
// }

// @Override
// public void deletear(Contacto contacto) {
// deleteCalled = true;
// }

// @Override
// public List<Contacto> traerContactos(Boolean active) {
// return List.of();
// }

// @Override
// public void actualizar(Contacto contacto) {
// updateCalled = true;
// }
// }

// private static class EstrategiaBusquedaNombre implements
// EstrategiaBusqueda<String> {
// @Override
// public List<Contacto> buscar(Agenda agenda, String nombre) {
// List<Contacto> resultados = new ArrayList<>();
// for (Contacto contacto : agenda) {
// if (contacto.nombre.equalsIgnoreCase(nombre)) {
// resultados.add(contacto);
// }
// }
// return resultados;
// }
// }

// }
