package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing empty find description with inherited functionalities from Exception.
 */
public class BobEmptyFindException extends BobException {


    public BobEmptyFindException () {
        super(Messages.EMPTY_FIND);
    }


    @Override
    public String toString() {
        return Messages.EMPTY_FIND;
    }
}
