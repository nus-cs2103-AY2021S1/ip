package duke;

public class DukeException extends Exception {
    DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
