
public class MyBinarySearchTree implements BST {
	private MyTreeNode root;

	
	public MyTreeNode returnNode () {
		return root;
	}
	
	public int externalNodes() {
		return root.countExternalNodes();
	}
	
	public int extPathLength() {
		return root.extPathLengthCalc(1);
	}
	
	public void avgPathLength(){
		root.avgPathLengthCalc(extPathLength(), externalNodes());	
	}
	
	public void insert(Line line) {
			if (root == null) {
				root = new MyTreeNode(line);
		}
			else { 
			root.insertNode(line);
		}
	} 
	
	public void printInOrder() {
		root.printInOrder();
	}

	public void lookup(Point firstPt, Point secPt) {
		root.lookup(firstPt, secPt);
	}
}
