package exception;

/**
 * Thrown to notify user that there are missing input
 * of command when the program is running or to notify
 * that there is something wrong with the file system.
 */
public class DukeException extends Exception {


    /**
     * Returns a short description of this throwable.
     * The result is "OOPS!!"
     *
     * @return String exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!";
    }
}
