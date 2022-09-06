/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exceptions;

/** Exception utilizada para fallos en el reconocimiento de una ER o regex
 *
 * @author Jose
 */
public class RegexRecognitionException extends Exception{

    /**
     * Creates a new instance of <code>RegexRecognitionException</code> without
     * detail message.
     */
    public RegexRecognitionException() {
    }

    /**
     * Constructs an instance of <code>RegexRecognitionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RegexRecognitionException(String msg) {
        super(msg);
    }
}
