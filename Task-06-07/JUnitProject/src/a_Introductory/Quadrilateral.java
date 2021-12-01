package a_Introductory;

public class Quadrilateral {

	// removed unnecessary points
	private final Line l1, l2, l3, l4;
	
	public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
		this.l1 = new Line(p1, p2);
		this.l2 = new Line(p2, p3);
		this.l3 = new Line(p3, p4);
		this.l4 = new Line(p4, p1);
	}
	
	public Boolean isRectangle() {
		Vector2D v1 = l1.getVector();
		Vector2D v2 = l2.getVector();
		Vector2D v3 = l3.getVector();
		Vector2D v4 = l4.getVector();
		
		return (v1.isOrthogonalTo(v2) &&
				v2.isOrthogonalTo(v3) &&
				v3.isOrthogonalTo(v4) &&
				v4.isOrthogonalTo(v1));
	}

	// fixed check for all sides
	public Boolean isSquare() {
		return (isRectangle() &&
				l1.isSameLengthAs(l2) &&
				l2.isSameLengthAs(l3) &&
				l3.isSameLengthAs(l4));
	}

}
