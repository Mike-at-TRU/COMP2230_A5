import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.digiteched.javadsa.LinkedMinHeap;
import com.digiteched.javadsa.interfaces.IMinHeap;

public class LinkedMinHeapTest {
    @Test
    public void itShouldCreateAnEmptyHeap() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        assertEquals(0, h.size());

        assertTrue(h.isEmpty());
    }

    @Test
    public void itShouldAddAFirstItem() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        Integer elementToAdd = 8;

        h.add(elementToAdd);

        assertEquals(elementToAdd, h.getMin());

        assertEquals(1, h.size());

        assertFalse(h.isEmpty());
    }

    @Test
    public void itShouldAddAnItemSmallerThanRoot() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        h.add(10);

        Integer elementToAdd = 9;

        h.add(elementToAdd);

        assertEquals(elementToAdd, h.getMin());

        assertEquals(2, h.size());

        assertFalse(h.isEmpty());
    }

    @Test
    public void itShouldAddAnItemLargerThanRoot() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        Integer rootElement = 10;

        h.add(rootElement);

        Integer elementToAdd = 11;

        h.add(elementToAdd);

        assertEquals(rootElement, h.getMin());

        assertEquals(2, h.size());

        assertFalse(h.isEmpty());
    }

    @Test
    public void itShouldRemoveTheMinimumFromALargeHeap() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();

        Integer minElement = 1;

        Integer secondMin = 2;

        h.add(5);

        h.add(3);

        h.add(7);

        h.add(secondMin);

        h.add(minElement);

        h.add(6);

        h.add(100);

        Integer result = h.removeMin();

        assertEquals(minElement, result);

        assertEquals(6, h.size());

        assertFalse(h.isEmpty());

        assertEquals(secondMin, h.removeMin());

        assertEquals(Integer.valueOf(3), h.removeMin());
        assertEquals(Integer.valueOf(5), h.removeMin());
        assertEquals(Integer.valueOf(6), h.removeMin());

        assertEquals(Integer.valueOf(7), h.removeMin());
        assertEquals(Integer.valueOf(100), h.removeMin());
    }
}
