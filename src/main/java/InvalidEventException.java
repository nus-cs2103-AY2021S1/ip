public class InvalidEventException extends DukeException {
    protected InvalidEventException() {
        super("Event task is poorly formatted.\n    A proper format would look like this: " +
                "`event name` /at `start time - end time` " +
                "(e.g. meeting /at Sunday 2pm - 4pm");
    }
}
