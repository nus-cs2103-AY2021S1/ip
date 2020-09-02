/**
 * DukeUnknownArgumentException is thrown when user input not recognised
 */
public class DukeUnknownArgumentException extends DukeException {

    /**
     * Constructor that creates a DukeUnknownArgumentException.
     * @param message the error message of the exception.
     */
    DukeUnknownArgumentException(String message) {
        super(message);
    }
    
    public String toString() {
        return "I can't hear youuuuuuuu";
    }
    
}
