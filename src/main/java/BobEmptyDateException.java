/**
 * Exception representing empty date input for DEADLINE and EVENT commands with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobEmptyDateException extends Exception {
    /** Error message */
    private static final String ERR =
            "Sorry, I can't guess your date that well. Please enter a date.\n";

    /** Divider */
    private static final String DIVIDER = "=====================================================================\n";

    /**
     * Creates a BobEmptyDateException.
     */
    public BobEmptyDateException () {
        super (ERR);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobEmptyDateException.
     */
    @Override
    public String toString() {
        return DIVIDER + ERR + DIVIDER;
    }
}
