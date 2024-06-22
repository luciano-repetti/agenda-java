package ar.unrn.contactos;

public interface Subject {
    void registrarObserver(Observer observer);
    void eliminarObserver(Observer observer);
    //void notificarObservers(Contacto contacto);
    void notificarObserversUpdate(Contacto contacto);
    void notificarObserversDelete(Contacto contacto);
    void notificarObserversAdd(Contacto contacto);


}
