package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing an unrecognisable command with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidCommandException extends BobException {
    /** Error message */

//    /** Divider */
//    private static final String DIVIDER =
//            "================================================================================================\n";

    /**
     * Creates a BobInvalidCommandException.
     */
    public BobInvalidCommandException () {
        super (Messages.INVALIDCOMMAND);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobInvalidCommandException.
     */
    @Override
    public String toString() {
        return Messages.INVALIDCOMMAND;
    }
}
