package exception;

/**
 * Thrown to notify user that the system failed
 * to create the intended external file.
 */
public class DukeCreateFileException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! Failed to create a file".
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + "Failed to create a file";
    }
}
