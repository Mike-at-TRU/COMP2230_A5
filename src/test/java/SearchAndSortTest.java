import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.digiteched.javadsa.SearchAndSort;

public class SearchAndSortTest {
    @Test
    public void heapSortShouldWork(){
        Integer[] numbers = {9,6,8,12,3,1,7};

        SearchAndSort.heapSort(numbers);

        Integer[] expectedResult = {1,3,6,7,8,9,12};

        // note that `heapSort` sorts in place
        assertArrayEquals(expectedResult, numbers);

    }
}
