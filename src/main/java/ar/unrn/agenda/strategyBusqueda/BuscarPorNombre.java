package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;
import java.util.List;

/**
 * Clase concreta que implementa una estrategia de búsqueda por nombre de un
 * contacto.
 * Extiende de la clase abstracta BusquedaPalabra y define la lógica de
 * búsqueda
 * específica para el nombre.
 */
public class BuscarPorNombre extends BusquedaPalabra {

    /**
     * Método que realiza la búsqueda del nombre en un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return El nombre del contacto, convertido a minúsculas.
     */
    @Override
    protected String busqueda(Contacto contactoActual) {
        List<Object> data = contactoActual.deshidratarContacto();
        String nombre = data.get(1).toString();
        return nombre.toLowerCase();
    }
}
