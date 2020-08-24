package exceptions;

/**
 * Over-arching Exception from which all other Duke-specific exceptions extend from
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
