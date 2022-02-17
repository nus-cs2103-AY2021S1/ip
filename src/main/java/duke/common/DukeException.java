package duke.common;

/**
 * Exception class for duke application.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class DukeException extends Exception {
    /**
     * Throws exception for duke application.
     *
     * @param message String details of the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
