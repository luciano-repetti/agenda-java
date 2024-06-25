package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

/**
 * Clase que se encarga de exportar los contactos de una agenda a un archivo de
 * texto.
 */
public class ExportadorContactos {

    /**
     * Prefijo y extensión del archivo de exportación.
     */
    private static final String FILE_PREFIX = "exportacion_contacto";
    private static final String FILE_EXTENSION = ".txt";

    /**
     * Exporta los contactos de la agenda a un archivo de texto.
     *
     * @param agenda La agenda cuyos contactos se desean exportar.
     * @throws IOException
     */
    public String exportarContactos(Agenda agenda) throws IOException {
        String nombreArchivo = generarNombreArchivo();

        StringBuilder contenido = new StringBuilder();
        contenido.append("Cantidad de contactos: ").append(agenda.cantidadContactos()).append("\n\n");

        int indice = 1;
        for (Contacto contacto : agenda) {
            contenido.append("Contacto ").append(indice).append(":\n");
            escribirContacto(contenido, contacto);
            contenido.append("-----------------------------------------------\n\n");
            indice++;
        }

        Path archivo = Paths.get(nombreArchivo);
        Files.writeString(archivo, contenido.toString());
        System.out.println("Contactos exportados correctamente en el archivo: " + nombreArchivo);
        return archivo.toAbsolutePath().toString();
    }

    /**
     * Genera un nombre de archivo único para la exportación de contactos.
     *
     * @return El nombre del archivo.
     */
    private String generarNombreArchivo() {
        int numeroSecuencia = 1;
        String nombreArchivo = FILE_PREFIX + numeroSecuencia + FILE_EXTENSION;

        while (existeArchivo(nombreArchivo)) {
            numeroSecuencia++;
            nombreArchivo = FILE_PREFIX + numeroSecuencia + FILE_EXTENSION;
        }

        return nombreArchivo;
    }

    /**
     * Escribe los detalles de un contacto en el StringBuilder de contenido.
     *
     * @param contenido El StringBuilder utilizado para construir el contenido del
     *                  archivo.
     * @param contacto  El contacto cuyos detalles se desean escribir.
     */
    private void escribirContacto(StringBuilder contenido, Contacto contacto) {
        List<Object> data = contacto.deshidratarContacto();
        String[] campos = { "Nombre: ", "Apellido: ", "Numero de telefono: ", "Email: ", "Notas: ", "Pais: ",
                "Provincia: ", "Ciudad: ", "Calle: " };

        for (int i = 1; i <= 9; i++) { // Los índices van del 1 al 9 en la lista de datos
            contenido.append(campos[i - 1]).append(data.get(i).toString()).append("\n");
        }
    }

    /**
     * Verifica si un archivo con el nombre especificado ya existe.
     *
     * @param nombreArchivo El nombre del archivo.
     * @return true si el archivo existe, false en caso contrario.
     */
    public static boolean existeArchivo(String nombreArchivo) {
        Path archivo = Paths.get(nombreArchivo);
        return Files.exists(archivo);
    }

}
