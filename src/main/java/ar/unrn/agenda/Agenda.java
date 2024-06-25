package ar.unrn.agenda;

import ar.unrn.agenda.strategyBusqueda.EstrategiaBusqueda;
import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.Observer;
import ar.unrn.contactos.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Clase que representa una agenda de contactos, implementando el patrón
 * Singleton,
 * la interfaz Subject para observadores y la interfaz Iterable para iterar
 * sobre los contactos.
 */
public class Agenda implements AgendaInterface, Subject, Iterable<Contacto> {
    /**
     * Atributos que representan una lista con objetos de Contacto que reprensanta
     * las funcionalidades de una agenda
     */
    private List<Contacto> contactos;
    private final List<Observer> observers;
    private static Agenda agenda;

    /**
     * Constructor privado de la clase Agenda.
     * Crea una nueva instancia de Agenda con la lista de contactos especificada y
     * una lista de observadores vacía.
     *
     * @param contactos La lista de contactos para inicializar la agenda.
     */
    private Agenda(List<Contacto> contactos) {
        this.contactos = contactos;
        this.observers = new ArrayList<>();
    }

    /**
     * Constructor por defecto de la clase Agenda.
     * Crea una nueva instancia de Agenda con una lista de contactos vacía y una
     * lista de observadores vacía.
     */
    private Agenda() {
        this.contactos = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única de la agenda, creándola si aún no existe.
     *
     * @param contactos La lista de contactos para inicializar la agenda si aún no
     *                  ha sido creada.
     * @return La instancia única de la agenda.
     */
    public static Agenda getAgenda(List<Contacto> contactos) {
        if (agenda == null) {
            agenda = new Agenda(contactos);
        }
        return agenda;
    }

    /**
     * Registra un observador en la lista de observadores de la agenda.
     *
     * @param observer El observador a registrar.
     */
    @Override
    public void registrarObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Elimina un observador de la lista de observadores de la agenda.
     *
     * @param observer El observador a eliminar.
     */
    @Override
    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifica a todos los observadores que se ha eliminado un contacto de la
     * agenda.
     *
     * @param contacto El contacto que ha sido eliminado.
     * @throws SQLException Si ocurre un error al notificar a los observadores.
     */
    @Override
    public void notificarObserversDelete(Contacto contacto) throws SQLException {
        for (Observer observer : observers) {
            observer.deletear(contacto);
        }
    }

    /**
     * Notifica a todos los observadores que se ha actualizado un contacto de la
     * agenda.
     *
     * @param contacto El contacto que ha sido actualizado.
     * @throws SQLException Si ocurre un error al notificar a los observadores.
     */
    @Override
    public void notificarObserversUpdate(Contacto contacto) throws SQLException {
        for (Observer observer : observers) {
            observer.actualizar(contacto);
        }
    }

    /**
     * Notifica a todos los observadores que se ha añadido un nuevo contacto a la
     * agenda.
     *
     * @param contacto El contacto que ha sido añadido.
     * @throws SQLException Si ocurre un error al notificar a los observadores.
     */
    @Override
    public void notificarObserversAdd(Contacto contacto) throws SQLException {
        for (Observer observer : observers) {
            observer.aniadir(contacto);
        }
    }

    /**
     * Devuelve la cantidad de contactos en la agenda.
     *
     * @return La cantidad de contactos en la agenda.
     */
    @Override
    public int cantidadContactos() {
        return this.contactos.size();
    }

    /**
     * Elimina todos los contactos de la agenda.
     */
    @Override
    public void limpiarAgenda() {
        this.contactos.clear();
    }

    /**
     * Agrega un nuevo contacto a la agenda y notifica a los observadores.
     *
     * @param contacto El contacto a agregar.
     * @throws SQLException Si ocurre un error al notificar a los observadores.
     */
    @Override
    public void agregarContacto(Contacto contacto) throws SQLException {
        contactos.add(contacto);
        notificarObserversAdd(contacto);
    }

    /**
     * Elimina un contacto de la agenda por su ID y notifica a los observadores.
     *
     * @param id El ID del contacto a eliminar.
     * @throws SQLException Si no se encuentra el contacto o si ocurre un error al
     *                      notificar a los observadores.
     */
    @Override
    public void eliminarContacto(UUID id) throws SQLException {
        Iterator<Contacto> iterator = contactos.iterator();
        boolean found = false;
        while (iterator.hasNext() && !found) {
            Contacto currentContacto = iterator.next();
            List<Object> data = currentContacto.deshidratarContacto();

            if (UUID.fromString(data.get(0).toString()).equals(id)) {
                iterator.remove();
                notificarObserversDelete(currentContacto);
                found = true;
            }
        }
        if (!found) {
            throw new SQLException();
        }
    }

    /**
     * Busca contactos en la agenda según una estrategia de búsqueda y una palabra
     * clave.
     *
     * @param estrategiaBusqueda La estrategia de búsqueda a utilizar.
     * @param palabra            La palabra clave a buscar.
     * @param <T>                El tipo de la palabra clave.
     * @return Una lista de contactos que coinciden con la búsqueda.
     */
    @Override
    public <T> List<Contacto> buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T palabra) {
        return estrategiaBusqueda.buscar(agenda, palabra);
    }

    /**
     * Devuelve un iterador sobre la lista de contactos de la agenda.
     *
     * @return Un iterador sobre la lista de contactos.
     */
    @Override
    public Iterator<Contacto> iterator() {
        return new ArregloIterator();
    }

    /**
     * Edita un contacto existente en la agenda por su ID y notifica a los
     * observadores.
     *
     * @param id              El ID del contacto a editar.
     * @param contactoEditado El contacto con los datos actualizados.
     * @throws SQLException Si ocurre un error al notificar a los observadores.
     */
    public void editarContacto(UUID id, Contacto contactoEditado) throws SQLException {
        for (Contacto contacto : contactos) {
            List<Object> data = contacto.deshidratarContacto();

            if (UUID.fromString(data.get(0).toString()).equals(id)) {
                List<Object> listaContacto = contactoEditado.deshidratarContacto();
                contacto.hidratarContacto(listaContacto);
                notificarObserversUpdate(contacto);
            }
        }
    }

    /**
     * Clase interna que implementa un iterador sobre la lista de contactos de la
     * agenda.
     */
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
