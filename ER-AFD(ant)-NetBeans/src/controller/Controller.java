/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.AutomataFinito;
import model.Estado;
import util.Regex;

/**
 *
 * @author Jose
 */
public abstract class Controller {

    public static String hallarSimbolos(String entrada) {
        return Regex.hallarSimbolos(entrada);
    }
    
    public static AutomataFinito generarAF(String entrada) throws Exception{
        AutomataFinito AF1 = new AutomataFinito();
        AF1.ERtoAFD(entrada);
        return AF1;
    }

    public static String[][] generarSalidas(AutomataFinito AF) {
        return AF.generarSalidas();
    }

    public static boolean validarCadena(AutomataFinito AF, String entrada) throws Exception {
        return AF.validarCadena(entrada);
    }
}
