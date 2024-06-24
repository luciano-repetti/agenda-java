package ar.unrn.ui;

import ar.unrn.agenda.Agenda;
import ar.unrn.database.DataBase;
import ar.unrn.database.DataBaseInterface;

import java.sql.SQLException;

public class Main {
    private static String databaseUrl;

    private static DataBaseInterface database;
    private static Agenda agenda;

    public static void main(String[] args) throws SQLException {
        databaseUrl = "test4.db";

        DataBaseInterface database = new DataBase(databaseUrl);
        agenda = Agenda.getAgenda(database.getContactos(true));

        new ContactManagementSystem(databaseUrl, database, agenda);
    }
}
