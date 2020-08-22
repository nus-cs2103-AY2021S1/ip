package duke.exception;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super("Apologies. " + msg);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
