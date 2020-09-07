package duke.dukeexception;

public class DukeMissingCommandNumberException extends DukeException {

    public DukeMissingCommandNumberException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return ":) OOPS!!! Ensure that you include a number in your command";
    }
}
