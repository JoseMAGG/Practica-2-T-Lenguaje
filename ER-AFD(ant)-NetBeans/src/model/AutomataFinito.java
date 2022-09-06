/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exceptions.AFConstructionException;
import exceptions.AFValidationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import util.ConstruccionesThompson;
import util.LinkedListManager;
import util.Operador;
import util.Regex;
import util.StringManager;

/**
 * Clase que representa un autómata finíto sea determinístico o no, maneja todos
 * sus estados y transiciones.
 *
 * @author Jose
 */
public class AutomataFinito {

    private String simbolosEntrada;
    private boolean determinista;
    private Estado[] estados;
    private Estado estadoInicial;
    private Estado estadoError;

    public AutomataFinito() {
    }

    public AutomataFinito(int cantidadSimbolos) {
        Estado inicial = new Estado(0, true, true);
    }

    /**
     * Recibe una expresión regular y completa el autómata finíto que llama el
     * método, generando un autómata finíto determinístico mínimo con base en la
     * expresión regular
     *
     * @param er
     * @throws exceptions.AFConstructionException
     */
    public void ERtoAFD(String er) throws AFConstructionException {
        er = er.trim();
        if (er.isEmpty()) {
            throw new AFConstructionException("No se puede transformar la ER a AF porque está vacía");
        }
        simbolosEntrada = Regex.hallarSimbolos(er);
        if (simbolosEntrada.isEmpty()) {
            throw new AFConstructionException("La expresión debe tener al menos un símbolo de entrada");
        }
        estadoInicial = ERtoAFND(er);
        System.out.println("Inicial: " + estadoInicial);
        AFNDtoAFD(estadoInicial);
        agregarEstadoError();
        metodoParticiones();
    }

