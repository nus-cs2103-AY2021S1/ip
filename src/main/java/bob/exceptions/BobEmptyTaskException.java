package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing empty task input.
 */
public class BobEmptyTaskException extends BobException {

    public BobEmptyTaskException () {
        super (Messages.EMPTY_TASK);
    }

    @Override
    public String toString() {
        return Messages.EMPTY_TASK;

    }
}
