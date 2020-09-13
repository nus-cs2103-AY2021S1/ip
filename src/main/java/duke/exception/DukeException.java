package duke.exception;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage + "\nType 'help' to view list of available commands!");
    }
}
