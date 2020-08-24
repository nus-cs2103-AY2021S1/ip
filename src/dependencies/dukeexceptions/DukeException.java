package dependencies.dukeexceptions;

/**
 * <p>The class DukeException indicates runtime conditions or situations that needs to be caught.
 * DukeExceptions and its subclasses are checked exceptions.</p>
 *
 * <p>Checked exceptions need to be declared in a method or constructor's throws clause if they can
 * be thrown by the execution of the method or constructor and propagate outside the method or
 * constructor boundary.</p>
 *
 * <p>How the program should respond depends on how the implementation and the characteristic of the main.java.Duke.
 * Given that the program should continue running and accepting user input, the program is recommended to
 * respond with a error message to try to get the user to pass in a valid input that would not throw
 * DukeExceptions</p>
 *
 */
public class DukeException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public DukeException(String message) {
        super(message);
    }
}