    /**
     * Recibe una expresión regular y genera un autómata finito usando
     * construcciones básicas de Thompson
     *
     * @param er
     * @return AFND
     * @throws Exception
     */
    private Estado ERtoAFND(String er) throws AFConstructionException {
        /*
        -Se va a devolver el estado inicial de la construcción de Thompson final que representa toda la ER
        -Utilizar una pila para almacenar de las transiciones faltantes
        por minimizar LIGADAS a sus estados correspondientes (vector de dos 
        posiciones, primera para estado y segunda para transición (subString))*/

        try {
            Stack<Object[]> faltantes = new Stack<>();
            String[] split;
            Estado inicial, auxEI, auxEF;
            String auxSubER;
            Object[] elementoPila = new Object[2];
            int cantidadSim = simbolosEntrada.length();

            //En caso de que la ER sea sólo un símbolo de entrada
            if (er.length() == 1) {
                if (Regex.esOperador(er.charAt(0))) {
                    throw new AFConstructionException("La expresión no puede ser únicamente un operador");
                }
                int pos = simbolosEntrada.indexOf(er);
                inicial = ConstruccionesThompson.r(pos, cantidadSim);
                inicial.setNombre("A");
                return inicial;
            }

            inicial = ConstruccionesThompson.r(cantidadSim, cantidadSim);
            elementoPila[0] = inicial;
            elementoPila[1] = er;
            faltantes.add(elementoPila);

            /*
        -Generar constr de Thompson hasta que se llegue a la transición mínima
        -Prioridad de constr:
            +or
            +concat
            +star & plus
            +r
             */
            int iter = 1;
            do {
                System.out.println("Interación: " + iter);
                iter++;
                elementoPila = faltantes.pop();
                auxEI = (Estado) elementoPila[0];
                auxEF = (Estado) auxEI.getTransicion(cantidadSim);
                auxSubER = (String) elementoPila[1];

                split = Regex.splitRegex(auxSubER);
                int posR, posS;

                if (split == null) {
                    Operador op = Regex.starOrPlus(auxSubER);
                    if (auxSubER.length() == 2) {
                        auxSubER = Character.toString(auxSubER.charAt(0));
                        posR = simbolosEntrada.indexOf(auxSubER.charAt(0));
                    } else if (auxSubER.length() >= 4) {
                        auxSubER = auxSubER.substring(1, auxSubER.length() - 2);
                        posR = cantidadSim;
                    } else {
                        throw new AFConstructionException("Hay errores en la ER");
                    }
                    switch (op) {
                        case STAR:
                            auxEI = ConstruccionesThompson.r_star(posR, cantidadSim, auxEI, auxEF);
                            break;
                        case PLUS:
                            auxEI = ConstruccionesThompson.r_plus(posR, cantidadSim, auxEI, auxEF);

                            break;
                        default:
                            throw new AFConstructionException("No se identificaron bien las operaciones");
                    }
                    if (auxSubER.length() > 1) {
                        elementoPila[0] = auxEI;
                        elementoPila[1] = auxSubER;
                        faltantes.add(elementoPila);
                    }
                } else if (split[0].equals(Operador.OR.toString())) {
                    posR = split[1].length() == 1 ? simbolosEntrada.indexOf(split[1].charAt(0)) : cantidadSim;
                    posS = split[2].length() == 1 ? simbolosEntrada.indexOf(split[2].charAt(0)) : cantidadSim;
                    Estado[] resultado = ConstruccionesThompson.r_or_s(posR, posS, cantidadSim, auxEI, auxEF);
                    if (posR == cantidadSim) {
                        elementoPila = new Object[2];
                        elementoPila[0] = resultado[0];
                        elementoPila[1] = split[1];
                        faltantes.add(elementoPila);
                    }
                    if (posS == cantidadSim) {
                        elementoPila = new Object[2];
                        elementoPila[0] = resultado[1];
                        elementoPila[1] = split[2];
                        faltantes.add(elementoPila);
                    }
                } else if (split[0].equals(Operador.CONCAT.toString())) {
                    posR = split[1].length() == 1 ? simbolosEntrada.indexOf(split[1].charAt(0)) : cantidadSim;
                    posS = split[2].length() == 1 ? simbolosEntrada.indexOf(split[2].charAt(0)) : cantidadSim;
                    Estado[] resultado = ConstruccionesThompson.r_concat_s(posR, posS, cantidadSim, auxEI, auxEF);
                    if (posR == cantidadSim) {
                        elementoPila = new Object[2];
                        elementoPila[0] = resultado[0];
                        elementoPila[1] = split[1];
                        faltantes.add(elementoPila);
                    }
                    if (posS == cantidadSim) {
                        elementoPila = new Object[2];
                        elementoPila[0] = resultado[1];
                        elementoPila[1] = split[2];
                        faltantes.add(elementoPila);
                    }
                }
            } while (!faltantes.isEmpty());

            /*
            -Siempre que se encuentre un símbolo de entrada se debe mirar el siguiente,
            si este es un * o un + se hace la respec const de Thompson
            -Cuando haya un paréntesis se tomará todo lo que contiene como un símbolo
            de entrada y se procede como en el punto anterior        
            -En la pila sólo se almacenarán aquellos substrings que tengan longitud mayor a 1
            -Si al tomar una transición de la pila el substring tiene longitud mayor
            a 1, la posición de la transición está en el último índice del vector de transiciones        
            -Siempre hacer que el estado inicial no sea de aceptación        
             */
            return inicial;
        } catch (Exception e) {
            throw new AFConstructionException(e.getMessage());
        }
    }

