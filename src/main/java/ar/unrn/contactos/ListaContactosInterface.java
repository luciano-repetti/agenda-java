package ar.unrn.contactos;

import java.util.UUID;

public interface ListaContactosInterface {

    void verContactos();

    void agregarContacto(Contacto contacto);

    void eliminarContacto(UUID id);

    <T> void buscarContacto(EstrategiaBusqueda<T> estrategiaBusqueda, T criterio);
}