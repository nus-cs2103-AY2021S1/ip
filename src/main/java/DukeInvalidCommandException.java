public class DukeInvalidCommandException extends DukeException {

    public DukeInvalidCommandException(String message) {
        super(message);
    }

    public String toString() {
        return getMessage();
    }

}

