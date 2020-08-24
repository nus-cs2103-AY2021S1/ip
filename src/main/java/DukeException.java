public class DukeException extends Exception {
    String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "    " + message;
    }

    public String toString() {
        return this.message;
    }
}
