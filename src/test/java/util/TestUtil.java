package src.test.java.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Utility methods for testing.
 */
public class TestUtil {
    /**
     * Returns true if every pair of corresponding elements two iterables are (deeply) identical.
     * In other words, the two containers must have the same elements, in the same order.
     */
    public static <T> boolean isIdentical(Iterable<T> firstIterable, Iterable<T> secondIterable) {
        Iterator<T> currentPtr0 = firstIterable.iterator();
        Iterator<T> currentPtr1 = secondIterable.iterator();

        while (currentPtr0.hasNext() && currentPtr1.hasNext()) {
            T val0 = currentPtr0.next();
            T val1 = currentPtr1.next();
            if (!val0.equals(val1)) {
                return false;
            }
        }

        // If any of the two iterables still have elements, then they have different sizes.
        return !(currentPtr0.hasNext() || currentPtr1.hasNext());
    }

    /**
     * Returns true if the underlying container behind an iterable is empty.
     */
    public static <T> boolean isEmpty(Iterable<T> it) {
        return !it.iterator().hasNext();
    }

    /**
     * Returns the number of elements in the container behind an iterable.
     */
    public static <T> int getSize(Iterable<T> it) {
        int numberOfElementsSeen = 0;

        for (T elem : it) {
            numberOfElementsSeen++;
        }

        return numberOfElementsSeen;
    }

    /**
     * Asserts whether the text in the two given files are the same. Ignores any
     * differences in line endings
     */
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }

    /**
     * Asserts that the file given does not exist on the filesystem.
     */
    public static void assertFileDoesNotExist(String filePath) {
        assertTrue(Files.notExists(Paths.get(filePath)));
    }
}
