public class InvalidDeadlineException extends DukeException {
    protected InvalidDeadlineException() {
        super("Deadline task is poorly formatted.\n    Here is a proper format: " +
                "deadline `task name` /by `end time`" +
                "\n      e.g. deadline Exercise /by 2020-12-01 12:00");
    }
}
