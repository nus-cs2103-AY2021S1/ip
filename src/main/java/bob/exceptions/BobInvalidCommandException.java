package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing an unrecognisable command.
 */
public class BobInvalidCommandException extends BobException {

    public BobInvalidCommandException () {
        super (Messages.INVALID_COMMAND);
    }

    @Override
    public String toString() {
        return Messages.INVALID_COMMAND;
    }
}
