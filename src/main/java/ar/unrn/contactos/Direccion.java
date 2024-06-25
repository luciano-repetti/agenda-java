package ar.unrn.contactos;

import java.util.ArrayList;
import java.util.List;

class Direccion {
    private String pais;
    private String provincia;
    private String ciudad;
    private String calle;

    public Direccion(String pais, String provincia, String ciudad, String calle) {
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    public List<Object> toList() {
        List<Object> list = new ArrayList<>();
        list.add(pais);
        list.add(provincia);
        list.add(ciudad);
        list.add(calle);
        return list;
    }

    public static Direccion fromList(List<Object> list) {
        String pais = (String) list.get(0);
        String provincia = (String) list.get(1);
        String ciudad = (String) list.get(2);
        String calle = (String) list.get(3);
        return new Direccion(pais, provincia, ciudad, calle);
    }

    public void hidratarDireccion(List<Object> list) {
        Direccion direccion = fromList(list);
        modificarDireccion(direccion);
    }

    private void modificarDireccion(Direccion direccion) {
        this.pais = direccion.pais;
        this.provincia = direccion.provincia;
        this.ciudad = direccion.ciudad;
        this.calle = direccion.calle;
    }
}