package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing an invalid undo attempt with inherited functionalities from Exception.
 */
public class BobInvalidUndoException extends BobException {

    public BobInvalidUndoException() {
        super(Messages.INVALID_UNDO_MSG);
    }

    @Override
    public String toString() {
        return Messages.INVALID_UNDO_MSG;
    }
}
