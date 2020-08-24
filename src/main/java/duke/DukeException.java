package duke;

public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super("OOPS!!! " + errorMsg);
    }
}