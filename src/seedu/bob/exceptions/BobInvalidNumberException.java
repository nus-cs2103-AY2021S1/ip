package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing invalid task number for DONE and DELETE commands
 * with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobInvalidNumberException extends BobException {

    /**
     * Creates a BobInvalidNumberException.
     */
    public BobInvalidNumberException () {
        super (Messages.INVALIDNUMBER);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobInvalidNumberException.
     */
    @Override
    public String toString() {
        return Messages.INVALIDNUMBER;
    }
}
