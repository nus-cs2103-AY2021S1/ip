package exceptions;

public class DukeUnknownCommandException extends DukeException {

    public DukeUnknownCommandException() {
        super("Oops! I'm sorry Boss! I do not know what that means :-(");
    }
}
