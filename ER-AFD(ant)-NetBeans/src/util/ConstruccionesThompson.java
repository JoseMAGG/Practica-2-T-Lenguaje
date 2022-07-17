/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.Estado;
import java.util.*;

/** Clase para generar las construcciones básicas de Thompson
 *
 * @author Jose
 */
public abstract class ConstruccionesThompson {
    
    public static Estado r(int pos, int cantidadSimbolos){
        return r(pos, cantidadSimbolos, null, null);
    }
    
    /** Construcción báscia de Thompson para reconocer r (el símbolo asociado a pos)
     * 
     * @param pos
     * @param cantidadSimbolos
     * @param estInicial
     * @param estFinal
     * @return Estado que tiene la transición asociada a pos
     */
    public static Estado r(int pos, int cantidadSimbolos,
            Estado estInicial, Estado estFinal){
        estInicial = estInicial == null ? new Estado(cantidadSimbolos, false, true): estInicial;
        estFinal = estFinal == null ? new Estado(cantidadSimbolos, true): estFinal;
        estInicial.setTransicion(pos, estFinal);
        return estInicial;
    }
    
    public static Estado[] r_concat_s(int posR, int posS, int cantidadSimbolos){
        return r_concat_s(posR, posS, cantidadSimbolos, null, null);
    }
    
    /** Construcción básica de Thompson para reconocer r (el símbolo asociado a posR)
     * seguido de s (el símbolo asociado a posS)
     * 
     * @param posR
     * @param posS
     * @param cantidadSimbolos
     * @param estInicial
     * @param estFinal
     * @return Estados que tienen la transiciones asociadas a posR y posS en ese orden
     */
    public static Estado[] r_concat_s(int posR, int posS, int cantidadSimbolos,
            Estado estInicial, Estado estFinal){
        estInicial = estInicial == null ? new Estado(cantidadSimbolos): estInicial;
        estFinal = estFinal == null ? new Estado(cantidadSimbolos, true): estFinal;
        Estado estMedio = new Estado(cantidadSimbolos);
        
        estInicial.setTransicion(posR, estMedio);
        estMedio.setTransicion(posS, estFinal);
        Estado[] retorno = {estInicial, estMedio};
        return retorno;
    }
    
    public static Estado[] r_or_s(int posR, int posS, int cantidadSimbolos){
        return r_or_s(posR, posS, cantidadSimbolos, null, null);
    }
    
    /** Construcción básica de Thompson para reconocer sea r (el símbolo asociado a posR)
     * o sea s (el símbolo asociado a posS)
     * 
     * @param posR
     * @param posS
     * @param cantidadSimbolos
     * @param estInicial
     * @param estFinal
     * @return Estados que tienen la transiciones asociadas a posR y posS en ese orden
     */
    public static Estado[] r_or_s(int posR, int posS, int cantidadSimbolos,
            Estado estInicial, Estado estFinal){
        estInicial = estInicial == null ? new Estado(cantidadSimbolos): estInicial;
        estFinal = estFinal == null ? new Estado(cantidadSimbolos, true): estFinal;
        Estado estado1 = new Estado(cantidadSimbolos);
        Estado estado2 = new Estado(cantidadSimbolos);
        Estado estado3 = new Estado(cantidadSimbolos);
        Estado estado4 = new Estado(cantidadSimbolos);
        Estado estado5 = new Estado(cantidadSimbolos);
        
        estInicial.addTransLambda(estado1);
        estInicial.addTransLambda(estado2);
        
        estado1.setTransicion(posR, estado3);
        estado2.setTransicion(posS, estado4);
        
        estado3.addTransLambda(estado5);
        estado4.addTransLambda(estado5);
        
        estado5.addTransLambda(estFinal);
        
        Estado[] retorno = {estado1, estado2};
        return retorno;
    }
    
    public static Estado r_plus(int pos, int cantidadSimbolos){
        return r_plus(pos, cantidadSimbolos, null, null);
    }
    
    /** Construcción básica de Thompson para reconocer r+ 
     * (cualquier secuencia de r sin admitir la secuencia nula)
     * 
     * @param pos
     * @param cantidadSimbolos
     * @param estInicial
     * @param estFinal
     * @return Estado que tiene la transición asociada a pos
     */
    public static Estado r_plus(int pos, int cantidadSimbolos,
            Estado estInicial, Estado estFinal){
        estInicial = estInicial == null ? new Estado(cantidadSimbolos): estInicial;
        estFinal = estFinal == null ? new Estado(cantidadSimbolos, true): estFinal;
        Estado estado1 = new Estado(cantidadSimbolos);
        Estado estado2 = new Estado(cantidadSimbolos);
        
        estInicial.addTransLambda(estado1);
        
        estado1.setTransicion(pos, estado2);
        
        estado2.addTransLambda(estado1);
        estado2.addTransLambda(estFinal);
        
        return estado1;
    }
    
    public static Estado r_star(int pos, int cantidadSimbolos){
        return r_star(pos, cantidadSimbolos, null, null);
    }
    
    /** Construcción básica de Thompson para reconocer r* 
     * (cualquier secuencia de r admitiendo la secuencia nula)
     * 
     * @param pos
     * @param cantidadSimbolos
     * @param estInicial
     * @param estFinal
     * @return Estado que tiene la transición asociada a pos
     */
    public static Estado r_star(int pos, int cantidadSimbolos,
            Estado estInicial, Estado estFinal){
        estInicial = estInicial == null ? new Estado(cantidadSimbolos): estInicial;
        estFinal = estFinal == null ? new Estado(cantidadSimbolos, true): estFinal;
        Estado estado1 = new Estado(cantidadSimbolos);
        Estado estado2 = new Estado(cantidadSimbolos);
        
        estInicial.addTransLambda(estado1);
        
        estado1.setTransicion(pos, estado2);
        
        estado2.addTransLambda(estado1);
        estado2.addTransLambda(estFinal);
        
        estInicial.addTransLambda(estFinal);
        
        return estado1;
    }
}
