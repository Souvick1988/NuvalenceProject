/**
 * 
 */
package org.nuvalence.application.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.nuvalence.application.exception.ShapeException;

/**
 * This is a custom utility class to implement all static util methods
 * @author SOUVICK
 *
 */
public class Utility {

	
	/**
	 * Util method to validates the input coordinates for a rectangle
	 * 
	 * @param List of coordinates (x,y,width & height)
	 * @throws ShapeException
	 * 
	 */
	
	public static boolean isValidCoordinate(List<Integer> coordinates) throws ShapeException {
		if (coordinates.size() != 4)
			throw new ShapeException("co-ordinatave must have 4 valid numerics");
		coordinates.stream().forEach(coordinate -> {
			if (coordinate < 0) {
				try {
					throw new ShapeException("coordinates have negative number");
				} catch (ShapeException ex) {
				}
			}
		});
		return true;
	}

	/*
	 * public static String checkXAxisAjacency(Rectangle rect1, Rectangle rect2) {
	 * 
	 * int firstRectTotalHeight = rect1.y+rect1.height; int secondRectTotalHeight =
	 * rect2.y +rect2.height;
	 * 
	 * if(rect1.y ==rect2.y) { if(firstRectTotalHeight == secondRectTotalHeight)
	 * return Constants.PROPER_ADJACENT; else return
	 * checkAdjacentType(firstRectTotalHeight,secondRectTotalHeight); }else if
	 * (rect2.y>rect1.y && rect2.y < firstRectTotalHeight) return
	 * checkAdjacentType(secondRectTotalHeight,firstRectTotalHeight); else if
	 * (rect1.y>rect2.y && rect1.y <secondRectTotalHeight) return
	 * checkAdjacentType(firstRectTotalHeight,secondRectTotalHeight);
	 * 
	 * return Constants.NOT_ADJACENT; }
	 * 
	 * private static String checkAdjacentType(int higherRectTotalHeight, int
	 * lowerRectTotalHeight) { if(higherRectTotalHeight <= lowerRectTotalHeight)
	 * return Constants.SUB_LINE_ADJACENT; return Constants.PARTIAL_ADJACENT; }
	 * 
	 * public static String checkYAxisAjacency(Rectangle rect1, Rectangle rect2) {
	 * int firsRectTotalWidth = rect1.x + rect1.width; int secondRectTotalWidth =
	 * rect2.x +rect2.width;
	 * 
	 * if(rect1.x ==rect2.x) { if(firsRectTotalWidth == secondRectTotalWidth) return
	 * Constants.PROPER_ADJACENT; else return
	 * checkAdjacentType(firsRectTotalWidth,secondRectTotalWidth); }else if
	 * (rect2.x>rect1.x && rect2.x < firsRectTotalWidth) return
	 * checkAdjacentType(secondRectTotalWidth,firsRectTotalWidth); else if
	 * (rect1.x>rect2.x && rect1.x <secondRectTotalWidth) return
	 * checkAdjacentType(firsRectTotalWidth,secondRectTotalWidth);
	 * 
	 * return Constants.NOT_ADJACENT; }
	 */
	
	
	/**
	 * Util method to get the user input for rectangle coordinates
	 * 
	 * Use Scanner utility to inspect and validate the input for a valid positive number for coordinates
	 *  
	 * @param List of coordinates (x,y,width & height)
	 * @throws ShapeException
	 * 
	 */

	
}
