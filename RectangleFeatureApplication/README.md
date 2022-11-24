				RECTANGLE FEATURE APPLICATION
				-----------------------------------------
				
				
Table :
	1. GOAL
	3. SPECIFICATION AND REQUIREMENT
	4. DESIGN
	

GOAL

		Implement a solution by using certain algorithms that analyze rectangles and features that exist among rectangles. Implementaon is required to cover the following:
		1. Intersecon: Must be able to determine whether two rectangles have one or more intersecting lines and produce a result idenfying the points of intersecon. 
		2. Containment: Solution must be able to determine whether a rectangle is wholly contained within another rectangle. 
		3. Adjacency: Implement the ability to detect whether two rectangles are adjacent. Adjacency is defined as the sharing of at least one side. Side sharing may be proper, sub-line or partial. A sub-line share is a share where one side of rectangle A is a line that exists as a set of points wholly contained on some other side of rectangle B, where partial is one where some line segment on a side of rectangle A exists as a set of points on some side of Rectangle B. 


SPECIFICATION AND REQUIREMENTS

		1. An implementation of the rectangle enty as well as implementaons for the algorithms thatdefine the operations listed above.
		2. Appropriate documentaon
		3. Test cases/unit tests
		4. For the exercise, please add your GitHub link or another public repository when you submit your exercise.

DESIGN

JDK Version 1.8 and above
dependecy - hamcrest-core & junit

		Implement a class named RectangleFeatureApplication at the root package, which has a main method named execute() which will expect coordinates for rectangles in a map (Integer, List<Integer>). execute() will read the data from input param and orchestrate the execution flow to initialize the rectangles & invokes service methods to idenfy the intersection, containment & adjacecy. 

		The Shape class
		The abstract class & the super class of all shape type classes. Shape contains the following member methods:

		getArea(), an abstract method which returns this shape's area.
		getPerimeter(), an abstract method which returns this shape's perimeter.

		The ShapeException class
		This is a simple Exception-type object which is thrown by ShapeHandler() or ShapeDescription() in response to invalid syntax within shapes.txt or other I/O issues.


		The RectangleFeatureService Interface

		This interface design the abstract methods for implementation 

		The RectangleFeatureServiceImpl Class

		This is the implementation class of above interface. Contains following methods

		isRectangleIntersecting() :  identify if two rectangles are intersecting and returns intersecting points
		isOneRectangleContainingOther()  : idenfy if one rectangle containing the other rectangle
		isOneRectangleAdjacentToOther() : idenfy if two rectangles are adjacent and type of adjacecy

		The Utility Class
		This is an utililty class contains static util methods. it contains the following methods
		isValidCoordinate : validate the coordinates

		The Constants Class
		 This is a constant class for String literals
		 
		The RectangleFeatureApplicationTest Class
		This is a junit test class for executing various scenarios as part of unit testing

		The PaintShapes Class
		This is to draw the input rectangles and intersected areas in a JPanel. Usefull for GUI view