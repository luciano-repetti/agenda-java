package ar.unrn.contactos;

import java.util.UUID;

public class Contacto {
    public String nombre;
    public String apellido;
    public String numeroTelefono;
    public String email;
    public String notas;
    public UUID id;
    public Direccion direccion;


    public Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas, String pais,
                    String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);

        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

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

    @Override
    public boolean equals(Object objeto) {
        boolean flag = false;
        if ((objeto instanceof Contacto)) {
            Contacto contacto = (Contacto) objeto;
            flag = this.id.equals(contacto.id);
        }
        return flag;
    }

}
