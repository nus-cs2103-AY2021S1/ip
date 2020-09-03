/**
 * Exception that occurs during a Duke exection.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super("\u2639 OOPS!!! I'm sorry, but " + msg + " :-(");
    }
}
