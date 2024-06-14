// Usa S I N G L E T O N
package ar.unrn.agenda;

import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.EstrategiaBusqueda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Agenda implements AgendaInterface, Iterable<Contacto>{

    private final List<Contacto> contactos;
    private static Agenda agenda;

    private Agenda (List<Contacto> contactos) {
        this.contactos = contactos;
    }

    private Agenda () {
        this.contactos = new ArrayList<>();
    }

    public static Agenda getAgenda() {
        if (agenda == null) {
            agenda = new Agenda();
        }
        return agenda;
    }

    @Override
    public int cantidadContactos() {
        return this.contactos.size();
    }

    @Override
    public void limpiarAgenda() {
        this.contactos.clear();
    }

    @Override
    public void verContactos() {
        System.out.println("+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");
        System.out.println("| ID | Nombre                | Apellido             | Telefono             | Email                          | Pais                 | Provincia            | Ciudad               | Notas                                                                                     |");
        System.out.println("+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");

        for (Contacto contacto : contactos) {
            System.out.printf("| %-2s | %-21s | %-20s | %-20s | %-30s | %-20s | %-20s | %-20s | %-89s |\n",
                    contacto.id,
                    contacto.nombre,
                    contacto.apellido,
                    contacto.numeroTelefono,
                    contacto.email,
                    contacto.direccion.pais,
                    contacto.direccion.provincia,
                    contacto.direccion.ciudad,
                    contacto.notas);
            System.out.println("+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");
        }
    }

    @Override
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    @Override
    public void eliminarContacto(UUID id) {
        Iterator<Contacto> iterator = contactos.iterator();
        while (iterator.hasNext()) {
            Contacto currentContacto = iterator.next();
            if (currentContacto.id.equals(id)) {
                iterator.remove();
            }
        }
    }

    @Override
    public <T> void buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T criterio) {

    }

    @Override
    public Iterator<Contacto> iterator() {
        return new ArregloIterator();
    }

    private class ArregloIterator implements Iterator<Contacto> {
        private int indiceActual = 0;

        /**
         * Devuelve true si todavía hay elementos no visitados en el arreglo.
         *
         * @return true si todavía hay elementos no visitados en el arreglo, de lo contrario, false.
         */
        @Override
        public boolean hasNext() {
            return indiceActual < contactos.size();
        }

        /**
         * Devuelve el siguiente elemento en el arreglo y avanza el iterador al siguiente elemento.
         *
         * @return el siguiente elemento en el arreglo.
         */
        @Override
        public Contacto next() {
            return contactos.get(indiceActual++);
        }
    }
}
