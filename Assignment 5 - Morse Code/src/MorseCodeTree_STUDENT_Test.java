import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is for student to create JUnit 
 * test for MorseCodeTree
 * 
 * @author Sokha Heng
 * Project 5: Morse Code
 * Due date: 12/04/2024
 */
public class MorseCodeTree_STUDENT_Test {
	private MorseCodeTree Tree;

	@Before
	public void setUp() {
		Tree = new MorseCodeTree();
	}

	@Test
	public void testBuildTree() {
		assertEquals("e", Tree.fetch("."));
		assertEquals("t", Tree.fetch("-"));
		assertEquals("i", Tree.fetch(".."));
		assertEquals("a", Tree.fetch(".-"));
		assertEquals("n", Tree.fetch("-."));
		assertEquals("m", Tree.fetch("--"));
	}

	@Test
	public void testInsertAndFetch() {
		Tree.insert("...", "s");
		Tree.insert("-.-.", "c");

		assertEquals("s", Tree.fetch("..."));
		assertEquals("c", Tree.fetch("-.-."));
	}

	@Test
	public void testFetchInvalidCode() {
		assertNull(Tree.fetch("...-."));
	}
	
	@Test
	public void testToArrayList() {
        ArrayList<String> list = Tree.toArrayList();
        assertTrue(list.contains("e"));
        assertTrue(list.contains("t"));
        assertTrue(list.contains("i"));
        assertTrue(list.contains("a"));
	}

}
