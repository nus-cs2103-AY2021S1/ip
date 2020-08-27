package duke.utils;

import java.util.Arrays;

/**
 * The utilities class for Duke.
 */
public final class Utils {
    /**
     * Concatenates the <code>String</code> elements of the array with specified indexes.
     * A whitespace is inserted in between all elements of the array.
     * @param arr the array of <code>String</code> that is to be concatenated.
     * @param start the starting index of the desired <code>String</code>.
     * @param end the last index the desired <code>String</code> is to end with.
     * @return the concatenated <code>String</code>.
     */
    public static String concatenate(String[] arr, int start, int end) {
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for (int i = start; i < end; i++) {
            builder.append(prefix);
            builder.append(arr[i]);
            prefix = " ";
        }
        return builder.toString();
    }

    public static final int INDEX_NOT_FOUND = -1;

    /**
     * Retrieves the index of a specified <code>String</code> within the array.
     * The method returns -1 if the specified <code>String</code> cannot be found.
     * @param arr the array that is to be searched on.
     * @param target the <code>String</code> that is to be searched for.
     * @return the index position of the target <code>String</code> in the array.
     */
    public static int getIndexOf(String[] arr, String target) {
        return Arrays.asList(arr).indexOf(target);
    }

    /**
     * Checks if the given <code>String</code> element of the array can be
     * parsed into an <code>Integer</code>.
     * @param command the array that contains the <code>String</code> that can be
     *                parsed into an <code>Integer</code>.
     * @param index the index of the element that is to be checked.
     * @return <code>true</code> if the specified element can be parsed into an <code>Integer</code>.
     */
    public static boolean hasInteger(String[] command, int index) {
        if (command.length - 1 < index) {
            return false;
        }
        return command[index].matches("\\d+");
    }
}
