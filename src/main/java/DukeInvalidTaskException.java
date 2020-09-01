public class DukeInvalidTaskException extends DukeException {

    public DukeInvalidTaskException(String message) {
        super(message);
    }

    public String toString() {
        return getMessage();
    }

}