    /**
     * Simplifica un autímata finito NO deterministico generado con
     * construcciones de Thompson y lo convierte en autómata finíto
     * determinístico
     *
     * @param inicial
     */
    private void AFNDtoAFD(Estado inicial) {
        LinkedList<Object[]> cierresLambda = getCierresLambda(inicial);
        /*
        -Una vez obtenidos todos los cierres lambda se mira en cada estado de un
        cierre hacia cuál estado lleva con cada transición NO lambda, en caso
        de que en un mismo cierre hayan estados que lleven hacia cierres diferentes
        con la misma trnasición, se crea un nuevo cierre con la suma de los
        estados de los diferentes cierres y un nuevo estado asociado
         */
        Object[] elementoListaAux;
        Object[] elementoLista;
        for (int j = 0; j < cierresLambda.size(); j++) {
            elementoLista = cierresLambda.get(j);
            LinkedList<Estado> cierre = (LinkedList<Estado>) elementoLista[1];
            Estado auxE1, auxE2, auxE3;
            for (Estado e : cierre) {
                for (int i = 0; i < simbolosEntrada.length(); i++) {
                    auxE1 = e.getTransicion(i);
                    if (auxE1 != null) {
                        //Buscar el cierre lambda de auxE
                        elementoListaAux = LinkedListManager.buscarCierre(cierresLambda, auxE1);
                        //Añadir el estado asociado al cierre de auxE a la transicion del estado asociado al cierre actual
                        auxE2 = (Estado) elementoLista[0];
                        if(auxE2 != null){
                            auxE3 = auxE2.getTransicion(i);
                            if (auxE3 != null) {
                                //Si en la transicion ya hay un estado se debe crear un nuevo estado que combine los dos cierres
                                Object[] auxObj = LinkedListManager.buscarCierre(cierresLambda, auxE3);
                                auxObj = LinkedListManager.combinarCierres(auxObj, elementoListaAux);
                                auxE2.setTransicion(i, (Estado) auxObj[0]);

                                cierresLambda.add(auxObj);
                            } else {
                                auxE2.setTransicion(i, (Estado) elementoListaAux[0]);
                            }
                        }                        
                    }
                }
            }
        }
        elementoListaAux = cierresLambda.getFirst();
        inicial = (Estado) elementoListaAux[0];
        estados = vectorEstados(inicial);
        System.out.println("Termina conversion a AFD con éxito");
        /*
        -Con el estado inicial, que es el primer estado de la lista de cierres
        lambda se construye la "matriz" de transiciones y se simplifica el AFD
         */
    }

