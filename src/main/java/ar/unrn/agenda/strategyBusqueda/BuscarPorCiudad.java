package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por ciudad en la
 * dirección de un contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para la ciudad en la dirección.
 */
public class BuscarPorCiudad extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda de la ciudad en la dirección de un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return La ciudad en la dirección del contacto, convertida a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String ciudad = data.get(8).toString();
        return ciudad.toLowerCase();
    }
}
