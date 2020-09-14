package duke;

public class OutOfBoundTaskException extends DukeException {
    public OutOfBoundTaskException() {
        super("Sorry there's no task with such index. Please enter a number in range.");
    }
}
