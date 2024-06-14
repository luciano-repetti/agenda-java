package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorEmail extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.email.toLowerCase();
    }
}
