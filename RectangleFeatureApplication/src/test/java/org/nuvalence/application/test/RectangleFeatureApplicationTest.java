package org.nuvalence.application.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.*;
import org.nuvalence.application.RectangleFeatureApplication;
import org.nuvalence.application.exception.ShapeException;
import org.nuvalence.application.util.Constants;
import org.nuvalence.test.utility.Util;

public class RectangleFeatureApplicationTest {

	static ConcurrentMap<Integer, List<Integer>> inputForIntersectionMap = new ConcurrentHashMap<Integer, List<Integer>>();

	static ConcurrentMap<Integer, List<Integer>> inputForContainmentMap = new ConcurrentHashMap<Integer, List<Integer>>();
	static ConcurrentMap<Integer, List<Integer>> inputForProperAdjMap = new ConcurrentHashMap<Integer, List<Integer>>();
	static ConcurrentMap<Integer, List<Integer>> inputForPartialAdjMap = new ConcurrentHashMap<Integer, List<Integer>>();
	static ConcurrentMap<Integer, List<Integer>> inputForSublineAdjMap = new ConcurrentHashMap<Integer, List<Integer>>();

	static ConcurrentMap<String, String> outputMap = new ConcurrentHashMap<String, String>();

	@Before
	public void setUp() {
		try {
			inputForIntersectionMap = Util.getInputFromFileForIntersectScenario();

			inputForContainmentMap = Util.getInputFromFileForContainmentScenario();
			inputForPartialAdjMap = Util.getInputFromFileForPartialAdjacentScenario();
			inputForProperAdjMap = Util.getInputFromFileForProperAdjacentScenario();
			inputForSublineAdjMap = Util.getInputFromFileForSublineScenario();

		} catch (ShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void executeForIntersectionScenarioTest() {
		try {
			outputMap = RectangleFeatureApplication.execute(inputForIntersectionMap);
			for (String key : outputMap.keySet()) {
				if (key.contains(Constants.IS_INTERSECTS)) {
					assertEquals("TRUE", outputMap.get(key));
				}
				if (key.contains(Constants.INTERSECT_POINTS)) {
					assertEquals("TRUE", containValidCoordinates(outputMap.get(key)));
				}
				if (key.contains(Constants.IS_CONTAIN)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.IS_ADJACENT)) {
					assertEquals(Constants.NOT_ADJACENT, outputMap.get(key));
				}
			}

		} catch (ShapeException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void executeForContainScenarioTest() {
		try {
			outputMap = RectangleFeatureApplication.execute(inputForContainmentMap);
			for (String key : outputMap.keySet()) {
				if (key.contains(Constants.IS_INTERSECTS)) {
					assertEquals("TRUE", outputMap.get(key));
				}
				if (key.contains(Constants.INTERSECT_POINTS)) {
					assertEquals("TRUE", containValidCoordinates(outputMap.get(key)));
				}
				if (key.contains(Constants.IS_CONTAIN)) {
					assertEquals("TRUE", outputMap.get(key));
				}
				if (key.contains(Constants.IS_ADJACENT)) {
					assertEquals(Constants.NOT_ADJACENT, outputMap.get(key));
				}
			}

		} catch (ShapeException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void executeForPartialAdjScenarioTest() {
		try {
			outputMap = RectangleFeatureApplication.execute(inputForPartialAdjMap);
			for (String key : outputMap.keySet()) {
				if (key.contains(Constants.IS_INTERSECTS)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.INTERSECT_POINTS)) {
					assertEquals("FALSE", containValidCoordinates(outputMap.get(key)));
				}
				if (key.contains(Constants.IS_CONTAIN)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.IS_ADJACENT)) {
					assertEquals(Constants.PARTIAL_ADJACENT, outputMap.get(key));
				}
			}

		} catch (ShapeException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void executeForProperAdjScenarioTest() {
		try {
			outputMap = RectangleFeatureApplication.execute(inputForProperAdjMap);
			for (String key : outputMap.keySet()) {
				if (key.contains(Constants.IS_INTERSECTS)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.INTERSECT_POINTS)) {
					assertEquals("FALSE", containValidCoordinates(outputMap.get(key)));
				}
				if (key.contains(Constants.IS_CONTAIN)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.IS_ADJACENT)) {
					assertEquals(Constants.PROPER_ADJACENT, outputMap.get(key));
				}
			}

		} catch (ShapeException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void executeForSublineAdjScenarioTest() {
		try {
			outputMap = RectangleFeatureApplication.execute(inputForSublineAdjMap);
			for (String key : outputMap.keySet()) {
				if (key.contains(Constants.IS_INTERSECTS)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.INTERSECT_POINTS)) {
					assertEquals("FALSE", containValidCoordinates(outputMap.get(key)));
				}
				if (key.contains(Constants.IS_CONTAIN)) {
					assertEquals("FALSE", outputMap.get(key));
				}
				if (key.contains(Constants.IS_ADJACENT)) {
					assertEquals(Constants.SUB_LINE_ADJACENT, outputMap.get(key));
				}
			}

		} catch (ShapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String containValidCoordinates(String coordinates) {
		List<Integer> coordinateList = Stream.of(coordinates.split(",")).map(String::trim).map(Integer::parseInt)
				.collect(Collectors.toList());
		if (coordinateList.stream().allMatch(i -> i > 0)) {
			return "TRUE";
		}
		return "FALSE";
	}

}
