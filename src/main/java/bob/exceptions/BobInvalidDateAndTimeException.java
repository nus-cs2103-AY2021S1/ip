package bob.exceptions;

import bob.common.Messages;

/**
 * Exception representing invalid date input for DEADLINE and EVENT commands.
 */
public class BobInvalidDateAndTimeException extends BobException {

    public BobInvalidDateAndTimeException () {
        super (Messages.INVALID_DATE_AND_TIME);
    }

    @Override
    public String toString() {
        return Messages.INVALID_DATE_AND_TIME;
    }
}
