package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.util.List;

/**
 * Interfaz que define un método de búsqueda genérico para una agenda.
 *
 * @param <T> El tipo de dato para el criterio de búsqueda.
 */
public interface EstrategiaBusqueda<T> {

    /**
     * Realiza una búsqueda en la agenda utilizando un criterio específico.
     *
     * @param agenda  La agenda en la que se realizará la búsqueda.
     * @param palabra El criterio de búsqueda.
     * @return Una lista de contactos que coinciden con el criterio de búsqueda.
     */
    List<Contacto> buscar(Agenda agenda, T palabra);
}
