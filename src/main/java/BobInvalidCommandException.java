/**
 * Exception representing an unrecognisable command with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidCommandException extends Exception {
    /** Error message */
    private static final String ERR =
            "Sorry boss, I am not smart enough to understand that. Please give me a valid instruction.\n";

    /** Divider */
    private static final String DIVIDER =
            "================================================================================================\n";

    /**
     * Creates a BobInvalidCommandException.
     */
    public BobInvalidCommandException () {
        super (ERR);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobInvalidCommandException.
     */
    @Override
    public String toString() {
        return DIVIDER + ERR + DIVIDER;
    }
}
