package ar.unrn.contactos;

import java.util.List;

/**
 * Interfaz que define métodos para observar cambios en una lista de contactos.
 */
public interface Observer {
    /**
     * Método para notificar la adición de un nuevo contacto.
     *
     * @param contacto El contacto que se añade.
     */
    void aniadir(Contacto contacto);

    /**
     * Método para actualizar un contacto existente.
     *
     * @param contacto El contacto actualizado.
     */
    void actualizar(Contacto contacto);

    /**
     * Método para eliminar un contacto.
     *
     * @param contacto El contacto que se elimina.
     */
    void deletear(Contacto contacto);


    /**
     * Método para obtener una lista de contactos según su estado.
     *
     * @param active true si se desea obtener los contactos activos, false si se desea obtener los inactivos.
     * @return Una lista de contactos que cumplen con el estado especificado.
     */
    List<Contacto> traerContactos(Boolean active);

}
