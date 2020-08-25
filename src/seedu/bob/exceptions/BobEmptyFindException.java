package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing empty find description with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobEmptyFindException extends BobException {


    public BobEmptyFindException () {
        super(Messages.EMPTYFIND);
    }


    @Override
    public String toString() {
        return Messages.EMPTYFIND;
    }
}
