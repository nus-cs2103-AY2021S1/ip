package duke.component;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super("☹ OH NO!!! " + errorMessage);
    }
}