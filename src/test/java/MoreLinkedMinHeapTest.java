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

}
