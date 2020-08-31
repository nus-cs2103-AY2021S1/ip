package duke.exception;

/**
 * Represents Exceptions that can occur from the {@link duke} package.
 */
public class DukeException extends Exception {
    private String uiMessage;

    /**
     * Initialises a new DukeException.
     * @param errorMessage A string that describes the error, to be displayed to the user.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.uiMessage = errorMessage;
    }

    /**
     * Gets the error message to be displayed to the user.
     * @return A string that describes the error, to be displayed to the user.
     */
    public String getUiMessage() {
        return this.uiMessage;
    }
}
