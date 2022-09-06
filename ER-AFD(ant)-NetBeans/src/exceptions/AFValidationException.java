/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/** Exception utilizada para fallos en la validación de una cadena con un AF
 *
 * @author Jose
 */
public class AFValidationException extends Exception{

    /**
     * Creates a new instance of <code>AFValidationException</code> without
     * detail message.
     */
    public AFValidationException() {
    }

    /**
     * Constructs an instance of <code>AFValidationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AFValidationException(String msg) {
        super(msg);
    }
}
