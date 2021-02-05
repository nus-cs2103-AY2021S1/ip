package exception;
/**
 * <h1>DukeException class</h1>
 * Inherits from the java Exception class and it is
 * the parent class for all exceptions in Duke.
 */
public class DukeException extends Exception {

    /**
     * Creates a DukeException object.
     *
     * @param s Error message.
     */
    public DukeException(String s) {
        super(s);
    }
}
