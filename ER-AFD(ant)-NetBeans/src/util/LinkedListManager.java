/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.LinkedList;
import model.Estado;

/**
 * Clase para realizar operaciones con LinkedList para algún proceso de la
 * creación de un AFD
 *
 * @author Jose
 */
public abstract class LinkedListManager {

    /** Ordena los estados en el listado de estados por orden de su nombre NO lambda
     * 
     * @param lista
     * @return Vector con los estados ordenados
     */
    public static Estado[] ordenarPorNombreNoLambda(LinkedList<Estado> lista) {
        Estado[] retorno = new Estado[lista.size()];
        Estado auxE1, auxE2;
        int posAux1 = 0;
        int cantidadEstados = retorno.length;

        for (Estado estado : lista) {
            retorno[posAux1] = estado;
            posAux1++;
        }

        for (int i = 0; i < cantidadEstados; i++) {
            if (retorno[i].getNombre().equals(Operador.Error.toString())) {
                auxE1 = retorno[cantidadEstados - 1];
                retorno[cantidadEstados - 1] = retorno[i];
                retorno[i] = auxE1;
            }
        }

        for (int i = 0; i < cantidadEstados; i++) {
            auxE1 = retorno[i];
            for (int j = i + 1; j < cantidadEstados - 1; j++) {
                auxE2 = retorno[j];
                if (StringManager.compararNombresNoLambda(auxE1.getNombre(), auxE2.getNombre()) != -1) {
                    auxE1 = retorno[i];
                    retorno[i] = retorno[j];
                    retorno[j] = auxE1;
                }
            }
        }
        return retorno;
    }

    /** Agrega el estado a la lista de estados siempre y cuando no esté en la misma
     * 
     * @param estado
     * @param lista
     * @return Lista con el estado
     */
    public static LinkedList<Estado> agregarEstadoALista(Estado estado, LinkedList<Estado> lista) {
        if (!lista.contains(estado)) {
            lista.addLast(estado);
        } else {
            System.out.println("El estado a añadir ya se encuentra en la lista");
        }
        return lista;
    }

    /** Busca en el listado de todosCirres el cierre lambda que contenga en primera 
     * posicion el estado ingresado como parámetro
     * 
     * @param todosCierres
     * @param estado
     * @return Cierre lambda de estado
     */
    public static Object[] buscarCierre(LinkedList<Object[]> todosCierres, Estado estado) {
        for (Object[] o : todosCierres) {
            Estado auxE = (Estado) o[0];
            if (auxE.getNombre().contains(estado.getNombre())) {
                return o;
            }
        }
        return null;
    }

    /** Busca en el listado de todasParticiones la partición que contiene el estado de error
     * 
     * @param todasParticiones
     * @return Partición que contiene el estado de error
     */
    public static LinkedList<Estado> buscarParticionError(LinkedList<LinkedList<Estado>> todasParticiones) {
        for (LinkedList<Estado> particion : todasParticiones) {
            for (Estado estado : particion) {
                if (estado.getNombre().equals(Operador.Error.toString())) {
                    return particion;
                }
            }
        }
        return null;
    }

    /**
     * Busca en el listado de todasParticiones la primera particion que contenga
     * el estado ingresado como parámetro
     *
     * @param todasParticiones
     * @param estado
     * @return Particion que contiene el estado
     */
    public static LinkedList<Estado> buscarParticion(LinkedList<LinkedList<Estado>> todasParticiones, Estado estado) {
        for (LinkedList<Estado> particion : todasParticiones) {
            if (particion.contains(estado)) {
                return particion;
            }
        }
        return null;
    }

    /**
     * Busca en el listado de todasParticiones el cierre lambda del estado
     * incial y lo mueve al principio de la lista
     *
     * @param todasParticiones
     * @param inicial
     * @return Listado de todasParticiones con el cierre lambda del estado
     * inicial como primero
     */
    public static LinkedList<LinkedList<Estado>>
            ordenarInicialParticiones(LinkedList<LinkedList<Estado>> todasParticiones,
                    Estado inicial) {
        LinkedList<Estado> p = buscarParticion(todasParticiones, inicial);
        todasParticiones.remove(p);
        todasParticiones.addFirst(p);
        return todasParticiones;
    }

    /**
     * Combina los cierres lambda elemento1 y elemento2 en una sola lista
     *
     * @param elemento1
     * @param elemento2
     * @return Vector de 2 posiciones con un nuevo estado y una lista de estados
     * (nuevo cierre lambda)
     */
    public static Object[] combinarCierres(Object[] elemento1, Object[] elemento2) {
        Object[] retorno = new Object[2];
        LinkedList<Estado> lista1 = (LinkedList<Estado>) elemento1[1];
        LinkedList<Estado> listaCopia = (LinkedList<Estado>) lista1.clone();
        LinkedList<Estado> lista2 = (LinkedList<Estado>) elemento2[1];

        for (Estado e : lista2) {
            listaCopia = agregarEstadoALista(e, listaCopia);
        }
        Estado aux = listaCopia.get(0);        
        retorno[0] = new Estado(aux.getTransiciones().length);
        retorno[1] = listaCopia;

        return retorno;
    }
}
