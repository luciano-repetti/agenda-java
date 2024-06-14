package ar.unrn.agenda.strategyBusqueda;

import java.util.List;

import ar.unrn.agenda.Agenda;
import ar.unrn.contactos.Contacto;

public interface EstrategiaBusqueda<T> {
    List<Contacto> buscar(Agenda agenda, T palabra);
}