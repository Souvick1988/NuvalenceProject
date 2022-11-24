/**
 * 
 */
package org.nuvalence.test.utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.nuvalence.application.util.Constants;

/**
 * @author SOUVICK
 *
 */
public class PaintShapes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static List<Rectangle> rectList;

	public PaintShapes(BorderLayout borderLayout) {
		super();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (rectList != null) {
			rectList.stream().forEach(rect -> {
				g.drawRect(rect.x, rect.y, rect.width, rect.height);
			});
			paintIntersectedAreas(g);
		}
	}

	private void paintIntersectedAreas(Graphics g) {
		Iterator<Rectangle> rectIterator = rectList.iterator();
		if (rectIterator.hasNext()) {
			Rectangle rect1 = rectIterator.next();
			Rectangle rect2 = rectIterator.hasNext() ? rectIterator.next() : null;
			if (rect1 != null && rect2 != null) {
				Rectangle interSectRect = rect1.intersection(rect2);
				g.setColor(Color.yellow);
				g.fillRect(interSectRect.x, interSectRect.y, interSectRect.width, interSectRect.height);
			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		int maxHeight = 0, maxWidth = 0, minX = 0, minY = 0;
		Iterator<Rectangle> rectIterator = rectList.iterator();
		if (rectIterator.hasNext()) {
			Rectangle rect1 = rectIterator.next();
			Rectangle rect2 = rectIterator.hasNext() ? rectIterator.next() : null;
			if (rect1 != null && rect2 != null) {
				maxHeight = Math.max(maxHeight, Math.max(rect1.height, rect2.height));
				maxWidth = Math.max(maxWidth, Math.max(rect1.width, rect2.width));
				minX = Math.min(minX, Math.min(rect1.x, rect2.x));
				minY = Math.min(minY, Math.min(rect1.y, rect2.y));
			}
		}
		return new Dimension((minX + (2 * maxWidth)), (minY + (2 * maxHeight)));

	}

	public static void showGUI(List<Rectangle> rectListParam, String isIntersect,String intersectPoint,
			String isContain, String adjacentType,String scenarioName) {
		// public static void showGUI(List<Rectangle> rectListParam) {
		if (rectListParam != null && rectListParam.size() > 0) {
			rectList = new ArrayList<Rectangle>();
			rectList.addAll(rectListParam);
			PaintShapes mainPanel = new PaintShapes(new BorderLayout());
			JFrame frame = new JFrame(scenarioName);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			String text = "";
			
				text += isContain+" <br/>";
				text += isIntersect+ "<br/>";
				text += "("+intersectPoint + ") <br/>";
				text += adjacentType;
			JLabel label = new JLabel("<html>" + text + "</html>");
			// mainPanel.add(label,BorderLayout.PAGE_END);
			frame.getContentPane().add(mainPanel);
			frame.pack();
			frame.add(label, BorderLayout.PAGE_END);
			//frame.setBackground(Color.WHITE);
			frame.setLocationByPlatform(true);
			frame.setVisible(true);

		}
	}
}
