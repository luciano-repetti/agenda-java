package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Test de Archivos")
class ArchivosTest {

    private Agenda agenda;
    private ExportadorContactos exportador;
    private List<Path> archivosCreados;

    @BeforeEach
    public void setUp() {
        agenda = Agenda.getAgenda(new ArrayList<>());
        exportador = new ExportadorContactos();
        archivosCreados = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() throws IOException {
        for (Path archivo : archivosCreados) {
            if (Files.exists(archivo)) {
                Files.delete(archivo);
            }
        }
        archivosCreados.clear();
    }

    @Test
    @DisplayName("Exportar contactos a archivo de texto")
    public void testExportarContactos() throws IOException {
        Contacto contacto1 = new Contacto("John", "Doe", "1234567890", "john.doe@example.com", "Notas1", "Argentina",
                "Buenos Aires", "CABA", "Calle Falsa 123");
        Contacto contacto2 = new Contacto("Jane", "Smith", "0987654321", "jane.smith@example.com", "Notas2",
                "Argentina", "Buenos Aires", "CABA", "Calle Falsa 456");
        agenda.agregarContacto(contacto1);
        agenda.agregarContacto(contacto2);

        String archivoExportado = exportador.exportarContactos(agenda);
        archivosCreados.add(Path.of(archivoExportado));

        Assertions.assertTrue(Files.exists(Path.of(archivoExportado)));

        try (BufferedReader reader = new BufferedReader(new FileReader(archivoExportado))) {
            StringBuilder contenido = new StringBuilder();
            String linea;
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
        }
    }

    @Test
    @DisplayName("Verificar existencia de archivo")
    public void testExisteArchivo() throws IOException {
        Path archivo = Files.createTempFile("test_archivo_existente", ".txt");
        archivosCreados.add(archivo);

        Assertions.assertTrue(Files.exists(archivo));

        try {
            Assertions.assertTrue(ExportadorContactos.existeArchivo(archivo.toString()));
        } finally {
            Files.deleteIfExists(archivo);
        }
    }
}
