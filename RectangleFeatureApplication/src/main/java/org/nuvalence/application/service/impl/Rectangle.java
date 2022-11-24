/**
 * 
 */
package org.nuvalence.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.nuvalence.application.exception.ShapeException;
import org.nuvalence.application.service.Shape;
import org.nuvalence.application.util.Utility;

/**
 * @author SOUVICK
 *
 */
public class Rectangle extends Shape {

	private List<Integer> coordinates;
	public double bottomLeftX;
	public double bottomLeftY;
	public double rightTopX;
	public double rightTopY;

	public Rectangle(List<Integer> coordinateParam) {
		this.coordinates = new ArrayList<Integer>();
		this.coordinates.addAll(coordinateParam);
	}

	@Override
	public double getArea() {
		if (coordinates.size() == 4) {
			return coordinates.get(2) * coordinates.get(3);
		}
		return 0;
	}

	@Override
	public double getPerimeter() {
		if (coordinates.size() == 4) {
			return 2 * (coordinates.get(2) + coordinates.get(3));
		}
		return 0;
	}

	public double getDiagonal() {
		if (coordinates.size() == 4) {

			return Math.sqrt(Math.pow(coordinates.get(2), 2) + Math.pow(coordinates.get(3), 2));
		}
		return 0;
	}

	public java.awt.Rectangle createRectangle() throws ShapeException {

		if (Utility.isValidCoordinate(coordinates)) {
			return new java.awt.Rectangle(coordinates.get(0), coordinates.get(1), coordinates.get(2),
					coordinates.get(3));
		} else
			throw new ShapeException("Rectangle creation failed due to incorrect coodinates");

	}

}
