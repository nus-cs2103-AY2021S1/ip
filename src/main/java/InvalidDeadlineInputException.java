public class InvalidDeadlineInputException extends DukeException {

    public InvalidDeadlineInputException() {
        super("☹ OOPS!!! Invalid input after deadline command. (Example input: deadline return book /by Sunday)");
    }
}
