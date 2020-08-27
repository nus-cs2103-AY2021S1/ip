package Duke.Exception;

/**
 * Exception representing invalid input after deadline command.
 */
public class InvalidDeadlineInputException extends DukeException {

    public InvalidDeadlineInputException() {
        super("OOPS!!! Invalid input after deadline command. "
                + "(Example input: deadline return book /by 2020/12/20 0800)");
    }
}
