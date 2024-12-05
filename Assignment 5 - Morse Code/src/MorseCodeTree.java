import java.util.ArrayList;

/**
 * This class is used for the conversion of morse code to english
 * 
 * It relies on a root (reference to root of the tree)
 * 
 * The root is set to null when the tree is empty.
 * 
 * The class uses an external generic TreeNode class which consists of a
 * reference to the data and a reference to the left and right child. The
 * TreeNode is parameterized as a String, TreeNode This class uses a private
 * member root (reference to a TreeNode) The constructor will call the buildTree
 * 
 * @author Sokha Heng 
 * Project 5: Morse Code
 * Due date: 12/04/2024
 */
public class MorseCodeTree extends Object implements LinkedConverterTreeInterface<String>{

	private TreeNode<String> root;
	
	
	// constructor - calls the buildTree method
	public MorseCodeTree() {
		root = new TreeNode<>("");
		buildTree();
	}
	
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}

	@Override
	public void insert(String code, String letter) {
		if (code == null || code.isEmpty()) {
			return;
		}
		
		if (root == null) {
			root = new TreeNode<>(letter);
		}
		addNode(root, code, letter);
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() == 1) {
			if (code.equals("-")) {
				root.rightChild = new TreeNode<>(letter);
			} else if (code.equals(".")) {
				root.leftChild = new TreeNode<>(letter);
			}
			
			return;
		}
		
		char c = code.charAt(0);
		StringBuilder nextCodeBuilder = new StringBuilder();
		for(int i = 1; i < code.length(); i++) {
			nextCodeBuilder.append(code.charAt(i)); 
		}
		String nextCode = nextCodeBuilder.toString();
		
//		String nextCode = code.substring(1);
		
		if (c == '-') {
			if(root.rightChild == null) {
				root.rightChild = new TreeNode<>("");
			}
			addNode(root.rightChild, nextCode, letter);
		
		} else if (c == '.') {
			if(root.leftChild == null) {
				root.leftChild = new TreeNode<>("");
			}
			addNode(root.leftChild, nextCode, letter);
			
		}
	}
	
	@Override
	public String fetch(String code) {
		if (root == null) {
			return null;
		}
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (root == null) {
			return null;
		}
		
		if (code.isEmpty()) {
			return root.data;
		}
		
		char c = code.charAt(0);
		StringBuilder nextCodeBuilder = new StringBuilder();
		for(int i = 1; i < code.length(); i++) {
			nextCodeBuilder.append(code.charAt(i)); 
		}
		String nextCode = nextCodeBuilder.toString();
		
//		String nextCode = code.substring(1);
		
		if (c == '-') {
			return fetchNode(root.rightChild, nextCode);
		} else if (c == '.') {
			return fetchNode(root.leftChild, nextCode);
		}
		
		return null;
		
	}

	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported for a LinkedConverterTree");
	}

	@Override
	public MorseCodeTree update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("This operation is not supported for a LinkedConverterTree");
	}

	@Override
	public void buildTree() {

		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> inOrderList = new ArrayList<>();
		LNRoutputTraversal(root, inOrderList);
		
		return inOrderList;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root != null) {
			LNRoutputTraversal(root.leftChild, list);
			if (root.data != null) {
				list.add(root.data);
			}
			LNRoutputTraversal(root.rightChild, list);
		}
	}

}
