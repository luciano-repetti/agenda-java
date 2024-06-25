package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        contenido.append("Nombre: ").append(contacto.nombre).append("\n");
        contenido.append("Apellido: ").append(contacto.apellido).append("\n");
        contenido.append("Numero de telefono: ").append(contacto.numeroTelefono).append("\n");
        contenido.append("Email: ").append(contacto.email).append("\n");
        contenido.append("Notas: ").append(contacto.notas).append("\n");
        contenido.append("Pais: ").append(contacto.direccion.pais).append("\n");
        contenido.append("Provincia: ").append(contacto.direccion.provincia).append("\n");
        contenido.append("Ciudad: ").append(contacto.direccion.ciudad).append("\n");
        contenido.append("Calle: ").append(contacto.direccion.calle).append("\n");
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
