import java.util.InputMismatchException;
import java.util.Scanner;

public class PointLocator {
	public static void main (String []args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of lines");
		int numOfLines = in.nextInt();
		MyBinarySearchTree tree = new MyBinarySearchTree();
		
		//takes in the coordinates and creates the appropriate number of lines
		System.out.println("Input coordinates of lines in order of x1, y1, x2, y2");	
		for (int i = 0; i< numOfLines; i++ ){
				double x1 = in.nextDouble();
				double y1 = in.nextDouble();
				double x2 = in.nextDouble();
				double y2 = in.nextDouble();
				Line inputLine = new Line(x1, y1, x2, y2);
				tree.insert(inputLine);
			}
		System.out.println("");
		System.out.println("Binary search tree in order: ");
		tree.printInOrder();
		System.out.println("Number of external nodes: ");
		int numOfExNodes = tree.externalNodes();
		System.out.println(numOfExNodes);
		System.out.println("Average path length: ");
		tree.avgPathLength();
		
		//takes in 2 comparison points 
		System.out.println("");
		System.out.println("Input 2 point coordinates for comparison in order of x coordinate, y coordinate" );		
		try {
			while (in.hasNext()) {
			double x1 = in.nextDouble();
			double y1 = in.nextDouble();
			Point inputPoint = new Point (x1, y1);
			
			double x2 = in.nextDouble();
			double y2 = in.nextDouble();
			Point nextPoint = new Point (x2, y2);
			tree.lookup(inputPoint, nextPoint);
		}
	}
		catch (InputMismatchException e) {
			}
	

	}
		
	

}


