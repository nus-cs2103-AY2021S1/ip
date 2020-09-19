package exception;

/**
 * Thrown to notify user that system failed to find
 * the external file.
 */
public class DukeFileNotFoundException extends DukeException {

    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!! I cannot find your file.
     * Sorry! :("
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return super.toString() + " I cannot find your file. Sorry! :(";
    }
}
