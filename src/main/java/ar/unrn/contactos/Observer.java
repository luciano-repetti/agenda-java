package ar.unrn.contactos;

import java.util.List;

public interface Observer {
    void aniadir(Contacto contacto);

    void actualizar(Contacto contacto);

    void deletear(Contacto contacto);

    List<Contacto> traerContactos(Boolean active);

}
