package ar.unrn.agenda;

import ar.unrn.contactos.Contacto;
import ar.unrn.contactos.EstrategiaBusqueda;

import java.util.UUID;

public interface AgendaInterface {

    int cantidadContactos();

    void limpiarAgenda();

    void verContactos();

    void agregarContacto(Contacto contacto);

    void eliminarContacto(UUID id);

    <T> void buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T criterio);
}