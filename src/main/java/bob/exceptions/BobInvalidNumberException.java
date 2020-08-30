package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing invalid task number for DONE and DELETE commands.
 */
public class BobInvalidNumberException extends BobException {

    public BobInvalidNumberException () {
        super (Messages.INVALID_NUMBER);
    }

    @Override
    public String toString() {
        return Messages.INVALID_NUMBER;
    }
}
