package duke.exception;

public class InvalidDescriptionException extends DukeException {
    /**
     * Exception class that handles the case where no description is provided to the task
     *
     * @param message
     */
    public InvalidDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Description is invalid. Please specify with more details.";
    }
}