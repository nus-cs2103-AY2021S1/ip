package utils;

import exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

    public static Date parseDateTimeString(String datetime, SimpleDateFormat sdf) throws DukeException {
        try {
            return sdf.parse(datetime);
        } catch (ParseException parseException) {
            String msg = String.format("Ensure the datetime passed in is of the form: '%s'.",
                    sdf.toPattern());
            throw new DukeException(msg);
        }
    }

    public static boolean hasInteger(String[] command, int index) {
        if (command.length <= index) {
            return false;
        }
        return command[index].matches("\\d+");
    }
}
