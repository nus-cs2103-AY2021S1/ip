package duke.exception;

public class FileError extends Exception {
    private String errorMessage;

    /**
     * Creates a FileError object with appripriate error message to be displayed.
     *
     * @param errorMessage Error message to be displayed to users.
     */
    public FileError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Displays FileError as the customised error message.
     *
     * @return Error Message.
     */
    @Override
    public String toString() {
        return this.errorMessage;
    }
}
