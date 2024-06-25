package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por número de
 * teléfono.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para el número de teléfono.
 */
public class BuscarPorTelefono extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda del número de teléfono en un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return El número de teléfono del contacto, convertido a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String telefono = data.get(3).toString();
        return telefono.toLowerCase();
    }
}
