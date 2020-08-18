public class DukeException extends Exception {
    DukeException(String msg) {
        super("☹ OOPS!!! I'm sorry, but " + msg + " :-(");
    }
}