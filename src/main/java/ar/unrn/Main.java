package ar.unrn;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear una instancia de la base de datos
            DataBaseInterface database = new DataBase("agenda4.db");

            // Obtener la instancia singleton de la agenda
            Agenda agenda = Agenda.getAgenda();

            // Registrar la base de datos como observer
            agenda.registrarObserver((Observer) database);

            Scanner scanner = new Scanner(System.in);
            boolean continuar = true;

            while (continuar) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Agregar contacto");
                System.out.println("2. Modificar contacto");
                System.out.println("3. Eliminar contacto");
                System.out.println("4. Ver contactos");
                System.out.println("5. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nombre:");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese el apellido:");
                        String apellido = scanner.nextLine();
                        System.out.println("Ingrese el número de teléfono:");
                        String numeroTelefono = scanner.nextLine();
                        System.out.println("Ingrese el email:");
                        String email = scanner.nextLine();
                        System.out.println("Ingrese las notas:");
                        String notas = scanner.nextLine();
                        System.out.println("Ingrese el país:");
                        String pais = scanner.nextLine();
                        System.out.println("Ingrese la provincia:");
                        String provincia = scanner.nextLine();
                        System.out.println("Ingrese la ciudad:");
                        String ciudad = scanner.nextLine();
                        System.out.println("Ingrese la calle:");
                        String calle = scanner.nextLine();

                        Contacto nuevoContacto = new Contacto(nombre, apellido, numeroTelefono, email, notas, pais, provincia, ciudad, calle);
                        agenda.agregarContacto(nuevoContacto);
                        System.out.println("Contacto agregado.");
                        break;

                    case 2:
                        System.out.println("Ingrese el UUID del contacto a modificar:");
                        UUID idModificar = UUID.fromString(scanner.nextLine());
                        System.out.println("Ingrese el atributo a modificar (nombre, apellido, numeroTelefono, email, notas, pais, provincia, ciudad, calle):");
                        String atributo = scanner.nextLine();
                        System.out.println("Ingrese el nuevo valor:");
                        String valor = scanner.nextLine();

                        if (agenda.modificarAtributoContacto(idModificar, atributo, valor)) {
                            System.out.println("Contacto modificado.");
                        } else {
                            System.out.println("Contacto no encontrado.");
                        }
                        break;

                    case 3:
                        System.out.println("Ingrese el UUID del contacto a eliminar:");
                        UUID idEliminar = UUID.fromString(scanner.nextLine());
                        agenda.eliminarContacto(idEliminar);
                        System.out.println("Contacto eliminado.");
                        break;

                    case 4:
                        agenda.verContactos();
                        break;

                    case 5:
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }

            // Cerrar la conexión a la base de datos
            database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}