package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing empty task input.
 */
public class BobEmptyTaskException extends BobException {

    public BobEmptyTaskException () {
        super (Messages.EMPTYTASK);
    }

    @Override
    public String toString() {
        return Messages.EMPTYTASK;

    }
}
