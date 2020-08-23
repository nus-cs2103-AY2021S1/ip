public class InvalidEventException extends DukeException {
    protected InvalidEventException() {
        super("Event task is poorly formatted.\n    Here is a proper format: " +
                "`event name` /at `start time - end time`" +
                "\n      e.g. meeting /at Sunday 2 - 4pm");
    }
}
