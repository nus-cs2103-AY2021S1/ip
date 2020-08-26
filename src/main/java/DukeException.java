public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public String toString() {
        return getMessage();
    }

}

