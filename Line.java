import java.awt.geom.Point2D;

public class Line {
	double x1;
	double x2; 
	double y1;
	double y2;
	Point startPt;
	Point endPt;
	
	public Line (double x1, double y1, double x2, double y2) {
		startPt = new Point(x1, y1);
		endPt = new Point (x2, y2);
	}
	
	public Line (Point point1, Point point2) {
		startPt = point1;
		endPt = point2;
	}

	public String toString() {
		return startPt.coordinate1 + " " + startPt.coordinate2 + " " + endPt.coordinate1 + " " +
				endPt.coordinate2;
	}
}