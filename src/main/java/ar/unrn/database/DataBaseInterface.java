package ar.unrn.database;

import ar.unrn.contactos.Contacto;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseInterface {

    public void close() throws SQLException;

    public void createTable() throws SQLException;

    public void postContacto(Contacto contacto) throws SQLException;

    public void updateContacto(Contacto contacto) throws SQLException;

    public void logicalDeleteContacto(String uuid) throws SQLException;

    public List<Contacto> getContactos(Boolean active) throws SQLException;


}
