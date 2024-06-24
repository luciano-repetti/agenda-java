package ar.unrn.contactos;

/**
 * Clase que representa la dirección de un contacto.
 */
public class Direccion {
    /**
     * Campos de la dirección.
     */
    public String pais;
    public String provincia;
    public String ciudad;
    public String calle;

    /**
     * Constructor para crear una nueva dirección.
     *
     * @param pais      El país de la dirección.
     * @param provincia La provincia de la dirección.
     * @param ciudad    La ciudad de la dirección.
     * @param calle     La calle de la dirección.
     */
    public Direccion(String pais, String provincia, String ciudad, String calle) {
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
    }
}
