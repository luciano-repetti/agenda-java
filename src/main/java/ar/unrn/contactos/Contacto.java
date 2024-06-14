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

    public static class Direccion {
        public String pais;
        public String provincia;
        public String ciudad;
        public String calle;

        public Direccion(String pais, String provincia, String ciudad, String calle) {
            this.pais = pais;
            this.provincia = provincia;
            this.ciudad = ciudad;
            this.calle = calle;
        }
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
}
