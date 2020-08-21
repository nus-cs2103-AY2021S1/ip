public class InvalidDeadlineException extends DukeException {
    protected InvalidDeadlineException() {
        super("Deadline task is poorly formatted.\n    A proper format would look like this: " +
                "deadline `task name` /by `end time` " +
                "(e.g. deadline Exercise /by Sunday");
    }
}
