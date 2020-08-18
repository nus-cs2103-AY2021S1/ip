public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("Sorry this is an unrecognised command");
    }
}
