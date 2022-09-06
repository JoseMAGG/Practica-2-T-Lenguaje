/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/** Exception utilizada para fallos en la construcción de un AF
 *
 * @author Jose
 */
public class AFConstructionException extends Exception{

    /**
     * Creates a new instance of <code>AFConstructionException</code> without
     * detail message.
     */
    public AFConstructionException() {
    }

    /**
     * Constructs an instance of <code>AFConstructionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AFConstructionException(String msg) {
        super(msg);
    }
}
