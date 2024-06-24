package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por provincia en la
 * dirección de un contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de búsqueda
 * específica para la provincia en la dirección.
 */
public class BuscarPorProvincia extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda de la provincia en la dirección de un
     * contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return La provincia en la dirección del contacto, convertida a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.direccion.provincia.toLowerCase();
    }
}
