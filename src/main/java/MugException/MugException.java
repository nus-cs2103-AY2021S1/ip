package MugException;

/**
 * Error that cause by Mug.
 */
public class MugException extends Exception {

    public MugException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Wrong command given/lack of information";
    }
}
