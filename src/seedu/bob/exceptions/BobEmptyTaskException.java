package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing empty task input with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobEmptyTaskException extends BobException {

    /**
     * Creates a BobEmptyTaskException.
     */
    public BobEmptyTaskException () {
        super (Messages.EMPTYTASK);
    }

    /**
     * Overriden toString method.
     * @return String value of the BobEmptyTaskException.
     */
    @Override
    public String toString() {
        return Messages.EMPTYTASK;

    }
}
