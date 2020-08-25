public class DukeException extends Exception {

    public DukeException(String msg) {
        super("☹ OOPS!!! I'm sorry, but " + msg + " :-(");
    }
}
