package a_Introductory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

	Point p1, p2, p3;

	// removed unnecessary exception
	@Before
	public void setUp() {
		p1 = new Point(7, 9);
		p2 = new Point(-3, -30);
		p3 = new Point(-10, 3);
	}

	@Test
	public void testAdd() {
		Point res1 = p1.add(p2);
		Point res2 = p1.add(p3);

		// should be Integer.valueOf() and not just int, added error message, fixed res.x to res.y
		assertEquals(String.format("res1.x (%d) should be 4", res1.x), Integer.valueOf(4), res1.x);
		assertEquals(String.format("res1.y (%d) should be -21", res1.y), Integer.valueOf(-21), res1.y);
		assertEquals(String.format("res2.x (%d) should be -3", res2.x), Integer.valueOf(-3), res2.x);
		assertEquals(String.format("res2.y (%d) should be 12", res2.y), Integer.valueOf(12), res2.y);
	}

	@Test
	public void testSub() {
		Point res1 = p1.sub(p2);
		Point res2 = p1.sub(p3);

		// should be Integer.valueOf() and not just int, added error message, fixed values and res.x to res.y
		assertEquals(String.format("res1.x (%d) should be 10", res1.x), Integer.valueOf(10), res1.x);
		assertEquals(String.format("res1.y (%d) should be 39", res1.y), Integer.valueOf(39), res1.y);
		assertEquals(String.format("res2.x (%d) should be 17", res2.x), Integer.valueOf(17), res2.x);
		assertEquals(String.format("res2.y (%d) should be 6", res2.y), Integer.valueOf(6), res2.y);
	}

}
