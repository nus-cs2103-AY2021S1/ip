package DukeException;

/**
 * Error that cause by Mug.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Wrong command given/lack of information";
    }
}
