package rogue.commons.util;

public class StringUtil {
    public static int findIndex(String[] arr, String searchTerm) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(searchTerm)) {
                return i;
            }
        }

        return -1;
    }

    public static String strArrJoin(String[] arr, int startIndex, int endIndex, String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int i = startIndex; i < endIndex; ++i) {
            builder.append(arr[i]);

            // Do not add delimiter in final iteration
            if (i != endIndex - 1) {
                builder.append(delimiter);
            }
        }

        return builder.toString();
    }
}
