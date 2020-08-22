package duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {
    @Test
    public void testConcatenate() {
        String[] arr = new String[]{"This", "is", "," ,"a", "test", "case", "."};
        assertEquals(Utils.concatenate(arr, 0, 7), "This is , a test case .");
        assertEquals(Utils.concatenate(arr, 1, 6), "is , a test case");
        assertEquals(Utils.concatenate(arr, 2, 3), ",");
        assertEquals(Utils.concatenate(arr, 1, 1), "");
    }

    @Test
    public void testGetIndexOf() {
        String[] arr = new String[]{"This", "has", "/some", "*special*", "strings", "."};
        assertEquals(Utils.getIndexOf(arr, "This"), 0);
        assertEquals(Utils.getIndexOf(arr, "/some"), 2);
        assertEquals(Utils.getIndexOf(arr, "*special*"), 3);
        assertEquals(Utils.getIndexOf(arr, "."), 5);
        assertEquals(Utils.getIndexOf(arr, "does not exist"), Utils.INDEX_NOT_FOUND);
    }

    @Test
    public void testHasInteger() {
        assertTrue(Utils.hasInteger(new String[]{"done", "2"}, 1));
        assertTrue(Utils.hasInteger(new String[]{"done", "2", "extra"}, 1));
        assertTrue(Utils.hasInteger(new String[]{"done", "extra", "2"}, 2));

        assertTrue(!Utils.hasInteger(new String[]{"done", "2.2"}, 1));
        assertTrue(!Utils.hasInteger(new String[]{"done", "2.2"}, 0));
        assertTrue(!Utils.hasInteger(new String[]{"done"}, 1));
    }
}
