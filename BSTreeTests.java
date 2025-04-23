package utilities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTreeTests {

	//    private myBSTree<Integer> tree;
	private myBSTree<Integer> tree;

	@BeforeEach
	void setUp() throws Exception {
//    	tree = new myBSTree<>();
		tree = new myBSTree<>();

	}

	@Test
	void testGetRootFull() {
		assertNull(tree.getRoot(), "Root of an empty tree should be null");
	}

	@Test
	void testGetRootEmpty() {
		assertNull(tree.getRoot(), "Root of an empty tree should be null");
	}

	@Test
	void testGetHeight() {
		assertEquals(0, tree.getHeight(), "Height of an empty tree should be 0");
	}

	@Test
	void testSizeEmpty() {
		assertEquals(0, tree.size(), "Size of an empty tree should be 0");
	}

	@Test
	void testIsEmptyPass() {
		assertTrue(tree.isEmpty(), "Empty tree should return true for isEmpty()");
	}

	@Test
	void testIsEmptyFail() {
		tree.add(10);
		assertFalse(tree.isEmpty(), "Non-empty tree should return false for isEmpty()");
	}

	@Test
	void testClearFull() {
		tree.add(10);
		tree.clear();
		assertEquals(0, tree.size(), "Clearing a non-empty tree should make its size 0");
		assertNull(tree.getRoot(), "Clearing a non-empty tree should set root to null");
	}

	@Test
	void testClearEmpty() {
		tree.clear();
		assertEquals(0, tree.size(), "Clearing an empty tree should not change its size");
		assertNull(tree.getRoot(), "Clearing an empty tree should not change its root");
	}

	@Test
	void testContainsPass() {
		tree.add(10);
		assertTrue(tree.contains(10), "Contains method should return true for an element present in the tree");
	}

	@Test
	void testContainsFail() {
		assertFalse(tree.contains(10), "Contains method should return false for an element not present in the tree");
	}

	@Test
	void testSearchInTree() {
		tree.add(10);
		assertNotNull(tree.search(10), "Search method should return a node for an element present in the tree");
	}

	@Test
	void testSearchNotInTree() {
		assertNull(tree.search(10), "Search method should return null for an element not present in the tree");
	}

	@Test
	void testAdd() {
		assertTrue(tree.add(10), "Adding an element to the tree should return true");
		assertEquals(1, tree.size(), "Size of the tree should increase after adding an element");
	}

	@Test
	void testRemoveMin() {
		assertNull(tree.removeMin(), "Removing min from an empty tree should return null");
	}


	@Test
	void testRemoveMax() {
		assertNull(tree.removeMax(), "Removing max from an empty tree should return null");
	}

	@Test
	void testInorderIterator() {

		// Add elements to the tree
		tree.add(50);
		tree.add(30);
		tree.add(70);
		tree.add(20);
		tree.add(40);
		tree.add(60);
		tree.add(80);

		// Get the inorder iterator
		Iterator<Integer> iterator = tree.inorderIterator();


//         Build the expected result
		StringBuilder expectedResult = new StringBuilder();
		expectedResult.append("20 30 40 50 60 70 80 ");

		// Build the actual result
		StringBuilder actualResult = new StringBuilder();
		while (iterator.hasNext()) {
			try {
				actualResult.append(iterator.next()).append(" ");
			} catch (NullPointerException e) {
				System.out.println("Error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}

		// Compare expected and actual results
		assertEquals(expectedResult.toString(), actualResult.toString(),
				"Inorder traversal should be in ascending order");
	}

	@Test
	void testPreorderIterator() {
		// Add elements to the tree
		tree.add(50);
		tree.add(30);
		tree.add(70);
		tree.add(20);
		tree.add(40);
		tree.add(60);
		tree.add(80);

		Iterator<Integer> iterator = tree.preorderIterator();
		StringBuilder result = new StringBuilder();
		while (iterator.hasNext()) {
			result.append(iterator.next()).append(" ");
		}
		assertEquals("50 30 20 40 70 60 80 ", result.toString(), "Preorder traversal should be root-left-right");
	}

	@Test
	void testPostorderIterator() {
		// Add elements to the tree
		tree.add(50);
		tree.add(30);
		tree.add(70);
		tree.add(20);
		tree.add(40);
		tree.add(60);
		tree.add(80);

		Iterator<Integer> iterator = tree.postorderIterator();
		StringBuilder result = new StringBuilder();
		while (iterator.hasNext()) {
			result.append(iterator.next()).append(" ");
		}
		assertEquals("20 40 30 60 80 70 50 ", result.toString(), "Postorder traversal should be left-right-root");
	}

	@Test
	void testAddMultipleElements() {
		assertTrue(tree.add(50), "Adding an element to the tree should return true");
		assertTrue(tree.add(30), "Adding an element to the tree should return true");
		assertTrue(tree.add(70), "Adding an element to the tree should return true");
		assertTrue(tree.add(20), "Adding an element to the tree should return true");
		assertTrue(tree.add(40), "Adding an element to the tree should return true");
		assertTrue(tree.add(60), "Adding an element to the tree should return true");
		assertTrue(tree.add(80), "Adding an element to the tree should return true");
		assertEquals(7, tree.size(), "Size of the tree should increase after adding multiple elements");
	}

	@Test
	void testRemoveElement() {
		assertTrue(tree.add(50), "Adding an element to the tree should return true");
		assertTrue(tree.removeMin().getElement() == 50, "Removing an element from the tree should return the removed node");
		assertTrue(tree.isEmpty(), "Tree should be empty after removing the only element");
	}

	@Test
	void testRemoveMinFull()
	{
		tree.add(40);
		tree.add(50);
		tree.add(10);
		tree.add(20);
		tree.add(80);
		tree.removeMin();
		assertFalse(tree.contains(10));

	}



}
