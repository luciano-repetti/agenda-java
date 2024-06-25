package ar.unrn.contactos;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase contacto representa un conjuto de datos de una persona, la cual
 * permite la cual permite su creación y edición
 */
public class Contacto {
    /**
     * Atributos que representan las caracteristicas generales de contacto de una
     * persona
     */
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private String email;
    private String notas;
    private Direccion direccion;
    /**
     * Atributo que representan un identificador unico en la base de datos
     */
    private UUID id;

    /**
     * Constructor que inicializa un contacto con todos sus atributos.
     *
     * @param uuid           UUID del contacto.
     * @param nombre         Nombre del contacto.
     * @param apellido       Apellido del contacto.
     * @param numeroTelefono Número de teléfono del contacto.
     * @param email          Correo electrónico del contacto.
     * @param notas          Notas adicionales del contacto.
     * @param pais           País de la dirección del contacto.
     * @param provincia      Provincia de la dirección del contacto.
     * @param ciudad         Ciudad de la dirección del contacto.
     * @param calle          Calle de la dirección del contacto.
     */
    public Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas,
            String pais, String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);
        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

    /**
     * Constructor privado utilizado internamente para inicializar un contacto con
     * una dirección ya creada.
     *
     * @param uuid           UUID del contacto.
     * @param nombre         Nombre del contacto.
     * @param apellido       Apellido del contacto.
     * @param numeroTelefono Número de teléfono del contacto.
     * @param email          Correo electrónico del contacto.
     * @param notas          Notas adicionales del contacto.
     * @param direccion      Dirección del contacto.
     */
    private Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas,
            Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);
        this.direccion = direccion;
    }

    /**
     * Constructor que inicializa un contacto con un UUID generado automáticamente.
     *
     * @param nombre         Nombre del contacto.
     * @param apellido       Apellido del contacto.
     * @param numeroTelefono Número de teléfono del contacto.
     * @param email          Correo electrónico del contacto.
     * @param notas          Notas adicionales del contacto.
     * @param pais           País de la dirección del contacto.
     * @param provincia      Provincia de la dirección del contacto.
     * @param ciudad         Ciudad de la dirección del contacto.
     * @param calle          Calle de la dirección del contacto.
     */
    public Contacto(String nombre, String apellido, String numeroTelefono, String email, String notas, String pais,
            String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.randomUUID();
        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

    /**
     * Deshidrata el contacto en una lista de objetos.
     *
     * @return Lista de objetos que representan el contacto.
     */
    public List<Object> deshidratarContacto() {
        List<Object> list = new ArrayList<>();
        list.add(id.toString());
        list.add(nombre);
        list.add(apellido);
        list.add(numeroTelefono);
        list.add(email);
        list.add(notas);
        list.addAll(direccion.deshidratarDireccion());
        return list;
    }

    /**
     * Crea un contacto a partir de una lista de objetos.
     *
     * @param list Lista de objetos que representan el contacto.
     * @return El contacto creado.
     */
    private Contacto fromList(List<Object> list) {
        UUID id = UUID.fromString((String) list.get(0));
        String nombre = (String) list.get(1);
        String apellido = (String) list.get(2);
        String numeroTelefono = (String) list.get(3);
        String email = (String) list.get(4);
        String notas = (String) list.get(5);
        List<Object> direccionList = list.subList(6, 10);
        Direccion direccion = Direccion.fromList(direccionList);
        return new Contacto(id.toString(), nombre, apellido, numeroTelefono, email, notas, direccion);
    }

    /**
     * Hidrata el contacto con una lista de objetos.
     *
     * @param list Lista de objetos que representan el contacto.
     */
    public void hidratarContacto(List<Object> list) {
        Contacto contacto = fromList(list);
        modificarContacto(contacto);
    }

    /**
     * Modifica los atributos de este contacto con los atributos de otro contacto.
     *
     * @param contacto El contacto cuyos atributos se utilizarán para modificar este
     *                 contacto.
     */
    private void modificarContacto(Contacto contacto) {
        this.nombre = contacto.nombre;
        this.apellido = contacto.apellido;
        this.numeroTelefono = contacto.numeroTelefono;
        this.email = contacto.email;
        this.notas = contacto.notas;
        this.direccion.hidratarDireccion(contacto.direccion.deshidratarDireccion());
    }

    /**
     * Compara este contacto con otro objeto para determinar si son iguales.
     *
     * @param objeto El objeto con el que se va a comparar este contacto.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto)
            return true;
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        Contacto contacto = (Contacto) objeto;
        return id.equals(contacto.id);
    }
}
