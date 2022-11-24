/**
 * 
 */
package org.nuvalence.application.service;

import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.nuvalence.application.exception.ShapeException;

/**
 * @author SOUVICK
 *
 */
public interface RectangleFeatureService {

	public ConcurrentMap<String, String> isRectangleIntersecting(List<Rectangle> rectList) throws ShapeException;

	public boolean isOneRectangleContainingOther(List<Rectangle> rectList) throws ShapeException;

	public String isOneRectangleAdjacentToOther(List<Rectangle> rectList) throws ShapeException;

}
