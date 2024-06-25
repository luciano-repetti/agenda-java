package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por ID de un
 * contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para el ID.
 */
public class BuscarPorId extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda del ID en un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return El ID del contacto, convertido a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String id = data.get(0).toString();
        return id.toLowerCase();
    }
}
