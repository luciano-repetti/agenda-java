package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Clase abstracta que define una estrategia de búsqueda por palabra en la
 * agenda.
 * Implementa la interfaz EstrategiaBusqueda para proporcionar un método de
 * búsqueda genérico.
 *
 * @param <String> El tipo de dato para la palabra de búsqueda.
 */
public abstract class BusquedaPalabra implements EstrategiaBusqueda<String> {

    /**
     * Método abstracto que debe ser implementado por las clases concretas para
     * realizar la búsqueda de una palabra en un contacto.
     *
     * @param contactoActual El contacto en el que se realizará la búsqueda.
     * @return La palabra encontrada en el contacto, o null si no se encuentra
     *         ninguna palabra.
     */
    abstract protected String busqueda(Contacto contactoActual);

    /**
     * Implementación del método de búsqueda genérico definido en la interfaz
     * EstrategiaBusqueda.
     * Itera sobre todos los contactos de la agenda y busca la palabra especificada.
     *
     * @param agenda          La agenda en la que se realizará la búsqueda.
     * @param palabraComparar La palabra que se está buscando.
     * @return Una lista de contactos que contienen la palabra buscada.
     */
    @Override
    public List<Contacto> buscar(Agenda agenda, String palabraComparar) {
        Iterator<Contacto> iterator = agenda.iterator();
        List<Contacto> listaRetorno = new ArrayList<>();

        while (iterator.hasNext()) {
            Contacto contactoActual = iterator.next();
            String palabra = busqueda(contactoActual);

            if (palabra != null && !palabra.isEmpty()) {

                if (palabra.contains(palabraComparar.toLowerCase())) {
                    listaRetorno.add(contactoActual);
                }

                // if (palabra.equals(palabraComparar.toLowerCase())) {
                // listaRetorno.add(contactoActual);
                // }
            }
        }

        return listaRetorno;
    }
}