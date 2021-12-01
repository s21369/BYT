package a_Introductory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuadrilateralTest {

	Quadrilateral square1, square2, rectangle1, rectangle2, quad;

	// removed unnecessary exception
	@Before
	public void setUp() {
		/* Set up two squares,
		 * two rectangles that are not squares,
		 * and a quad that is neither a rectangle nor a square. */
		square1 = new Quadrilateral(new Point(2, 3), new Point(4, 7), new Point(8, 5), new Point(6, 1));
		square2 = new Quadrilateral(new Point(-1, -1), new Point(-1, 1), new Point(1, 1), new Point(1, -1));
		rectangle1 = new Quadrilateral(new Point(4, 2), new Point(3, 4), new Point(9, 7), new Point(10, 5));
		rectangle2 = new Quadrilateral(new Point(-2, -1), new Point(-2, 1), new Point(2, 1), new Point(2, -1));
		quad = new Quadrilateral(new Point(-2, -2), new Point(-1, 1), new Point(1, 1), new Point(1, -1));
	}
	
	@Test
	public void testRectangle() {
		String msg = "Should be a rectangle";
		assertTrue(msg, square1.isRectangle());
		assertTrue(msg, square2.isRectangle());
		assertTrue(msg, rectangle1.isRectangle());
		assertTrue(msg, rectangle2.isRectangle());
		assertFalse("Should not be a rectangle", quad.isRectangle());
	}
	
	@Test
	public void testSquare() {
		String tMsg = "Should be a square";
		String fMsg = "Should not be a square";
		assertTrue(tMsg, square1.isSquare());
		assertTrue(tMsg, square2.isSquare());
		assertFalse(fMsg, rectangle1.isSquare());
		assertFalse(fMsg, rectangle2.isSquare());
		assertFalse(fMsg, quad.isSquare());
	}

}
