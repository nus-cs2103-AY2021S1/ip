package exception;

/**
 * Thrown to notify user that they forgot to include
 * task description in their command.
 */
public class DescriptionException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! Description cannot be empty".
     *
     * @return String exception message
     */
    @Override
    public String toString() {
        return super.toString() + " Description cannot be empty";
    }
}
