package exception;

public class DukeInvalidIndexException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " You have to specify the correct index";
    }
}
