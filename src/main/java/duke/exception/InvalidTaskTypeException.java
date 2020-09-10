package duke.exception;

/** Exception that occurs when the task type is not valid. **/
public class InvalidTaskTypeException extends StorageException {
    /**
     * Returns String message of this Exception.
     *
     * @return String message of this Exception.
     */
    @Override
    public String getMessage() {
        return "  Oops, invalid task type detected!";
    }
}
