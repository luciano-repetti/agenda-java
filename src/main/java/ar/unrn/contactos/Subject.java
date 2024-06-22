package ar.unrn.agenda.interfaces;

import ar.unrn.contactos.Contacto;

public interface Subject {
    void registrarObserver(Observer observer);
    void eliminarObserver(Observer observer);
    void notificarObservers(Contacto contacto);
}
