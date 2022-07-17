/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Jose
 */
public abstract class StringManager {

    private static String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     *
     * @param nombre1
     * @param nombre2
     * @return -1 si el primero es menor al segundo, 0 si ambos son iguales, 1
     * si el primero es mayor al segundo.
     */
    public static int compararNombresNoLambda(String nombre1, String nombre2) {
        int valorNombre1 = 0, valorNombre2 = 0;
        int multiplicador = 0;
        for (int i = nombre1.length() - 1; i >= 0; i--) {
            valorNombre1 += abecedario.indexOf(nombre1.charAt(i)) + 1 + (10 * multiplicador);
            multiplicador++;
        }

        multiplicador = 0;
        for (int i = nombre1.length() - 1; i >= 0; i--) {
            valorNombre2 += abecedario.indexOf(nombre2.charAt(i)) + 1 + (10 * multiplicador);
            multiplicador++;
        }

        if (valorNombre1 > valorNombre2) {
            return 1;
        } else if (valorNombre1 < valorNombre2) {
            return -1;
        } else {
            return 0;
        }
    }

    public static boolean validarSimbolosEntrada(String cadena, String simbolos){
        int longitud = cadena.length();
        for (int i = 0; i < longitud; i++) {
            if(!simbolos.contains(Character.toString(cadena.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    
    public static String clonar(String exp) {
        StringBuilder clone = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            clone.append(exp.charAt(i));
        }
        return clone.toString();
    }

    //Método para quitar espacios al principio y final de una expresión y luego 
    //quitar paréntesis
    public static String quitarParentesis(String exp) throws Exception {
        String copia = clonar(exp);
        copia = copia.trim();
        int expLength = copia.length();
        int i = 0;
        if (copia.charAt(i) == '(') {
            i = encontrarCierreParentesis(exp, i, expLength);
            if (i == expLength - 1) {
                copia = copia.substring(1, expLength - 1);
            }
        }
        return copia;
    }

    public static int encontrarCierreParentesis(String exp, int i, int expLength) throws Exception {
        int parentesis = 1;
        do {
            i++;
            if (exp.charAt(i) == ')') {
                parentesis--;
            } else if (exp.charAt(i) == '(') {
                parentesis++;
            }
        } while (parentesis > 0 && i < expLength - 1);

        if (parentesis > 0) {
            throw new Exception("Se han abierto paréntesis y no se han cerrado");
        }
        return i;
    }

    public static String replacePos(String exp, int pos, char car) {
        StringBuilder retorno = new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            if (i == pos) {
                retorno.append(car);
            } else {
                retorno.append(exp.charAt(i));
            }
        }
        return retorno.toString();
    }
}
