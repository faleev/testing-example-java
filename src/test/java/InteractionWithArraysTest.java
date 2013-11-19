import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class InteractionWithArraysTest {

    private Double[] array = {2.0, 5.0, 1.0, 3.0, 7.0, 9.0, 4.0, 8.0, 6.0};
    private ArrayList<Double> arrayList = new ArrayList<>(Arrays.asList(array));

    @Test
    public void testFindMaxItemInArray() {
        Double result = new InteractionWithArrays().getMaxValueFromArray(arrayList);
        assertEquals(result, 9.0);
    }

    @Test
    public void testGetMeanValueFromArray() {
        Double result = new InteractionWithArrays().getMeanValueFromArray(arrayList);
        assertEquals(result, 5.0);
    }

    @Test
    public void testCopyOneArrayToAnother() {
        ArrayList<Double> arrayListCopy1 = arrayList;
        ArrayList<Double> arrayListCopy2 = new InteractionWithArrays().copyOneArrayToAnother(arrayList);
        // Two objects has the same reference
        assertTrue(arrayListCopy1 == arrayList);
        // Two objects has different references
        assertFalse(arrayListCopy2 == arrayList);
        // Two objects has the same content
        assertTrue(arrayListCopy2.equals(arrayList));
    }

    @Test
    public void testGetReverseOrderedArray() {
        Double[] arrayReversed = {6.0, 8.0, 4.0, 9.0, 7.0, 3.0, 1.0, 5.0, 2.0};
        ArrayList<Double> arrayListReversed = new ArrayList<>(Arrays.asList(arrayReversed));
        ArrayList<Double> result = new InteractionWithArrays().getReverseOrderedArray(arrayList);
        assertEquals(arrayListReversed, result);
    }
}
