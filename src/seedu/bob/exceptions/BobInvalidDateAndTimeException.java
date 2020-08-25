package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing invalid date input for DEADLINE and EVENT commands.
 */
public class BobInvalidDateAndTimeException extends BobException {

    public BobInvalidDateAndTimeException () {
        super (Messages.INVALIDDATEANDTIME);
    }

    @Override
    public String toString() {
        return Messages.INVALIDDATEANDTIME;
    }
}
