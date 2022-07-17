/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.LinkedList;
import model.Estado;

/**
 *
 * @author Jose
 */
public abstract class LinkedListManager {

    public static Estado[] ordenarPorNombreNoLambda(LinkedList<Estado> lista) {
        Estado[] retorno = new Estado[lista.size()];
        Estado auxE1, auxE2;
        int posMenor, posAux1 = 0;
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

        posMenor = 0;

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

    public static LinkedList<Estado> agregarEstadoALista(Estado estado, LinkedList<Estado> lista) {
        if (!lista.contains(estado)) {
            lista.addLast(estado);
        } else {
            System.out.println("El estado a añadir ya se encuentra en la lista");
        }
        return lista;
    }

    public static Object[] buscarCierre(LinkedList<Object[]> todosCierres, Estado estado) {
        for (Object[] o : todosCierres) {
            Estado auxE = (Estado) o[0];
            if (auxE.getNombre().contains(estado.getNombre())) {
                return o;
            }
        }
        return null;
    }

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

    public static LinkedList<Estado> buscarParticion(LinkedList<LinkedList<Estado>> todasParticiones, Estado estado) {
        for (LinkedList<Estado> particion : todasParticiones) {
            if (particion.contains(estado)) {
                return particion;
            }
        }
        return null;
    }

    public static LinkedList<LinkedList<Estado>> ordenarInicialParticiones(LinkedList<LinkedList<Estado>> todasParticiones, Estado inicial) {
        LinkedList<Estado> p = buscarParticion(todasParticiones, inicial);
        todasParticiones.remove(p);
        todasParticiones.addFirst(p);
        return todasParticiones;
    }

    public static Object[] combinarCierres(Object[] elemento1, Object[] elemento2) {
        Object[] retorno = new Object[2];
        retorno[0] = new Estado();
        LinkedList<Estado> lista1 = (LinkedList<Estado>) elemento1[1];
        LinkedList<Estado> listaCopia = (LinkedList<Estado>) lista1.clone();
        LinkedList<Estado> lista2 = (LinkedList<Estado>) elemento2[1];

        for (Estado e : lista2) {
            listaCopia = agregarEstadoALista(e, listaCopia);
        }
        retorno[1] = listaCopia;

        return retorno;
    }
}
