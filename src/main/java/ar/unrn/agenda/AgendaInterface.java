package ar.unrn.agenda;

import ar.unrn.agenda.strategyBusqueda.EstrategiaBusqueda;
import ar.unrn.contactos.Contacto;

import java.util.UUID;
import java.util.List;

/**
 * Interfaz que define las operaciones básicas que debe ofrecer una agenda de
 * contactos.
 */
public interface AgendaInterface {

    /**
     * Obtiene la cantidad de contactos en la agenda.
     *
     * @return La cantidad de contactos en la agenda.
     */
    int cantidadContactos();

    /**
     * Elimina todos los contactos de la agenda.
     */
    void limpiarAgenda();

    /**
     * Muestra todos los contactos de la agenda.
     */
    void verContactos();

    /**
     * Agrega un contacto a la agenda.
     *
     * @param contacto El contacto a agregar.
     */
    void agregarContacto(Contacto contacto);

    /**
     * Elimina un contacto de la agenda dado su ID.
     *
     * @param id El ID del contacto a eliminar.
     */
    void eliminarContacto(UUID id);

    /**
     * Busca contactos en la agenda utilizando una estrategia de búsqueda
     * específica.
     *
     * @param estrategiaBusqueda La estrategia de búsqueda a utilizar.
     * @param palabra            El criterio de búsqueda.
     * @param <T>                El tipo de dato del criterio de búsqueda.
     * @return Una lista de contactos que coinciden con el criterio de búsqueda.
     */
    <T> List<Contacto> buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T palabra);
}
