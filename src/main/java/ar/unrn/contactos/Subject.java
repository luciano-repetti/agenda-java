package ar.unrn.contactos;

/**
 * Interfaz que define métodos para gestionar observadores de cambios en los contactos.
 */
public interface Subject {
    /**
     * Registra un observador para recibir notificaciones de cambios en los contactos.
     *
     * @param observer El observador a registrar.
     */
    void registrarObserver(Observer observer);

    /**
     * Elimina un observador registrado para dejar de recibir notificaciones.
     *
     * @param observer El observador a eliminar.
     */
    void eliminarObserver(Observer observer);

    /**
     * Notifica a los observadores que se ha actualizado un contacto.
     *
     * @param contacto El contacto que ha sido actualizado.
     */
    void notificarObserversUpdate(Contacto contacto);

    /**
     * Notifica a los observadores que se ha eliminado un contacto.
     *
     * @param contacto El contacto que ha sido eliminado.
     */
    void notificarObserversDelete(Contacto contacto);

    /**
     * Notifica a los observadores que se ha añadido un nuevo contacto.
     *
     * @param contacto El contacto que ha sido añadido.
     */
    void notificarObserversAdd(Contacto contacto);

}
