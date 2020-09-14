package duke;

/**
 * Used to represent common exceptions or erroneous situations encountered by duke.Ui.Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