    /**
     * Genera los cierres lambda de todos los estados de un autómata finíto
     * generado con base en construcciones de Thompson
     *
     * @param inicial (Estado inicial)
     * @return Todos los cierres lambda
     */
    private LinkedList<Object[]> getCierresLambda(Estado inicial) {
        /*
        -Generar los cierres lambda de cada Estado como listas ligadas
        -Almacenar los cierres en una lista ligada que guarde listas ligadas
         */
        LinkedList<Object[]> todosCierres = new LinkedList<>();

        /*-Se crea y se relaciona cada cierre lambda con un estado nuevo: en el
            array de Object de tamaño 2, el primer espacio es para el nuevo
            estado y el segundo es para el cierre
         */
        Object[] elementoCierres = new Object[2];
        /*
        -Se maneja una lista ligada para almacenar los estados ya visitados en 
            el mismo momento en que se hace el cierre lambda de un estado
        -Se maneja pila para recordar los estados a los que hay que volver para 
            continuar con el cierre, esto debido a que cada vez que se encuentre
            estado no visitado se revisarán todas sus transiciones
         */
        LinkedList<Estado> visitados = new LinkedList<>();
        Queue<Estado> faltantesCierre = new LinkedList<>();
        Queue<Estado> faltantesLambda = new LinkedList<>();

        LinkedList<Estado> cierreLambda;

        int cantidadSim = simbolosEntrada.length();
        Estado auxE;
        LinkedList<Estado> lambdasEstado;
        Estado[] transicionesEstado;

        faltantesCierre.add(inicial);

        do {
            cierreLambda = new LinkedList<>();
            auxE = faltantesCierre.poll();
            LinkedListManager.agregarEstadoALista(auxE, cierreLambda);
            LinkedListManager.agregarEstadoALista(auxE, visitados);
            lambdasEstado = auxE.getTransLambda();
            elementoCierres = new Object[2];
            Estado aux2 = new Estado(cantidadSim, "Cierre Lambda " + auxE.getNombre());
            if (auxE.isInicial()) {
                aux2.setInicial(true);
            }

            elementoCierres[0] = aux2;

            /*-Si uno de los estados de un cierre es de aceptación, el estado nuevo 
                que representa ese cierre será de aceptación
             */
            Estado a = (Estado) elementoCierres[0];
            if (auxE.getAceptacion()) {
                a.setAceptacion(true);
            }

            //Añadiendo los estados de las transiciones lambda a ambas pilas
            for (Estado e : lambdasEstado) {
                if (!faltantesCierre.contains(e) && !visitados.contains(e)) {
                    faltantesCierre.add(e);
                }
                if (!faltantesLambda.contains(e)) {
                    faltantesLambda.add(e);
                }

                LinkedListManager.agregarEstadoALista(e, cierreLambda);

                if (!a.getAceptacion() && e.getAceptacion()) {
                    a.setAceptacion(true);
                }
            }

            //Añadiendo los estados de las transiciones NO lambda solo a la pila de faltantesCierre
            transicionesEstado = auxE.getTransiciones();
            Estado estado;
            for (int i = 0; i < cantidadSim; i++) {
                estado = transicionesEstado[i];
                if (estado != null) {
                    if (!faltantesCierre.contains(estado) && !visitados.contains(estado)) {
                        faltantesCierre.add(estado);
                    }
                }
            }

            while (!faltantesLambda.isEmpty()) {
                auxE = faltantesLambda.poll();
                lambdasEstado = auxE.getTransLambda();

                //Añadiendo los estados de las transiciones lambda a ambas pilas
                for (Estado e : lambdasEstado) {
                    if (!faltantesCierre.contains(e) && !visitados.contains(e)) {
                        faltantesCierre.add(e);
                    }
                    if (!faltantesLambda.contains(e)) {
                        faltantesLambda.add(e);
                    }

                    LinkedListManager.agregarEstadoALista(e, cierreLambda);

                    if (!a.getAceptacion() && e.getAceptacion()) {
                        a.setAceptacion(true);
                    }
                }

                /*
                -Una vez realizado el cierre lambda de un estado, se busca en sus 
                    transiciones para encontrar los estados a los que llega y realizar sus 
                    respectivos cierres también        
                 */
                //Añadiendo los estados de las transiciones NO lambda solo a la pila de faltantesCierre
                transicionesEstado = auxE.getTransiciones();
                for (int i = 0; i < cantidadSim; i++) {
                    estado = transicionesEstado[i];
                    if (estado != null) {
                        if (!faltantesCierre.contains(estado) && !visitados.contains(estado)) {
                            faltantesCierre.add(estado);
                        }
                    }
                }
            }//Fin de while
            elementoCierres[1] = cierreLambda;
            todosCierres.add(elementoCierres);
        } while (!faltantesCierre.isEmpty());
        //-El proceso de cierres finaliza cuando la pila faltantesCierre quede vacía

        return todosCierres;
    }

    /**
     * Devuelve el vector de Estados del AFD eliminando los estados extraños
     *
     * @param inicial
     * @return vector de estados
     */
    private Estado[] vectorEstados(Estado inicial) {
        Queue<Estado> visitados = new LinkedList<>();
        Queue<Estado> faltantes = new LinkedList<>();

        Estado[] retorno;
        faltantes.add(inicial);
        Estado aux, aux2;
        int cantidadSimbolos = simbolosEntrada.length();

        do {
            aux = faltantes.poll();
            if (!visitados.contains(aux)) {
                aux.quitarUltimaTransicion(cantidadSimbolos);
                visitados.add(aux);
            }
            for (int i = 0; i < cantidadSimbolos; i++) {
                aux2 = aux.getTransicion(i);
                if (aux2 != null) {
                    if (!faltantes.contains(aux2) && !visitados.contains(aux2)) {
                        faltantes.add(aux2);
                    }
                }
            }
        } while (!faltantes.isEmpty());

        int size = visitados.size();
        retorno = new Estado[size];
        for (int i = 0; i < size; i++) {
            retorno[i] = visitados.poll();
        }

        return retorno;
    }

