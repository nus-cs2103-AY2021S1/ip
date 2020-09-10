package duke.exceptions;

import static duke.utils.Messages.MESSAGE_NO_SUCH_TENTATIVE_DATE_EXCEPTION;

/**
 * Thrown to indicate that the tentative date specified by the user does not exist.
 */
public class NoSuchTentativeDateException extends DukeException {

    /**
     * Constructs a NoSuchTentativeDateException with the relevant error message.
     */
    public NoSuchTentativeDateException() {
        super(MESSAGE_NO_SUCH_TENTATIVE_DATE_EXCEPTION);
    }

}
