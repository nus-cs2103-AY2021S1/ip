package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing empty find description with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobEmptyFindException extends BobException {

    /**
     * Creates a BobEmptyTaskException.
     */
    public BobEmptyFindException () {
        super(Messages.EMPTYFIND);
    }

    /**
     * Overridden toString method.
     * @return String value of the BobEmptyFindException.
     */
    @Override
    public String toString() {
        return Messages.EMPTYFIND;
    }
}
