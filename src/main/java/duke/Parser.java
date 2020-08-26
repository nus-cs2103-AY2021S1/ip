package duke;

/**
 * <h>duke.Parser</h>
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Constructor for duke.Parser.
     */
    public Parser() {
    }

    /**
     * Converts the string to upper case.
     * @param s String String to be converted.
     * @return String Upper-cased string.
     */
    public static String parseCmd(String s) {
        return s.toUpperCase().trim();
    }

    /**
     * Get the last digit of the string.
     * @param str String The string to extract last digit from.
     * @return int The int at the back of the string.
     */
    public static int parseInt(String str) {
        return Integer.parseInt(str.substring(str.length() - 1)) - 1;
    }

    /**
     * This method returns a array of string being split by regex depending on task type.
     * @param arr String[] The inout array.
     * @param i int The type of the regex.
     * @return String[] The split string in array.
     */
    public static String[] parse(String[] arr, int i) {
        String secondStr = arr[1];
        if (i == 1) {
            // For duke.Deadline
            return secondStr.split(" /by ", 2);
        } else {
            // For duke.Event
            return secondStr.split(" /at ", 2);
        }
    }
}
