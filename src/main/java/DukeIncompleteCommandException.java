public class DukeIncompleteCommandException extends DukeException {

    public DukeIncompleteCommandException(String message) {
        super(message);
    }

    public String toString() {
        return getMessage();
    }

}
