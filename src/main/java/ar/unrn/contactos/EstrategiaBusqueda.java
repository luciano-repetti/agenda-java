package ar.unrn.contactos;

import java.util.List;

public interface EstrategiaBusqueda<T> {
    List<Contacto> buscar(List<Contacto> contactos, T criterio);
}