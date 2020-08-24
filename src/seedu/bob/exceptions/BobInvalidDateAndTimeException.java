package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing invalid date input for DEADLINE and EVENT commands with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidDateAndTimeException extends BobException {

    /**
     * Creates a BobEmptyDateException.
     */
    public BobInvalidDateAndTimeException () {
        super (Messages.INVALIDDATEANDTIME);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobEmptyDateException.
     */
    @Override
    public String toString() {
        return Messages.INVALIDDATEANDTIME;
    }
}
