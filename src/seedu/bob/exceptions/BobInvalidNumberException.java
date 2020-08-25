package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing invalid task number for DONE and DELETE commands.
 */
public class BobInvalidNumberException extends BobException {

    public BobInvalidNumberException () {
        super (Messages.INVALIDNUMBER);
    }

    @Override
    public String toString() {
        return Messages.INVALIDNUMBER;
    }
}
