package seedu.bob.exceptions;

/**
 * Exception representing invalid date input for DEADLINE and EVENT commands with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidDateAndTimeException extends Exception {
    /** Error message */
    private static final String ERR =
            "Sorry, I don't recognize your date and time. Please enter a valid date"
                    + " and time in this format \"YY/MM/DD HHmm\" after \"/by\" or \"/at\".\n"
                    + "For example, \"deadline return book /by 2020/08/23 1930\".\n"
                    + "Or, \"event birthday /at 2020/12/09 0000\".\n";


    /** Divider */
    private static final String DIVIDER = "====================================================================="
            + "=====================================================================\n";

    /**
     * Creates a BobEmptyDateException.
     */
    public BobInvalidDateAndTimeException () {
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
