package duke.Exceptions;

public class DukeException extends Exception {

    public DukeException(String error){
        super(error);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
