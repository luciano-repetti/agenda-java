package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.contactos.Contacto;

/**
 * Clase concreta que implementa una estrategia de búsqueda por apellido de un
 * contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para el apellido.
 */
public class BuscarPorApellido extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda del apellido en un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return El apellido del contacto, convertido a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String apellido = data.get(2).toString();
        return apellido.toLowerCase();
    }
}
