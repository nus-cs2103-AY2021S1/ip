public class InvalidDeadlineFormatException extends DukeException {
    public InvalidDeadlineFormatException() {
        super("☹ OOPS!!! The format of deadline command seems to be wrong.\n"
        + "Try the following format: deadline task /by YYYY-MM-DD HH:MM");
    }
}
