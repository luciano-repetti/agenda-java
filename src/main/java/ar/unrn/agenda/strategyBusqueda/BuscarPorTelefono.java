package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorTelefono extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.numeroTelefono.toLowerCase();
    }
}
