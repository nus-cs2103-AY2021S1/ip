package duke.exception;

public class DeleteException extends DukeException {

    /**
     * Constructs the delete exception for invalid details.
     * @param msg Error message.
     */
    public DeleteException(String msg) {
        super(msg);
    }

}
