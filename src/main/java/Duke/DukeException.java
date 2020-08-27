package duke;

/**
 * DukeException is the class for the exceptions thrown during the Duke program.
 *
 * @author Joshua
 */
public class DukeException extends Exception{

    /**
     * Creates the exception specific to Duke.
     *
     * @param message the error message to be printed out.
     */
    public DukeException(String message) {
        super(message);
    }

}
