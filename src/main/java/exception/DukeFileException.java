package exception;

/**
 * Thrown to notify user that system failed to access
 * the external file.
 */
public class DukeFileException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! Something went wrong when
     * accessing your file."
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + " Something went wrong when accessing your file";
    }
}
