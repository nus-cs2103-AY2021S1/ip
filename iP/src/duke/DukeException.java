package duke;

/**
 * Exception related to Duke application
 */
public class DukeException extends Exception {
    /**
     * constructor of DukeException
     * @param errorMessage error message
     */
    public DukeException(String errorMessage){
        super(errorMessage);
    }
}
