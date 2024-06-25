package ar.unrn.contactos;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Contacto {
    private String nombre;
    private String apellido;
    private String numeroTelefono;
    private String email;
    private String notas;
    private UUID id;
    private boolean active;
    private Direccion direccion;

    public Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas,
            String pais, String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);
        this.active = true;
        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

    private Contacto(String uuid, String nombre, String apellido, String numeroTelefono, String email, String notas,
            Direccion direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.fromString(uuid);
        this.direccion = direccion;
        this.active = true;
    }

    public Contacto(String nombre, String apellido, String numeroTelefono, String email, String notas, String pais,
            String provincia, String ciudad, String calle) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.notas = notas;
        this.id = UUID.randomUUID();
        this.active = true;
        this.direccion = new Direccion(pais, provincia, ciudad, calle);
    }

    public List<Object> toList() {
        List<Object> list = new ArrayList<>();
        list.add(id.toString());
        list.add(nombre);
        list.add(apellido);
        list.add(numeroTelefono);
        list.add(email);
        list.add(notas);
        list.addAll(direccion.toList());
        list.add(active);
        return list;
    }

    public static Contacto fromList(List<Object> list) {
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

    public void hidratarContacto(List<Object> list) {
        Contacto contacto = fromList(list);
        modificarContacto(contacto);
    }

    private void modificarContacto(Contacto contacto) {
        this.nombre = contacto.nombre;
        this.apellido = contacto.apellido;
        this.numeroTelefono = contacto.numeroTelefono;
        this.email = contacto.email;
        this.notas = contacto.notas;
        this.direccion.hidratarDireccion(contacto.direccion.toList());
    }

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
