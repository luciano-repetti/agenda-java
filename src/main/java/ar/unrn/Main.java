package ar.unrn;

import ar.unrn.agenda.Agenda;
import ar.unrn.agenda.strategyBusqueda.BuscarPorId;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Direccion;
import ar.unrn.contactos.Observer;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Ingrese el nombre de su agenda (puede ser una existente o una nueva): ");
            String dataBaseUrl = scanner.nextLine();

            // Crear una instancia de la base de datos
            DataBaseInterface database = new DataBase(dataBaseUrl);

            // Obtener la instancia singleton de la agenda
            Agenda agenda = Agenda.getAgenda(database.getContactos(true));

            // Registrar la base de datos como observer
            agenda.registrarObserver((Observer) database);

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

                        Contacto nuevoContacto = new Contacto(nombre, apellido, numeroTelefono, email, notas, pais,
                                provincia, ciudad, calle);
                        agenda.agregarContacto(nuevoContacto);
                        System.out.println("Contacto agregado.");
                        break;

                    case 2:
                        System.out.println("Ingrese el UUID del contacto a modificar:");
                        UUID idModificar = UUID.fromString(scanner.nextLine());
                        // System.out.println("Ingrese el atributo a modificar (nombre, apellido,
                        // numeroTelefono, email, notas, pais, provincia, ciudad, calle):");
                        // String atributo = scanner.nextLine();
                        // System.out.println("Ingrese el nuevo valor:");
                        // String valor = scanner.nextLine();

                        // if (agenda.modificarAtributoContacto(idModificar, atributo, valor)) {
                        // System.out.println("Contacto modificado.");
                        // } else {
                        // System.out.println("Contacto no encontrado.");
                        // }

                        Contacto contactoBuscado = agenda.buscarContacto(new BuscarPorId(),
                                idModificar.toString()).get(0);

                        System.out.println("Ingrese el dato a editar o presione enter para omitir.");

                        // Editar nombre
                        System.out.println("Modifique nombre " + contactoBuscado.nombre + ": ");
                        String nombreNuevo = scanner.nextLine();
                        contactoBuscado.nombre = !nombreNuevo.isEmpty() ? nombreNuevo : contactoBuscado.nombre;

                        // Editar apellido
                        System.out.println("Modifique apellido " + contactoBuscado.apellido + ": ");
                        String apellidoNuevo = scanner.nextLine();
                        contactoBuscado.apellido = !apellidoNuevo.isEmpty() ? apellidoNuevo : contactoBuscado.apellido;

                        // Editar número de teléfono
                        System.out.println("Modifique número de teléfono " + contactoBuscado.numeroTelefono + ": ");
                        String telefonoNuevo = scanner.nextLine();
                        contactoBuscado.numeroTelefono = !telefonoNuevo.isEmpty() ? telefonoNuevo
                                : contactoBuscado.numeroTelefono;

                        // Editar email
                        System.out.println("Modifique email " + contactoBuscado.email + ": ");
                        String emailNuevo = scanner.nextLine();
                        contactoBuscado.email = !emailNuevo.isEmpty() ? emailNuevo : contactoBuscado.email;

                        // Editar notas
                        System.out.println("Modifique notas " + contactoBuscado.notas + ": ");
                        String notasNuevas = scanner.nextLine();
                        contactoBuscado.notas = !notasNuevas.isEmpty() ? notasNuevas : contactoBuscado.notas;

                        // Editar dirección
                        Direccion direccionActual = contactoBuscado.direccion;
                        System.out.println("Modifique dirección:");
                        System.out.println("Pais (" + direccionActual.pais + "): ");
                        String paisNuevo = scanner.nextLine();
                        direccionActual.pais = !paisNuevo.isEmpty() ? paisNuevo : direccionActual.pais;

                        System.out.println("Provincia (" + direccionActual.provincia + "): ");
                        String provinciaNueva = scanner.nextLine();
                        direccionActual.provincia = !provinciaNueva.isEmpty() ? provinciaNueva
                                : direccionActual.provincia;

                        System.out.println("Ciudad (" + direccionActual.ciudad + "): ");
                        String ciudadNueva = scanner.nextLine();
                        direccionActual.ciudad = !ciudadNueva.isEmpty() ? ciudadNueva : direccionActual.ciudad;

                        System.out.println("Calle (" + direccionActual.calle + "): ");
                        String calleNueva = scanner.nextLine();
                        direccionActual.calle = !calleNueva.isEmpty() ? calleNueva : direccionActual.calle;

                        // Actualizar el contacto en la agenda (si es necesario)
                        // Suponiendo que tienes un método para actualizar el contacto en la agenda
                        agenda.editarContacto(idModificar, contactoBuscado);

                        System.out.println("Contacto actualizado correctamente.");

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