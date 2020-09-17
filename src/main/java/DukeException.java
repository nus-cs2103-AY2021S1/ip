/**
 * Encapsulates a DukeException object, contains information about the exception being thrown.
 */
public class DukeException extends Exception {
    
    DukeException(String message) {
        super(message);
    }

    DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
