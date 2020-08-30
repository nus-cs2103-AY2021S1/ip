package exceptions;

/**
 * Customized exceptions class
 */
public class DukeException extends Exception {
    protected String message;

    /**
     * Constructor
     * @param message the message to send to users
     */
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
