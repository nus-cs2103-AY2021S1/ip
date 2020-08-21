package duke.exception;

public class DukeFileNotFoundException extends DukeException {

    @Override
    public String toString() {
        return "ERROR: File to load cannot be found";
    }

}
