package util;

/**
 * Exception storage used by Duke app during runtime to capture invalid inputs from users.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
