/**
 * A collection of exceptions due to invalid commands passed to the Duke program.
 */
public class DukeException extends Exception {
    public static String EMPTY = "-1";
    public static String IGNORE = "0";
    public static String EMPTY_TODO = "1";
    public static String WRONG_DEADLINE = "2";
    public static String WRONG_EVENT = "3";

    public DukeException(String message) {
        super(message);
    }
}
