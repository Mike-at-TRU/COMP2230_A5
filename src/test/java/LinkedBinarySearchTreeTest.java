import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.digiteched.javadsa.LinkedBinarySearchTree;
import com.digiteched.javadsa.TreeIterationStrategy;

public class LinkedBinarySearchTreeTest {
    @Test
    public void itShouldBuildAnEmptyTree() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<Integer>();

        assertEquals(0, tree.size());

        assertEquals(true, tree.isEmpty());
    }

    @Test
    public void itShouldAddARoot() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<Integer>();

        Integer rootValue = 18;

        tree.add(rootValue);

        assertEquals(1, tree.size());

        assertEquals(false, tree.isEmpty());

        assertEquals(rootValue, tree.getRoot());
    }

    @Test
    public void itShouldAddAValueSmallerThanRoot() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<Integer>();

        Integer rootValue = 18;

        tree.add(rootValue);

        Integer targetValue = 10;

        tree.add(targetValue);

        assertEquals(2, tree.size());

        assertEquals(false, tree.isEmpty());

        assertEquals(rootValue, tree.getRoot());

        assertEquals(tree.contains(targetValue), true);

        Integer foundMinValue = tree.removeMin();

        assertEquals(foundMinValue, targetValue);
    }

    @Test
    public void itShouldAddAValueLargerThanRoot() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<Integer>();

        Integer rootValue = 18;

        tree.add(rootValue);

        Integer targetValue = 22;

        tree.add(targetValue);

        assertEquals(2, tree.size());

        assertEquals(false, tree.isEmpty());

        assertEquals(rootValue, tree.getRoot());

        assertEquals(tree.contains(targetValue), true);

        Integer foundMaxValue = tree.removeMax();

        assertEquals(foundMaxValue, targetValue);
    }

    @Test
    public void itShouldRemoveADeepMinimum() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        Integer minValue = 2;

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(minValue);

        tree.add(95);

        // remove 2
        Integer foundMin = tree.removeMin();

        assertEquals(minValue, foundMin);

        assertEquals(tree.size(), 7);

        assertEquals(tree.isEmpty(), false);

        // remove 5
        foundMin = tree.removeMin();

        assertEquals(Integer.valueOf(5), foundMin);

        assertEquals(tree.size(), 6);

        assertEquals(tree.isEmpty(), false);

    }

    @Test
    public void itShouldRemoveADeepMaximum() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(2);

        tree.add(95);

        // remove 2
        Integer foundMax = tree.removeMax();

        assertEquals(Integer.valueOf(95), foundMax);

        assertEquals(tree.size(), 7);

        assertEquals(tree.isEmpty(), false);

        // remove 5
        foundMax = tree.removeMax();

        assertEquals(Integer.valueOf(80), foundMax);

        assertEquals(tree.size(), 6);

        assertEquals(tree.isEmpty(), false);

    }

    @Test
    public void itShouldIterateInOrder() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(2);

        tree.add(95);

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertEquals(Integer.valueOf(95), iterator.next());

        // the iterator should not modify the tree
        assertEquals(8, tree.size());
    }

    @Test
    public void itShouldIteratePreOrder() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(2);

        tree.add(95);

        tree.setIterationStrategy(TreeIterationStrategy.PRE_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        /**
         * TODO let's make this example more balanced
         * The tree should be
         * 10
         * / \
         * 5 15
         * / \ / \
         * 2 20
         * \
         * 30
         * \
         * 80
         * \
         * 95
         */
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertEquals(Integer.valueOf(95), iterator.next());

        // the iterator should not modify the tree
        assertEquals(8, tree.size());
    }

    @Test
    public void itShouldIteratePostOrder() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(2);

        tree.add(95);

        tree.setIterationStrategy(TreeIterationStrategy.POST_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        /**
         * TODO let's make this example more balanced
         * The tree should be
         * 10
         * / \
         * 5 15
         * / \ / \
         * 2 20
         * \
         * 30
         * \
         * 80
         * \
         * 95
         */
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(95), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());

        // the iterator should not modify the tree
        assertEquals(8, tree.size());
    }

    @Test
    public void itShouldIterateLevelOrder() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(30);

        tree.add(80);

        tree.add(2);

        tree.add(95);

        tree.setIterationStrategy(TreeIterationStrategy.LEVEL_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        /**
         * TODO let's make this example more balanced
         * The tree should be
         * 10
         * / \
         * 5 15
         * / \ / \
         * 2 20
         * \
         * 30
         * \
         * 80
         * \
         * 95
         */
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(15), iterator.next());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(Integer.valueOf(30), iterator.next());
        assertEquals(Integer.valueOf(80), iterator.next());
        assertEquals(Integer.valueOf(95), iterator.next());

        // the iterator should not modify the tree
        assertEquals(8, tree.size());
    }

    @Test
    public void itShouldRemoveAnInteriorNodeWithNoChildren() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(2);

        /**
         * The tree should be
         * 10
         * / \
         * 5 15 <-- target
         * / \
         * 2
         * 
         */

        tree.remove(Integer.valueOf(15));

        assertEquals(3, tree.size());

        tree.setIterationStrategy(TreeIterationStrategy.IN_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void itShouldRemoveAnInteriorNodeWithOneChild() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(2);

        tree.add(13);

        /**
         * The tree should be
         * 10
         * / \
         * 5 15 <-- target
         * / \ /
         * 2 13
         * 
         */

        tree.remove(Integer.valueOf(15));

        assertEquals(4, tree.size());

        tree.setIterationStrategy(TreeIterationStrategy.IN_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(13), iterator.next());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void itShouldRemoveAnInteriorNodeWithTwoChildren() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(2);

        tree.add(13);

        /**
         * The tree should be
         * 10
         * / \
         * 5 15 <-- target
         * / \ / \
         * 2 13 20
         * 
         */

        tree.remove(Integer.valueOf(15));

        assertEquals(5, tree.size());

        tree.setIterationStrategy(TreeIterationStrategy.IN_ORDER);

        Iterator<Integer> iterator = tree.iterator();

        assertEquals(Integer.valueOf(2), iterator.next());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertEquals(Integer.valueOf(13), iterator.next());
        assertEquals(Integer.valueOf(20), iterator.next());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void equalsShouldReturnTrueForAClone() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(2);

        tree.add(13);

        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();

        // note these are added in the same order
        tree2.add(10);

        tree2.add(5);

        tree2.add(15);

        tree2.add(20);

        tree2.add(2);

        tree2.add(13);

        assertTrue(tree2.equals(tree));
    }

    @Test
    public void equalsShouldReturnTrueForEquivalentTrees() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(2);

        tree.add(13);

        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();

        // note the different order
        tree2.add(5);

        tree2.add(20);

        tree2.add(2);

        tree2.add(15);

        tree2.add(13);

        tree2.add(10);

        assertTrue(tree2.equals(tree));
    }

    @Test
    public void equalsShouldReturnFalseForDistinctTreesWithDifferentSize() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(2);

        tree.add(13);

        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();

        tree2.add(5);

        tree2.add(20);

        tree2.add(2);

        tree2.add(15);

        // missing
        // tree2.add(13);

        tree2.add(10);

        assertFalse(tree2.equals(tree));
    }

    @Test
    public void equalsShouldReturnFalseForDistinctTreesWithSameSize() {
        LinkedBinarySearchTree<Integer> tree = new LinkedBinarySearchTree<>();

        tree.add(10);

        tree.add(5);

        tree.add(15);

        tree.add(20);

        tree.add(2);

        tree.add(13);

        LinkedBinarySearchTree<Integer> tree2 = new LinkedBinarySearchTree<>();

        tree2.add(5);

        tree2.add(20);

        tree2.add(2);

        tree2.add(15);

        // different
        tree2.add(130);

        tree2.add(10);

        assertFalse(tree2.equals(tree));
    }
}
