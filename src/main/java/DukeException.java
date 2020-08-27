public class DukeException extends Exception {
    DukeException(String msg) {
        super("☹ ERROR: " + msg);
    }
}
