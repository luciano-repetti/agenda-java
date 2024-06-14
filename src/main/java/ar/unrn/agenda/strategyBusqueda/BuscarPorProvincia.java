package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorProvincia extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.direccion.provincia.toLowerCase();
    }
}
