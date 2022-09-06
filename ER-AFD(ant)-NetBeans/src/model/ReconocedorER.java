/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exceptions.RegexRecognitionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que actua de reconocedor descendente para regex
 *
 * @author Jose
 */
public abstract class ReconocedorER {

    private static String entrada;
    private static int posSimbolo;
    private static char simbolo;
    private static StringBuilder errores;

    public static void main(String[] args) {
        try {
            reconocer("afc(d.f*)");
            System.out.println("Se ha reconocido correctamente.");
        } catch (Exception ex) {
            Logger.getLogger(ReconocedorER.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reconoce la regex ingresada como parámetro. El rechazo se efectúa
     * mediante excepciones
     *
     * @param regex
     * @throws RegexRecognitionException
     */
    public static void reconocer(String regex) throws RegexRecognitionException {
        posSimbolo = -1;
        entrada = regex;
        siguiente();
        errores = new StringBuilder();
        NTS();
        if (simbolo != '┤') {
            errores.append("\n-Se ingresaron más símbolos después" + "\nde una expresión correcta.");
        }
        if (!errores.isEmpty()) {
            throw new RegexRecognitionException(errores.toString());
        }
    }

    /**
     * Define la variable global simbolo como el siguiente simbolo de el String
     * entrada. En caso de llegar al final de la entrada, el simbolo se define
     * como fin de secuencia "┤"
     *
     */
    private static void siguiente() {
        posSimbolo++;
        simbolo = posSimbolo < entrada.length() ? entrada.charAt(posSimbolo) : '┤';
    }

    /**
     * Subprograma para el no terminal S de la gramática generadora de ER
     *
     */
    private static void NTS() {
        switch (simbolo) {
            case '|','.',')','+','*' -> {
                errores.append("\n-Toda ER debe comenzar con ( o un"
                        + "\nsimbolo de entrada del AF, se ingreso: \"" + simbolo + "\".");
            }
            case '┤' ->
                errores.append("\n-Expresion vacia.");
            default -> {
                NTT();
                NTC();
            }
        }
    }

    /**
     * Subprograma para el no terminal C de la gramática generadora de ER
     *
     */
    private static void NTC() {
        switch (simbolo) {
            case '|','.' -> {
                siguiente();
                NTT();
                NTC();
            }
            case ')','┤' -> {
                return;
            }
            case '+','*' ->
                errores.append("\n-Se esperaba una concatenación, unión"
                        + "\no un ) pero se ingresó: \"" + simbolo + "\".");
            default -> {
                NTT();
                NTC();
            }
        }
    }

    /**
     * Subprograma para el no terminal T de la gramática generadora de ER
     *
     */
    private static void NTT() {
        switch (simbolo) {
            case '(' -> {
                siguiente();
                NTT();
                NTC();
                switch (simbolo) {
                    case ')' -> {
                        siguiente();
                        NTE();
                        return;
                    }
                    case '┤' ->
                        errores.append("\n-Expresion incompleta.");
                    default ->
                        errores.append("\n-Se esperaba ) pero se ingresó: \"" + simbolo + "\".");
                }
            }
            case '┤' -> {
                errores.append("\n-Expresión incompleta.");
            }
            case '|','.',')','+','*' -> {
                errores.append("\n-Se esperaba un término pero se ingresó: \"" + simbolo + "\".");
            }
            default -> {
                siguiente();
                NTE();
            }
        }
    }

    /**
     * Subprograma para el no terminal E de la gramática generadora de ER
     *
     */
    private static void NTE() {
        switch (simbolo) {
            case '+','*' -> {
                siguiente();
                return;
            }
            default -> {
                return;
            }
        }
    }
}
