package duke.exception;

/**
 * Exception for handling add commands with no task.
 */
public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException(String input) {
        super(input);
    }

    /**
     * Returns the error message containing the user input which caused the error. 
     *
     * @return String of error message.
     */
    @Override
    public String toString() {
        return "ERROR: Duke can't find your task details -> " + input;
    }
}
