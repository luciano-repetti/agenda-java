package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por país en la
 * dirección de un contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para el país en la dirección.
 */
public class BuscarPorPais extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda del país en la dirección de un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return El país en la dirección del contacto, convertido a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String pais = data.get(6).toString();
        return pais.toLowerCase();
    }
}
