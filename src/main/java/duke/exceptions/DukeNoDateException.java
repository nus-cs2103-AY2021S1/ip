package duke.exceptions;


public class DukeNoDateException extends DukeInvalidDescriptionException {

    public DukeNoDateException() {
        super();
    }


    public DukeNoDateException(String errMsg) {
        super(errMsg);
    }

}
