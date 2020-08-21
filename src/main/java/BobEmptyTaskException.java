/**
 * Exception representing empty task input with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobEmptyTaskException extends Exception {
    /** Error message */
    private static final String ERR =
            "Sorry, I can't guess your task that well. Please enter a description for your task.\n";

    /** Divider */
    private static final String DIVIDER =
            "===========================================================================================\n";

    /**
     * Creates a BobEmptyTaskException.
     */
    public BobEmptyTaskException () {
        super (ERR);
    }

    /**
     * Overriden toString method.
     * @return String value of the BobEmptyTaskException.
     */
    @Override
    public String toString() {
        return DIVIDER + ERR + DIVIDER;

    }
}
