package duke;

import java.util.stream.IntStream;

/**
 * Defines common methods used by Duke Program.
 */
public class CommonMethod {

    /**
     * Returns a <code>String</code> object after merging the Strings in the given <code>String</code> array.
     * The Strings to be merged are denoted by the Integer index <code>start</code>
     * until (and not including) Integer index <code>end</code>.
     *
     * @param array String array containing Strings to be merged.
     * @param start The starting index to be merged.
     * @param end   The value after the last index to be merged.
     * @return String output.
     */
    public static String mergeArray(String[] array, int start, int end) {
        assert array != null : "mergeArray - Array is null";
        assert end <= array.length : "mergeArray - end index is out of bounds";
        assert start >= 0 && start < array.length : "mergeArray - start index out of bounds";
        StringBuilder output = new StringBuilder();
        IntStream.range(start, end).forEach(i -> output.append(array[i]).append(" "));
        return output.toString().trim();
    }

    /**
     * Returns a <code>Boolean</code> denoting if the given <code>year</code> is a leap year.
     *
     * @param year Integer denoting the year.
     * @return boolean isLeap.
     */
    public static boolean isLeapYear(int year) {
        assert year >= 0 : "isLeapYear - year cannot be negative!";
        boolean isLeap = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                isLeap = year % 400 == 0;
            } else {
                isLeap = true;
            }
        }
        return isLeap;
    }
}
