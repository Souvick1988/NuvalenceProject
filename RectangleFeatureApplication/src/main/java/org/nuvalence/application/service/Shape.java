/**
 * 
 */
package org.nuvalence.application.service;

/**
 * @author Souvick
 *
 */
public abstract class Shape {

	public enum ShapeTypes {
		CIRCLE, RECTANGLE, SQUARE, TRIANGLE
	}

	private ShapeTypes shapeTypes;

	/**
	 * Returns the area of a shape
	 * 
	 * @return the area
	 */
	public abstract double getArea();

	/**
	 * Returns the perimeter of a shape
	 * 
	 * @return the perimeter
	 */
	public abstract double getPerimeter();
}
