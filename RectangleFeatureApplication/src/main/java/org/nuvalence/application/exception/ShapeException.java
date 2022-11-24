/**
 * 
 */
package org.nuvalence.application.exception;

/**
 * This is a custom exception handler,dedicated exclusively to this Rectangle
 * feature assignment
 * 
 * @author SOUVICK
 *
 */
public class ShapeException extends Exception {

	/**
	 * satisfy a compiler warnings, ignore this
	 */
	private static final long serialVersionUID = 1L;

	public ShapeException() {
		super();
	}

	public ShapeException(String message) {
		super(message);
	}
}
