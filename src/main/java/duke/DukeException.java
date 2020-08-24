package duke;

public class DukeException extends Exception {

    public DukeException(String message) {
        super("Action failed: " + message);
    }
}
