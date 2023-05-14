/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorlexico_propio;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class AnalizadorLexico_Propio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una cadena de texto:");
        String linea = scanner.nextLine();
        System.out.println("-------------------------------");
        String palabraActual = "";
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (esSeparador(c)) { // si se encuentra un separador
                evaluarPalabraActual(palabraActual);
                palabraActual = ""; // reiniciar // reiniciar la variable para comenzar a leer la siguiente palabra
            } else if (esDigito(c) || c == '.') {
                palabraActual += c;
            } else if (esLetra(c)) {
                palabraActual += c;
            } else if (esOperadorAritmetico(c)) {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": operador aritmético");
                palabraActual = "";
            } else if (esOperadorLogicos(c)) {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": operador lógico");
                palabraActual = "";
            } else if (esOperadorelacional(c)) {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": operador relacional");
                palabraActual = "";
            } else if (esSignoPuntuacion(c)) {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": signo de puntuación");
                palabraActual = "";
            } else if (esOperadorAsignacion(c)) {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": operador de asignación");
                palabraActual = "";
            } else {
                evaluarPalabraActual(palabraActual);
                System.out.println(c + ": Caracter no reconocido");
                palabraActual = "";
            }
        }

        evaluarPalabraActual(palabraActual);
    }

    private static void evaluarPalabraActual(String palabra) {
        if (!palabra.isEmpty()) {
            if (esNumeroInt(palabra)) {
                System.out.println(palabra + ": Numero Entero");
            } else if (esNumeroDouble(palabra)) {
                System.out.println(palabra + ": Numero Decimal");
            } else if (esPalabraReservada(palabra)) { // si la palabra es una palabra reservada
                System.out.println(palabra + ": Palabra reservada");
            } else {
                System.out.println(palabra + ": palabra");
            }
        }
    }

    public static boolean esNumeroInt(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esNumeroDouble(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean esDigito(char x) {
        return (x >= '0' && x <= '9');
    }

    public static boolean esLetra(char x) {
        if ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esSeparador(char x) {
        if (x == ' ' || x == '\t') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esFinLinea(char x) {
        if (x == '\r' || x == '\n') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esOperadorAritmetico(char x) {
        if (x == '+' || x == '-' || x == '*' || x == '/' || x == '%') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esOperadorLogicos(char x) {
        if (x == '$' || x == '@' || x == '_') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esOperadorelacional(char x) {
        if (x == '<' || x == '>' || x == '#' || x == '^') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esSignoPuntuacion(char x) {
        if (x == '.' || x == ',' || x == ':' || x == ';' || x == '!' || x == '?') {
            return true;
        } else {
            return false;
        }
    }

    public static boolean esPalabraReservada(String palabra) {
        String[] palabrasReservadas = {"if", "else", "while", "for", "do", "switch", "case", "break", "continue", "int", "float", "double", "char", "long", "void", "return", "true", "false"}; // lista de palabras reservadas

        for (String palabraReservada : palabrasReservadas) {
            if (palabraReservada.equals(palabra)) {
                return true;
            }
        }

        return false; 
    }

    public static boolean esOperadorAsignacion(char x) {
        return (x == '=');
    }
}
