/**
 * 
 */
package org.nuvalence.application.service.impl;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.nuvalence.application.exception.ShapeException;
import org.nuvalence.application.service.RectangleFeatureService;
import org.nuvalence.application.util.Constants;
import org.nuvalence.application.util.Utility;

/**
 * @author SOUVICK
 *
 */
public class RectangleFeatureServiceImpl implements RectangleFeatureService {

	/**
	 * Determines whether or not rectangles in the list {@code RectangleList}
	 * intersect. Two rectangles intersect if their intersection is nonempty.
	 *
	 *if intersects ,then returns the coordinates of the intersecting points
	 *
	 * @param r the specified {@List<Rectangle>}
	 * @return {@code ConcurrentMap<intesectingStatus =true & coordinates} if the specified rectangles in {@List<Rectangle>}
	 *         intersect; {@code ConcurrentMap<intesectingStatus =false} only otherwise.
	 */
	@Override
	public ConcurrentMap<String, String> isRectangleIntersecting(List<Rectangle> rectList) throws ShapeException {
		ConcurrentMap<String, String> intersetMap = new ConcurrentHashMap<String, String>();
		intersetMap.put(Constants.IS_INTERSECTS, "FALSE");
		intersetMap.put(Constants.INTERSECT_POINTS, "0,0,0,0");
		List<Integer> intersectionPointList = new ArrayList<Integer>();
		Iterator<Rectangle> rectIterator = rectList.iterator();
		if (rectIterator.hasNext()) {
			Rectangle rect1 = rectIterator.next();
			Rectangle rect2 = rectIterator.hasNext() ? rectIterator.next() : null;
			if (rect1 != null && rect2 != null) {
				if ((rect1.x < rect2.x + rect2.width) && (rect2.x < rect1.x + rect1.width)
						&& (rect1.y < rect2.y + rect2.height) && (rect2.y < rect1.y + rect1.height)) {

					intersectionPointList=intersectionPoints(rect1, rect2);
					String intersectingCoordinates = intersectionPointList.stream().map(String::valueOf).collect(Collectors.joining(","));
					
					intersetMap.put(Constants.IS_INTERSECTS, "TRUE");
					intersetMap.put(Constants.INTERSECT_POINTS, intersectingCoordinates);
				}

			}

		}

		return intersetMap;
	}

	/**
	 * Checks whether any rectangle in {@Code List<Rectangle>} entirely contains the
	 * {@code Rectangle} at the specified location {@code (X,Y)} with the specified
	 * dimensions {@code (W,H)}. X the specified X coordinate Y the specified Y
	 * coordinate W the width of the {@code Rectangle} H the height of the
	 * {@code Rectangle}
	 * 
	 * @return {@code true} if one Rectangle in {@List<Rectangle>} specified by
	 *         {@code (X, Y, W, H)} is entirely enclosed inside another Rectangle in
	 *         {@List<Rectangle>}; {@code false} otherwise.
	 */
	@Override
	public boolean isOneRectangleContainingOther(List<Rectangle> rectList) throws ShapeException {
		Iterator<Rectangle> rectIterator = rectList.iterator();
		if (rectIterator.hasNext()) {
			Rectangle rect1 = rectIterator.next();
			Rectangle rect2 = rectIterator.hasNext() ? rectIterator.next() : null;
			if (rect1 != null && rect2 != null) {
				int r1x = rect1.x;
				int r1y = rect1.y;
				int r1h = rect1.height;
				int r1w = rect1.width;

				int r2x = rect2.x;
				int r2y = rect2.y;
				int r2h = rect2.height;
				int r2w = rect2.width;

				if (r2x >= r1x && r2y >= r1y) {
					return ((r2x + r2w) <= (r1x + r1w) && (r2y + r2h <= r1y + r1h));
				} else if (r1x >= r2x && r1y >= r2y) {
					return ((r1x + r1w) <= (r2x + r2w) && (r1y + r1h <= r2y + r2h));
				}
			}
		}
		return false;
	}

	/**
	 * detects whether two rectangles are adjacent in
	 * {@Code List<Rectangle>} Returns a {@code String} that represents the
	 * adjacency types proper, sub-line or partial.
	 * <p>
	 * If two rectangles are exact X axis or Y axis adjacent, return
	 * {@code Proper Adjacent} .
	 * <p>
	 * sub-line share is a share where one side of rectangle A is a line that exists
	 * as a set of points wholly contained on some other side of rectangle B
	 * <p>
	 * partial is one where some line segment on a side of rectangle A exists as a
	 * set of points on some side of Rectangle B.
	 * 
	 * @param {@code List of Rectangle}
	 * @return {@code String adjacency type}
	 */

	@Override
	public String isOneRectangleAdjacentToOther(List<Rectangle> rectList) throws ShapeException {
		Iterator<Rectangle> rectIterator = rectList.iterator();
		if (rectIterator.hasNext()) {
			Rectangle rect1 = rectIterator.next();
			Rectangle rect2 = rectIterator.hasNext() ? rectIterator.next() : null;
			if (rect1 != null && rect2 != null) {
				if (rect1.x == (rect2.x + rect2.width) || rect2.x == (rect1.x + rect1.width)) {
					return checkXAxisAjacency(rect1, rect2);
				} else if (rect1.y == (rect2.y + rect2.height) || rect2.y == (rect1.y + rect1.height)) {
					return checkYAxisAjacency(rect1, rect2);
				}
			}
		}
		return Constants.NOT_ADJACENT;
	}

