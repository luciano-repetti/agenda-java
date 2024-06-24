package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@DisplayName("Test de Archivos")
class ArchivosTest {

    /**
     * Este test es una plantilla.
     */
    @Test
    @DisplayName("Completar que se esta probando")
    void testPlantilla() {
        ;//
    }


    private Agenda agenda;
    private ExportadorContactos exportador;
    private List<File> archivosCreados;

    @BeforeEach
    public void setUp() {
        agenda = Agenda.getAgenda(new ArrayList<>());
        exportador = new ExportadorContactos();
        archivosCreados = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        for (File archivo : archivosCreados) {
            if (archivo.exists()) {
                archivo.delete();
            }
        }
        archivosCreados.clear();
    }

    @Test
    public void testExportarContactos() throws IOException {
        Contacto contacto1 = new Contacto("John", "Doe", "1234567890", "john.doe@example.com", "Notas1", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 123");
        Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321", "jane.smith@example.com", "Notas2", "Argentina", "Buenos Aires", "CABA", "Calle Falsa 456");
        agenda.agregarContacto(contacto1);
        agenda.agregarContacto(contacto2);

        exportador.exportarContactos(agenda);

        File archivoExportado = new File("exportacion_contacto1.txt");
        archivosCreados.add(archivoExportado);

        Assertions.assertTrue(archivoExportado.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoExportado))) {
            String linea;
            StringBuilder contenido = new StringBuilder();
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            String contenidoEsperado = "Cantidad de contactos: 2\n\n" +
                    "Contacto 1:\n" +
                    "Nombre: John\n" +
                    "Apellido: Doe\n" +
                    "Numero de telefono: 1234567890\n" +
                    "Email: john.doe@example.com\n" +
                    "Notas: Notas1\n" +
                    "Pais: Argentina\n" +
                    "Provincia: Buenos Aires\n" +
                    "Ciudad: CABA\n" +
                    "Calle: Calle Falsa 123\n" +
                    "-----------------------------------------------\n\n" +
                    "Contacto 2:\n" +
                    "Nombre: Jane\n" +
                    "Apellido: Smith\n" +
                    "Numero de telefono: 0987654321\n" +
                    "Email: jane.smith@example.com\n" +
                    "Notas: Notas2\n" +
                    "Pais: Argentina\n" +
                    "Provincia: Buenos Aires\n" +
                    "Ciudad: CABA\n" +
                    "Calle: Calle Falsa 456\n" +
                    "-----------------------------------------------\n\n";

            Assertions.assertEquals(contenidoEsperado, contenido.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void testGenerarNombreArchivo() {
//        String nombreArchivo1 = exportador.exportarContactos();
//        String nombreArchivo2 = exportador.generarNombreArchivo();
//
//        File archivo1 = new File(nombreArchivo1);
//        File archivo2 = new File(nombreArchivo2);
//
//        archivosCreados.add(archivo1);
//        archivosCreados.add(archivo2);
//
//        Assertions.assertNotEquals(nombreArchivo1, nombreArchivo2);
//    }

    @Test
    public void testExisteArchivo() {
        File archivo = new File("test_archivo_existente.txt");
        archivosCreados.add(archivo);

        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            Assertions.assertTrue(ExportadorContactos.existeArchivo("test_archivo_existente.txt"));
        } catch (IOException e) {
            Assertions.fail("No se pudo crear el archivo de prueba");
        }
    }
}

