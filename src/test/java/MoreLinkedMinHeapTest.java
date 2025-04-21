import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.digiteched.javadsa.LinkedMinHeap;
import com.digiteched.javadsa.interfaces.IMinHeap;

public class MoreLinkedMinHeapTest {
    @Test
    public void itShouldAddItemsSmallerThanRoot() {
        IMinHeap<Integer> minHeap = new LinkedMinHeap<Integer>();
        for (int i = 10; i >= 0; i--) {
            minHeap.add(i);
            assertEquals((Integer) i, minHeap.getMin());
        }
    }

    @Test
    public void itShouldAddGreaterThanRoot() {
        IMinHeap<Integer> minHeap = new LinkedMinHeap<Integer>();
        for (int i = 0; i <= 10; i++) {
            minHeap.add(i);
            assertEquals((Integer) 0, minHeap.getMin());
            assertEquals(i, minHeap.size() - 1);
        }
    }

    @Test
    public void heapSortCopy() {
        IMinHeap<Integer> h = new LinkedMinHeap<>();
        h.add(9);
        h.add(6);
        h.add(8);
        h.add(12);
        h.add(7);
        h.add(12);
        h.add(3);
        h.add(1);
        h.add(7); // it seems the last number is never deleted
        // when I print the tree the length doesn't change but it seems either the data
        // is overwriten or it points to the last node added

        assertEquals((Integer) 1, h.removeMin());
        assertEquals((Integer) 3, h.removeMin());
        assertEquals((Integer) 6, h.removeMin());
        assertEquals((Integer) 7, h.removeMin());
        assertEquals((Integer) 8, h.removeMin()); // 7!= 8
        assertEquals((Integer) 9, h.removeMin());
        assertEquals((Integer) 12, h.removeMin());

    }

}