	/**
	 * when two rectangles are adjacent on either top & bottom, this utility computes the adjacency type
	 * based on the sharing side on X axis
	 * <p>
	 * If two rectangles are exact X axis adjacent, return  {@code "Proper Adjacent"} .
	 * <p>
	 * if the side of rectangle1 is a line that wholly combined by a other rectangle, return  {@code "Sub Line Adjacent"} .
	 * 
	 * <p>
	 * partial is one where some line segment on a side of rectangle A exists as a
	 * set of points on Rectangle B.
	 *
	 * @param {{@code Rectangle1 , Rectangle 2}}
	 * @return {@code String adjacency type}
 	 * 
	 */
	private String checkYAxisAjacency(Rectangle rect1, Rectangle rect2) {
		
		
		
		int firsRectTotalWidth = rect1.x + rect1.width;
		int secondRectTotalWidth = rect2.x +rect2.width;
		
		if(rect1.x ==rect2.x) {
			if(firsRectTotalWidth == secondRectTotalWidth) return Constants.PROPER_ADJACENT;
			else return checkAdjacentType(firsRectTotalWidth,secondRectTotalWidth);
		}else if (rect2.x>rect1.x && rect2.x < firsRectTotalWidth) 
			return checkAdjacentType(secondRectTotalWidth,firsRectTotalWidth);
		else if (rect1.x>rect2.x && rect1.x <secondRectTotalWidth)
			return checkAdjacentType(firsRectTotalWidth,secondRectTotalWidth);
		
		return Constants.NOT_ADJACENT;
		}

	/**
	 * 
	 * when two rectangles are adjacent on either side, this utility computes the adjacency type
	 * based on the sharing side on Y axis
	 * <p>
	 * If two rectangles are exact Y axis adjacent, return  {@code "Proper Adjacent"} .
	 * <p>
	 * if the height of rectangle 1 is a line that wholly combined by a other rectangle, return  {@code "Sub Line Adjacent"} .
	 * 
	 * <p>
	 * partial is one where some line segment on a height of rectangle A exists as a
	 * set of points on Rectangle B.
	 * @param {{@code Rectangle1 , Rectangle 2}}
	 * @return {@code String adjacency type}
	 * 
	 */
	private String checkXAxisAjacency(Rectangle rect1, Rectangle rect2) {
		int firstRectTotalHeight = rect1.y+rect1.height;
		int secondRectTotalHeight = rect2.y +rect2.height;
		
		if(rect1.y ==rect2.y) {
			if(firstRectTotalHeight == secondRectTotalHeight) return Constants.PROPER_ADJACENT;
			else return checkAdjacentType(firstRectTotalHeight,secondRectTotalHeight);
		}else if (rect2.y>rect1.y && rect2.y < firstRectTotalHeight) 
			return checkAdjacentType(secondRectTotalHeight,firstRectTotalHeight);
		else if (rect1.y>rect2.y && rect1.y <secondRectTotalHeight)
			return checkAdjacentType(firstRectTotalHeight,secondRectTotalHeight);
		
		return Constants.NOT_ADJACENT;
	
	}
	
	/**
	 * Internal utility to determine if the adjacent type is of type SUBLINE or PARTIAL
	 * 
	 * @param ( int - value of height/width for the higher rectangle , int - value of height/width for the lower rectangle
	 * @return {@code String}
	 * 
	 */

	private String checkAdjacentType(int higherRectTotalHeight, int lowerRectTotalHeight) {
		if(higherRectTotalHeight <= lowerRectTotalHeight) return Constants.SUB_LINE_ADJACENT;
		return Constants.PARTIAL_ADJACENT;
	}

	/**
	 * Computes the intersection of this {@code Rectangle} with the specified
	 * {@code Rectangle}. Returns the coordinates that represents the intersection
	 * of the two rectangles. If the two rectangles do not intersect, the result
	 * will be an empty rectangle.
	 *
	 * @param r1 the specified {@code Rectangle}
	 * @param r2 the specified {@code Rectangle}
	 * @return intersection points of the rectangle
	 */
	public List<Integer> intersectionPoints(Rectangle r1, Rectangle r2) {
		List<Integer> intersectionPoints = new ArrayList<Integer>();
		
		int r1TotalWidth = r1.x + r1.width;
		int r1TotalHeight = r1.y + r1.height;
		int r2TotalWidth = r2.x + r2.width;
		int r2TotalHeight = r2.y + r2.height;
		
		int bottomLeftX = Math.max(r1.x,r2.x);
		int bottomLeftY = Math.max(r1.y,r2.y);
		int topRightX=Math.min(r1TotalWidth, r2TotalWidth)-bottomLeftX;
		int topRightY=Math.min(r1TotalHeight, r2TotalHeight)-bottomLeftY;
		
		intersectionPoints.add(bottomLeftX);
		intersectionPoints.add(bottomLeftY);
		intersectionPoints.add(topRightX);
		intersectionPoints.add(topRightY);

		return intersectionPoints;
	}
}
