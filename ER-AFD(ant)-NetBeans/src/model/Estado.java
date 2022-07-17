/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.LinkedList;
import util.LinkedListManager;
import util.NombreEstadosManager;

/** Clase que representa un estado que pertenece a un autómata finito
 *
 * @author Jose
 */
public class Estado implements Comparable<Object> {

    private boolean inicial;
    private boolean aceptacion;
    private String nombre;
    private Estado[] transiciones;
    /* transiciones tiene tamaño cantidadSimbolos + 1, Cuando pos en transiciones
    es igual a cantidadSimbolos + 1, es porque no hay una transición mínima sino
    un conjunto de transiciones (expresión de más de un símbolo).*/
    private LinkedList<Estado> transLambda = new LinkedList<>();

    public Estado() {
        nombre = NombreEstadosManager.getNombreLambda();
    }

    public Estado(boolean nombreLambda, int cantidadSimbolos) {
        nombre = nombreLambda ? NombreEstadosManager.getNombreLambda()
                : NombreEstadosManager.getNombreNoLambda();
        transiciones = nombreLambda ? new Estado[cantidadSimbolos + 1]
                : new Estado[cantidadSimbolos];;
    }

    public Estado(int cantidadSimbolos) {
        transiciones = new Estado[cantidadSimbolos + 1];
        aceptacion = false;
        nombre = NombreEstadosManager.getNombreLambda();
    }

    public Estado(int cantidadSimbolos, boolean aceptacion) {
        transiciones = new Estado[cantidadSimbolos + 1];
        this.aceptacion = aceptacion;
        nombre = NombreEstadosManager.getNombreLambda();
    }

    public Estado(int cantidadSimbolos, String name) {
        transiciones = new Estado[cantidadSimbolos + 1];
        nombre = name;
    }

    public Estado(int cantidadSimbolos, boolean aceptacion, boolean inicial) {
        transiciones = new Estado[cantidadSimbolos + 1];
        this.aceptacion = aceptacion;
        this.inicial = inicial;
        nombre = NombreEstadosManager.getNombreLambda();
    }

    public void setTransicion(int simbolo, Estado estado) {
        transiciones[simbolo] = estado;
    }

    public Estado[] getTransiciones() {
        return transiciones;
    }

    public Estado getTransicion(int pos) {
        return transiciones[pos];
    }

    /** Agrega un estado a la lista ligada de transiciones lambda
     * 
     * @param estado 
     */
    public void addTransLambda(Estado estado) {
        transLambda = LinkedListManager.agregarEstadoALista(estado, transLambda);
    }

    /** Elimina un estado de la lista ligada de transiciones lambda
     * 
     * @param estado 
     */
    public void removeTransLambda(Estado estado) {
        transLambda.remove(estado);
    }

    public LinkedList<Estado> getTransLambda() {
        return transLambda;
    }

    public void setAceptacion(boolean value) {
        aceptacion = value;
    }

    public boolean getAceptacion() {
        return aceptacion;
    }

    public void setInicial(boolean value) {
        inicial = value;
    }

    public boolean isInicial() {
        return inicial;
    }

    public void setNombre(String name) {
        nombre = name;
    }

    public String getNombre() {
        return nombre;
    }

    /** Crea un nuevo array de transiciones para el estado con las mismas 
     * transiciones desde el espacio 0 hasta el penúltimo. Se utiliza cuando ya 
     * no quedan transiciones que representen una expresión de más de un símbolo
     * 
     * @param cantidadSimbolos 
     */
    public void quitarUltimaTransicion(int cantidadSimbolos) {
        Estado[] nuevo = new Estado[cantidadSimbolos];
        System.arraycopy(transiciones, 0, nuevo, 0, cantidadSimbolos);
        transiciones = nuevo;
    }

    @Override
    public String toString() {
        StringBuilder transCopia = new StringBuilder();
        transCopia.append("[");
        for (int i = 0; i < transiciones.length; i++) {
            if (transiciones[i] == null) {
                transCopia.append("Vacío, ");
            } else {
                transCopia.append(transiciones[i].getNombre() + ", ");
            }
        }
        transCopia.replace(transCopia.length() - 2, transCopia.length() - 1, "]");
        //Todos los nombres de los estados en un array
        return "Estado: " + nombre + ", Aceptación: " + aceptacion + ", Inicial: " + inicial + "\nTransiciones: " + transCopia;
    }

    @Override
    public int compareTo(Object o) {
        Estado estO = (Estado) o;
        if (nombre.equals(estO.getNombre())) {
            return 0;
        } else {
            return -1;
        }
    }
}
