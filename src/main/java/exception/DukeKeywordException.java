package exception;

/**
 * Thrown to notify user when user forget to include
 * keyword when using FindCommand.
 */
public class DukeKeywordException extends DukeException {

    /**
     * Returns a short message of the throwable.
     * The message is"OOPS!! You need to specify the keyword."
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + " You need to specify the keyword.";
    }
}
