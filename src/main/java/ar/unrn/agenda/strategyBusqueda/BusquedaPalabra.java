package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public abstract class BusquedaPalabra implements EstrategiaBusqueda<String> {
    abstract protected String busqueda(Contacto contactoActual);

    @Override
    public List<Contacto> buscar(Agenda agenda, String palabraComparar) {
        Iterator<Contacto> iterator = agenda.iterator();
        List<Contacto> listaRetorno = new ArrayList<>();

        while (iterator.hasNext()) {
            Contacto contactoActual = iterator.next();
            String palabra = busqueda(contactoActual);

            if (palabra != null && !palabra.isEmpty()) {

                if (palabra.equals(palabraComparar.toLowerCase())) {
                    listaRetorno.add(contactoActual);
                }
            }
        }

        return listaRetorno;
    }
}