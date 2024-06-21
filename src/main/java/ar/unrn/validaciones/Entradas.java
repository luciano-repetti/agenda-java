package ar.unrn.validaciones;

import java.util.Scanner;

public class Entradas {
    public String value = "";
    public String mensajePrint;
    public Validaciones validacion;

    public Entradas(String expresionRegular, String mensajeError, String mensajePrint) {
        this.mensajePrint = mensajePrint;
        this.validacion = new Validaciones(expresionRegular, mensajeError);
    }

    public void repeticionEntrada() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensajePrint);
        do {
            if (value != "") {
                System.out.print(validacion.mensajeError);
            }

            value = scanner.nextLine();
        } while (validacion.validarEntrada(value));
    }
}
