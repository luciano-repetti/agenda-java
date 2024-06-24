package ar.unrn.database;

public class DataBaseException extends RuntimeException{
    /**
     * Excepción con un mensaje.
     * En otros casos, solo queremos saber que pasó, siendo
     * la razón algo que nosotros originamos.
     *
     * @param message la descripción textual del problema.
     */
    public DataBaseException(String message) {
        super(message);
    }

    /**
     * Cuando queremos saber la razón de manera detallada.
     * La combinación de los dos casos anteriores, cuando queremos
     * una descripción que y porque pasó algo.
     *
     * @param message la descripción textual del problema.
     * @param reason   la causa del problema
     */
    public DataBaseException(String message, Throwable reason) {
        super(message, reason);
    }
    /**
     * Encadenamiento de problemas.
     * En algunos casos, el problema se origina desde otro,
     * esta es la forma de conectar uno con otro, de forma
     * de conocer "la razón" por la cual estamos viendo este
     * problema.
     *
     * @param reason la excepción causante del problema.
     */
    public DataBaseException(Throwable reason) {
        super(reason);
    }
}
