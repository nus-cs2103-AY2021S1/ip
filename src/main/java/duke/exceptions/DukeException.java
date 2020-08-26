package duke.exceptions;

public class DukeException extends RuntimeException {

    public DukeException() {
        super();
    }


    public DukeException(String errMsg) {
        super(errMsg);
    }

}
