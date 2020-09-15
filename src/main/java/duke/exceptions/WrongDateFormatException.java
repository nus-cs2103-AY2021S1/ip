package duke.exceptions;

public class WrongDateFormatException extends DukeException {
    public WrongDateFormatException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Creates an exception with te default error message.
     */
    public WrongDateFormatException() {
        this("Task not saved due to wrong format. ꉂ `o´ ) "
                + "Please specify the date in the following format: \n"
                + "yyyy-mm-dd i.e. 2020-01-01");
    }
}
