package duke.exceptions;


public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException() {
        super();
    }


    public DukeInvalidCommandException(String errorMsg) {
        super(errorMsg);
    }

}
