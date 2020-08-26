package duke.exceptions;

public class DukeNoDescriptionException extends DukeInvalidDescriptionException {

    public DukeNoDescriptionException() {
        super();
    }


    public DukeNoDescriptionException(String errMsg) {
        super(errMsg);
    }

}
