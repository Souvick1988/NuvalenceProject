/**
 * 
 */
package org.nuvalence.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.nuvalence.application.exception.ShapeException;
import org.nuvalence.application.service.RectangleFeatureService;
import org.nuvalence.application.service.impl.Rectangle;
import org.nuvalence.application.service.impl.RectangleFeatureServiceImpl;
import org.nuvalence.application.util.Constants;
import org.nuvalence.application.util.Utility;

/**
 * This is the main class and entry point of the program
 * execute () orchestrate the flow
 * 
 * @author SOUVICK
 *
 */
public class RectangleFeatureApplication {

	private static List<java.awt.Rectangle> rectList;
	private static RectangleFeatureService service;

	/**
	 * Entry point of the program to determine the following feature
	 * 
	 * if two rectangle intersects each other , if intersects it determines the
	 * intersecting points
	 * 
	 * if one rectangle contain the other rectangle
	 * 
	 * if two rectangle share the same side, sharing side can be of 3 types proper,
	 * subline & partial
	 * 
	 * Program also draw the rectangles in a JPanel based on the input coordinate
	 * 
	 * @param coordinateMap , where key is the index number of rectangle value is
	 *                      the list of coordinates (x,y,width & height)
	 * @throws ShapeException
	 */
	public static ConcurrentMap<String, String> execute(ConcurrentMap<Integer, List<Integer>> coordinateMap)
			throws ShapeException {
		ConcurrentMap<String, String> outputMap = new ConcurrentHashMap<String, String>();
		if (coordinateMap != null && coordinateMap.keySet().size() > 0) {
			rectList = new ArrayList<java.awt.Rectangle>();
			coordinateMap.keySet().stream().forEach(coordinateList -> {
				try {
					// Rectangle is a custom POJO class, which need coordinates(x,y,width,height) to
					// get initialized
					// createRectangle is a method in Rectangle class to create and return a
					// java.awt.Rectangle using the coordinates used during initialization
					Rectangle rectangle = new Rectangle(coordinateMap.get(coordinateList));
					rectList.add(rectangle.createRectangle());

					System.out.println(" Area : " + rectangle.getArea());
					System.out.println(" Perimeter : " + rectangle.getPerimeter());
					System.out.println(" Diagonal : " + rectangle.getDiagonal());

				} catch (ShapeException ex) {
					ex.printStackTrace();
				}

			});

			if (rectList != null && rectList.size() > 0) {
				service = new RectangleFeatureServiceImpl();
				ConcurrentMap<String, String> intersectMap = service.isRectangleIntersecting(rectList);
				intersectMap.forEach((k, v) -> outputMap.put(k, v));
				outputMap.put(Constants.IS_CONTAIN, String.valueOf(service.isOneRectangleContainingOther(rectList)).toUpperCase());
				outputMap.put(Constants.IS_ADJACENT, service.isOneRectangleAdjacentToOther(rectList));
				// PaintShapes.showGUI(rectList, intersectMap, isContain, adjacentType);

			}
		}
		return outputMap;
	}
}
