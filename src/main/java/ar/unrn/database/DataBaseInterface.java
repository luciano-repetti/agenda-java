package ar.unrn.database;

import ar.unrn.contactos.Contacto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DataBaseInterface {

    public void close();

    public void createTable();

    public void postContacto(Contacto contacto);

    public void updateContacto(Contacto contacto);

    public void deleteContacto(String uuid);

    public List<Contacto> getContactos();


}