    /**
     * Elimina estados equivalentes con el método de particiones
     *
     * @return vector de estados sin equivalentes
     */
    private void metodoParticiones() {
        if (estados == null) {
            return;
        }
        LinkedList<LinkedList<Estado>> particiones = generarParticiones();
        estados = generarEstadosConParticiones(particiones);
        estadoInicial = estados[0];
    }

    /**
     * Genera particiones con estados equivalentes de el autómata finito.
     *
     * @return Lista con las particiones
     */
    private LinkedList<LinkedList<Estado>> generarParticiones() {
        int cantidadSimbolos = simbolosEntrada.length();

        LinkedList<LinkedList<Estado>> todasParticiones = new LinkedList<>();
        LinkedList<Estado> particionA = new LinkedList<>();
        LinkedList<Estado> particionB = new LinkedList<>();
        Estado aux;
        boolean cambios;

        for (Estado e : estados) {
            if (e.getAceptacion()) {
                particionA.add(e);
            } else {
                particionB.add(e);
            }
        }
        todasParticiones.addLast(particionA);
        todasParticiones.addLast(particionB);

        Object[] nuevasParticiones = new Object[2];
        LinkedList<Estado> diferente;
        Iterator iter = todasParticiones.iterator();
        LinkedList<Estado> particion;
        do {
            particion = (LinkedList<Estado>) iter.next();
            if (particion.size() > 1) {

                cambios = false;
                for (int i = 0; i < cantidadSimbolos; i++) {
                    if (cambios) {
                        break;
                    }
                    //Buscar en qué partición está la transicion del primer estado y crear una nueva particion donde se guarde este
                    aux = particion.getFirst().getTransicion(i);
                    diferente = LinkedListManager.buscarParticion(todasParticiones, aux);
                    particionA = diferente;
                    nuevasParticiones[0] = new LinkedList<Estado>();
                    nuevasParticiones[1] = new LinkedList<Estado>();
                    for (Estado estado : particion) {
                        aux = estado.getTransicion(i);
                        particionB = LinkedListManager.buscarParticion(todasParticiones, aux);
                        //Comparar esta particion con la de los siguientes estados:
                        //Si son iguales se agrega el estado en la misma nueva particion y se agrega a visitados
                        if (particionA == particionB) {
                            particionB = (LinkedList<Estado>) nuevasParticiones[0];
                            particionB.add(estado);
                        } else {//Si es diferente se crea una nueva particion para los diferentes
                            particionB = (LinkedList<Estado>) nuevasParticiones[1];
                            particionB.add(estado);
                            cambios = true;
                        }
                    } //Fin foreach estados en particion
                }//Fin for para las transiciones
                if (cambios) {
                    todasParticiones.remove(particion);
                    for (Object nuevaParticion : nuevasParticiones) {
                        particionA = (LinkedList<Estado>) nuevaParticion;
                        if (particionA.size() > 0) {
                            todasParticiones.addLast((LinkedList<Estado>) nuevaParticion);
                        }
                    }
                    iter = todasParticiones.iterator();
                }
            }//Fin if particion tiene mas de 1 elemento            
        } while (iter.hasNext());

        return todasParticiones;
    }

    /**
     * Agrega el estado de error al autómata finíto, en cada transición vacía de
     * cada estado y al final del array de estados
     *
     */
    private void agregarEstadoError() {
        int cantidadSimbolos = simbolosEntrada.length();
        Estado aux;
        estadoError = new Estado(cantidadSimbolos, Operador.Error.toString());
        Estado[] nuevo = new Estado[estados.length + 1];
        int j = 0;
        for (Estado e : estados) {
            for (int i = 0; i < cantidadSimbolos; i++) {
                if (e.getTransicion(i) == null) {
                    e.setTransicion(i, estadoError);
                }
            }
            nuevo[j] = e;
            j++;
        }
        for (int i = 0; i < cantidadSimbolos; i++) {
            estadoError.setTransicion(i, estadoError);
        }
        estadoError.quitarUltimaTransicion(cantidadSimbolos);
        nuevo[estados.length] = estadoError;
        estados = nuevo;
    }

