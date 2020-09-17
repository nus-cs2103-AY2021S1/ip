/**
 * DukeException throws an exception when input is invalid
 */
public class DukeException extends Exception {

    /**
     * Throws an exception when there are invalid arguments
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

}
