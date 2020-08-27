/**
 * Represents exceptions specific to Duke.
 */
public class DukeException extends Exception {
    DukeException(String msg) {
        super("☹ ERROR: " + msg);
    }
}