    /**
     * Crea un nuevo estado por cada particion en la lista de particiones.
     *
     * @param particiones
     * @return Array de nuevos estados
     */
    private Estado[] generarEstadosConParticiones(LinkedList<LinkedList<Estado>> particiones) {
        int cantidadSim = simbolosEntrada.length();

        Estado[] retorno = new Estado[particiones.size()];
        Estado aux, aux2, aux3;
        Map<LinkedList<Estado>, Estado> nuevosEstados = new HashMap<>();

        aux = estados[0];
        particiones = LinkedListManager
                .ordenarInicialParticiones(particiones, aux);

        LinkedList<Estado> particion = LinkedListManager.buscarParticion(particiones, aux);
        aux = new Estado(false, cantidadSim);
        aux.setInicial(true);
        nuevosEstados.put(particion, aux);

        aux = estados[estados.length - 1];
        particion = LinkedListManager.buscarParticionError(particiones);
        nuevosEstados.put(particion, aux);
        LinkedList<Estado> visitados = new LinkedList<>();

        for (LinkedList<Estado> part : particiones) {
            aux = nuevosEstados.get(part);
            if (aux == null) {
                aux = new Estado(false, cantidadSim);
                nuevosEstados.put(part, aux);
            }

            //Se hacen las transiciones del nuevo estado a los nuevos estados ligados a sus particiones
            aux2 = part.getFirst();
            for (int i = 0; i < cantidadSim; i++) {
                aux3 = aux2.getTransicion(i);
                particion = LinkedListManager.buscarParticion(particiones, aux3);
                aux3 = nuevosEstados.get(particion);
                if (aux3 == null) {
                    aux3 = new Estado(false, cantidadSim);
                    nuevosEstados.put(particion, aux3);
                }
                aux.setTransicion(i, aux3);
            }

            //Si un estado de la particion es de aceptación, el nuevo estado también debe serlo
            for (Estado estado : part) {
                if (estado.getAceptacion()) {
                    aux.setAceptacion(true);
                }
            }
            visitados.add(aux);
        }

        retorno = LinkedListManager.ordenarPorNombreNoLambda(visitados);
        return retorno;
    }

    public boolean validarCadena(String cadena) throws AFValidationException {
        if (cadena.isEmpty()) {
            throw new AFValidationException("No se puede validar una cadena vacía");
        }
        if (estados == null) {
            throw new AFValidationException("No se ha generado un AFD para validar la cadena");
        }
        if (!StringManager.validarSimbolosEntrada(cadena, simbolosEntrada)) {
            throw new AFValidationException("Se han ingresado símbolos que no pertenecen a los símbolos de entrada");
        }
        int longitud = cadena.length(), transicion;
        Estado actual = estadoInicial;
        char car;
        for (int i = 0; i < longitud; i++) {
            if (actual == estadoError) {
                return false;
            }
            car = cadena.charAt(i);
            transicion = simbolosEntrada.indexOf(car);
            actual = actual.getTransicion(transicion);
        }
        if (actual.getAceptacion()) {
            return true;
        }
        return false;
    }

    public String[][] generarSalidas() {
        int cantidadEstados = estados.length;
        int cantidadSimbolos = simbolosEntrada.length();

        String[][] retorno = new String[cantidadEstados][cantidadSimbolos + 2];
        for (int i = 0; i < cantidadEstados; i++) {
            retorno[i][0] = estados[i].getNombre();
            retorno[i][cantidadSimbolos + 1] = estados[i].getAceptacion() ? "Si" : "No";
            for (int j = 1; j < cantidadSimbolos + 1; j++) {
                retorno[i][j] = estados[i].getTransicion(j - 1).getNombre();
            }
        }
        return retorno;
    }
}
