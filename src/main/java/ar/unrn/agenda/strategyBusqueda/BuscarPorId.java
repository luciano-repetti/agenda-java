package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorId extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        String id = contactoActual.id.toString();
        return id.toLowerCase();
    }
}
