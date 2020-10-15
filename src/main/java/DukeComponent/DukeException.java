package DukeComponent;

/**
 * A collection of exceptions due to invalid commands passed to the Duke program.
 */
public class DukeException extends Exception {
    public static final String EMPTY = "-1";
    public static final String IGNORE = "0";
    public static final String EMPTY_TODO = "1";
    public static final String WRONG_DEADLINE = "2";
    public static final String WRONG_EVENT = "3";
    public static final String WRONG_DONE_OR_DELETE = "4";
    public static final String WRONG_FIND = "5";

    public DukeException(String message) {
        super(message);
    }
}
