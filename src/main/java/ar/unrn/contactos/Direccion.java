package ar.unrn.contactos;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Direccion representa un conjuto de datos de la direccion de una
 * persona, la cual permite su creación y edición
 */
class Direccion {
    /**
     * Atributos que representan las caracteristicas generales de la direccion de
     * una persona
     */
    private String pais;
    private String provincia;
    private String ciudad;
    private String calle;

    /**
     * Constructor que inicializa una dirección con todos sus atributos.
     *
     * @param pais      País de la dirección.
     * @param provincia Provincia de la dirección.
     * @param ciudad    Ciudad de la dirección.
     * @param calle     Calle de la dirección.
     */
    public Direccion(String pais, String provincia, String ciudad, String calle) {
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    /**
     * Deshidrata la dirección en una lista de objetos.
     *
     * @return Lista de objetos que representan la dirección.
     */
    public List<Object> deshidratarDireccion() {
        List<Object> list = new ArrayList<>();
        list.add(pais);
        list.add(provincia);
        list.add(ciudad);
        list.add(calle);
        return list;
    }

    /**
     * Crea una dirección a partir de una lista de objetos.
     *
     * @param list Lista de objetos que representan la dirección.
     * @return La dirección creada.
     */
    public static Direccion fromList(List<Object> list) {
        String pais = (String) list.get(0);
        String provincia = (String) list.get(1);
        String ciudad = (String) list.get(2);
        String calle = (String) list.get(3);
        return new Direccion(pais, provincia, ciudad, calle);
    }

    /**
     * Hidrata la dirección con una lista de objetos.
     *
     * @param list Lista de objetos que representan la dirección.
     */
    public void hidratarDireccion(List<Object> list) {
        Direccion direccion = fromList(list);
        modificarDireccion(direccion);
    }

    /**
     * Modifica los atributos de esta dirección con los atributos de otra dirección.
     *
     * @param direccion La dirección cuyos atributos se utilizarán para modificar
     *                  esta dirección.
     */
    private void modificarDireccion(Direccion direccion) {
        this.pais = direccion.pais;
        this.provincia = direccion.provincia;
        this.ciudad = direccion.ciudad;
        this.calle = direccion.calle;
    }
}
