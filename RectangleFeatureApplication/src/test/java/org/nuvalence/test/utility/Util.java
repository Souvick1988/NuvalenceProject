/**
 * 
 */
package org.nuvalence.test.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nuvalence.application.exception.ShapeException;

/**
 * This is a custom utility class to implement all static util methods These
 * Util methods are intended to use in @Test class
 * 
 * @author SOUVICK
 *
 */
public class Util {

	public static ConcurrentMap<Integer, List<Integer>> getUserInput() throws ShapeException {

		ConcurrentMap<Integer, List<Integer>> coordinateMap = new ConcurrentHashMap<Integer, List<Integer>>();
		;
		List<Integer> coordinateList;

		Scanner inputScanner = new Scanner(System.in);
		int x, y, height, width;
		for (int i = 0; i < 2; i++) {
			if (i == 0)
				System.out.println(" Enter the coordinate for the first rectangle");
			else
				System.out.println("Enter the coordinate for the second rectanngle");
			do {
				System.out.println("X coordinate value :");
				while (!inputScanner.hasNext()) {
					System.out.println("That's not a valid number");
					inputScanner.next();
				}
				x = inputScanner.nextInt();
				System.out.println("Y coordinate value :");
				while (!inputScanner.hasNext()) {
					System.out.println("That's not a valid number");
					inputScanner.next();
				}
				y = inputScanner.nextInt();
				System.out.println(" Width :");
				while (!inputScanner.hasNext()) {
					System.out.println("That's not a valid number");
					inputScanner.next();
				}
				width = inputScanner.nextInt();
				System.out.println("Height :");
				while (!inputScanner.hasNext()) {
					System.out.println("That's not a valid number");
					inputScanner.next();
				}
				height = inputScanner.nextInt();
			} while (x < 0 || y < 0 || height <= 0 || width <= 0);
			coordinateList = new ArrayList<Integer>();

			coordinateList.add(x);
			coordinateList.add(y);
			coordinateList.add(width);
			coordinateList.add(height);
			coordinateMap.put(i, coordinateList);
		}
		inputScanner.close();
		return coordinateMap;

	}

	public static ConcurrentMap<Integer, List<Integer>> getInputFromFileForIntersectScenario() throws ShapeException {

		InputStream input;
		ConcurrentMap<Integer, List<Integer>> inputMap = new ConcurrentHashMap<Integer, List<Integer>>();
		try {
			File wd = new File(".");
			System.out.println("working dir: " + wd.getAbsolutePath());
			input = new FileInputStream("src\\test\\resources\\Intersect.properties");
			Properties prop = new Properties();

			prop.load(input);
			List<Integer> rect1CoordinateList = Stream.of(prop.getProperty("rect1.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> rect2CoordinateList = Stream.of(prop.getProperty("rect2.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			inputMap.put(0, rect1CoordinateList);
			inputMap.put(1, rect2CoordinateList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputMap;
	}
	
	
	public static ConcurrentMap<Integer, List<Integer>> getInputFromFileForContainmentScenario() throws ShapeException {

		InputStream input;
		ConcurrentMap<Integer, List<Integer>> inputMap = new ConcurrentHashMap<Integer, List<Integer>>();
		try {
			input = new FileInputStream(new File("src\\test\\resources\\Containment.properties"));
			Properties prop = new Properties();

			prop.load(input);
			List<Integer> rect1CoordinateList = Stream.of(prop.getProperty("rect1.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> rect2CoordinateList = Stream.of(prop.getProperty("rect2.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			inputMap.put(0, rect1CoordinateList);
			inputMap.put(1, rect2CoordinateList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputMap;
	}
	
	
	public static ConcurrentMap<Integer, List<Integer>> getInputFromFileForPartialAdjacentScenario() throws ShapeException {

		InputStream input;
		ConcurrentMap<Integer, List<Integer>> inputMap = new ConcurrentHashMap<Integer, List<Integer>>();
		try {
			input = new FileInputStream(new File("src\\test\\resources\\PartialAdjacent.properties"));
			Properties prop = new Properties();

			prop.load(input);
			List<Integer> rect1CoordinateList = Stream.of(prop.getProperty("rect1.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> rect2CoordinateList = Stream.of(prop.getProperty("rect2.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			inputMap.put(0, rect1CoordinateList);
			inputMap.put(1, rect2CoordinateList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputMap;
	}
	
	public static ConcurrentMap<Integer, List<Integer>> getInputFromFileForProperAdjacentScenario() throws ShapeException {

		InputStream input;
		ConcurrentMap<Integer, List<Integer>> inputMap = new ConcurrentHashMap<Integer, List<Integer>>();
		try {
			input = new FileInputStream(new File("src\\test\\resources\\ProperAdjacent.properties"));
			Properties prop = new Properties();

			prop.load(input);
			List<Integer> rect1CoordinateList = Stream.of(prop.getProperty("rect1.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> rect2CoordinateList = Stream.of(prop.getProperty("rect2.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			inputMap.put(0, rect1CoordinateList);
			inputMap.put(1, rect2CoordinateList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputMap;
	}
	
	public static ConcurrentMap<Integer, List<Integer>> getInputFromFileForSublineScenario() throws ShapeException {

		InputStream input;
		ConcurrentMap<Integer, List<Integer>> inputMap = new ConcurrentHashMap<Integer, List<Integer>>();
		try {
			input = new FileInputStream(new File("src\\test\\resources\\SubLineAdjacent.properties"));
			Properties prop = new Properties();

			prop.load(input);
			List<Integer> rect1CoordinateList = Stream.of(prop.getProperty("rect1.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			List<Integer> rect2CoordinateList = Stream.of(prop.getProperty("rect2.coordinates").split(","))
					.map(String::trim).map(Integer::parseInt).collect(Collectors.toList());

			inputMap.put(0, rect1CoordinateList);
			inputMap.put(1, rect2CoordinateList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return inputMap;
	}
	

}
