/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Jose
 */
public abstract class NombreEstadosManager {

    private static int contadorLambda = 0;
    private static String ultimoNoLambda;
    private static String abecedario = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void reiniciarNombresLambda(){
        contadorLambda = 0;
    }
    
    public static void reiniciarNombresNoLambda(){
        ultimoNoLambda = null;
    }
    
    public static String getNombreLambda() {
        contadorLambda++;
        return "E" + contadorLambda;
    }

    public static String getNombreNoLambda() {//todo terminar esto
        if (ultimoNoLambda == null) {
            ultimoNoLambda = abecedario.substring(0, 1);
            return ultimoNoLambda;
        } else {
            boolean finalizar = true;
            int posNombre, posLetra;
            String nombre = StringManager.clonar(ultimoNoLambda);
            posNombre = nombre.length() - 1;
            do {
                posLetra = abecedario.indexOf(nombre.charAt(posNombre)) + 1;
                if (posLetra >= abecedario.length()) {
                    posLetra = 0;
                    nombre = StringManager.replacePos(nombre, posNombre, abecedario.charAt(posLetra));
                    finalizar = false;
                    posNombre--;
                    if (posNombre < 0) {
                        nombre = abecedario.charAt(0) + nombre;
                        finalizar = true;
                    }
                } else {
                    nombre = StringManager.replacePos(nombre, posNombre, abecedario.charAt(posLetra));
                    finalizar = true;
                }
            } while (!finalizar);
            ultimoNoLambda = nombre;
            return nombre;
        }
    }
}
