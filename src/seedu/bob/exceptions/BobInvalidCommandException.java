package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing an unrecognisable command.
 */
public class BobInvalidCommandException extends BobException {

    public BobInvalidCommandException () {
        super (Messages.INVALIDCOMMAND);
    }

    @Override
    public String toString() {
        return Messages.INVALIDCOMMAND;
    }
}
