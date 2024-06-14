package ar.unrn.agenda;

import ar.unrn.agenda.strategyBusqueda.EstrategiaBusqueda;
import ar.unrn.contactos.Contacto;

import java.util.UUID;
import java.util.List;

public interface AgendaInterface {

    int cantidadContactos();

    void limpiarAgenda();

    void verContactos();

    void agregarContacto(Contacto contacto);

    void eliminarContacto(UUID id);

    <T> List<Contacto> buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T palabra);
}