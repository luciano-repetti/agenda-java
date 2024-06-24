package ar.unrn.contactos;

import java.util.UUID;

/**
 * Clase que representa un contacto con su información personal y dirección.
 */
public class Contacto {
    public String nombre;
    public String apellido;
    public String numeroTelefono;
    public String email;
    public String notas;
    public UUID id;
    public Direccion direccion;

    /**
     * Constructor para crear un contacto con un UUID específico.
     *
     * @param uuid          El UUID del contacto.
     * @param nombre        El nombre del contacto.
     * @param apellido      El apellido del contacto.
     * @param numeroTelefono El número de teléfono del contacto.
     * @param email         El correo electrónico del contacto.
     * @param notas         Las notas adicionales del contacto.
     * @param pais          El país de la dirección del contacto.
     * @param provincia     La provincia de la dirección del contacto.
     * @param ciudad        La ciudad de la dirección del contacto.
     * @param calle         La calle de la dirección del contacto.
     */
    public Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas,
            String pais,
            String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);

        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

    /**
     * Constructor para crear un contacto con un UUID generado automáticamente.
     *
     * @param nombre        El nombre del contacto.
     * @param apellido      El apellido del contacto.
     * @param numeroTelefono El número de teléfono del contacto.
     * @param email         El correo electrónico del contacto.
     * @param notas         Las notas adicionales del contacto.
     * @param pais          El país de la dirección del contacto.
     * @param provincia     La provincia de la dirección del contacto.
     * @param ciudad        La ciudad de la dirección del contacto.
     * @param calle         La calle de la dirección del contacto.
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
     * Compara dos objetos.
     *
     * @param objeto
     * @return true si los objetos son iguales
     */
    @Override
    public boolean equals(Object objeto) {
        boolean flag = false;
        if ((objeto instanceof Contacto)) {
            Contacto contacto = (Contacto) objeto;
            flag = this.id.equals(contacto.id);
        }
        return flag;
    }

    /**
     * Modifica los detalles del contacto actual con los detalles del contacto proporcionado.
     *
     * @param contacto El contacto con los nuevos detalles.
     */
    public void modificarContacto(Contacto contacto) {
        this.nombre = contacto.nombre;
        this.apellido = contacto.apellido;
        this.numeroTelefono = contacto.numeroTelefono;
        this.email = contacto.email;
        this.notas = contacto.notas;
        this.direccion.pais = contacto.direccion.pais;
        this.direccion.provincia = contacto.direccion.provincia;
        this.direccion.ciudad = contacto.direccion.ciudad;
        this.direccion.calle = contacto.direccion.calle;
    }

    /**
     * Actualiza un atributo específico del contacto con un nuevo valor.
     *
     * @param atributo El nombre del atributo a actualizar.
     * @param valor    El nuevo valor del atributo.
     */
    public void actualizarAtributo(String atributo, String valor) {
        switch (atributo.toLowerCase()) {
            case "nombre":
                this.nombre = valor;
                break;
            case "apellido":
                this.apellido = valor;
                break;
            case "numerotelefono":
                this.numeroTelefono = valor;
                break;
            case "email":
                this.email = valor;
                break;
            case "notas":
                this.notas = valor;
                break;
            case "pais":
                this.direccion.pais = valor;
                break;
            case "provincia":
                this.direccion.provincia = valor;
                break;
            case "ciudad":
                this.direccion.ciudad = valor;
                break;
            case "calle":
                this.direccion.calle = valor;
                break;
            default:
                throw new IllegalArgumentException("Atributo desconocido: " + atributo);
        }
    }
}
