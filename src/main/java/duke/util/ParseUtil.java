package duke.util;

public class ParseUtil {

    /**
     * Checks if the input string can be parsed to an integer.
     * @param str string to parse
     * @return
     */
    public static boolean canParseInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
