public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("Command is empty!");
    }
}
