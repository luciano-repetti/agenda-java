package ar.unrn.archivos;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ExportadorContactos {

    private static final String FILE_PREFIX = "exportacion_contacto";
    private static final String FILE_EXTENSION = ".txt";

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

    private String generarNombreArchivo() {
        int numeroSecuencia = 1;
        String nombreArchivo = FILE_PREFIX + numeroSecuencia + FILE_EXTENSION;

        while (existeArchivo(nombreArchivo)) {
            numeroSecuencia++;
            nombreArchivo = FILE_PREFIX + numeroSecuencia + FILE_EXTENSION;
        }

        return nombreArchivo;
    }

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

    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

}
