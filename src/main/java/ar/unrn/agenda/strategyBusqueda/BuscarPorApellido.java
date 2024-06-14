package ar.unrn.agenda.strategyBusqueda;

import ar.unrn.contactos.Contacto;

public class BuscarPorApellido extends BusquedaPalabra {

    @Override
    protected String busqueda(Contacto contactoActual) {
        return contactoActual.apellido.toLowerCase();
    }
}
