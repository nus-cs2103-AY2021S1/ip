public class DukeException extends Exception {
    public DukeException(String msg) {
        super("OOPS! " + msg);
    }

}