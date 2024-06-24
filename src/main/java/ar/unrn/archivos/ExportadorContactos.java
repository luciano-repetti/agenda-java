package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que se encarga de exportar los contactos de una agenda a un archivo de texto.
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
     */
    public void exportarContactos(Agenda agenda) {
        String nombreArchivo = generarNombreArchivo();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Cantidad de contactos: " + agenda.cantidadContactos() + "\n\n");

            int indice = 1;
            for (Contacto contacto : agenda) {
                writer.write("Contacto " + indice + ":\n");
                escribirContacto(writer, contacto);
                writer.write("-----------------------------------------------\n\n");
                indice++;
            }

            System.out.println("Contactos exportados correctamente en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al exportar los contactos: " + e.getMessage());
        }
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
     * Escribe los detalles de un contacto en el archivo de exportación.
     *
     * @param writer   El BufferedWriter utilizado para escribir en el archivo.
     * @param contacto El contacto cuyos detalles se desean escribir.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    private void escribirContacto(BufferedWriter writer, Contacto contacto) throws IOException {
        writer.write("Nombre: " + contacto.nombre + "\n");
        writer.write("Apellido: " + contacto.apellido + "\n");
        writer.write("Numero de telefono: " + contacto.numeroTelefono + "\n");
        writer.write("Email: " + contacto.email + "\n");
        writer.write("Notas: " + contacto.notas + "\n");
        writer.write("Pais: " + contacto.direccion.pais + "\n");
        writer.write("Provincia: " + contacto.direccion.provincia + "\n");
        writer.write("Ciudad: " + contacto.direccion.ciudad + "\n");
        writer.write("Calle: " + contacto.direccion.calle + "\n");
    }

    /**
     * Verifica si un archivo con el nombre especificado ya existe.
     *
     * @param nombreArchivo El nombre del archivo.
     * @return true si el archivo existe, false en caso contrario.
     */
    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

}
