public class DoneException extends DukeException {

    public DoneException() {
        super("OOPS!!! The description of a done cannot be empty.");
    }
}