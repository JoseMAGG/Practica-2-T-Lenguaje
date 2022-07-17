/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Jose
 */
public abstract class Regex {

    private static final String operadores = "|.*+";

    /**
     * Identifica en la expresión regular ingresada como parámetro los símbolos
     * de entrada para el autómata finito y los retorna
     *
     * @param expresion
     * @return
     */
    public static String hallarSimbolos(String expresion) {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);
            if (!esOperador(c) & c != '(' & c != ')' & cadena.indexOf(String.valueOf(c)) == -1) {
                cadena.append(c);
            }
        }
        return cadena.toString();
    }

    /**
     * Identifica si el char ingresado como parámetro es operador o no,
     * comparándolo con una hilera de operadores previamente definida
     *
     * @param c
     * @return
     */
    public static boolean esOperador(char c) {
        for (int i = 0; i < operadores.length(); i++) {
            if (c == operadores.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    /** Método para partir una regex en dos cuando hay OR o CONCAT
     * 
     * @param regex
     * @return Array con los dos substrigns y la información del tipo de split:
     * OR, CONCAT o null si no se logró realizar
     * @throws Exception 
     */
    public static String[] splitRegex(String regex) throws Exception {
        String[] retorno = new String[3];
        //Se cortan los espacios al principio y al final, también los paréntesis si los tiene
        String copia = StringManager.quitarParentesis(regex);

        int regexLength = copia.length();
        if (regexLength <= 1) {
            throw new Exception("No se puede dividir la ER porque está vacía o sólo tiene un símbolo");
        }

        if (esOperador(copia.charAt(0))) {
            throw new Exception("La expresión no debe comenzar con operador");
        }

        int i;
        //Identifica operador OR
        for (i = 0; i < regexLength; i++) {
            if (copia.charAt(i) == ')') {
                throw new Exception("Se ha cerrado un paréntesis sin abrir antes");
            }
            if (copia.charAt(i) == '(') {
                i = StringManager.encontrarCierreParentesis(copia, i, regexLength);
            }
            if (copia.charAt(i) == '|') {
                if (i == regexLength - 1) {
                    throw new Exception("La expresión no debe finalizar con \"|\"");
                }
                retorno[0] = Operador.OR.toString();
                retorno[1] = copia.substring(0, i);
                retorno[2] = copia.substring(i + 1, regexLength);
                return retorno;
            }
        }
        //Identifica operador CONCAT
        i = 0;
        char car = copia.charAt(i);
        if (car == '(') {
            i = StringManager.encontrarCierreParentesis(copia, i, regexLength);
        }
        car = copia.charAt(i + 1);
        switch (car) {
            case '|' ->
                throw new Exception("Ha fallado la identificación de OR");
            case '.' -> {
                retorno[0] = Operador.CONCAT.toString();
                retorno[1] = copia.substring(0, i + 1);
                retorno[2] = copia.substring(i + 2, regexLength);
                return retorno;
            }
            case '*' -> {
                if (regexLength - (i + 2) > 0) {
                    return concatStarPlus(retorno, i, car, copia);
                } else {
                    return null;
                }
            }
            case '+' -> {
                if (regexLength - (i + 2) > 0) {
                    return concatStarPlus(retorno, i, car, copia);
                } else {
                    return null;
                }
            }
            default -> {
                retorno[0] = Operador.CONCAT.toString();
                retorno[1] = copia.substring(0, i + 1);
                retorno[2] = copia.substring(i + 1, regexLength);
                return retorno;
            }
        }
    }

    /**
     * Método para saber si un string es star o plus, no debe haber más
     * operadores, a excepción de los que pueda tener una expresión entre
     * paréntesis seguida de + o *. Si la expresión no es star o plus devuelve
     * null
     *
     * @param exp
     * @return
     * @throws Exception
     */
    public static Operador starOrPlus(String exp) throws Exception {
        String copia = StringManager.quitarParentesis(exp);
        int expLength = copia.length();
        if (expLength <= 1) {
            throw new Exception("La expresión debe tener al menos"
                    + " dos caracteres para ser de tipo + o *");
        }
        int i = 0;
        char car = copia.charAt(i);
        if (car == '(') {
            i = StringManager.encontrarCierreParentesis(exp, i, expLength);
        }

        car = copia.charAt(i);
        if (car == ')' || !esOperador(car)) {
            if (i + 1 == expLength - 1) {
                car = copia.charAt(i + 1);
                return switch (car) {
                    case '+' ->
                        Operador.PLUS;
                    case '*' ->
                        Operador.STAR;
                    default ->
                        null;
                };
            } else {
                return null;
            }
        } else {
            throw new Exception("La expresión no debe comenzar con un operador");
        }
    }

    private static String[] concatStarPlus(String[] retorno, int i, char car, String copia) throws Exception {
        car = copia.charAt(i + 2);
        int regexLength = copia.length();
        if (car == '.') {
            retorno[0] = Operador.CONCAT.toString();
            retorno[1] = copia.substring(0, i + 2);
            retorno[2] = copia.substring(i + 3, regexLength);
            return retorno;
        } else if (!esOperador(car)) {
            retorno[0] = Operador.CONCAT.toString();
            retorno[1] = copia.substring(0, i + 2);
            retorno[2] = copia.substring(i + 2, regexLength);
            return retorno;
        } else {
            throw new Error("Mal uso de los operadores");
        }
    }
}
