/**
 * Exception representing invalid task number for DONE and DELETE commands
 * with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidNumberException extends Exception {
    /** Error message */
    private static final String ERR =
            "Sorry but there is no valid task number. Please enter a valid task number.\n";

    /** Divider */
    private static final String DIVIDER =
            "========================================================================================\n";

    /**
     * Creates a BobInvalidNumberException.
     */
    public BobInvalidNumberException () {
        super (ERR);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobInvalidNumberException.
     */
    @Override
    public String toString() {
        return DIVIDER + ERR + DIVIDER;
    }
}
