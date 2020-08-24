public class DukeException extends Exception {
    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super("\u2639 OOPS!!! " + message);
    }
}
