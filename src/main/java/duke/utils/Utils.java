package duke.utils;

import java.util.Arrays;

public final class Utils {
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

    public static int getIndexOf(String[] arr, String target) {
        return Arrays.asList(arr).indexOf(target);
    }

    public static boolean hasInteger(String[] command, int index) {
        if (command.length <= index) {
            return false;
        }
        return command[index].matches("\\d+");
    }
}
