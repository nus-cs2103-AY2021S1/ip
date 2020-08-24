public class DukeException extends Exception {
    public DukeException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public DukeException(String msg) {
        super(msg);
    }
}
