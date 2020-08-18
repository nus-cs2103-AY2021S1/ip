public class InvalidCommandException extends DukeException {

    InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
