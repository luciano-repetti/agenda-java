// Usa S I N G L E T O N
package ar.unrn.agenda;

import ar.unrn.agenda.strategyBusqueda.EstrategiaBusqueda;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;
import ar.unrn.contactos.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Agenda implements AgendaInterface, Subject, Iterable<Contacto> {

    private final List<Contacto> contactos;
    private final List<Observer> observers;
    private static Agenda agenda;

    private Agenda(List<Contacto> contactos) {
        this.contactos = contactos;
        this.observers = new ArrayList<>();
    }

    private Agenda() {
        this.contactos = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static Agenda getAgenda() {
        if (agenda == null) {
            agenda = new Agenda();
        }
        return agenda;
    }

    @Override
    public void registrarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObserversDelete(Contacto contacto) {
        for (Observer observer : observers) {
            observer.deletear(contacto);
        }
    }

    @Override
    public void notificarObserversUpdate(Contacto contacto) {
        for (Observer observer : observers) {
            observer.actualizar(contacto);
        }
    }

    @Override
    public void notificarObserversAdd(Contacto contacto) {
        for (Observer observer : observers) {
            observer.aniadir(contacto);
        }
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
        System.out.println(
                "+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");
        System.out.println(
                "| ID | Nombre                | Apellido             | Telefono             | Email                          | Pais                 | Provincia            | Ciudad               | Notas                                                                                     |");
        System.out.println(
                "+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");

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
            System.out.println(
                    "+----+-----------------------+----------------------+----------------------+--------------------------------+----------------------+----------------------+----------------------+-------------------------------------------------------------------------------------------+");
        }
    }

    @Override
    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        notificarObserversAdd(contacto);
    }

    @Override
    public void eliminarContacto(UUID id) {
        Iterator<Contacto> iterator = contactos.iterator();
        while (iterator.hasNext()) {
            Contacto currentContacto = iterator.next();
            if (currentContacto.id.equals(id)) {
                iterator.remove();
                notificarObserversDelete(currentContacto);
                break;
            }
        }
    }

    @Override
    public <T> List<Contacto> buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T palabra) {
        return estrategiaBusqueda.buscar(agenda, palabra);
    }

    @Override
    public Iterator<Contacto> iterator() {
        return new ArregloIterator();
    }

    public boolean modificarAtributoContacto(UUID id, String atributo, String valor) {
        for (Contacto contacto : contactos) {
            if (contacto.id.equals(id)) {
                contacto.actualizarAtributo(atributo, valor);
                notificarObserversUpdate(contacto);
                return true;
            }
        }
        return false;
    }

    private class ArregloIterator implements Iterator<Contacto> {
        private int indiceActual = 0;

        @Override
        public boolean hasNext() {
            return indiceActual < contactos.size();
        }

        @Override
        public Contacto next() {
            return contactos.get(indiceActual++);
        }
    }
}
