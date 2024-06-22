package ar.unrn.contactos;

public interface Observer {
    void aniadir(Contacto contacto);
    void actualizar(Contacto contacto);
    void deletear(Contacto contacto);

}
