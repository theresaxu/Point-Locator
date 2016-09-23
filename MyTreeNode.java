
public class MyTreeNode{
	public Line line;
	public MyTreeNode leftChild;
	public MyTreeNode rightChild;
	public MyTreeNode parent;
	private static final int COUNTERCLOCKWISE = 1;
	private static final int CLOCKWISE = -1;
	private static final int COLINEAR = 0;
	
	public MyTreeNode (Line line) {
		this.line = line;
	}
	
	//insert line into binary tree
	public void insertNode (Line line) {
		if (ccw(this.line.startPt,this.line.endPt, line.startPt)<0 && ccw(this.line.startPt, this.line.endPt, line.endPt) <0||
			Math.abs(ccw(this.line.startPt, this.line.endPt, line.startPt))<0.000000001 && ccw(this.line.startPt, this.line.endPt, line.endPt)<0 || 
			ccw(this.line.startPt, this.line.endPt, line.endPt) == 0 && ccw(this.line.startPt, this.line.endPt, line.startPt)<0) {
			if (this.leftChild == null ){
				leftChild = new MyTreeNode(line);
				leftChild.parent = this;
			}
			else 
				leftChild.insertNode(line);
		}
		
		else if (ccw(this.line.startPt,this.line.endPt, line.startPt)>0 && ccw(this.line.startPt, this.line.endPt, line.endPt) >0||
				ccw(this.line.startPt, this.line.endPt, line.startPt)==0 && ccw(this.line.startPt, this.line.endPt, line.endPt)>0 || 
				ccw(this.line.startPt, this.line.endPt, line.endPt) == 0 && ccw(this.line.startPt, this.line.endPt, line.startPt)>0) {
			if (this.rightChild == null){
				rightChild = new MyTreeNode(line);
				rightChild.parent = this;
			}
			else 
				rightChild.insertNode(line);
		}
		//if they intersect, then split the line into two separate lines
		else if ((ccw(this.line.startPt,this.line.endPt, line.startPt)>0) && ccw(this.line.startPt,this.line.endPt, line.endPt) <0 ||  
				(ccw(this.line.startPt,this.line.endPt, line.startPt) <0 && ccw(this.line.startPt,this.line.endPt, line.endPt)>0)) {
				
				Point intersectPt = intersectionPt(this.line, line);
				Line newLine1 = new Line (intersectPt, line.startPt);
				Line newLine2 = new Line (intersectPt, line.endPt);
				
				//call insertNode method on newly split 2 lines 
				insertNode(newLine1);
				insertNode(newLine2);
			
		}  	 
	}
	
	//determine intersection point of two lines
	public Point intersectionPt (Line line1, Line line2) {
		float x1 = (float) line1.startPt.coordinate1;
		float x2 = (float) line1.endPt.coordinate1;
		float y1 = (float) line1.startPt.coordinate2;
		float y2 = (float) line1.endPt.coordinate2;
		
		float x3 = (float) line2.startPt.coordinate1;
		float x4 = (float) line2.endPt.coordinate1;
		float y3 = (float) line2.startPt.coordinate2;
		float y4 = (float) line2.endPt.coordinate2;

		//intersection point formula 
		float coord1Num = (float) ((((x1*y2)-(y1*x2))*(x3-x4))- (x1-x2)*((x3*y4)-(y3*x4)));
		float deno = (float) (((x1-x2)*(y3-y4))- ((y1-y2)*(x3-x4)));
		float coord2Num = (float) ((((x1*y2)-(y1*x2))*(y3-y4))- (y1-y2)*((x3*y4)-(y3*x4)));

		double coord1 = (double) (coord1Num/deno); 
		double coord2 = (double) (coord2Num/deno);
		
		Point intersectionPt = new Point (coord1, coord2);
		return intersectionPt;
	}
	
	//prints the whole tree in order
	public void printInOrder() {
		if (this.leftChild != null) {
			leftChild.printInOrder();
			}
		System.out.println(line.startPt.coordinate1 + " " + line.startPt.coordinate2 + " " + line.endPt.coordinate1 + " "
						   + line.endPt.coordinate2);
		if (this.rightChild != null) {
			rightChild.printInOrder();
			}
	}
	
	//used for comparing where two points are located in respect to each other
	public String lookup(Point firstPt, Point secPt) {
		int point1Location = ccw(this.line.startPt, this.line.endPt, firstPt  );
		int point2Location = ccw(this.line.startPt, this.line.endPt, secPt);
		
		//points aren't on the same side 
		if (point1Location != point2Location){
			System.out.println("Line that separates: " + this.line.toString());
			//this.line is the line that separates
		}
		
		//points are on the same side of line
		else if (point1Location>0) {
			if (rightChild != null) {
				rightChild.lookup(firstPt, secPt);
				
			}
			else {
				System.out.println("The points are in the same region");
			}
		}
		else if (point1Location<0) {
			if (leftChild != null) {
				leftChild.lookup(firstPt, secPt);
			}
			else {
				System.out.println("The points are in the same region");
			}
		}
		return null;
	}
	
	//counts the number of external nodes in tree
	public int countExternalNodes(){
		int exNodes = 0;
		
		if (this.leftChild != null ){
			exNodes += leftChild.countExternalNodes();
		}
		else {
			exNodes++;
		}
			
		if (this.rightChild != null) {
			exNodes += rightChild.countExternalNodes();
		}
		
		else {
			exNodes++;
		}
		
		return exNodes;
	}

	
	public int extPathLengthCalc(int depth) {
		int pathLength =0;
		if (this.leftChild!= null) {
			pathLength += leftChild.extPathLengthCalc(depth+1);
			pathLength = pathLength + leftChild.countExternalNodes(); 
		}
		else {
			pathLength++; 
		}
		if (this.rightChild != null) {
			pathLength += rightChild.extPathLengthCalc(depth+1);
			pathLength = pathLength + rightChild.countExternalNodes(); 
		}
		else {
			pathLength++;
		}		
	return pathLength;
	}
	
	public void avgPathLengthCalc(int pathLength, int exNodes) {
		int avgPathLength  = pathLength/exNodes;
		System.out.println(avgPathLength);
	}
	
	//based on handout int ccw method 
	public int ccw(Point p0, Point p1, Point p2) {
		 float dx1 = (float) (p1.coordinate1 - p0.coordinate1);
		 float dy1 = (float) (p1.coordinate2 - p0.coordinate2);
		 float dx2 = (float) (p2.coordinate1 - p0.coordinate1);
		 float dy2 = (float) (p2.coordinate2 - p0.coordinate2);
		 if (dx1*dy2 - dy1*dx2 > .0000001) return COUNTERCLOCKWISE;
		 else if (dx1*dy2 - dy1*dx2 < -0.0000001) return CLOCKWISE;
		 else if ((dx1*dx2 < -.0000001) || (dy1*dy2 < -.0000001)) return CLOCKWISE;
		 else if ((dx1*dx1+dy1*dy1) - (dx2*dx2+dy2*dy2) < -.0000001) return COUNTERCLOCKWISE;
		 else return COLINEAR;
	}
}