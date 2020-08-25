package duke;

/**
 * Encapsulates the error handling and printing of messages
 */
public class DukeException extends Exception {

    /**
     * Constructor for Custom Exception
     * @param errorMessage error message to be printed when exception is thrown
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
