public class DukeException extends Exception {
    DukeException(String msg) {
        super("☹ OOPS!!! " + msg);
    }
}