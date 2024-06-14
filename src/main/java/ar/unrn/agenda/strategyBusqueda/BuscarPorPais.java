package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorPais extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.direccion.pais.toLowerCase();
    }
}
